package controllers

import play.api.mvc._
import models.{GroupDAO, Group}

object Application extends Controller with Access {

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
     println("id, name, token: " +group)
     GroupDAO.insert(group)
  }

  def uploadImage = Action {
    Ok(views.html.index(loginForm, registerForm))
  }

}
