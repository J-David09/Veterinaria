package models

import slick.lifted.Tag
import slick.jdbc.SQLiteProfile.api._

class MascotaTable(tag: Tag) extends Table[Mascota](tag, "mascota") {
  def id = column[Int]("id", O.PrimaryKey)
  def nombre = column[String]("nombre")
  def raza = column[String]("raza")
  def edad = column[Int]("edad")
  def peso = column[Double]("peso")
  def idCliente = column[Int]("idCliente")

  def idClienteFK = foreignKey("client_fk", idCliente, TableQuery[ClienteTable])(_.id)
  def * = (id, nombre, raza, edad, peso, idCliente) <> (Mascota.tupled, Mascota.unapply)
}