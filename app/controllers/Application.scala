package controllers

import play.api.mvc._
import models.Group
import utils.DatabaseService

object Application extends Controller with Access with DatabaseService {

  def index = Action {
    Ok(views.html.index(loginForm, registerForm))
  }

  def register = Action { implicit request =>
    registerForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index(loginForm, registerForm)),
      group => createGroup(group)
    )
    Ok(views.html.index(loginForm, registerForm))
  }

  def createGroup(group: Group) = {
    val groups = getCollection("groups")
    groups.insert(group).map(lastError => println(lastError.ok))
  }

  def uploadImage = Action {
    Ok(views.html.index(loginForm, registerForm))
  }

}
