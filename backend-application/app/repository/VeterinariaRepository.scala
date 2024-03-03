package repository

import models.{AdminUserTable, ClienteTable, MascMedTable, MascotaTable, MedicamentoTable}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.mvc.{AbstractController, ControllerComponents}
import slick.jdbc.JdbcProfile
import slick.lifted.TableQuery
import slick.jdbc.MySQLProfile.api._

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class VeterinariaRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider, cc: ControllerComponents)
                                     (implicit  ec: ExecutionContext)
  extends AbstractController(cc)
    with HasDatabaseConfigProvider[JdbcProfile] {

  private lazy val adminUserQuery = TableQuery[AdminUserTable]
  private lazy val clienteQuery = TableQuery[ClienteTable]
  private lazy val mascMedQuery = TableQuery[MascMedTable]
  private lazy val mascotaQuery = TableQuery[MascotaTable]
  private lazy val medicamentoQuery = TableQuery[MedicamentoTable]


  def dbInit: Future[Unit] = {
    val createUsersSchema = adminUserQuery.schema.createIfNotExists
    db.run(createUsersSchema)

    val createClienteSchema = clienteQuery.schema.createIfNotExists
    db.run(createClienteSchema)

    val createMascMedSchema = mascMedQuery.schema.createIfNotExists
    db.run(createMascMedSchema)

    val createMascotaSchema = mascotaQuery.schema.createIfNotExists
    db.run(createMascotaSchema)

    val createMedicamentoSchema = medicamentoQuery.schema.createIfNotExists
    db.run(createMedicamentoSchema)
  }
}
