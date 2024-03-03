package models

import slick.lifted.Tag
import slick.jdbc.SQLiteProfile.api._

class ClienteTable(tag: Tag) extends Table[Cliente](tag, "cliente") {
  def id = column[Int]("id", O.PrimaryKey)
  def nombre = column[String]("nombre")
  def apellido = column[String]("apellido")
  def direccion = column[String]("direccion")
  def telefono = column[String]("telefono")

  def * = (id, nombre, apellido, direccion, telefono) <> (Cliente.tupled, Cliente.unapply)
}
