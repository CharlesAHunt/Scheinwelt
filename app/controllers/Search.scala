package controllers

import play.api.mvc._
import utils.DatabaseService
import play.api.data.Form
import play.api.data.Forms._
import models.{LogDAO, Log}
import com.mongodb.casbah.commons.MongoDBObject

object Search extends Controller with Access with DatabaseService {

  def index = Action { implicit request =>
    Ok(views.html.index(loginForm, registerForm, searchForm))
  }

  def search = Action { implicit request =>
    Ok(views.html.index(loginForm, registerForm, searchForm))
  }

  def about = Action { implicit request =>
    Ok(views.html.about(loginForm, registerForm))
  }

  case class LogSearch(environment: String, region:String, application:String, exception:String, message:String, level:String, date:String)

  val searchForm: Form[LogSearch] = Form(
    mapping(
      "application" -> text,
      "environment" -> text,
      "region" -> text,
      "level" -> text,
      "exception" -> text,
      "message" -> text,
      "date" -> text
    )(
        (environment, region, application, exception, message, level, date) =>
          LogSearch(environment = environment, region = region, application = application, exception = exception, message = message, level = level, date = date)
      )(
        (logSearch: LogSearch) => Option(logSearch.environment, logSearch.region, logSearch.application, logSearch.exception, logSearch.message, logSearch.level, logSearch.date)
      )
  )

  def searchResults(): List[Log] = {
    LogDAO.find(ref = MongoDBObject()).toList
  }

}
