package io.gatling.demo

import io.gatling.core.Predef._
import io.gatling.demo.config.BasicHttpProtocol
import io.gatling.http.Predef._

import scala.concurrent.duration._

class _07Assertions extends Simulation {

  val httpProtocol = BasicHttpProtocol.GitHubProtocolBuilder


  val findRepositories = scenario("Browsing repos")
    .repeat(2, "n") {
      exec(http("BrowsingPages")
        .get("/user/repos?page=${n}&per_page=5")
        .check(status.is(200)))
        .pause(2 seconds)
    }

  setUp(findRepositories.inject(atOnceUsers(1))).protocols(httpProtocol)
    // requests can not take more than 2000 ms
    .assertions(global.responseTime.max.lt(2000))
    // when BrowsingPages scenario no request failures are allowed
    .assertions(details("BrowsingPages").failedRequests.percent.is(0))

}