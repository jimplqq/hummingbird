package controllers.scala.aos


object TestForm {

  import play.api.data.Forms._
  import play.api.data.Form

  case class Data(name: String)

  val form = Form(
    mapping(
      "name" -> nonEmptyText
    )(Data.apply)(Data.unapply)
  )

}
