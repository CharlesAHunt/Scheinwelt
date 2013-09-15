package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.Group

trait Access extends Controller {

  val loginForm: Form[Group] = Form(
    mapping(
      "name" -> text,
      "token" -> text
    )(
      (name, token) =>
        new Group(name, token)
    )(
      (group: Group) => Option(group.name, group.token)
    )
  )

  val registerForm: Form[Group] = Form(
    mapping(
      "name" -> text,
      "token" -> text
    )(
      (name, token) =>
        new Group(name, token)
    )(
      (group: Group) => Option(group.name, group.token)
    )
  )

  def checkLogin(username: String, password: String) = {
//    GroupDAO.isPasswordCorrect(username,password)
  }

  def checkRegister(username: String, password: String, email: String) = {

  }

  def login = Action { implicit request =>

      Ok(views.html.index(loginForm, registerForm)).withSession(
        session + ("logged_in_user" -> loginForm.bindFromRequest.data.get("name").toString)
      )

  }

  def logout = Action {
    Redirect(routes.Application.index()).withNewSession.flashing(
      "success" -> "You've been logged out"
    )
  }

  /**
   * Redirect to login if the user in not authorized.
   */
  private def onUnauthorized(request: RequestHeader) = Results.Redirect(routes.Application.index())

  // --

  /**
   * Action for authenticated users.
   */
//  def IsAuthenticated(f: => String => Request[AnyContent] => Result) = Security.Authenticated(username, onUnauthorized) {
//    user =>
//      Action(request => f(user)(request))
//  }

}
