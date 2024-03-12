package repository

import models.{Cliente, ClienteTable}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.mvc.{AbstractController, ControllerComponents}
import slick.jdbc.JdbcProfile
import slick.lifted.TableQuery
import slick.jdbc.SQLiteProfile.api._

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class ClienteRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider, cc: ControllerComponents)
                                 (implicit  ec: ExecutionContext)
  extends AbstractController(cc)
    with HasDatabaseConfigProvider[JdbcProfile] {

  private lazy val clienteQuery = TableQuery[ClienteTable]

  def getOne (id: Int) = {
    val clientById = clienteQuery.filter(_.id === id)
    db.run(clientById.result.headOption)
  }

  def getAll = {
    val clientById = clienteQuery.sortBy(_.id)
    db.run(clientById.result)
  }

  def create (cliente: Cliente) = {
    val clientById = clienteQuery.filter(_.id === cliente.id)
    db.run(clientById.result.headOption).flatMap{
      case Some(_) => db.run(clientById.result.headOption)
      case None =>
        val insert = clienteQuery += cliente
        db.run(insert).flatMap(_ => getOne(cliente.id))
    }
  }

  def update (id: Int, cliente: Cliente) = {
    val clientById = clienteQuery.filter(_.id === cliente.id && cliente.id.equals(id))
    val update = clientById.update(cliente)
    db.run(update)
      .flatMap(_ => db.run(clientById.result.headOption))
  }

  def delete (id: Int) = {
    val clientById = clienteQuery.filter(_.id === id)
    for {
      objeto <- db.run(clientById.result.headOption)
      _ <- db.run(clientById.delete)
    } yield objeto
  }

}
