package repository

import models.{Mascota, MascotaTable}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.mvc.{AbstractController, ControllerComponents}
import slick.jdbc.JdbcProfile
import slick.lifted.TableQuery
import slick.jdbc.SQLiteProfile.api._

import javax.inject.Inject
import scala.concurrent.ExecutionContext

class MascotaRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider, cc: ControllerComponents)
                                 (implicit  ec: ExecutionContext)
  extends AbstractController(cc)
    with HasDatabaseConfigProvider[JdbcProfile] {

  private lazy val mascotaQuery = TableQuery[MascotaTable]

  def getOne (id: Int) = {
    val mascotaById = mascotaQuery.filter(_.id === id)
    db.run(mascotaById.result.headOption)
  }

  def getAll = {
    val mascotaById = mascotaQuery.sortBy(_.id)
    db.run(mascotaById.result)
  }

  def create (mascota: Mascota) = {
    val mascotaById = mascotaQuery.filter(_.id === mascota.id)
    db.run(mascotaById.result.headOption).flatMap{
      case Some(_) => db.run(mascotaById.result.headOption)
      case None =>
        val insert = mascotaQuery += mascota
        db.run(insert).flatMap(_ => getOne(mascota.id))
    }
  }

  def update (id: Int, mascota: Mascota) = {
    val mascotaById = mascotaQuery.filter(_.id === mascota.id && mascota.id.equals(id))
    val update = mascotaById.update(mascota)
    db.run(update)
      .flatMap(_ => db.run(mascotaById.result.headOption))
  }

  def delete (id: Int) = {
    val mascotaById = mascotaQuery.filter(_.id === id)
    for {
      objeto <- db.run(mascotaById.result.headOption)
      _ <- db.run(mascotaById.delete)
    } yield objeto
  }

}