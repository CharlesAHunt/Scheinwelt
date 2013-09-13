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
    println(registerForm)
    registerForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index(loginForm, registerForm)),
      group => createGroup(group)
    )
    Ok(views.html.index(loginForm, registerForm))
  }

  def createGroup(group: Group) = {
     println("id, name, token: " +group)
  }

  def uploadImage = Action {
    Ok(views.html.index(loginForm, registerForm))
  }

}
