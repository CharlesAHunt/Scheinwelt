package controllers

import play.api.mvc._

object Notify extends Controller with Access {

  def index = Action {  implicit request =>
    Ok(views.html.notify(loginForm, registerForm))
  }

}
