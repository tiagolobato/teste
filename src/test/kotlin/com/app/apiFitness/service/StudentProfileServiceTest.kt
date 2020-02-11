package com.app.apiFitness.service

import com.app.apiFitness.constants.BusinessExceptionMessages
import com.app.apiFitness.controller.dto.request.TrainingCreateRequestDTO
import com.app.apiFitness.controller.dto.request.UserRequestDTO
import com.app.apiFitness.database.repository.*
import com.app.apiFitness.database.repository.entity.StudentEntity
import com.app.apiFitness.database.repository.entity.TrainingEntity
import com.app.apiFitness.database.repository.entity.TrainingHasTrainingsheetEntity
import com.app.apiFitness.database.repository.entity.UserEntity
import com.app.apiFitness.exceptions.BusinessException
import com.app.apiFitness.model.TrainingModel
import com.app.apiFitness.model.UserProfileModel
import com.nhaarman.mockitokotlin2.refEq
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.junit.Assert.assertEquals

@RunWith(JUnit4::class)
class StudentProfileServiceTest {
    @InjectMocks
    lateinit var studentProfileServiceImpl: StudentProfileServiceImpl

    @Mock
    lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder

    @Mock
    lateinit var studentRepository: StudentRepository

    @Mock
    lateinit var userRepository: UserRepository


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    //<editor-fold desc="CREATE">
    @Test
    fun create_success() {
        // Mock API response
        //<editor-fold desc="ARRANGE">
        var request = UserRequestDTO(user = UserProfileModel(email = "Teste",password = "Teste"))
        var userEntityResponse = UserEntity(request.user)
        userEntityResponse.id = 10
        var passwordEncode = "XXXXX"

        var userEntitySave = UserEntity(request.user)
        userEntitySave.password = passwordEncode
        whenever(bCryptPasswordEncoder.encode(request.user.password)).thenReturn(passwordEncode)

        whenever(userRepository.findByEmail(request.user.email)).thenReturn(null)

        whenever(userRepository.save(refEq(userEntitySave))).thenReturn(userEntityResponse)

        whenever(studentRepository.save(refEq(StudentEntity(userEntityResponse.id)))).thenReturn(StudentEntity())

        //</editor-fold>

        //<editor-fold desc="ACT">

        this.studentProfileServiceImpl.create(request)
        //</editor-fold>

        //<editor-fold desc="ASSERT">

        verify(userRepository).findByEmail(request.user.email)
        verify(userRepository).save(refEq(userEntitySave))
        verify(studentRepository).save(refEq(StudentEntity(userEntityResponse.id)))

        //</editor-fold>
    }

    @Test
    fun create_emailUtilizado_businessException() {
        // Mock API response
        //<editor-fold desc="ARRANGE">
        var request = UserRequestDTO(user = UserProfileModel(email = "Teste",password = "Teste"))
        var userEntityResponse = UserEntity(request.user)
        userEntityResponse.id = 10



        whenever(userRepository.findByEmail(request.user.email)).thenReturn(userEntityResponse)



        //</editor-fold>

        //<editor-fold desc="ACT">

        var retornoException  = assertThrows<BusinessException>(){this.studentProfileServiceImpl.create(request)}
        //</editor-fold>

        //<editor-fold desc="ASSERT">

        assertEquals(BusinessExceptionMessages.EMAIL_JA_UTILIZADO,retornoException.message)
        verify(userRepository).findByEmail(request.user.email)

        //</editor-fold>
    }
}