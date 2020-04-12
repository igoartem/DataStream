package org.example.DataStreams;

import org.apache.kafka.common.utils.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import kafka.zk.AdminZkClient;
import kafka.zk.KafkaZkClient;

@Configuration
@PropertySource("classpath:app.properties")
public class KafkaConfig {
    private static final Logger log = LoggerFactory.getLogger(KafkaConfig.class);

    @Value("${connection.url}")
    private String connectionUrl;

    @Value("${is.secure}")
    private String isSecure;

    @Value("${session.timeout}")
    private String sessionTimeout;

    @Value("${connection.timeout}")
    private String connectionTimeoutMs;

    @Value("${max.in.flight.requests}")
    private String maxInFlightRequests;

    @Value("${metric.group}")
    private String metricGroup;

    @Value("${metric.type}")
    private String metricType;

    @Bean
    public KafkaZkClient kafkaZkClient() {
        Time time = Time.SYSTEM;
        log.debug("Init kafkaZkClient connection {}", connectionUrl);
        KafkaZkClient zkClient = KafkaZkClient
                .apply(connectionUrl, Boolean.parseBoolean(isSecure), Integer.parseInt(sessionTimeout), Integer.parseInt(connectionTimeoutMs),
                        Integer.parseInt(maxInFlightRequests), time, metricGroup, metricType, KafkaZkClient.apply$default$9());
        return zkClient;
    }

    @Bean
    public AdminZkClient adminZkClient(KafkaZkClient kafkaZkClient) {
        return new AdminZkClient(kafkaZkClient);
    }
}
