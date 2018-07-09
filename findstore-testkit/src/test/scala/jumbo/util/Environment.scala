package jumbo.util

object Environment {

  val baseURL = scala.util.Properties
    .envOrElse("baseURL", "http://localhost:8080/stores/findCloseStores?latitude=51.6085&longitude=5.4975")
  val users = scala.util.Properties.envOrElse("numberOfUsers", "10")
  val maxResponseTime = scala.util.Properties.envOrElse("maxResponseTime", "500")

}
