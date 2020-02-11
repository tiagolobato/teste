package com.app.apiFitness.database.repository.entity

import javax.persistence.*

@Entity
@Table(name = "nutrition", schema = "db_apifitness", catalog = "")
open class NutritionEntity {
    @get:Id
    @get:Column(name = "id", nullable = false)
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null
    @get:Basic
    @get:Column(name = "urlImage", nullable = true)
    var urlImage: String? = null
    @get:Basic
    @get:Column(name = "student_id", nullable = false)
    var studentId: Int? = null

    @get:ManyToOne(fetch = FetchType.LAZY)
    @get:JoinColumn(name = "student_id", referencedColumnName = "id", insertable = false,updatable = false)
    var refStudentEntity: StudentEntity? = null

    override fun toString(): String =
            "Entity of type: ${javaClass.name} ( " +
                    "id = $id " +
                    "urlImage = $urlImage " +
                    "studentId = $studentId " +
                    ")"

}

