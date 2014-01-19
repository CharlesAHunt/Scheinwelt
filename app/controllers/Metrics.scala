package controllers

import play.api.mvc._

object Metrics extends Controller with Access {

  def index = Action {  implicit request =>
    Ok(views.html.metrics(loginForm, registerForm))
  }

}
