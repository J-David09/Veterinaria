package controllers

import models.{Cliente, Mascota}
import play.api.libs.json.Json
import play.api.mvc._
import repository.{ClienteRepository, MascotaRepository}

import javax.inject.Inject
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class MascotaController @Inject()(cc: ControllerComponents, mascotaRepository: MascotaRepository )
  extends AbstractController(cc){

  implicit var serializador = Json.format[Mascota]

  def getOne (id: String) = Action.async {
    try{
      val mascotaId = id.toInt
      mascotaRepository.getOne(mascotaId).map(mascota => {
        val jsonReport = Json.obj(
          "data" -> mascota,
          "message" -> "get one mascota succesfully."
        )
        Ok(jsonReport)
      })
    }
  }

  def getAll () = Action.async {
    mascotaRepository.getAll.map(mascotas => {
      val jsonReport = Json.obj(
        "data" -> mascotas,
        "message" -> "get all mascotas succesfully."
      )
      Ok(jsonReport)
    })
  }

  def create = Action.async(parse.json) {request =>
    val validador = request.body.validate[Mascota]

    validador.asEither match {
      case Left(error) => Future.successful(BadRequest(error.toString()))
      case Right(mascota) => {
        mascotaRepository.create(mascota).map(mascota => {
          val jsonReport = Json.obj(
            "data" -> mascota,
            "message" -> "create mascota succesfully."
          )
          Ok(jsonReport)
        })
      }
    }
  }

  def update (id: String) = Action.async(parse.json) {request =>
    val validador = request.body.validate[Mascota]
    try{
      val MascotaId = id.toInt
      validador.asEither match {
        case Left(error) => Future.successful(BadRequest(error.toString()))
        case Right(mascota) => {
          mascotaRepository.update(MascotaId, mascota).map(mascota => {
            val jsonReport = Json.obj(
              "data" -> mascota,
              "message" -> "update mascota succesfully."
            )
            Ok(jsonReport)
          })
        }
      }
    }

  }

  def delete (id:String)= Action.async {
    try{
      val mascotaId = id.toInt
      mascotaRepository.delete(mascotaId).map(mascota => {
        val jsonReport = Json.obj(
          "data" -> mascota,
          "message" -> "get one mascota succesfully."
        )
        Ok(jsonReport)
      })
    }
  }

}
