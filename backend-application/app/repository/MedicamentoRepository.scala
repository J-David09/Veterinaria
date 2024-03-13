package repository

import models.{Medicamento, MedicamentoTable}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.mvc.{AbstractController, ControllerComponents}
import slick.jdbc.JdbcProfile
import slick.lifted.TableQuery
import slick.jdbc.SQLiteProfile.api._

import javax.inject.Inject
import scala.concurrent.ExecutionContext

class MedicamentoRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider, cc: ControllerComponents)
                                 (implicit  ec: ExecutionContext)
  extends AbstractController(cc)
    with HasDatabaseConfigProvider[JdbcProfile] {

  private lazy val medicamentoQuery = TableQuery[MedicamentoTable]

  def getOne (id: Int) = {
    val medicamentoById = medicamentoQuery.filter(_.id === id)
    db.run(medicamentoById.result.headOption)
  }

  def getAll = {
    val medicamentoById = medicamentoQuery.sortBy(_.id)
    db.run(medicamentoById.result)
  }

  def create (medicamento: Medicamento) = {
    val medicamentoById = medicamentoQuery.filter(_.id === medicamento.id)
    db.run(medicamentoById.result.headOption).flatMap{
      case Some(_) => db.run(medicamentoById.result.headOption)
      case None =>
        val insert = medicamentoQuery += medicamento
        db.run(insert).flatMap(_ => getOne(medicamento.id))
    }
  }

  def update (id: Int, medicamento: Medicamento) = {
    val medicamentoById = medicamentoQuery.filter(_.id === medicamento.id && medicamento.id.equals(id))
    val update = medicamentoById.update(medicamento)
    db.run(update)
      .flatMap(_ => db.run(medicamentoById.result.headOption))
  }

  def delete (id: Int) = {
    val medicamentoById = medicamentoQuery.filter(_.id === id)
    for {
      objeto <- db.run(medicamentoById.result.headOption)
      _ <- db.run(medicamentoById.delete)
    } yield objeto
  }
}
