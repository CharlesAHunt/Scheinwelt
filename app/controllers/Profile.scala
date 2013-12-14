package controllers

import play.api.mvc._

object Profile extends Controller with Access {

    def index = Action {  implicit request =>
        Ok(views.html.profile(loginForm, registerForm))
    }

    def uploadImage = Action { implicit request =>
      Ok(views.html.index(loginForm, registerForm, searchForm))
    }
}
