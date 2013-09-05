package controllers

import play.api._
import play.api.mvc._
import views.html._

object Profile extends Controller {

    def index = Action {
        Ok(views.html.profile())
    }

}
