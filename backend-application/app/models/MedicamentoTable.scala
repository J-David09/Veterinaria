package models

import slick.lifted.Tag
import slick.jdbc.SQLiteProfile.api._

class MedicamentoTable(tag: Tag) extends Table[Medicamento](tag, "medicamento") {
  def id = column[Int]("id", O.PrimaryKey)
  def nombre = column[String]("nombre")
  def descripcion = column[String]("descripcion")

  def * =(id, nombre, descripcion) <> (Medicamento.tupled, Medicamento.unapply)
}
