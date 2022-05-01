package com.vds.kultraback.domain.model

//@Entity
//@Table(name = "PUBLISHER")
data class Publisher(
    val id: Long,
    val name: String,
    val siret: Long,
    val phone: String
)
