package routing

import akka.http.scaladsl.marshalling.{ToResponseMarshallable, ToResponseMarshaller}
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.model.headers.Location
import akka.http.scaladsl.server.{Directives, Route}
import serializers.JsonSupport

import scala.concurrent.{ExecutionContext, Future}

trait JsonResource extends Directives with JsonSupport {
  implicit def executionContext: ExecutionContext

  def completeWithLocationHeader[T](location: Future[String]): Route =
    onSuccess(location) { url =>
      respondWithHeader(Location(url)) {
        complete(StatusCodes.MovedPermanently, None)
      }
    }

  def complete[T: ToResponseMarshaller](resource: Future[Option[T]]): Route =
    onSuccess(resource) {
      case Some(t) => complete(ToResponseMarshallable(t))
      case None => complete(StatusCodes.NotFound, None)
    }

  def complete(resource: Future[Unit]): Route = onSuccess(resource) { complete(StatusCodes.NoContent, None) }
}
