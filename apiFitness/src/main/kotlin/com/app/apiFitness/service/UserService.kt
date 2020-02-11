package com.app.apiFitness.service

import com.app.apiFitness.controller.dto.request.UserRequestDTO
import com.app.apiFitness.database.repository.UserRepository
import com.app.apiFitness.database.repository.entity.UserEntity
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService {

    @Autowired
    private lateinit var userRepository: UserRepository

    @Autowired
    private lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder

    fun create(user: UserRequestDTO): UserEntity {
        var userEntity = UserEntity();
        userEntity.password = bCryptPasswordEncoder.encode(userEntity.password)
        return userRepository.save(userEntity)
    }
}