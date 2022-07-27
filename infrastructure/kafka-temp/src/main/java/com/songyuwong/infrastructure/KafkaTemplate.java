package com.songyuwong.infrastructure;

import com.songyuwong.infrastructure.kafka.temp.serializer.JsonSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.LinkedHashMap;


/**
 * <p>
 * </p>
 *
 * @author SongYu
 * @since 2022/7/26
 */
public class KafkaTemplate {

    public static void main(String[] args) {
        LinkedHashMap<String, Object> configs = new LinkedHashMap<>();
        try (
                KafkaProducer<String, Object> kafkaProducer = new KafkaProducer<>(configs, new StringSerializer(), new JsonSerializer())
        ) {
            ProducerRecord<String, Object> record = new ProducerRecord<>("TOPIC", 1, "1000", new Exception());
            kafkaProducer.send(record, (recordMetadata, e) -> {
                System.out.println(recordMetadata.offset());
                e.printStackTrace();
            });
        }
    }

}
