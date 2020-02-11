package com.app.apiFitness.controller

import com.app.apiFitness.constants.ReturnMessages
import com.app.apiFitness.controller.dto.request.TrainingChangeRequestDTO
import com.app.apiFitness.controller.dto.request.TrainingCreateRequestDTO
import com.app.apiFitness.controller.dto.request.TrainingSearchRequestDTO
import com.app.apiFitness.controller.dto.response.StandardResponseDTO
import com.app.apiFitness.controller.dto.response.TrainingSearchResponseDTO
import com.app.apiFitness.exceptions.BusinessException
import com.app.apiFitness.service.TrainingServiceImpl
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/training")
class TrainingController {
    private val logger = Logger.getLogger(javaClass)
    @Autowired
    private lateinit var trainingServiceImpl: TrainingServiceImpl

    @PostMapping(value = ["/create"])
    fun create(@RequestBody trainingCreateRequestDTO: TrainingCreateRequestDTO): ResponseEntity<StandardResponseDTO> {
        try {
            trainingServiceImpl.create(trainingCreateRequestDTO)
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
    @PostMapping(value = ["/change"])
    fun change(@RequestBody trainingChangeRequestDTO: TrainingChangeRequestDTO): ResponseEntity<StandardResponseDTO> {
        try {
            trainingServiceImpl.change(trainingChangeRequestDTO)
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

    @PostMapping(value = ["/search"])
    fun search(@RequestBody trainingSearchRequestDTO: TrainingSearchRequestDTO): ResponseEntity<TrainingSearchResponseDTO> {
        var retorno = TrainingSearchResponseDTO()
        try {
            retorno.trainings =  trainingServiceImpl.search(trainingSearchRequestDTO)
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

    @DeleteMapping(value = ["/{id}"])
    fun deleteTraining(@PathVariable  id: Long): ResponseEntity<StandardResponseDTO> {
        try {
            trainingServiceImpl.deleteTraining(id)
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