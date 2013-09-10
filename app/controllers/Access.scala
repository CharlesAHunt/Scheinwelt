package controllers

import play.api._
import play.api.mvc._
import views.html._
import models.{GroupDAO, Group}
import play.api.data._
import play.api.data.Forms._

object Access extends Controller {

  val loginForm = Form(
    tuple(
      "name" -> text,
      "token" -> text
    ) verifying("Invalid username or password", result => result match {
      case (name, token) => check(name, token)
    })
  )

  def check(username: String, password: String) = {
    GroupDAO.isPasswordCorrect(username,password)
  }

  def login = Action { implicit request =>

      Ok(views.html.index()).withSession(
        session + ("logged_in_user" -> loginForm.bindFromRequest.data.get("name").toString)
      )

  }

  //  def authenticate = Action { implicit request =>
  //    loginForm.bindFromRequest.fold(
  //      formWithErrors => BadRequest(views.html.index(formWithErrors)),
  //      user => Redirect(routes.Application.index).withSession(Security.username -> user._1)
  //    )
  //  }

  def logout = Action {
    Redirect(routes.Application.index()).withNewSession.flashing(
      "success" -> "You've been logged out"
    )
  }

  def getUserName = Action { implicit request =>

    val body: AnyContent = request.body
    val textBody: Option[String] = body.asText

    // Expecting text body
    textBody.map { text =>
      Ok("Got: " + text)
    }.getOrElse {
      BadRequest("Expecting text/plain request body")
    }

//    val userName: String = request.session.get("logged_in_user").toString
//    Ok(body.asText.get)

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
