package model

import com.outworkers.phantom.dsl._

import scala.concurrent.Future

case class ShortLinkClick(linkId: String, timestamp: DateTime, countryCode: Option[String], ipAddress: Option[String])

class ShortLinkClicksSchema extends CassandraTable[ShortLinkClicksSchema, ShortLinkClick] {
  override lazy val tableName = "short_link_clicks"

  object linkId extends StringColumn(this) with PartitionKey {
    override lazy val name = "link_id"
  }
  object timestamp extends DateTimeColumn(this) with ClusteringOrder with Descending
  object countryCode extends OptionalStringColumn(this) with ClusteringOrder {
    override lazy val name = "country_code"
  }
  object ipAddress extends OptionalStringColumn(this) {
    override lazy val name = "ip_address"
  }

  override def fromRow(r: Row): ShortLinkClick = ShortLinkClick(linkId(r), timestamp(r), countryCode(r), ipAddress(r))
}

abstract class ShortLinkClicks extends ShortLinkClicksSchema with RootConnector {
  def findByLinkId(linkId: String): Future[List[ShortLinkClick]] = {
    select.where(_.linkId eqs linkId).fetch()
  }
}