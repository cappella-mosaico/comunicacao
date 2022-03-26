package services;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.enterprise.inject.Produces;

public class ObjectMapperFactory {

    private static ObjectMapper singleton;

    private ObjectMapperFactory() {}

    @Produces
    public ObjectMapper getInstance() {
        if (singleton == null) {
            singleton = new ObjectMapper();
        }
        return singleton;
    }

}
