package io.gatling.demo

import io.gatling.core.Predef._
import io.gatling.demo.config.BasicHttpProtocol
import io.gatling.http.Predef._

import scala.concurrent.duration._


class _01BasicSimulation extends Simulation {

  val httpProtocol = BasicHttpProtocol.GitHubProtocolBuilder

  val findRepositories = scenario("Find repositories from GitHub")
    .exec(http("find enriquezrene repositories")
      .get("/users/enriquezrene/repos")
      .check(status.is(200)))


  setUp(findRepositories.inject(constantUsersPerSec(1) during (1 seconds))).protocols(httpProtocol)

}
