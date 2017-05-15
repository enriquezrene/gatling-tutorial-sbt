package io.gatling.demo

import io.gatling.commons.validation._
import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.core.session.Expression
import io.gatling.demo.config.BasicHttpProtocol
import io.gatling.http.Predef._

import scala.concurrent.duration._

class _05MultiChecksScenario extends Simulation {

  val httpProtocol = BasicHttpProtocol.GitHubProtocolBuilder

  val tagPublicRepos: (Session) => Validation[String] = "public_repos"
  val tagPublicReposAsExp: Expression[String] = "public_repos"

  val checkStatus = scenario("Check response status codes")
    .exec(http("find_enriquezrene_repos")
      .get("/users/enriquezrene")
      .check(status.is(session => 200))
      // Get the whole body as string and verify that tagPublicRepos content exist there
      .check(substring(tagPublicRepos).exists)
      .check(substring(tagPublicReposAsExp).exists))


  setUp(
    checkStatus.inject(constantUsersPerSec(1) during (1 seconds))
  ).protocols(httpProtocol)

}
