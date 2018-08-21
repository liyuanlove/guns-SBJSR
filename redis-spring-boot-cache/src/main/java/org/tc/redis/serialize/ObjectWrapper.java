package org.tc.redis.serialize;

import lombok.Data;

import java.io.Serializable;

/**
 * Redis序列化包装类
 *
 * @param <T>
 */
@Data
public class ObjectWrapper<T> implements Serializable {

    private T value;

    public ObjectWrapper() {
    }

    public ObjectWrapper(T value) {
        this.value = value;
    }
}
