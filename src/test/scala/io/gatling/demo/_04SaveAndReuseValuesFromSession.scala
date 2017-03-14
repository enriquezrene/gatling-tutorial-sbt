package io.gatling.demo

import io.gatling.core.Predef._
import io.gatling.demo.config.BasicHttpProtocol
import io.gatling.http.Predef._
import scala.concurrent.duration._

class _04SaveAndReuseValuesFromSession extends Simulation {

  val httpProtocol = BasicHttpProtocol.GitHubProtocolBuilder

  val get_followers_scn = scenario("Find followers and following users")
    .exec(http("Find enriquezrene followers")
      .get("/users/enriquezrene")
      .check(status.is(session => 200))
      .check(jsonPath("$.followers_url").saveAs("my_followers_url"))
      .check(jsonPath("$.following_url").transform(following_tag => following_tag.replace("{/other_user}", "")).saveAs("my_followings_url")))
    .exec(http("get followers from enriquezrene")
      .get("${my_followers_url}")
      .check(status.is(session => 200)))
    .exec(http("get enriquezrene followings")
      .get("${my_followings_url}")
      .check(status.is(session => 200)))


  setUp(
    get_followers_scn.inject(constantUsersPerSec(1) during (1 seconds))
  ).protocols(httpProtocol)

}
