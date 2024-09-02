package com.sparta.aibusinessproject.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public RedisCacheManager cacheManager(
            RedisConnectionFactory redisConnectionFactory // RedisTemplate과 같이 Redis와의 연결정보가 구성돼야 함.
    ){
        // 설정 구성을 먼저 진행
        // Redis를 이용해서 Spring Cache를 사용할 때
        // Redis 관련 설정을 모아두는 클래스
        RedisCacheConfiguration configuration = RedisCacheConfiguration
                .defaultCacheConfig()
                // 결과가 null이면 캐싱하지 않겠다.
                .disableCachingNullValues()
                // 기본 캐시 유지 시간 (Time To Live) -> 10초 이후에 들어오는 요청에 대해서는 새로 데이터를 조회해서 쓴다.
                .entryTtl(Duration.ofSeconds(60*60))
                // 캐시를 구분하는 접두사 설정 -> 캐시 데이터가 Redis에 들어갈 때 키의 값 설정(기본으로 함)
                .computePrefixWith(CacheKeyPrefix.simple())
                // 캐시에 저장할 값을 어떻게 직렬화, 역직렬화 할 것인지
                .serializeValuesWith( // key는 문자열로 진행.. value만 따로 설정
                        // 여기서 json(), string() 등을 선택할 수 있음
                        // 선택에 따라서 설정해야 하는 것들이 늘어날 수 있음(클래스 등을 만들어서..)
                        RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.java()) // static import해서 사용!
                );

        return RedisCacheManager
                .builder(redisConnectionFactory)
                .cacheDefaults(configuration) // 위에서 정한 설정들을 기본으로 사용
                .build();
        // 메서드들이 호출될 때, 사용될 캐시를 따로 설정할 수도 있음.
        // 그런 것들이 없다면 위의 설정을 기본으로 사용.

    }
}