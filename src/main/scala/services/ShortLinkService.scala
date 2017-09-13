package services

import com.outworkers.phantom.connectors.CassandraConnection
import com.outworkers.phantom.dsl._
import common.utils.cassandra.DefaultConnection
import model.{ShortLinkClicks, ShortLinks}

import scala.concurrent.Future

object ShortLinkService extends ShortLinkService(DefaultConnection.connector) {
  def getUrl(id: String): Future[String] = shortLinks.findById(id).map(sl => sl.get.url)
}

abstract class ShortLinkService(val keyspace: CassandraConnection) extends Database[ShortLinkService](keyspace) {
  object shortLinks extends ShortLinks with keyspace.Connector
  object shortLinkClicks extends ShortLinkClicks with keyspace.Connector
}
