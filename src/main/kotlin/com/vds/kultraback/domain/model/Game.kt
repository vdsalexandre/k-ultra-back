package com.vds.kultraback.domain.model

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date

object Game : Table() {
    val id = long(name = "id")
    val title = varchar(name = "title", length = 255)
    val price = decimal(name = "price", precision = 2, scale = 2)
    val publisher = reference(name = "publisher", refColumn = Publisher.id)
    val tags = text(name = "tags")
    val releaseDate = date(name = "releaseDate")
}
