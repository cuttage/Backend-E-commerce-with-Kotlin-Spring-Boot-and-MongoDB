package com.cuttage.ecommerce.controllers

import com.cuttage.ecommerce.repositories.UserRepository
import com.cuttage.ecommerce.services.UserDetailsServiceImpl
import com.cuttage.ecommerce.security.JwtTokenProvider
import com.cuttage.ecommerce.domain.User

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userRepository: UserRepository,
    private val authenticationManager: AuthenticationManager,
    private val userDetailsService: UserDetailsServiceImpl,
    private val tokenProvider: JwtTokenProvider
) {
    @PostMapping("/register")
    fun registerUser(@RequestBody user: User): User {
        val passwordEncoder = BCryptPasswordEncoder()
        user.password = passwordEncoder.encode(user.password)
        return userRepository.save(user)
    }

    @PostMapping("/login")
    fun loginUser(@RequestBody loginRequest: LoginRequest): LoginResponse {
        val authenticationToken = UsernamePasswordAuthenticationToken(
            loginRequest.email,
            loginRequest.password
        )
        val authentication = authenticationManager.authenticate(authenticationToken)
        SecurityContextHolder.getContext().authentication = authentication
        val userDetails: UserDetails = userDetailsService.loadUserByUsername(loginRequest.email)
        val token = tokenProvider.generateToken(userDetails)
        return LoginResponse(token)
    }
}

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val token: String
)
