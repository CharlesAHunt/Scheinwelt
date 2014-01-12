package controllers

import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.User
import com.mongodb.casbah.commons.MongoDBObject
import utils.{DatabaseService, EncryptionUtil}

trait Access extends Controller with DatabaseService {

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
      "username" -> text,
      "password" -> text
    )(
      (username, password) =>
        User(username = username, password = password)
    )(
      (user: User) => Option(user.username, user.password)
    )
  )

  def checkLogin(username: String, password: String) = {
//    UserDAO.isPasswordCorrect(username,password)
  }

  def checkRegister(username: String, password: String, email: String) = {

  }



  /**
   * Redirect to login if the user in not authorized.
   */
  private def onUnauthorized(request: RequestHeader) = Results.Redirect(routes.Search.index())

  // --

  /**
   * Action for authenticated users.
   */
//  def IsAuthenticated(f: => String => Request[AnyContent] => Result) = Security.Authenticated(username, onUnauthorized) {
//    user =>
//      Action(request => f(user)(request))
//  }

}

object Access extends Controller with Access {

  def register = Action { implicit request =>
    registerForm.bindFromRequest.fold(
      errors => {    Redirect("/index").flashing(
        "error" -> "There were errors in your registration form."
      )},
      user => {
        val a = MongoDBObject("username" -> user.username, "password" -> EncryptionUtil.encrypt(user.password))
        getCollection("users").insert(a)
      }
    )

    Redirect("/index").flashing(
      "success" -> "You have successfully registered. You may now log in."
    )
  }


  def login = Action { implicit request =>

    Redirect("/index").withSession(
      session + ("logged_in_user" -> loginForm.bindFromRequest.data.get("name").get)
    ).flashing(
        "success" -> "You are now logged in."
      )

  }

  def logout = Action {
    Redirect("/index").withNewSession.flashing(
      "success" -> "You've been logged out"
    )
  }
}