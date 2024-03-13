package controllers

import models.{Mascota, Medicamento}
import play.api.libs.json.Json
import play.api.mvc._
import repository.{MascotaRepository, MedicamentoRepository}

import javax.inject.Inject
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class MedicamentoController @Inject()(cc: ControllerComponents, medicamentoRepository: MedicamentoRepository )
  extends AbstractController(cc){

  implicit var serializador = Json.format[Medicamento]

  def getOne (id: String) = Action.async {
    try{
      val medicamentoId = id.toInt
      medicamentoRepository.getOne(medicamentoId).map(medicamento => {
        val jsonReport = Json.obj(
          "data" -> medicamento,
          "message" -> "get one medicamento succesfully."
        )
        Ok(jsonReport)
      })
    }
  }

  def getAll () = Action.async {
    medicamentoRepository.getAll.map(medicamento => {
      val jsonReport = Json.obj(
        "data" -> medicamento,
        "message" -> "get all medicamento succesfully."
      )
      Ok(jsonReport)
    })
  }

  def create = Action.async(parse.json) {request =>
    val validador = request.body.validate[Medicamento]

    validador.asEither match {
      case Left(error) => Future.successful(BadRequest(error.toString()))
      case Right(medicamento) => {
        medicamentoRepository.create(medicamento).map(medicamento => {
          val jsonReport = Json.obj(
            "data" -> medicamento,
            "message" -> "create medicamento succesfully."
          )
          Ok(jsonReport)
        })
      }
    }
  }

  def update (id: String) = Action.async(parse.json) {request =>
    val validador = request.body.validate[Medicamento]
    try{
      val medicamentoId = id.toInt
      validador.asEither match {
        case Left(error) => Future.successful(BadRequest(error.toString()))
        case Right(medicamento) => {
          medicamentoRepository.update(medicamentoId, medicamento).map(medicamento => {
            val jsonReport = Json.obj(
              "data" -> medicamento,
              "message" -> "update medicamento succesfully."
            )
            Ok(jsonReport)
          })
        }
      }
    }

  }

  def delete (id:String)= Action.async {
    try{
      val medicamentoId = id.toInt
      medicamentoRepository.delete(medicamentoId).map(medicamento => {
        val jsonReport = Json.obj(
          "data" -> medicamento,
          "message" -> "get one medicamento succesfully."
        )
        Ok(jsonReport)
      })
    }
  }

}
