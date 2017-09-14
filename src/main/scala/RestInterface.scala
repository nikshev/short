import akka.http.scaladsl.server.Route
import resources.ShortLinkResource
import services.ShortLinkService

import scala.concurrent.ExecutionContext

trait RestInterface extends Resources {

  override implicit def executionContext: ExecutionContext

  val clickRoutes = path(Segment) { id =>
    get {
      completeWithLocationHeader(ShortLinkService.getUrl(id))
    }
  }

  val routes: Route = shortLinkRoutes ~ clickRoutes
}

trait Resources extends ShortLinkResource
