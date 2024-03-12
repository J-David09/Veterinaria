package controllers

import models.{AdminUser, Cliente}
import play.api.libs.json.Json
import play.api.mvc._
import repository.{AdminUserRepository, ClienteRepository}

import javax.inject.Inject
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class ClienteController @Inject()(cc: ControllerComponents, clienteRepository: ClienteRepository )
  extends AbstractController(cc){

  implicit var serializador = Json.format[Cliente]

  def getOne (id: String) = Action.async {
    try{
      val clientId = id.toInt
      clienteRepository.getOne(clientId).map(client => {
        val jsonReport = Json.obj(
          "data" -> client,
          "message" -> "get one client succesfully."
        )
        Ok(jsonReport)
      })
    }
  }

  def getAll () = Action.async {
    clienteRepository.getAll.map(clients => {
      val jsonReport = Json.obj(
        "data" -> clients,
        "message" -> "get all client succesfully."
      )
      Ok(jsonReport)
    })
  }

  def create = Action.async(parse.json) {request =>
    val validador = request.body.validate[Cliente]

    validador.asEither match {
      case Left(error) => Future.successful(BadRequest(error.toString()))
      case Right(cliente) => {
        clienteRepository.create(cliente).map(client => {
          val jsonReport = Json.obj(
            "data" -> client,
            "message" -> "create client succesfully."
          )
          Ok(jsonReport)
        })
      }
    }
  }

  def update (id: String) = Action.async(parse.json) {request =>
    val validador = request.body.validate[Cliente]
    try{
      val clientId = id.toInt
      validador.asEither match {
        case Left(error) => Future.successful(BadRequest(error.toString()))
        case Right(cliente) => {
          clienteRepository.update(clientId, cliente).map(client => {
            val jsonReport = Json.obj(
              "data" -> client,
              "message" -> "update client succesfully."
            )
            Ok(jsonReport)
          })
        }
      }
    }

  }

  def delete (id:String)= Action.async {
    try{
      val clientId = id.toInt
      clienteRepository.delete(clientId).map(client => {
        val jsonReport = Json.obj(
          "data" -> client,
          "message" -> "get one client succesfully."
        )
        Ok(jsonReport)
      })
    }
  }

}
