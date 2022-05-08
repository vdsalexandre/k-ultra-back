package com.vds.kultraback.adapters.repository

import org.jetbrains.exposed.sql.Database
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component

@Component
class DbConnect {

    @Autowired
    lateinit var environment: Environment

    fun getConnection(): Database {
        return Database.connect(getUrl(), getDriver(), getUser(), getPassword())
    }

    private fun getUrl(): String {
        return environment.getProperty("spring.datasource.url")!!
    }

    private fun getDriver(): String {
        return environment.getProperty("spring.datasource.driver-class-name")!!
    }

    private fun getUser(): String {
        return environment.getProperty("spring.datasource.username")!!
    }

    private fun getPassword(): String {
        return environment.getProperty("spring.datasource.password")!!
    }
}