package com.vds.kultraback.model

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable

object Publishers : LongIdTable() {
    val name = varchar(name = "name", length = 255)
    val siret = long(name = "siret")
    val phone = varchar(name = "phone", length = 50)
}

class PublisherEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<PublisherEntity>(Publishers)

    var name by Publishers.name
    var siret by Publishers.siret
    var phone by Publishers.phone

    fun toPublisher() = Publisher(id.value, name, siret, phone)
}

data class Publisher(
    val id: Long,
    val name: String,
    val siret: Long,
    val phone: String
)
