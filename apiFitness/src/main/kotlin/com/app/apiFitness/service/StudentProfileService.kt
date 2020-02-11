package com.app.apiFitness.service

import com.app.apiFitness.controller.dto.request.UserRequestDTO
import com.app.apiFitness.database.repository.StudentRepository
import com.app.apiFitness.database.repository.TeacherRepository
import com.app.apiFitness.database.repository.UserRepository
import com.app.apiFitness.database.repository.entity.StudentEntity
import com.app.apiFitness.database.repository.entity.TeacherEntity
import com.app.apiFitness.database.repository.entity.UserEntity
import com.app.apiFitness.exceptions.BusinessException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Required
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException
import javax.persistence.NonUniqueResultException


interface StudentProfileService {


}