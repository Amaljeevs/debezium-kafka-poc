package com.baas.charges.main.config;
import com.baas.charges.main.dto.DebeziumEventDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class DebeziumEventDtoDeserializer implements Deserializer<DebeziumEventDto> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public DebeziumEventDto deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, DebeziumEventDto.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void close() {
    }
}
