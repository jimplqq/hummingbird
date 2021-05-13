package controllers.scala

import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents, Request}

@Singleton
class TestController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def index() = Action {
    implicit request: Request[AnyContent] =>
      Ok("hello world")
  }

}
