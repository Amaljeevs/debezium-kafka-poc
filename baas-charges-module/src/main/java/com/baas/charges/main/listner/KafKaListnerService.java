package com.baas.charges.main.listner;

import com.baas.charges.main.dto.DebeziumEventDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;


@Service
public class KafKaListnerService {

    @KafkaListener(topics = {"pg-changes.public.api", "pg-changes.public.api_usage"}, groupId = "debezium-consumer-group")
    public void listen(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {
        System.out.println("Received message on topic " + record.topic() + ": " + record.value());
        try {
            DebeziumEventDto eventDto = new ObjectMapper().readValue(record.value(), DebeziumEventDto.class);
            eventData(eventDto);
        } catch (Exception e) {
            e.printStackTrace();
        }

        acknowledgment.acknowledge();
    }

    public void eventData(DebeziumEventDto eventDto) {
        try {
            final var payload = eventDto.getPayload();
            System.out.println("Payload Operation : " + eventDto.getPayload().getOperationType().getCode());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


