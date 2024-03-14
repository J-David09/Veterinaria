package repository

import models.{MascMed, MascMedTable, Mascota, MascotaTable}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.mvc.{AbstractController, ControllerComponents}
import slick.jdbc.JdbcProfile
import slick.lifted.TableQuery
import slick.jdbc.SQLiteProfile.api._

import javax.inject.Inject
import scala.concurrent.ExecutionContext

class MascMedRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider, cc: ControllerComponents)
                                 (implicit  ec: ExecutionContext)
  extends AbstractController(cc)
    with HasDatabaseConfigProvider[JdbcProfile] {

  private lazy val mascMedQuery = TableQuery[MascMedTable]

  def getOne (id: String) = {
    val mascMedById = mascMedQuery.filter(_.id === id)
    db.run(mascMedById.result.headOption)
  }

  def getAll = {
    val mascMedById = mascMedQuery.sortBy(_.id)
    db.run(mascMedById.result)
  }

  def create (mascMed: MascMed) = {
    val mascotaById = mascMedQuery.filter(_.id === mascMed.id)
    db.run(mascotaById.result.headOption).flatMap{
      case Some(_) => db.run(mascotaById.result.headOption)
      case None =>
        val insert = mascMedQuery += mascMed
        db.run(insert).flatMap(_ => getOne(mascMed.id))
    }
  }

  def update (id: Int, mascota: Mascota) = {
    val mascotaById = mascMedQuery.filter(_.id === mascota.id && mascota.id.equals(id))
    val update = mascotaById.update(mascota)
    db.run(update)
      .flatMap(_ => db.run(mascotaById.result.headOption))
  }

  def delete (id: Int) = {
    val mascotaById = mascMedQuery.filter(_.id === id)
    for {
      objeto <- db.run(mascotaById.result.headOption)
      _ <- db.run(mascotaById.delete)
    } yield objeto
  }

}