name := """url-shortener"""

version := "1.0"

organization := "com.vaiski"

scalaVersion := "2.11.10"

resolvers ++= Seq(
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
  "SnowPlow Repo" at "http://maven.snplow.com/releases/",
  "Twitter's Repository" at "http://maven.twttr.com/",
  Resolver.bintrayRepo("hseeberger", "maven")
)

libraryDependencies ++= {
  val AkkaVersion       = "2.3.9"
  val AkkaHttpVersion   = "10.0.6"
  val Json4sVersion     = "3.5.1"
  val PhantomVersion    = "2.6.3"
  val PillarVersion     = "2.3.0"

  Seq(
    "com.typesafe.akka" %% "akka-slf4j"      % AkkaVersion,
    "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-testkit" % AkkaHttpVersion,
    "com.outworkers"    %% "phantom-dsl" % PhantomVersion,
    "com.chrisomeara"   %% "pillar" % PillarVersion,
    "com.snowplowanalytics"  %% "scala-maxmind-iplookups"  % "0.2.0",
    "ch.qos.logback"    %  "logback-classic" % "1.1.2",
    "org.json4s"        %% "json4s-native"   % Json4sVersion,
    "org.json4s"        %% "json4s-ext"      % Json4sVersion,
    "de.heikoseeberger" %% "akka-http-json4s" % "1.14.0",
    "joda-time"         % "joda-time" % "2.9.9"
  )
}

mainClass in Global := Some("Main")