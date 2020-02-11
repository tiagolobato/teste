package com.app.apiFitness.controller

import com.app.apiFitness.controller.dto.request.UserRequestDTO
import com.app.apiFitness.controller.dto.response.StandardResponseDTO
import com.app.apiFitness.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/signup")
class SignupController {

    @Autowired
    private lateinit var userService: UserService

    @PostMapping
    fun signup(@RequestBody user: UserRequestDTO): ResponseEntity<StandardResponseDTO> {
        val userCreated = userService.create(user)
        return ResponseEntity.created(URI("")).body(StandardResponseDTO(0,""))
    }
}