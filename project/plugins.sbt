//enablePlugins(BuildInfoPlugin)
val playGrpcV = "0.9.1"
//buildInfoKeys := Seq[BuildInfoKey]("playGrpcVersion" -> playGrpcV)
//buildInfoPackage := "hummingbird"

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.8.8")
addSbtPlugin("com.typesafe.sbt" % "sbt-play-ebean" % "5.0.2")

// #grpc_sbt_plugin
// project/plugins.sbt
addSbtPlugin("com.lightbend.akka.grpc" %% "sbt-akka-grpc" % "1.0.2")
libraryDependencies += "com.lightbend.play" %% "play-grpc-generators" % playGrpcV
// #grpc_sbt_plugin

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.5")
addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.10.0-RC1")