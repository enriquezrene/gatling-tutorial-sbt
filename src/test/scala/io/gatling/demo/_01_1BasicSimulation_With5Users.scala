package io.gatling.demo

import io.gatling.core.Predef._
import io.gatling.demo.config.BasicHttpProtocol
import io.gatling.http.Predef._

import scala.concurrent.duration._


class _01_1BasicSimulation_With5Users extends Simulation {

  val httpProtocol = BasicHttpProtocol.GitHubProtocolBuilder

  val findRepositories = scenario("Find repositories from GitHub")
    .exec(http("find enriquezrene repositories")
      .get("/users/enriquezrene/repos")
      .check(status.is(200)))


  setUp(findRepositories.inject(atOnceUsers(5))).protocols(httpProtocol)

}