package startup;

import entities.Pastoral;
import entities.PastoralDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import services.NotificationService;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class App {
    private final static String TOPIC = "pastorais";
    private final static String INTERNAL_KAFKA_LISTENER = "localhost:29092";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, INTERNAL_KAFKA_LISTENER);
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "ConsumerPastorais");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, PastoralDeserializer.class.getName());

        try (final KafkaConsumer<String, Pastoral> consumer = new KafkaConsumer<>(properties)) {
            consumer.subscribe(Collections.singletonList(TOPIC));

            while (true) {
                ConsumerRecords<String, Pastoral> records = consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String, Pastoral> record : records) {
                    String key = record.key();
                    Pastoral value = record.value();
                    System.out.println("WE HAVE RECEIVED YOUR MESSAGE SIR, PLEASE STOP ASKING US TO CONFIRM " + key + " " + value);
                    new NotificationService().sendPush(value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to initialize Consumer for PASTORAIS");
        }
    }

}