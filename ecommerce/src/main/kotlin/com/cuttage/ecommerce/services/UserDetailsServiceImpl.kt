package com.cuttage.ecommerce.services

import com.cuttage.ecommerce.repositories.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(private val userRepository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails {
        val user = userRepository.findByEmail(email)
        if (user != null) {
            return User(user.email, user.password, emptyList())
        }
        throw UsernameNotFoundException("User '$email' not found.")
    }
}
