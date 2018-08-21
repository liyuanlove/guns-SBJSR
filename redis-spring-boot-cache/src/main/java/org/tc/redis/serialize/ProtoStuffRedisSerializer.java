package org.tc.redis.serialize;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.lang.Nullable;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 自定义Redis的序列化
 *
 * @param <T>
 */
@Slf4j
public class ProtoStuffRedisSerializer<T> implements RedisSerializer<T> {

    //schema的缓存
    private static ConcurrentHashMap<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap<Class<?>, Schema<?>>();

    //获取schema
    private static <T> Schema<T> getSchema(Class<T> clazz) {
        @SuppressWarnings("unchecked")
        Schema<T> schema = (Schema<T>) cachedSchema.get(clazz);
        if (schema == null) {
            schema = RuntimeSchema.createFrom(clazz);
            cachedSchema.put(clazz, schema);
        }
        return schema;
    }

    /**
     * 序列化对象
     */
    @Override
    public byte[] serialize(@Nullable T source) throws SerializationException {
        if (source == null) {
            return new byte[0];
        }
        ObjectWrapper<T> vo = new ObjectWrapper<T>(source);
        final Schema<ObjectWrapper> schema = getSchema(ObjectWrapper.class);
        LinkedBuffer buffer = LinkedBuffer.allocate(1024 * 1024);
        byte[] protoStuff;
        try {
            protoStuff = ProtostuffIOUtil.toByteArray(vo, schema, buffer);
        } catch (Exception ex) {
            throw new SerializationException("Cannot serialize", ex);
        } finally {
            buffer.clear();
        }
        return protoStuff;
    }

    /**
     * 反序列化对象
     *
     * @param bytes
     * @return
     */
    @Override
    public T deserialize(@Nullable byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        ObjectWrapper object = new ObjectWrapper();
        try {
            Schema<ObjectWrapper> schema = getSchema(ObjectWrapper.class);
            ProtostuffIOUtil.mergeFrom(bytes, object, schema);
            if (object != null && object.getValue() != null) {
                return (T) object.getValue();
            }
        } catch (final Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
        return null;
    }

}