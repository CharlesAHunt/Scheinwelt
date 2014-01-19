package controllers

import play.api.mvc._

object Precog extends Controller with Access {

  def index = Action {  implicit request =>
    Ok(views.html.precog(loginForm, registerForm))
  }

}
