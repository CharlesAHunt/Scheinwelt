package controllers

import play.api.mvc._
import utils.DatabaseService
import play.api.data.Form
import play.api.data.Forms._
import models.{Log, User}

object Search extends Controller with Access with DatabaseService {

  def index = Action { implicit request =>
    Ok(views.html.index(loginForm, registerForm, searchForm))
  }

  def search = Action { implicit request =>
    Ok(views.html.index(loginForm, registerForm, searchForm))
  }

}
