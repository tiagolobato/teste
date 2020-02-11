package com.app.apiFitness.service

import com.app.apiFitness.controller.dto.request.TrainingChangeRequestDTO
import com.app.apiFitness.controller.dto.request.TrainingCreateRequestDTO
import com.app.apiFitness.controller.dto.request.TrainingSearchRequestDTO
import com.app.apiFitness.database.repository.StudentRepository
import com.app.apiFitness.database.repository.TrainingHasTrainingsheetRepository
import com.app.apiFitness.database.repository.TrainingRepository
import com.app.apiFitness.database.repository.TrainingsheetRepository
import com.app.apiFitness.database.repository.entity.TrainingEntity
import com.app.apiFitness.database.repository.entity.TrainingHasTrainingsheetEntity
import com.app.apiFitness.model.TrainingModel
import org.hibernate.Hibernate
import org.hibernate.proxy.HibernateProxy
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors
import kotlin.reflect.KClass

@Service
class TrainingServiceImpl {

    @Autowired
    private lateinit var trainingsheetRepository: TrainingsheetRepository

    @Autowired
    private lateinit var trainingRepository: TrainingRepository

    @Autowired
    private lateinit var trainingHasTrainingsheetRepository: TrainingHasTrainingsheetRepository

    @Autowired
    private lateinit var studentRepository: StudentRepository

    @Autowired
    private lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder

    fun create(trainingCreateRequestDTO: TrainingCreateRequestDTO) {
        val trainingId =  createTraining(trainingCreateRequestDTO.trainingModel).id
        createTrainingHasTrainingsheet(trainingCreateRequestDTO.trainingSheetId,trainingId,"10")
    }
    private fun createTraining(training: TrainingModel):TrainingEntity {
        var trainingEntity = TrainingEntity(training);
        return trainingRepository.save(trainingEntity)
    }

    private fun createTrainingHasTrainingsheet(trainingSheetId: Int?,trainingId: Long?,orderTraining: String?) {
        var trainingHasTrainingsheetEntity = TrainingHasTrainingsheetEntity();
        trainingHasTrainingsheetEntity.orderTraining = orderTraining
        trainingHasTrainingsheetEntity.trainingId = trainingId?.toInt()
        trainingHasTrainingsheetEntity.trainingSheetId = trainingSheetId
        trainingHasTrainingsheetRepository.save(trainingHasTrainingsheetEntity)
    }

    fun change(trainingChangeRequestDTO: TrainingChangeRequestDTO) {
        createTraining(trainingChangeRequestDTO.trainingModel)
    }

    fun search(trainingSearchRequestDTO : TrainingSearchRequestDTO): List<TrainingModel>?{

        return unproxyTrainingList(trainingHasTrainingsheetRepository.findAllByTrainingSheetId(trainingSearchRequestDTO.trainingSheetId))
    }

    fun deleteTraining(id :Long) {

        trainingRepository.deleteById(id)
    }
    private fun unproxyTrainingList(trainingHasTrainingSheets: List<TrainingHasTrainingsheetEntity>): List<TrainingModel>? {
        return trainingHasTrainingSheets.stream().map { x ->  TrainingModel( Hibernate.unproxy(x.refTrainingEntity,TrainingEntity::class.java)) }.collect(Collectors.toList());
    }

}