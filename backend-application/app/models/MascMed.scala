package models
import java.util.UUID

case class MascMed  (
                    id: Option[String] = Option(UUID.randomUUID().toString),
                    idMedicamento: Int,
                    idMascota : Int,
                    dosis: String
                    )
