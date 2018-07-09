package jumbo.simulation

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import jumbo.scenarios.GetCloseStores
import jumbo.util.Environment

class StoreServiceSimulation extends Simulation {

  val httpConf = http.baseURL(Environment.baseURL)

  val storeServiceScenarios = List(

    GetCloseStores.getCloseStores.inject(
      atOnceUsers(1),
      rampUsersPerSec(1) to 100 during (30 seconds)
    )

  )

  setUp(storeServiceScenarios)
    .protocols(httpConf)
    .maxDuration(1 minutes)
    .assertions(
      global.responseTime.max.lte(Environment.maxResponseTime.toInt)
    )


}
