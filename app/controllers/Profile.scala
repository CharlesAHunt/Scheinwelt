package controllers

import play.api.mvc._

object Profile extends Controller with Access {

    def index = Action {
        Ok(views.html.profile(loginForm, registerForm))
    }

}
