package controllers

import play.api._
import play.api.mvc._
import views.html._
import play.api.data.Form
import models.Group
import play.api.data.Forms._
import models.Group
import org.bson.types.ObjectId

object Application extends Controller {

  val loginForm: Form[Group] = Form(
    mapping(
      "id" -> ignored(new ObjectId),
      "name" -> text,
      "token" -> text
    )(Group.apply)(Group.unapply)
  )

  val registerForm: Form[Group] = Form(
    mapping(
      "id" -> ignored(new ObjectId),
      "name" -> text,
      "token" -> text
    )(Group.apply)(Group.unapply)
  )

  def index = Action {
    Ok(views.html.index(loginForm, registerForm))
  }

  def register = Action {
    Ok(views.html.index(loginForm, registerForm))
  }

  def uploadImage = Action {
    Ok(views.html.index(loginForm, registerForm))
  }


}
