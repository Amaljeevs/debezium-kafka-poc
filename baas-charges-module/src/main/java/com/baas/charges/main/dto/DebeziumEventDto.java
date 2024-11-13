package com.baas.charges.main.dto;


import com.baas.charges.main.config.jackson.MillisToLocalDateTimeDeserializer;
import com.baas.charges.main.config.jackson.OperationTypeDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDateTime;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DebeziumEventDto {

    private Payload payload;

    @Override
    public String toString() {
        return "DebeziumEventDto{" +
                "payload=" + payload +
                '}';
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Payload {
        private Source source;
        private Map<String, Object> before;
        private Map<String, Object> after;

        @JsonDeserialize(using = OperationTypeDeserializer.class)
        @JsonProperty("op")
        @JsonFormat
        private OperationType operationType;

        public Source getSource() {
            return source;
        }

        public void setSource(Source source) {
            this.source = source;
        }

        public Map<String, Object> getBefore() {
            return before;
        }

        public void setBefore(Map<String, Object> before) {
            this.before = before;
        }

        public Map<String, Object> getAfter() {
            return after;
        }

        public void setAfter(Map<String, Object> after) {
            this.after = after;
        }

        public OperationType getOperationType() {
            return operationType;
        }

        public void setOperationType(OperationType operationType) {
            this.operationType = operationType;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Source {
            @JsonProperty("ts_ms")
            @JsonDeserialize(using = MillisToLocalDateTimeDeserializer.class)
            private LocalDateTime transactionTime;

            private String db;
            private String schema;
            private String table;

            @JsonProperty("txId")
            private long transactionId;

            public LocalDateTime getTransactionTime() {
                return transactionTime;
            }

            public void setTransactionTime(LocalDateTime transactionTime) {
                this.transactionTime = transactionTime;
            }

            public String getDb() {
                return db;
            }

            public void setDb(String db) {
                this.db = db;
            }

            public String getSchema() {
                return schema;
            }

            public void setSchema(String schema) {
                this.schema = schema;
            }

            public String getTable() {
                return table;
            }

            public void setTable(String table) {
                this.table = table;
            }

            public long getTransactionId() {
                return transactionId;
            }

            public void setTransactionId(long transactionId) {
                this.transactionId = transactionId;
            }

            @Override
            public String toString() {
                return "Source{" +
                        "transactionTime=" + transactionTime +
                        ", db='" + db + '\'' +
                        ", schema='" + schema + '\'' +
                        ", table='" + table + '\'' +
                        ", transactionId=" + transactionId +
                        '}';
            }
        }
    }

    public enum OperationType {
        CREATE("c"),
        UPDATE("u"),
        DELETE("d"),
        READ("r");

        private final String code;

        OperationType(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }

        @Override
        public String toString() {
            return "OperationType{" +
                    "code='" + code + '\'' +
                    '}';
        }

        public static OperationType fromCode(String code) {
            for (OperationType type : OperationType.values()) {
                if (type.getCode().equals(code)) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Unknown operation type code: " + code);
        }
    }
}