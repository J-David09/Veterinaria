package models

import slick.lifted.Tag

import slick.jdbc.SQLiteProfile.api._

class MascMedTable(tag: Tag) extends Table[MascMed](tag, "mascmed") {
  def id = column[String]("id", O.PrimaryKey)
  def idMedicamento = column[Int]("idMedicamento")
  def idMascota = column[Int] ("idMascota")
  def dosis = column[String] ("dosis")

  def * = (id.?, idMedicamento, idMascota, dosis) <> (MascMed.tupled, MascMed.unapply)
}
