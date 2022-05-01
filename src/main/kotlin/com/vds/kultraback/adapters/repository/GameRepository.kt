package com.vds.kultraback.adapters.repository

import com.vds.kultraback.domain.model.Game
import java.util.UUID
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface GameRepository: CrudRepository<Game, UUID>