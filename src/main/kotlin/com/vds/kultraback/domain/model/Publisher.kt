package com.vds.kultraback.domain.model

import org.jetbrains.exposed.dao.id.LongIdTable

object Publisher : LongIdTable() {
    val publisherId = long(name = "id").uniqueIndex()
    val name = varchar(name = "name", length = 255)
    val siret = long(name = "siret")
    val phone = varchar(name = "phone", length = 50)
}
