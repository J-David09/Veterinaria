package repository

import models.AdminUserTable
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.mvc.{AbstractController, ControllerComponents}
import slick.jdbc.JdbcProfile
import slick.lifted.TableQuery
import slick.jdbc.SQLiteProfile.api._

import javax.inject.Inject
import scala.concurrent.ExecutionContext

class AdminUserRepository @Inject() (protected val dbConfigProvider: DatabaseConfigProvider,  cc: ControllerComponents)
                                    (implicit  ec: ExecutionContext)
  extends AbstractController(cc)
    with HasDatabaseConfigProvider[JdbcProfile]{

  private lazy val adminUserQuery = TableQuery[AdminUserTable]

  def login (userName: String, password: String) = {
    val adminQuery = adminUserQuery.filter(_.userName === userName)
    val userWithMatchingPassword = adminQuery.result.headOption.flatMap {
      case Some(user) if user.password == password => DBIO.successful(Some(user))
      case _ => DBIO.successful(None)
    }
    db.run(userWithMatchingPassword)
  }

}
