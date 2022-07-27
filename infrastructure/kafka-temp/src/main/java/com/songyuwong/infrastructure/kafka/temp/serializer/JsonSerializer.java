package com.songyuwong.infrastructure.kafka.temp.serializer;

import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

/**
 * <p>
 * </p>
 *
 * @author SongYu
 * @since 2022/7/26
 */
public class JsonSerializer implements Serializer<Object> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public byte[] serialize(String s, Object o) {
        return new byte[0];
    }

    @Override
    public byte[] serialize(String topic, Headers headers, Object data) {
        return Serializer.super.serialize(topic, headers, data);
    }

    @Override
    public void close() {
        Serializer.super.close();
    }

}
