package com.cuttage.ecommerce.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenProvider(
    @Value("\${app.jwtSecret}")
    private val jwtSecret: String,
    @Value("\${app.jwtExpirationInMs}")
    private val jwtExpirationInMs: Long
) {
    fun generateToken(userDetails: UserDetails): String {
        val claims: Claims = Jwts.claims().setSubject(userDetails.username)
        val now = Date()
        val validity = Date(now.time + jwtExpirationInMs)

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(SignatureAlgorithm.HS256, jwtSecret)
            .compact()
    }

    fun validateToken(token: String, userDetails: UserDetails): Boolean {
        val username = extractUsername(token)
        return username == userDetails.username && !isTokenExpired(token)
    }

    private fun extractUsername(token: String): String {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).body.subject
    }

    private fun isTokenExpired(token: String): Boolean {
        val expiration = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).body.expiration
        return expiration.before(Date())
    }
}
