package controllers

import play.api.mvc._
import play.api.data.Form
import models.{Environment, Application, User}
import play.api.data.Forms._
import com.mongodb.casbah.Imports

object Manage extends Controller with Access {

  val appForm: Form[models.Application] = Form(
    mapping(
      "name" -> text
    )(
        (name) =>
          Application(name = name)
      )(
        (application: Application) => Option(application.name)
      )
  )

  val envForm: Form[models.Environment] = Form(
    mapping(
      "name" -> text
    )(
        (name) =>
          Environment(name = name)
      )(
        (environment: Environment) => Option(environment.name)
      )
  )

  def index = Action {  implicit request =>
        Ok(views.html.manage(loginForm, registerForm, appForm, envForm))
  }

  def getEnvironments(): List[Imports.DBObject] = {
    getCollection("environments").find().toList
  }

  def getApplications(): List[Imports.DBObject] = {
    getCollection("regions").find().toList
  }

  def getRegions(): List[Imports.DBObject] = {
    getCollection("applications").find().toList
  }

}
