package com.app.apiFitness.model

import com.app.apiFitness.constants.enums.UserStatusEnum
import com.app.apiFitness.database.repository.entity.UserEntity
import java.net.Inet4Address

data class UserProfileModel (
        var id: Long? = null,
        var name: String? = null,
        var nickname: String? = null,
        var email: String = "",
        var role: UserStatusEnum? = null,
        var password: String? = null,
        var gender: String? = null,
        var age: Long? = null,
        var zipCode: Long? = null,
        var telephone: Long? = null,
        var address: String? = null
){
    constructor(user: UserEntity) : this() {
        this.address = user.address
        this.age = user.age?.toLong()
        this.email = user.email.toString()
        this.gender = user.gender
        this.id = user.id
        this.name = user.name
        this.nickname = user.nickname
        this.password = user.password
        this.role = UserStatusEnum(user.role)
        this.telephone = user.telephone?.toLong()
        this.zipCode = user.zipCode?.toLong()
    }

    private fun UserStatusEnum(value: String?): UserStatusEnum {
        if(UserStatusEnum.STUDENT.equals(value)){
            return UserStatusEnum.STUDENT
        }
        else return UserStatusEnum.TEACHER

    }
}