package io.gatling.demo

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._


class _03ChecksScenario extends Simulation{
  val httpProtocol = http
    .baseURL("https://api.github.com")
    .userAgentHeader("enriquezrene")
    .acceptHeader("application/vnd.github.v3+json")

  val checkStatus = scenario("Check multiple response status codes")
    .exec(http("find_enriquezrene_repos")
      .get("/users/enriquezrene/repos")
      .check(status.is(200))
      .check(status.not(404)))

  setUp(
    checkStatus.inject(constantUsersPerSec(1) during(1 seconds))
  ).protocols(httpProtocol)

}
