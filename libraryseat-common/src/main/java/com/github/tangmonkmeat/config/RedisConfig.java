package com.github.tangmonkmeat.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 配置redis
 *
 * @author zwl
 * @date 2020/11/27 10:38
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        // 配置redis工厂
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        //// 使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
        //Jackson2JsonRedisSerializer<Object> jsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        //// 对象映射成json的构建器
        //ObjectMapper mapper = new ObjectMapper();
        //// 指定序列化的范围: field,get和set,以及修饰符范围，ANY是都有包括private和public
        //mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        //// 指定序列化输入的类型，类必须是非final修饰的，final修饰的类，比如String,Integer等会跑出异常
        //mapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance , ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.PROPERTY);
        //jsonRedisSerializer.setObjectMapper(mapper);
        //
        //// value序列化方式采用jackson
        //redisTemplate.setValueSerializer(jsonRedisSerializer);
        //// key采用String的序列化方式
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //
        //// 对hash的key采用String的序列化方式
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        //// 对hash的value采用jackson的序列化方式
        //redisTemplate.setHashValueSerializer(jsonRedisSerializer);
        // 设置支持事物
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
