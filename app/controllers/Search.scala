package controllers

import org.apache.logging.log4j._
import play.api.mvc._
import utils.{LogQueryBuilder, DatabaseService}
import play.api.data.Form
import play.api.data.Forms._
import models.{LogDAO, Log}
import com.mongodb.casbah.commons.MongoDBObject
import scala.collection.immutable.StringOps

object Search extends Controller with Access with DatabaseService {

  val logger: Logger = LogManager.getLogger("Logic")

  def index = Action { implicit request =>
    Ok(views.html.index(loginForm, registerForm, searchForm))
  }

  def search(search: String) = Action {
    println("search:" + search)
    val stringArray = search.split(":::")
    val jsonBuilder = StringBuilder.newBuilder
    val queryObject = LogQueryBuilder.apply(Option(stringArray(0)), Option(stringArray(1)), Option(stringArray(2)), Option(stringArray(3)),
      Option(stringArray(4)), Option(stringArray(5)), Option(stringArray(6)), Option(stringArray(7))).buildQuery()

    println(queryObject)

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