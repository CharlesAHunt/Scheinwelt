package controllers

import play.api._
import play.api.mvc._
import views.html._
import play.api.data.Form
import models.Group
import play.api.data.Forms._
import models.Group
import org.bson.types.ObjectId

object Application extends Controller with Access {

  def index = Action {
    Ok(views.html.index(loginForm, registerForm))
  }

  def register = Action { implicit request =>
    registerForm.bindFromRequest.fold(
      formWithErrors => // binding failure, you retrieve the form containing errors,
        BadRequest(views.html.index(loginForm, formWithErrors)),
      value => // binding success, you get the actual value
        Ok(views.html.index(loginForm, registerForm))
    )
  }

  def uploadImage = Action {
    Ok(views.html.index(loginForm, registerForm))
  }

}
