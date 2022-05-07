package com.vds.kultraback.domain.model

import org.jetbrains.exposed.sql.Table

object Publisher : Table() {
    val id = long(name = "id")
    val name = varchar(name = "name", length = 255)
    val siret = long(name = "siret")
    val phone = varchar(name = "phone", length = 50)
}
