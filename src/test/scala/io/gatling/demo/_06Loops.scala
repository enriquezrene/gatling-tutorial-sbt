package io.gatling.demo

import io.gatling.core.Predef._
import io.gatling.demo.config.BasicHttpProtocol
import io.gatling.http.Predef._

import scala.concurrent.duration._

class _06Loops extends Simulation {

  val httpProtocol = BasicHttpProtocol.GitHubProtocolBuilder


  val findRepositories = scenario("Browsing repos")
    .repeat(2, "n") {
      exec(http("Browsing repo page ${n}")
        .get("/user/repos?page=${n}&per_page=5")
        .check(status.is(200)))
        .pause(2 seconds)
    }

  setUp(findRepositories.inject(atOnceUsers(1))).protocols(httpProtocol)

}