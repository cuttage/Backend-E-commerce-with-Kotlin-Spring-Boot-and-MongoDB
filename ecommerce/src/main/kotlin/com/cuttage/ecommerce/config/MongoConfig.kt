package com.cuttage.ecommerce.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory

@Configuration
class MongoConfig {
    @Bean
    fun mongoTemplate(): MongoTemplate {
        val mongoClientURI = "mongodb+srv://${System.getenv("MONGODB_USERNAME")}:${System.getenv("MONGODB_PASSWORD")}@ecommerce.xcrsaqr.mongodb.net/Ecommerce?retryWrites=true&w=majority"
        val databaseFactory = SimpleMongoClientDatabaseFactory(mongoClientURI)
        return MongoTemplate(databaseFactory)
    }
}
