package com.app.apiFitness.service.impl

import com.app.apiFitness.controller.dto.request.CreateTeacherHasStudentRequestDTO
import com.app.apiFitness.controller.dto.request.SearchTeacherStudentsRequestDTO
import com.app.apiFitness.controller.dto.request.TeacherProfileRequestDTO
import com.app.apiFitness.database.repository.TeacherHasStudentRepository
import com.app.apiFitness.database.repository.TeacherRepository
import com.app.apiFitness.database.repository.UserRepository
import com.app.apiFitness.database.repository.entity.*
import com.app.apiFitness.exceptions.BusinessException
import com.app.apiFitness.model.UserProfileModel
import com.app.apiFitness.service.TeacherProfileService
import org.hibernate.Hibernate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TeacherProfileServiceImpl @Autowired constructor(
        private val teacherHasStudentRepository: TeacherHasStudentRepository,
        private val userRepository: UserRepository,
        private val teacherRepository: TeacherRepository,
        private val bCryptPasswordEncoder: BCryptPasswordEncoder): TeacherProfileService {

    override fun create(teacherProfileRequestDTO: TeacherProfileRequestDTO) {
        val emailIsValid = verifyIfEmailIsValid(teacherProfileRequestDTO.user.email)
        if (emailIsValid) {
            createTeacher(createUser(teacherProfileRequestDTO).id,teacherProfileRequestDTO.CREF)
        }
        else{
            throw BusinessException("O email passado já está sendo utilizado")
        }
    }

    override fun verifyIfEmailIsValid(email: String): Boolean {
        return userRepository.findByEmail(email) == null
    }

    override fun createUser(teacherProfileRequestDTO: TeacherProfileRequestDTO): UserEntity {
        val userEntity = UserEntity(teacherProfileRequestDTO.user)
        userEntity.password = bCryptPasswordEncoder.encode(userEntity.password)
        return userRepository.save(userEntity)
    }

    override fun createTeacher(userId: Long?,CREF: String?): TeacherEntity {
        val teacherEntity = TeacherEntity()
        teacherEntity.userId = userId
        teacherEntity.CREF = CREF
        return teacherRepository.save(teacherEntity)
    }

    override fun searchTeacherStudents(searchTeacherStudentsRequestDTO : SearchTeacherStudentsRequestDTO): List<UserProfileModel>? {
        var response = userRepository.findById(searchTeacherStudentsRequestDTO.userId)
        if(response != null){
            throw  BusinessException("Nenhum professor encontrado")
        }
        else{
            return response.get().refTeacherEntities?.refTeacherHasStudentEntities?.stream()?.map { x->
                UserProfileModel(Hibernate.unproxy(Hibernate.unproxy(x.refStudentEntity, StudentEntity::class.java).refUserEntity!!,UserEntity::class.java))
            }?.collect(Collectors.toList());
        }
    }

    override fun createTeacherHasStudent(createTeacherHasStudentRequestDTO: CreateTeacherHasStudentRequestDTO) {
        var teacherHasStudentEntity = TeacherHasStudentEntity()
        teacherHasStudentEntity.studentId = createTeacherHasStudentRequestDTO.studentId
        teacherHasStudentEntity.teacherId = createTeacherHasStudentRequestDTO.teacherId
        teacherHasStudentRepository.save(teacherHasStudentEntity)
    }
}