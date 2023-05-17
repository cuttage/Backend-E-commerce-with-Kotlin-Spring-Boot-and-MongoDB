package com.cuttage.ecommerce.repositories

import com.cuttage.ecommerce.domain.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User, String> {
    fun findByEmail(email: String): User?
}

