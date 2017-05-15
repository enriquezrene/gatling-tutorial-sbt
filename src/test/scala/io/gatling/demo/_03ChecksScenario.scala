package io.gatling.demo

import io.gatling.core.Predef._
import io.gatling.demo.config.BasicHttpProtocol
import io.gatling.http.Predef._

import scala.concurrent.duration._


class _03ChecksScenario extends Simulation {
  val httpProtocol = BasicHttpProtocol.GitHubProtocolBuilder

  val checkStatus = scenario("Check multiple response status codes")
    .exec(http("find_enriquezrene_repos")
      .get("/users/enriquezrene/repos")
      .check(status.is(200))
      .check(status.not(404)))

  setUp(
    checkStatus.inject(constantUsersPerSec(1) during (1 seconds))
  ).protocols(httpProtocol)

}
