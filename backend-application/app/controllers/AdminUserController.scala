package controllers

import models.AdminUser
import play.api.libs.json.Json
import play.api.mvc._
import repository.AdminUserRepository

import javax.inject.Inject
import scala.concurrent.ExecutionContext.Implicits.global

class AdminUserController @Inject()( cc: ControllerComponents, adminUserRepository: AdminUserRepository)
  extends AbstractController(cc){

  implicit var serializador = Json.format[AdminUser]

  def login (user: String, password: String) = Action.async{
    adminUserRepository.login(user, password).map {
      case Some(user) =>
        val jsonReports = Json.obj(
          "data" -> user,
          "message" -> "Successfully retrieved reports."
        )
        Ok(jsonReports)
      case None =>
        NotFound(Json.obj("data" -> null, "message" -> "User or password invalid"))
    }
  }

}
