package models
import java.util.UUID

case class MascMed  (
                    id: Option[Int] = None,
                    idMedicamento: Int,
                    idMascota : Int,
                    dosis: String
                    )
