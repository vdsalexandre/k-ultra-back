package com.vds.kultraback.domain.model

import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type

@Entity
@Table(name = "GAME")
data class Game(
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    val id: UUID,
    val title: String,
    val price: BigDecimal,
    val publisher: Long,
    val tags: String,
    val releaseDate: LocalDate?
)
