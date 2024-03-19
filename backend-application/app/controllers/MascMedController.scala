package controllers

import models.{MascMed, Medicamento}
import play.api.libs.json.Json
import play.api.mvc._
import repository.{MascMedRepository, MedicamentoRepository}

import javax.inject.Inject
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class MascMedController @Inject()(cc: ControllerComponents, mascMedRepository: MascMedRepository )
  extends AbstractController(cc){

  implicit var serializador = Json.format[MascMed]

//  def getOne (id: String) = Action.async {
//    try{
//      val medicamentoId = id.toInt
//      mascMedRepository.getOne(medicamentoId).map(medicamento => {
//        val jsonReport = Json.obj(
//          "data" -> medicamento,
//          "message" -> "get one medicamento succesfully."
//        )
//        Ok(jsonReport)
//      })
//    }
//  }
//
  def getAll () = Action.async {
    mascMedRepository.getAll.map(mascMed => {
      val jsonReport = Json.obj(
        "data" -> mascMed,
        "message" -> "get all mascMed succesfully."
      )
      Ok(jsonReport)
    })
  }

  def create = Action.async(parse.json) {request =>
    val validador = request.body.validate[MascMed]

    validador.asEither match {
      case Left(error) => Future.successful(BadRequest(error.toString()))
      case Right(mascMed) => {
        mascMedRepository.create(mascMed).map(mascMed => {
          val jsonReport = Json.obj(
            "data" -> mascMed,
            "message" -> "create mascMed succesfully."
          )
          Ok(jsonReport)
        })
      }
    }
  }

  def getByMascota (id: String) = Action.async {
    try{
      val mascotaId = id.toInt
      mascMedRepository.getByMascota(mascotaId).map(mascMeds => {
        val jsonReport = Json.obj(
          "data" -> mascMeds,
          "message" -> "get mascMed by Mascota succesfully."
        )
        Ok(jsonReport)
      })
    }
  }
//
//  def update (id: String) = Action.async(parse.json) {request =>
//    val validador = request.body.validate[Medicamento]
//    try{
//      val medicamentoId = id.toInt
//      validador.asEither match {
//        case Left(error) => Future.successful(BadRequest(error.toString()))
//        case Right(medicamento) => {
//          mascMedRepository.update(medicamentoId, medicamento).map(medicamento => {
//            val jsonReport = Json.obj(
//              "data" -> medicamento,
//              "message" -> "update medicamento succesfully."
//            )
//            Ok(jsonReport)
//          })
//        }
//      }
//    }
//
//  }
//
//  def delete (id:String)= Action.async {
//    try{
//      val medicamentoId = id.toInt
//      mascMedRepository.delete(medicamentoId).map(medicamento => {
//        val jsonReport = Json.obj(
//          "data" -> medicamento,
//          "message" -> "get one medicamento succesfully."
//        )
//        Ok(jsonReport)
//      })
//    }
//  }

}
