package com.app.apiFitness.controller

import com.app.apiFitness.constants.ReturnMessages
import com.app.apiFitness.controller.dto.request.ChangeTrainingSheetRequestDTO
import com.app.apiFitness.controller.dto.request.TrainingSheetCreateRequestDTO
import com.app.apiFitness.controller.dto.response.SearchTrainingSheetResponseDTO
import com.app.apiFitness.controller.dto.response.StandardResponseDTO
import com.app.apiFitness.exceptions.BusinessException
import com.app.apiFitness.service.TrainingSheetService
import com.app.apiFitness.service.TrainingSheetServiceImpl
import org.apache.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/createSheet")
class TrainingSheetController {
    private val logger = Logger.getLogger(javaClass)
    @Autowired
    private lateinit var trainingSheetServiceImpl: TrainingSheetServiceImpl
    @Autowired
    private lateinit var trainingSheetService: TrainingSheetService

    @PostMapping
    fun createTrainingSheets(@RequestBody trainingSheetCreateRequestDTO: TrainingSheetCreateRequestDTO): ResponseEntity<StandardResponseDTO> {
        try {
            trainingSheetServiceImpl.create(trainingSheetCreateRequestDTO)
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

    @PostMapping("/edit")
    fun editTrainingSheet(@RequestBody changeTrainingSheetRequestDTO: ChangeTrainingSheetRequestDTO): ResponseEntity<StandardResponseDTO> {
        try {
            trainingSheetServiceImpl.change(changeTrainingSheetRequestDTO)
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

    @GetMapping(value = ["/{id}"])
    fun searchAllTrainingSheetsFromTeacher(@PathVariable  id: Long): Any {
        var retorno = SearchTrainingSheetResponseDTO()
        try {
            retorno =  trainingSheetService.searchAllTrainingSheets(id)
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

        val trainingSheetsList = trainingSheetService.searchAllTrainingSheets(id)
        return trainingSheetsList
    }
}