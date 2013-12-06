package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.{UserDAO, User}
import com.novus.salat._
import com.novus.salat.global.ctx

trait Access extends Controller {

  val loginForm: Form[User] = Form(
    mapping(
      "name" -> text,
      "token" -> text
    )(
      (name, token) =>
        User(username = name, password = token)
    )(
      (user: User) => Option(user.username, user.password)
    )
  )

  val registerForm: Form[User] = Form(
    mapping(
      "name" -> text,
      "token" -> text
    )(
      (name, token) =>
        User(username = name, password = token)
    )(
      (user: User) => Option(user.username, user.password)
    )
  )

  def checkLogin(username: String, password: String) = {
//    UserDAO.isPasswordCorrect(username,password)
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
