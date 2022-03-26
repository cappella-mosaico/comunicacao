package entities;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.StandardCharsets;

public class PastoralDeserializer implements Deserializer<Pastoral> {

    @Override
    public Pastoral deserialize(String s, byte[] bytes) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(new String(bytes, StandardCharsets.UTF_8.name()), Pastoral.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SerializationException(e.getMessage());
        }
    }
}
