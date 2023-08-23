package kafka;

import entities.RelatorioFinanceiro;
import entities.RelatorioFinanceiroDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import services.NotificationService;
import java.time.format.DateTimeFormatter;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Map;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class NewRelatorioFinanceiroConsumer extends Thread {

  private static final String APP_ID = System.getenv("ONE_SIGNAL_APP_ID");
  private final static String TOPIC = "novo-relatorio-financeiro";
  private final static String INTERNAL_KAFKA_LISTENER = "localhost:29092";

  private Map<LocalDate, String> notifications = new ConcurrentHashMap<>();

  @Override
  public void run() {
    Properties properties = new Properties();
    properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, INTERNAL_KAFKA_LISTENER);
    properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "ConsumerRelatoriosFinanceiros");
    properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
    properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, RelatorioFinanceiroDeserializer.class.getName());

    try (final KafkaConsumer<String, RelatorioFinanceiro> consumer = new KafkaConsumer<>(properties)) {
      consumer.subscribe(Collections.singletonList(TOPIC));

      while (true) {
        ConsumerRecords<String, RelatorioFinanceiro> records = consumer.poll(Duration.ofMillis(1000));

        for (ConsumerRecord<String, RelatorioFinanceiro> record : records) {
          String key = record.key();
          RelatorioFinanceiro value = record.value();
          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
          String strJsonBody = "{"
            + "\"app_id\":\"" + APP_ID + "\","
            + "\"included_segments\": [\"Subscribed Users\"],"
            + "\"headings\": {\"en\": \"Atualização Financeira\"},"
            + "\"contents\": {\"en\": \"área de finanças atualizada com balanço de " + formatter.format(value.getAnoMes()) + "\"},"
            + "\"web_url\": \"https://cappella.meteorapp.com/\"}";

          String notification = notifications.get(LocalDate.now());
          if (notification == null) {
            new NotificationService().sendPush(strJsonBody);
            notifications.put(LocalDate.now(), strJsonBody);
          }
        }

      }
    }
  }

}
