package repository

import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.mvc.{AbstractController, ControllerComponents}
import slick.jdbc.JdbcProfile
import slick.lifted.TableQuery

import javax.inject.Inject
import scala.concurrent.{ExecutionContext, Future}

class ClienteRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider, cc: ControllerComponents)
                                 (implicit  ec: ExecutionContext)
  extends AbstractController(cc)
    with HasDatabaseConfigProvider[JdbcProfile] {


}
