package controllers

import play.api.mvc._
import utils.DatabaseService
import play.api.libs.concurrent.Execution.Implicits._
import models.User
import com.mongodb.casbah.commons.MongoDBObject

object Application extends Controller with Access with DatabaseService {

  def index = Action {
    Ok(views.html.index(loginForm, registerForm))
  }

  def register = Action { implicit request =>
    registerForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index(loginForm, registerForm)),
      user => {
        val a = MongoDBObject("username" -> user.username, "password" -> user.password)
        getCollection("users").insert(a)
      }
    )

    Ok(views.html.index(loginForm, registerForm))
  }

  def createUser(user: User) = {
    val users = getCollection("users")
  }

  def uploadImage = Action {
    Ok(views.html.index(loginForm, registerForm))
  }

}
