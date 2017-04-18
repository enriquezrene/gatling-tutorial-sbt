package io.gatling.demo.config

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.util.Properties

object BasicHttpProtocol {

  val ghPwd: String = Properties.envOrElse("gh.pwd", "NO_PWD_PROVIDED")

  val GitHubProtocolBuilder = http
    .baseURL("https://api.github.com")
    .userAgentHeader("enriquezrene")
    .acceptHeader("application/vnd.github.v3+json")
    .basicAuth(username = "enriquezrene", password = ghPwd)

}
