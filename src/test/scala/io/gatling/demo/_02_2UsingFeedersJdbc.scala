package io.gatling.demo

import io.gatling.core.Predef._
import io.gatling.demo.config.BasicHttpProtocol
import io.gatling.http.Predef._

import scala.concurrent.duration._

class _02_2UsingFeedersJdbc extends Simulation {

  val httpProtocol = BasicHttpProtocol.GitHubProtocolBuilder

  val DB_JDBC_URL = System.getenv("db_jdbc_url")
  val DB_USERNAME = System.getenv("db_username")
  val DB_PWD = System.getenv("db_password")
  val DB_QUERY_GH_USERS = "SELECT github_username as user FROM github_user"

  val feeder = io.gatling.jdbc.Predef.jdbcFeeder(DB_JDBC_URL, DB_USERNAME, DB_PWD, DB_QUERY_GH_USERS).circular

  val findRepos = scenario("Find repos from GitHub")
    .feed(feeder)
    .exec(http("find_enriquezrene_repos")
      .get("/users/${user}/repos")
      .check(status.is(200)))

  setUp(findRepos.inject(constantUsersPerSec(2) during (2 seconds))).protocols(httpProtocol)

}
