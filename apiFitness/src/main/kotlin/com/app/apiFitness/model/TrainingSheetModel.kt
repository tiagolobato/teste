package com.app.apiFitness.model

import com.app.apiFitness.database.repository.entity.TrainingsheetEntity
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

data class TrainingSheetModel (
        var id: Long? = null,
        var name: String = "",
        var description: String? = null,
        @JsonIgnore
        var objective: String? = null,
        @JsonIgnore
        var studentId: Int? = null,
        @JsonIgnore
        var teacherId: Int? = null,
        @JsonProperty("startDate")
        var dateStart: java.sql.Date? = null,
        @JsonProperty("endDate")
        var dateEnd: java.sql.Date? = null,

        var numberOfExercises: Long = 0

){
    constructor(trainingsheetEntity: TrainingsheetEntity,numberOfExercises :Long) : this() {
        this.dateEnd = trainingsheetEntity.dateEnd
        this.dateStart = trainingsheetEntity.dateStart
        this.description = trainingsheetEntity.description
        this.name = trainingsheetEntity.name
        this.objective = trainingsheetEntity.objective
        this.studentId = trainingsheetEntity.studentId
        this.teacherId = trainingsheetEntity.teacherId
        this.id = trainingsheetEntity.id
        this.numberOfExercises = numberOfExercises
    }
}