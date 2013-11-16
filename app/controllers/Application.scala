package controllers

import play.api.mvc._
import models.Log
import utils.DatabaseService
import play.api.libs.concurrent.Execution.Implicits._

object Application extends Controller with Access with DatabaseService {

  def index = Action {
    Ok(views.html.index(loginForm, registerForm))
  }

  def register = Action { implicit request =>
    registerForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index(loginForm, registerForm)),
      group => createLog()
    )
    Ok(views.html.index(loginForm, registerForm))
  }

  def createUser(user: User) = {
    val users = getCollection("users")
    users.insert(user).map(lastError => println(lastError.ok))
  }

  def uploadImage = Action {
    Ok(views.html.index(loginForm, registerForm))
  }

}
