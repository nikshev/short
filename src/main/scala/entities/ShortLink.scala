package entities

import com.outworkers.phantom.dsl._

case class ShortLink(id: String, url: String, createdAt: DateTime = org.joda.time.DateTime.now())