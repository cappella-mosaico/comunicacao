package entities;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.StandardCharsets;

public class RelatorioFinanceiroDeserializer implements Deserializer<RelatorioFinanceiro> {

    @Override
    public RelatorioFinanceiro deserialize(String s, byte[] bytes) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(new String(bytes, StandardCharsets.UTF_8.name()), RelatorioFinanceiro.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SerializationException(e.getMessage());
        }
    }
}
