package com.vds.kultraback.api.resources

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/game"], produces = ["application/json"])
class GameResource {
}