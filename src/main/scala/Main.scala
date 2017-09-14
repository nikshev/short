import scala.concurrent.duration._
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import akka.util.Timeout
import common.utils.AppConfiguration


object Main extends App with AppConfiguration with RestInterface {
  val host = config.getString("http.host")
  val port = config.getInt("http.port")

  implicit val system = ActorSystem("short-service")
  implicit val materializer = ActorMaterializer()

  implicit val executionContext = system.dispatcher
  implicit val timeout = Timeout(10 seconds)

  val api = routes

  Http().bindAndHandle(handler = api, interface = host, port = port) map {
    binding => println(s"REST interface bound to ${binding.localAddress}")
  } recover {
    case ex => println(s"REST interface could not bind to $host:$port", ex.getMessage)
  }
}