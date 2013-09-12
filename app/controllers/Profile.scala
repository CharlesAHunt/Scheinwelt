package controllers

import play.api._
import play.api.mvc._
import views.html._
import play.api.data.Form
import models.Group
import play.api.data.Forms._
import models.Group
import org.bson.types.ObjectId

object Profile extends Controller with Access {

    def index = Action {
        Ok(views.html.profile(loginForm, registerForm))
    }

}
