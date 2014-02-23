package controllers

import org.apache.logging.log4j._
import play.api.mvc._
import utils.{LogQueryBuilder, DatabaseService}
import models.{ApplicationDAO, Application, LogDAO, Log}
import com.mongodb.casbah.commons.MongoDBObject
import scala.io.Source
import org.bson.types.ObjectId

object Search extends Controller with Access with DatabaseService {

  val logger: Logger = LogManager.getLogger("Logic")

  def index = Action { implicit request =>
    Ok(views.html.index(loginForm, registerForm))
  }

  def search(search: String) = Action {
    val stringArray = search.split(":::")
    val jsonBuilder = StringBuilder.newBuilder
    val applicationQuery = LogQueryBuilder.apply(Option(stringArray(0).trim), Option(stringArray(1).trim), Option(stringArray(2).trim),None,None,None,None,None,None).buildQuery()

    val app = ApplicationDAO.findOne(applicationQuery)

    val application: Application = app match {
      case Some(a: Application) => app.get
      case None => generateLogs()
      case _ => generateLogs()
    }

    val logQuery = LogQueryBuilder.apply(None, None, None, Option(stringArray(3).trim), Option(stringArray(4).trim),
      Option(stringArray(5).trim), Option(stringArray(6).trim), Option(stringArray(7).trim), Option(application._id.toString)).buildQuery()
    println(logQuery)
    jsonBuilder.append("[")
    getCollection("logs").find(logQuery).foreach( jsonBuilder.append(_).append(",") )
    jsonBuilder.deleteCharAt(jsonBuilder.length-1)
    jsonBuilder.append("]")

    Ok(jsonBuilder.toString())
  }

  def about = Action { implicit request =>
    Ok(views.html.about(loginForm, registerForm))
  }

  def searchResults(): List[Log] = {
    LogDAO.find(ref = MongoDBObject()).toList
  }

  def generateLogs() = {
    var logIdList: List[String] = List()
    val appId = new ObjectId
    val file = Source.fromFile("public/logs.txt")

    val logBuilder = StringBuilder.newBuilder
    file.getLines().foreach( line => if(!line.startsWith("2014-0")) logBuilder.append(line) else logBuilder.append("::-::").append(line))
    val logGroups = logBuilder.toString().split("::-::")

    //use a bulk query builder here next time....
    for(x : String <- logGroups) {
      try {
        val date = x.substring(0,10)
        val time = x.substring(11,19)
        val level = x.substring(25,30)
        val message = x.substring(31,x.length-1)

        val log = MongoDBObject("application" -> appId.toString, "message" -> message,
          "level" -> level, "trace" -> "stacktrace...", "date" -> date ,"time" -> time)

        getCollection("logs").insert(log)

        val id = log.get("_id").toString

        logIdList = id :: logIdList
      }
      catch {
        case e: Exception =>
      }
    }

    val application = Application(_id = appId, name = "Pay & Statements", environment = "STA", region = "1", logList = logIdList)
    ApplicationDAO.insert(application)
    application
  }

}