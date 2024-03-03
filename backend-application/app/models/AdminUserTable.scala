package models

import slick.lifted.Tag

import slick.jdbc.SQLiteProfile.api._

class AdminUserTable (tag: Tag) extends Table[AdminUser](tag, "adminUser") {
  def id = column[Int]("id", O.PrimaryKey)
  def userName = column[String] ("username")
  def password = column[String] ("password")

  def * =(id, userName, password) <> (AdminUser.tupled, AdminUser.unapply)

}
