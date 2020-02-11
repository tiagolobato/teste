package com.app.apiFitness.controller;

import com.app.apiFitness.constants.ReturnMessages
import com.app.apiFitness.controller.dto.request.CreateTeacherHasStudentRequestDTO
import com.app.apiFitness.controller.dto.request.SearchTeacherStudentsRequestDTO
import com.app.apiFitness.controller.dto.request.TeacherProfileRequestDTO;
import com.app.apiFitness.controller.dto.request.TrainingSearchRequestDTO
import com.app.apiFitness.controller.dto.response.SearchTeacherStudentsResponseDTO
import com.app.apiFitness.controller.dto.response.StandardResponseDTO
import com.app.apiFitness.controller.dto.response.TrainingSearchResponseDTO
import com.app.apiFitness.exceptions.BusinessException
import com.app.apiFitness.service.TeacherProfileService;
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.net.URI

@Controller
@RequestMapping("/teacherProfile")
class TeacherProfileController {
    private val logger = Logger.getLogger(javaClass)
    @Autowired
    private lateinit var teacherProfileService: TeacherProfileService

    @PostMapping(value = ["/create"])
    fun createTeacherProfile(@RequestBody teacherProfileRequestDTO: TeacherProfileRequestDTO): ResponseEntity<StandardResponseDTO> {
        try{
            val teacherCreated = teacherProfileService.create(teacherProfileRequestDTO)
        }

        catch (ex : BusinessException){
            logger.error(ex.message,ex)
            return ResponseEntity.unprocessableEntity().body(StandardResponseDTO(2,ex.message))
        }
        catch (ex : Exception){
            logger.error(ex.message,ex)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(StandardResponseDTO(1, ReturnMessages.INTERNAL_SERVER_ERROR))
        }
        return ResponseEntity.created(URI("")).body(StandardResponseDTO(0,""))
    }

    @PostMapping(value = ["/searchStudents"])
    fun searchStudents(@RequestBody searchTeacherStudentsRequestDTO: SearchTeacherStudentsRequestDTO): ResponseEntity<SearchTeacherStudentsResponseDTO> {
        var retorno = SearchTeacherStudentsResponseDTO()
        try {
            retorno.users =  teacherProfileService.searchTeacherStudents(searchTeacherStudentsRequestDTO)
        }
        catch (ex : BusinessException){
            logger.error(ex.message,ex)
            retorno.cdReturn = 2
            retorno.dsReturn = ex.message
            return ResponseEntity.unprocessableEntity().body(retorno)
        }
        catch (ex : Exception){
            logger.error(ex.message,ex)
            retorno.cdReturn = 1
            retorno.dsReturn = ReturnMessages.INTERNAL_SERVER_ERROR
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(retorno)
        }
        retorno.cdReturn = 0
        retorno.dsReturn = ""
        return ResponseEntity.created(URI("")).body(retorno)
    }

    @PostMapping(value = ["/createTeacherHasStudent"])
    fun createTeacherHasStudent(@RequestBody createTeacherHasStudentRequestDTO: CreateTeacherHasStudentRequestDTO): ResponseEntity<StandardResponseDTO> {
        try{
            teacherProfileService.createTeacherHasStudent(createTeacherHasStudentRequestDTO)
        }

        catch (ex : BusinessException){
            logger.error(ex.message,ex)
            return ResponseEntity.unprocessableEntity().body(StandardResponseDTO(2,ex.message))
        }
        catch (ex : Exception){
            logger.error(ex.message,ex)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(StandardResponseDTO(1, ReturnMessages.INTERNAL_SERVER_ERROR))
        }
        return ResponseEntity.created(URI("")).body(StandardResponseDTO(0,""))
    }
}
