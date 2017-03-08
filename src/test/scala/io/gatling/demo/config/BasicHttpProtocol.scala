package io.gatling.demo.config

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object BasicHttpProtocol {

  val GitHubProtocolBuilder = http
    .baseURL("https://api.github.com")
    .userAgentHeader("enriquezrene")
    .acceptHeader("application/vnd.github.v3+json")

}
