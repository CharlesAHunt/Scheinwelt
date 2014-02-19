package controllers

import org.apache.logging.log4j._
import play.api.mvc._
import utils.{LogQueryBuilder, DatabaseService}
import play.api.data.Form
import play.api.data.Forms._
import models.{LogDAO, Log}
import com.mongodb.casbah.commons.MongoDBObject

object Search extends Controller with Access with DatabaseService {

  val logger: Logger = LogManager.getLogger("Logic")

  def index = Action { implicit request =>
    Ok(views.html.index(loginForm, registerForm, searchForm))
  }

  def search(app: Option[String],env: Option[String],region: Option[String],level: Option[String],exception: Option[String],message: Option[String],beforeDate: Option[String],afterDate: Option[String]) = Action {

    val jsonBuilder = StringBuilder.newBuilder
    val queryObject = LogQueryBuilder.apply(app, env, region, level, exception, message, beforeDate, afterDate).buildQuery()

    jsonBuilder.append("[")
    getCollection("logs").find(queryObject).foreach( jsonBuilder.append(_).append(",") )
    jsonBuilder.deleteCharAt(jsonBuilder.length-1)
    jsonBuilder.append("]")

    Ok(jsonBuilder.toString())

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

  def generateLogs() = {

    val log = MongoDBObject("application" -> "todo", "exception" -> "java.lang.NoClassDefFoundError", "message" -> "Class definition not found at runtime",
      "level" -> "ERROR", "trace" -> "stacktrace...", "timeStamp" -> new org.joda.time.DateTime().toString())

    getCollection("logs").insert(log)

  }

}