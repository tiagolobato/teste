package com.app.apiFitness.service

import com.app.apiFitness.controller.dto.request.TrainingChangeRequestDTO
import com.app.apiFitness.controller.dto.request.TrainingCreateRequestDTO
import com.app.apiFitness.controller.dto.request.TrainingSearchRequestDTO
import com.app.apiFitness.database.repository.TrainingHasTrainingsheetRepository
import com.app.apiFitness.database.repository.TrainingRepository
import com.app.apiFitness.database.repository.TrainingsheetRepository
import com.app.apiFitness.database.repository.entity.TrainingEntity
import com.app.apiFitness.database.repository.entity.TrainingHasTrainingsheetEntity
import com.app.apiFitness.exceptions.BusinessException
import com.app.apiFitness.model.TrainingModel
import com.nhaarman.mockitokotlin2.*
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

import org.springframework.beans.factory.annotation.Autowired

@RunWith(JUnit4::class)
class TrainingServiceTest {

    @InjectMocks
    lateinit var trainingServiceImpl: TrainingServiceImpl

    @Mock
    lateinit var trainingRepository: TrainingRepository

    @Mock
    lateinit var trainingHasTrainingsheetRepository: TrainingHasTrainingsheetRepository

    @Mock
    lateinit var trainingsheetRepository: TrainingsheetRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        //this.trainingServiceImpl = TrainingServiceImpl()
    }

    //<editor-fold desc="CREATE">
    @Test
    fun create_success() {
        // Mock API response
        //<editor-fold desc="ARRANGE">
        var request = TrainingCreateRequestDTO(trainingModel = TrainingModel(),trainingSheetId = 10 )
        var trainingEntity = TrainingEntity(request.trainingModel)
        trainingEntity.id = 10
        var trainingEntity2 = TrainingEntity(request.trainingModel)
        var trTrainingHasTrainingsheetEntity = TrainingHasTrainingsheetEntity()
        trTrainingHasTrainingsheetEntity.orderTraining = "10"
        trTrainingHasTrainingsheetEntity.trainingId = trainingEntity.id!!.toInt()
        trTrainingHasTrainingsheetEntity.trainingSheetId = request.trainingSheetId
        trainingEntity.id = 10
        whenever(trainingRepository.save(refEq(trainingEntity2))).thenReturn(trainingEntity)

        //</editor-fold>

        //<editor-fold desc="ACT">

        var retorno = this.trainingServiceImpl.create(request)
        //</editor-fold>

        //<editor-fold desc="ASSERT">

        verify(trainingHasTrainingsheetRepository).save(refEq(trTrainingHasTrainingsheetEntity))
        verify(trainingRepository).save(refEq(trainingEntity2))

        //</editor-fold>
    }

    @Test
    fun create_saveTraining_exception() {
        // Mock API response
        //<editor-fold desc="ARRANGE">
        var request = TrainingCreateRequestDTO(trainingModel = TrainingModel(),trainingSheetId = 10 )
        var trainingEntity2 = TrainingEntity(request.trainingModel)

        var mensagemException = "Teste"
        whenever(trainingRepository.save(refEq(trainingEntity2))).thenThrow(RuntimeException(mensagemException))

        //</editor-fold>

        //<editor-fold desc="ACT">

        var runtimeException = assertThrows<RuntimeException> { this.trainingServiceImpl.create(request) }
        //</editor-fold>

        //<editor-fold desc="ASSERT">
        assertEquals(mensagemException,runtimeException.message)
        verify(trainingRepository).save(refEq(trainingEntity2))
        //</editor-fold>
    }

    @Test
    fun create_saveTrainingHasTrainingsheet_exception() {
        // Mock API response
        //<editor-fold desc="ARRANGE">
        var request = TrainingCreateRequestDTO(trainingModel = TrainingModel(),trainingSheetId = 10 )
        var trainingEntity = TrainingEntity(request.trainingModel)
        trainingEntity.id = 10

        var trainingEntity2 = TrainingEntity(request.trainingModel)

        var trTrainingHasTrainingsheetEntity = TrainingHasTrainingsheetEntity()
        trTrainingHasTrainingsheetEntity.orderTraining = "10"
        trTrainingHasTrainingsheetEntity.trainingId = trainingEntity.id!!.toInt()
        trTrainingHasTrainingsheetEntity.trainingSheetId = request.trainingSheetId

        var mensagemException = "Teste"

        whenever(trainingHasTrainingsheetRepository.save(refEq(trTrainingHasTrainingsheetEntity))).thenThrow(RuntimeException(mensagemException))

        whenever(trainingRepository.save(refEq(trainingEntity2))).thenReturn(trainingEntity)
        //</editor-fold>

        //<editor-fold desc="ACT">

        var runtimeException = assertThrows<RuntimeException> { this.trainingServiceImpl.create(request) }
        //</editor-fold>

        //<editor-fold desc="ASSERT">

        assertEquals(mensagemException,runtimeException.message)
        verify(trainingRepository).save(refEq(trainingEntity2))
        verify(trainingHasTrainingsheetRepository).save(refEq(trTrainingHasTrainingsheetEntity))
        //</editor-fold>
    }

    //</editor-fold>

    //<editor-fold desc="CHANGE">

    @Test
    fun change_saveTraining_exception() {
        // Mock API response
        //<editor-fold desc="ARRANGE">
        var request = TrainingChangeRequestDTO(trainingModel = TrainingModel(id = 10))
        var trainingEntity = TrainingEntity(request.trainingModel)

        var mensagemException = "Teste"
        whenever(trainingRepository.save(refEq(trainingEntity))).thenThrow(RuntimeException(mensagemException))

        //</editor-fold>

        //<editor-fold desc="ACT">

        var runtimeException = assertThrows<RuntimeException> { this.trainingServiceImpl.change(request) }
        //</editor-fold>

        //<editor-fold desc="ASSERT">
        assertEquals(mensagemException,runtimeException.message)
        verify(trainingRepository).save(refEq(trainingEntity))
        //</editor-fold>
    }

    @Test
    fun change_success() {
        // Mock API response
        //<editor-fold desc="ARRANGE">
        var request = TrainingChangeRequestDTO(trainingModel = TrainingModel(id = 10,machineId = 1))
        var trainingEntity = TrainingEntity(request.trainingModel)
        whenever(trainingRepository.save(refEq(trainingEntity))).thenReturn(trainingEntity)

        //</editor-fold>

        //<editor-fold desc="ACT">

        var retorno = this.trainingServiceImpl.change(request)
        //</editor-fold>

        //<editor-fold desc="ASSERT">

        verify(trainingRepository).save(refEq(trainingEntity))

        //</editor-fold>
    }

    //</editor-fold>


    //<editor-fold desc="SEARCH">
    @Test
    fun search_success() {
        // Mock API response
        //<editor-fold desc="ARRANGE">
        var request = TrainingSearchRequestDTO(20)
        var trainingEntity = TrainingEntity()
        trainingEntity.id = 5

        var trainingEntity2 = TrainingEntity()
        trainingEntity2.id = 6

        var trainingHasTrainingsheetEntity = TrainingHasTrainingsheetEntity()
        trainingHasTrainingsheetEntity.refTrainingEntity = trainingEntity
        trainingHasTrainingsheetEntity.id = 10

        var trainingHasTrainingsheetEntity2 = TrainingHasTrainingsheetEntity()
        trainingHasTrainingsheetEntity2.refTrainingEntity = trainingEntity2
        trainingHasTrainingsheetEntity2.id = 11

        var trainingHasTrainingsheetEntities =  listOf<TrainingHasTrainingsheetEntity>(trainingHasTrainingsheetEntity,trainingHasTrainingsheetEntity2)

        var mensagemException = "Teste"
        whenever(trainingHasTrainingsheetRepository.findAllByTrainingSheetId(request.trainingSheetId)).thenReturn(trainingHasTrainingsheetEntities)

        //</editor-fold>

        //<editor-fold desc="ACT">

        var retorno = this.trainingServiceImpl.search(request)
        //</editor-fold>

        //<editor-fold desc="ASSERT">
        assertEquals(5, retorno?.get(0)?.id)
        assertEquals(6, retorno?.get(1)?.id)
        verify(trainingHasTrainingsheetRepository).findAllByTrainingSheetId(request.trainingSheetId)
        //</editor-fold>
    }

    @Test
    fun search_findAllByTrainingSheetId_exception() {
        // Mock API response
        //<editor-fold desc="ARRANGE">
        var request = TrainingSearchRequestDTO(20)

        var mensagemException = "Teste"
        whenever(trainingHasTrainingsheetRepository.findAllByTrainingSheetId(request.trainingSheetId)).thenThrow(RuntimeException(mensagemException))

        //</editor-fold>

        //<editor-fold desc="ACT">

        var runtimeException = assertThrows<RuntimeException> { this.trainingServiceImpl.search(request) }
        //</editor-fold>

        //<editor-fold desc="ASSERT">
        assertEquals(mensagemException,runtimeException.message)
        verify(trainingHasTrainingsheetRepository).findAllByTrainingSheetId(request.trainingSheetId)
        //</editor-fold>
    }
    //</editor-fold>

    //<editor-fold desc="DELETE">

    @Test
    fun deleteTraining_success() {
        // Mock API response
        //<editor-fold desc="ARRANGE">
        var request = 10L

        //</editor-fold>

        //<editor-fold desc="ACT">

        this.trainingServiceImpl.deleteTraining(request)
        //</editor-fold>

        //<editor-fold desc="ASSERT">
        verify(trainingRepository).deleteById(request)
        //</editor-fold>
    }

    @Test
    fun deleteTraining_delete_exception() {
        // Mock API response
        //<editor-fold desc="ARRANGE">
        var request = 10L

        var mensagemException = "Teste"
        whenever(trainingRepository.deleteById(request)).thenThrow(RuntimeException(mensagemException))

        //</editor-fold>

        //<editor-fold desc="ACT">

        var runtimeException = assertThrows<RuntimeException> { this.trainingServiceImpl.deleteTraining(request) }
        //</editor-fold>

        //<editor-fold desc="ASSERT">
        assertEquals(mensagemException,runtimeException.message)
        verify(trainingRepository).deleteById(request)
        //</editor-fold>
    }
    //</editor-fold>
}