package com.marcelogontijo.stocks_backend.core.application.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.annotation.CachingConfigurer
import org.springframework.cache.annotation.CachingConfigurerSupport
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration

@Configuration
class RedisConfig(
    @Value("\${spring.data.redis.host}")
    private val redisHost: String,
    @Value("\${spring.data.redis.port}")
    private val redisPort: Int,
): CachingConfigurer {
    @Bean
    fun cacheManager(objectMapper: ObjectMapper): RedisCacheManager {
        val jsonSerializer = GenericJackson2JsonRedisSerializer(objectMapper)

        val config = RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofMinutes(10)) // Cache TTL
            .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(StringRedisSerializer()))
            .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jsonSerializer))

        return RedisCacheManager.builder(redisConnectionFactory())
            .cacheDefaults(config)
            .build()
    }

    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        val redis = RedisStandaloneConfiguration(
            redisHost,
            redisPort,
        )
        return LettuceConnectionFactory(redis)
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, String> {
        val template = RedisTemplate<String, String>()
        template.setConnectionFactory(redisConnectionFactory())
        template.setKeySerializer(StringRedisSerializer())
        template.setValueSerializer(StringRedisSerializer())
        return template
    }
}