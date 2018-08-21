package org.tc.redis;

import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.tc.redis.serialize.ProtoStuffRedisSerializer;

import java.lang.reflect.Method;
import java.net.UnknownHostException;
import java.time.Duration;

@Configuration
public class RedisConfig {

    /**
     * 注册redis模板类
     *
     * @return
     */
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {

        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        //关闭默认序列化
        ProtoStuffRedisSerializer serializer = protoStuffRedisSerializer();
        template.setDefaultSerializer(serializer);
//        使用自定义的序列化工具
        return template;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory connectionFactory) throws UnknownHostException {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(connectionFactory);
        return template;
    }

    /**
     * 自定义的redis缓存管理
     *
     * @return
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {

        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        //在这里替换为自定义序列化
        config = config.serializeValuesWith(RedisSerializationContext.SerializationPair
                .fromSerializer(protoStuffRedisSerializer()));
        config.entryTtl(Duration.ofSeconds(30));

        //初始化一个RedisCacheWriter
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);
        //初始化RedisCacheManager
        RedisCacheManager cacheManager = new RedisCacheManager(redisCacheWriter, config);
        return cacheManager;
    }

    /**
     * 自定义的序列化器
     *
     * @return
     */
    @Bean
    public ProtoStuffRedisSerializer protoStuffRedisSerializer() {
        return new ProtoStuffRedisSerializer();
    }

    /**
     * 缓存对象集合中，缓存是以key-value形式保存的。
     * 当不指定缓存的key时，SpringBoot会使用keyGenerator() 方法生成key，所以重写keyGenerator() 方法
     *
     * @return
     */
    @Bean
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }


}
