package controllers

import play.api._
import play.api.mvc._
import views.html._
import models.Group

object Access extends Controller {

  def getUserName(implicit request: RequestHeader) = request.session.get(Security.username)

}
