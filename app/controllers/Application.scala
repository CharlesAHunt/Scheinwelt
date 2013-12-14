package controllers

import play.api.mvc.{Action, Controller}
import utils.{EncryptionUtil, DatabaseService}
import play.api.data.Form
import models.User
import play.api.data.Forms._
import models.User
import com.mongodb.casbah.commons.MongoDBObject
import com.mongodb.casbah.Imports


object Application extends Controller with DatabaseService {

  val createForm: Form[User] = Form(
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

//  def create = Action { implicit request =>
//    createForm.bindFromRequest.fold(
//      errors => BadRequest(views.html.index(create)),
//      user => {
//        val a = MongoDBObject("username" -> user.username, "password" -> EncryptionUtil.encrypt(user.password))
//        getCollection("users").insert(a)
//      }
//    )
//
//    Redirect("/index").flashing(
//      "success" -> "You have successfully registered. You may now log in."
//    )
//  }
//
//  def view() {
//    Ok(views.html.compensation.investmentcategory.view.render(Access.loginForm, getAll(), createForm))
//  }
//
//  def delete(String id) {
//    getCollection("users").removeById(id)
//
//    redirect("/investmentcategory/view");
//  }

  def getAll(): List[Imports.DBObject] = {
    getCollection("users").find().toList
  }


}
