package jumbo.scenarios

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object GetCloseStores {

  val httpConfig = http("get close stores")
    .get("")
    .check(status is 200)

  val getCloseStores = scenario("get close stores")
    .exec(httpConfig)

}
