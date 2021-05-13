package controllers.scala.aos

import grpc.reflection.v1alpha.reflection.ServerReflectionRequest.MessageRequest
import javax.inject.Inject
import play.api.mvc.{AnyContent, MessagesAbstractController, MessagesControllerComponents, MessagesRequest}

class AosController @Inject()(cc: MessagesControllerComponents) extends MessagesAbstractController(cc) {

  def save() = Action { implicit request: MessagesRequest[AnyContent] =>

    Ok
  }

}
