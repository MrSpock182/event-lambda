package io.github.mrspock182.kafka_publish.configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SqsConfig {
    @Bean
    public AmazonSQS amazonSQS(
            @Value("${spring.sql.region}") final String region,
            @Value("${spring.sql.access-key}") final String accessKey,
            @Value("${spring.sql.secret-key}") final String secretKey) {
        return AmazonSQSClient.builder()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .build();
    }
}