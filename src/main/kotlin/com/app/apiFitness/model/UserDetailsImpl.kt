package com.app.apiFitness.model

import com.app.apiFitness.controller.dto.request.UserRequestDTO
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsImpl(private val user: UserRequestDTO) : UserDetails {

    override fun getAuthorities() = mutableListOf<GrantedAuthority>()

    override fun isEnabled() = true

    override fun getUsername() =  user.user.email

    override fun isCredentialsNonExpired() = true

    override fun getPassword() = user.user.password

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true
}