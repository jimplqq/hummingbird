import play.core.PlayVersion.akkaVersion
import play.core.PlayVersion.akkaHttpVersion
import play.grpc.gen.javadsl.{PlayJavaClientCodeGenerator, PlayJavaServerCodeGenerator}
import play.sbt.PlayImport
import com.typesafe.sbt.SbtMultiJvm.multiJvmSettings
import com.typesafe.sbt.SbtMultiJvm.MultiJvmKeys.MultiJvm

organization := "com.gin"
name := "hummingbird"
version := "1.0-SNAPSHOT"

// #grpc_play_plugins
// build.sbt
lazy val `hummingbird` = (project in file("."))
  .enablePlugins(PlayJava)
  .enablePlugins(PlayScala)
  .enablePlugins(AkkaGrpcPlugin) // enables source generation for gRPC
  .enablePlugins(PlayAkkaHttp2Support) // enables serving HTTP/2 and gRPC
  .enablePlugins(PlayEbean) // ebean
  .enablePlugins(MultiJvmPlugin)
  .settings(multiJvmSettings: _*)
  // #grpc_play_plugins
  .settings(
    resolvers ++= Seq(
      "Local Ivy Repository" at "file:///" + Path.userHome.absolutePath + "/.ivy2/local",
      "tuxburner.github.io" at "https://tuxburner.github.io/repo",
      "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
      Resolver.mavenLocal,
      Resolver.bintrayRepo("helloscala", "maven"),
      Resolver.jcenterRepo,
    ),

    akkaGrpcGeneratedLanguages := Seq(AkkaGrpc.Java),
    // #grpc_client_generators
    // build.sbt
    akkaGrpcExtraGenerators += PlayJavaClientCodeGenerator,
    // #grpc_client_generators
    // #grpc_server_generators
    // build.sbt
    akkaGrpcExtraGenerators += PlayJavaServerCodeGenerator,
    // #grpc_server_generators
    PlayKeys.devSettings ++= Seq(
      "play.server.http.port" -> "9044",
      //      "play.server.https.port" -> "9443"
      //     Configures the keystore to use in Dev mode. This setting is equivalent to `play.server.https.keyStore.path`
      //     in `application.conf`.
      //          "play.server.https.keyStore.path" -> "conf/selfsigned.keystore",
    )
  )
  .settings(
    libraryDependencies ++= CompileDeps
  )
  .configs(MultiJvm)

unmanagedBase := baseDirectory.value / "lib"

scalaVersion := "2.12.13"
scalacOptions ++= List("-encoding", "utf8", "-deprecation", "-feature", "-unchecked")
javacOptions ++= List("-Xlint:unchecked", "-Xlint:deprecation")

val CompileDeps = Seq(
  PlayImport.guice,
  PlayImport.cacheApi,
  javaClusterSharding,
  guice,
  javaJdbc,
  javaWs,
  //  "com.lightbend.play"      %% "play-grpc-runtime"    % BuildInfo.playGrpcVersion,
  "com.typesafe.akka" %% "akka-discovery" % akkaVersion,
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
)

// https://mvnrepository.com/artifact/com.typesafe.akka/akka-stream-typed
libraryDependencies += "com.typesafe.akka" %% "akka-stream-typed" % "2.6.14"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
)
libraryDependencies += "com.typesafe.akka" %% "akka-serialization-jackson" % akkaVersion
libraryDependencies += "com.typesafe.akka" %% "akka-cluster-typed" % akkaVersion


// Make verbose tests
testOptions in Test := Seq(Tests.Argument(TestFrameworks.JUnit, "-a", "-v"))


routesGenerator := InjectedRoutesGenerator

val scalaNacosVersion = "1.3.2"
// Play WS
libraryDependencies += "me.yangbajing.nacos4s" %% "nacos-play-ws" % scalaNacosVersion
// Scala API
libraryDependencies += "me.yangbajing.nacos4s" %% "nacos-client-scala" % scalaNacosVersion
// Akka Discovery
libraryDependencies += "me.yangbajing.nacos4s" %% "nacos-akka" % scalaNacosVersion

// https://mvnrepository.com/artifact/mysql/mysql-connector-java
libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.24"

// https://mvnrepository.com/artifact/org.projectlombok/lombok
libraryDependencies += "org.projectlombok" % "lombok" % "1.18.20" % "provided"

// https://mvnrepository.com/artifact/cglib/cglib
libraryDependencies += "cglib" % "cglib" % "3.3.0"

// enable Play cache API (based on your Play version)
libraryDependencies += play.sbt.PlayImport.cacheApi
// include play-redis library
libraryDependencies += "com.github.karelcemus" %% "play-redis" % "2.6.1"
// https://mvnrepository.com/artifact/com.rabbitmq/amqp-client
libraryDependencies += "com.rabbitmq" % "amqp-client" % "5.12.0"


//libraryDependencies += "com.github.tuxBurner" %% "play-akkajobs" % "2.6.1"

// https://mvnrepository.com/artifact/com.auth0/java-jwt
//libraryDependencies += "com.auth0" % "java-jwt" % "3.16.0" % "provided"

jvmOptions in MultiJvm := Seq("-Xmx256M")

javaOptions in Universal ++= Seq(
  // JVM memory tuning
  //  "-J-Xmx512m",
  //  "-J-Xms128m",
  "-J-Xms128M -J-Xmx512m -J-server",

  // Since play uses separate pidfile we have to provide it with a proper path
  // name of the pid file must be play.pid
  //  s"-Dpidfile.path=/var/run/${packageName.value}/play.pid",

  // alternative, you can remove the PID file
  // s"-Dpidfile.path=/dev/null",

  // Use separate configuration file for production environment
  //  s"-Dconfig.file=/usr/share/${packageName.value}/conf/production.conf",

  // Use separate logger configuration file for production environment
  //  s"-Dlogger.file=/usr/share/${packageName.value}/conf/production-logger.xml",

  // You may also want to include this setting if you use play evolutions
  //  "-DapplyEvolutions.default=true"

  "-Dplay.server.https.engineProvider=common.ssl.CustomSSLEngineProvider"
)

sources in(Compile, doc) := Seq.empty
publishArtifact in(Compile, packageDoc) := false

mainClass in assembly := Some("play.core.server.ProdServerStart")
fullClasspath in assembly += Attributed.blank(PlayKeys.playPackageAssets.value)

assemblyMergeStrategy in assembly := {
  case manifest if manifest.contains("MANIFEST.MF") =>
    // We don't need manifest files since sbt-assembly will create
    // one with the given settings
    MergeStrategy.discard
  case referenceOverrides if referenceOverrides.contains("reference-overrides.conf") =>
    // Keep the content for all reference-overrides.conf files
    MergeStrategy.concat
  case PathList(ps@_*) if ps.last endsWith ".RSA" => MergeStrategy.first


  case x if x.contains("io.netty.versions.properties") => MergeStrategy.last
  case x if x.contains("native-image.properties") => MergeStrategy.last
  case x if x.contains("module-info.class") => MergeStrategy.last
  case x if x.contains("Log.class") => MergeStrategy.last
  case x if x.contains("LogConfigurationException.class") => MergeStrategy.last
  case x if x.contains("LogFactory.class") => MergeStrategy.last
  case x if x.contains("NoOpLog.class") => MergeStrategy.last
  case x if x.contains("SimpleLog$1.class") => MergeStrategy.last
  case x if x.contains("SimpleLog.class") => MergeStrategy.last
  case x if x.contains("nowarn$.class") => MergeStrategy.last
  case x if x.contains("nowarn.class") => MergeStrategy.last
  case x =>
    // For all the other files, use the default sbt-assembly merge strategy
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}




