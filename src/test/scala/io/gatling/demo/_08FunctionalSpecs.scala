package io.gatling.demo

import io.gatling.commons.validation.Validation
import io.gatling.core.Predef._
import io.gatling.demo.config.BasicHttpProtocol
import io.gatling.http.Predef._
import io.gatling.http.funspec.GatlingHttpFunSpec

import scala.concurrent.duration._

class _08FunctionalSpecs extends GatlingHttpFunSpec {

  val baseURL = "https://api.github.com"

  override def httpConf = super.httpConf.basicAuth(username = "enriquezrene", password = "G!+hub2017").userAgentHeader("enriquezrene").acceptHeader("application/vnd.github.v3+json")

  spec {
    http("Looking for my repos")
      .get("/users/enriquezrene")
      .check(status.is(ReposSpec.successfulResponse))
      .check(ReposSpec.tagPublicRepos.exists)
  }

}

object ReposSpec {

  def tagPublicRepos = substring("public_repos")

  def successfulResponse = 200
}