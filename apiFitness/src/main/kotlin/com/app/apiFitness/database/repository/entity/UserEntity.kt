package com.app.apiFitness.database.repository.entity

import com.app.apiFitness.controller.dto.request.UserRequestDTO
import com.app.apiFitness.model.UserProfileModel
import javax.persistence.*

@Entity
@Table(name = "user", schema = "db_apifitness", catalog = "")
open class UserEntity (){
    @get:Id
    @get:Column(name = "id", nullable = false, insertable = false, updatable = false)
    @get:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    @get:Basic
    @get:Column(name = "name", nullable = true)
    var name: String? = null
    @get:Basic
    @get:Column(name = "nickname", nullable = true)
    var nickname: String? = null
    @get:Basic
    @get:Column(name = "email", nullable = true)
    var email: String? = null
    @get:Basic
    @get:Column(name = "address", nullable = true)
    var address: String? = null
    @get:Basic
    @get:Column(name = "role", nullable = true)
    var role: String? = null
    @get:Basic
    @get:Column(name = "password", nullable = true)
    var password: String? = null
    @get:Basic
    @get:Column(name = "gender", nullable = true)
    var gender: String? = null
    @get:Basic
    @get:Column(name = "age", nullable = true)
    var age: Int? = null
    @get:Basic
    @get:Column(name = "zipCode", nullable = true)
    var zipCode: Int? = null
    @get:Basic
    @get:Column(name = "telephone", nullable = true)
    var telephone: Int? = null

    @get:OneToMany(mappedBy = "refUserEntity")
    var refGymHasUserEntities: List<GymHasUserEntity>? = null
    @get:OneToOne(mappedBy = "refUserEntity")
    var refStudentEntities: StudentEntity? = null
    @get:OneToOne(mappedBy = "refUserEntity")
    var refTeacherEntities: TeacherEntity? = null

    override fun toString(): String =
            "Entity of type: ${javaClass.name} ( " +
                    "id = $id " +
                    "name = $name " +
                    "nickname = $nickname " +
                    "email = $email " +
                    "address = $address " +
                    "role = $role " +
                    "password = $password " +
                    "gender = $gender " +
                    "age = $age " +
                    "zipCode = $zipCode " +
                    "telephone = $telephone " +
                    ")"

    // constant value returned to avoid entity inequality to itself before and after it's update/merge
    override fun hashCode(): Int = 42

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as UserEntity

        if (id != other.id) return false
        if (name != other.name) return false
        if (nickname != other.nickname) return false
        if (email != other.email) return false
        if (address != other.address) return false
        if (role != other.role) return false
        if (password != other.password) return false
        if (gender != other.gender) return false
        if (age != other.age) return false
        if (zipCode != other.zipCode) return false
        if (telephone != other.telephone) return false

        return true
    }
    constructor(user: UserProfileModel) : this() {
        this.address = user.address
        this.age = user.age?.toInt()
        this.email = user.email
        this.gender = user.gender
        this.id = user.id
        this.name = user.name
        this.nickname = user.nickname
        this.password = user.password
        this.role = user.role.toString()
        this.telephone = user.telephone?.toInt()
        this.zipCode = user.zipCode?.toInt()
    }
}

