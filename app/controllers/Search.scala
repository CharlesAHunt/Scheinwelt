package controllers

import org.apache.logging.log4j._
import play.api.mvc._
import utils.{EncryptionUtil, DatabaseService}
import play.api.data.Form
import play.api.data.Forms._
import models.{LogDAO, Log}
import com.mongodb.casbah.commons.MongoDBObject
import com.mongodb.casbah.commons.ValidBSONType.BSONTimestamp
import org.bson.types.BSONTimestamp

object Search extends Controller with Access with DatabaseService {

  val logger: Logger = LogManager.getLogger("Logic");

  def index = Action { implicit request =>
    Ok(views.html.index(loginForm, registerForm, searchForm))
  }

  def search(id : String) = Action { implicit request =>
    Ok("""{"id":"27"}""").as(JSON)
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
    logger.error("Hello, World!")
    val a = MongoDBObject("NoClassDefFound" -> "ERROR", "message" -> "Class definition not found at runtime",
      "level" -> "ERROR", "trace" -> "stacktrace", "timestamp" -> new BSONTimestamp())

    getCollection("users").insert(a)
  }

}
