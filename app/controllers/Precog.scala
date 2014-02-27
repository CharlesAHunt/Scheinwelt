package controllers

import play.api.mvc._
import akka.actor.{Props, ActorSystem}
import utils.{Master, Listener, Calculate}

object Precog extends Controller with Access {

  def index = Action {  implicit request =>
    calc()
    Ok(views.html.precog(loginForm, registerForm))
  }

  def calc() = {
    calculate(nrOfWorkers = 5, nrOfElements = 10000, nrOfMessages = 10000)

    def calculate(nrOfWorkers: Int, nrOfElements: Int, nrOfMessages: Int) {

      val system = ActorSystem("PiSystem")

      // create the result listener, will print the result and shutdown the system
      val listener = system.actorOf(Props[Listener], name = "listener")

      val master = system.actorOf(Props(new Master(
        nrOfWorkers, nrOfMessages, nrOfElements, listener)),
        name = "master")

      // start the calculation
      master ! Calculate

    }
  }

}
