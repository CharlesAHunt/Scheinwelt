package controllers

import play.api.mvc._
import akka.actor.{Props, ActorSystem}
import utils._
import scala.concurrent.{Await, Future}
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._
import play.api.libs.concurrent.Execution.Implicits._

object Precog extends Controller with Access {

  def index = Action {  implicit request =>
    calcDangerRating()
    calcPi()
    Ok(views.html.precog(loginForm, registerForm))
  }

  def calcDangerRating() = {
    calculate(nrOfWorkers = 5, nrOfMessages = 1000)

    def calculate(nrOfWorkers: Int, nrOfMessages: Int) {

      val system = ActorSystem("LogSystem")

      // create the result listener, will print the result and shutdown the system
      val listener = system.actorOf(Props[Listener], name = "listener")

      val master = system.actorOf(Props(new Master( nrOfWorkers, nrOfMessages, listener )),
        name = "master")

      // start the calculation
      master ! Calculate
    }
  }

  def calcPi() = {
    calculate(nrOfWorkers = 5, nrOfElements = 500, nrOfMessages = 1000)

    def calculate(nrOfWorkers: Int, nrOfElements: Int, nrOfMessages: Int) {
      val system = ActorSystem("PiSystem")
      val listener = system.actorOf(Props[PiListener], name = "Pilistener")
      val master = system.actorOf(Props(new PiMaster(
        nrOfWorkers, nrOfMessages, nrOfElements, listener)),
        name = "master")

      implicit val timeout = Timeout(5 seconds)
      val future = master ! PiCalculate



    }

  }

}
