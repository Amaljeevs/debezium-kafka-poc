package com.baas.charges.main.config.jackson;

import com.baas.charges.main.dto.DebeziumEventDto;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class OperationTypeDeserializer extends JsonDeserializer<DebeziumEventDto.OperationType> {

    @Override
    public DebeziumEventDto.OperationType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return DebeziumEventDto.OperationType.fromCode(jsonParser.getValueAsString());
    }
}