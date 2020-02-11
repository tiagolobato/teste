package com.app.apiFitness.database.repository.entity

import javax.persistence.*

@Entity
@Table(name = "appointment", schema = "db_apifitness", catalog = "")
open class AppointmentEntity {
    @get:Id
    @get:Column(name = "id", nullable = false)
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null
    @get:Basic
    @get:Column(name = "data", nullable = true)
    var data: java.sql.Timestamp? = null
    @get:Basic
    @get:Column(name = "teacher_has_student_teacher_id", nullable = false)
    var teacherHasStudentTeacherId: Int? = null

    @get:ManyToOne(fetch = FetchType.LAZY)
    @get:JoinColumn(name = "teacher_has_student_teacher_id", referencedColumnName = "id", insertable = false,updatable = false)
    var refTeacherHasStudentEntity: TeacherHasStudentEntity? = null

    override fun toString(): String =
            "Entity of type: ${javaClass.name} ( " +
                    "id = $id " +
                    "data = $data " +
                    "teacherHasStudentTeacherId = $teacherHasStudentTeacherId " +
                    ")"
    
}

