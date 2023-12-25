//package com.example.orderservice.config;
//
//import brave.baggage.*;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.config.KafkaListenerContainerFactory;
//import org.springframework.kafka.core.*;
//import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
//import org.springframework.kafka.support.converter.RecordMessageConverter;
//import org.springframework.kafka.support.converter.StringJsonMessageConverter;
//import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
//import org.springframework.kafka.support.serializer.JsonDeserializer;
//import org.springframework.kafka.support.serializer.JsonSerializer;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//public class KafkaConfig {
//    @Value("${kafka.group.id}")
//    private String groupId;
//    @Value("${kafka.reply.topic}")
//    private String replyTopic;
//    @Value("${spring.kafka.producer.bootstrap-servers}")
//    private String bootstrapServers;
//    @Value("${kafka.json.type.mapping}")
//    private String mappings;
//
//    @Bean
//    public BaggagePropagationCustomizer baggagePropagationCustomizer() {
//        return (factoryBuilder) -> {
//            factoryBuilder.add(
//                    BaggagePropagationConfig.SingleBaggageField.remote(BaggageField.create("Correlation-Id")));
//        };
//    }
//
//    @Bean
//    public CorrelationScopeCustomizer correlationScopeCustomizer() {
//        return builder -> {
//            builder.add(CorrelationScopeConfig.SingleCorrelationField.newBuilder(BaggageField.create("Correlation-Id"))
//                    .flushOnUpdate()
//                    .build());
//        };
//    }
//
//    @Bean
//    public Map<String, Object> producerConfigs() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//        props.put(JsonSerializer.TYPE_MAPPINGS, mappings);
//        return props;
//    }
//
//    @Bean
//    public ProducerFactory<Object, Object> producerFactory() {
//        return new DefaultKafkaProducerFactory<>(producerConfigs());
//    }
//
//    @Bean
//    public KafkaTemplate<Object, Object> kafkaTemplate() {
//        return new KafkaTemplate<>(producerFactory());
//    }
//
//    /*@Bean
//    public ReplyingKafkaTemplate<Object, Object, Object> replyingKafkaTemplate(ProducerFactory<Object, Object> pf, ConcurrentKafkaListenerContainerFactory<Object, Object> factory) {
//        ConcurrentMessageListenerContainer<Object, Object> replyContainer = factory.createContainer(replyTopic);
//        replyContainer.getContainerProperties().setMissingTopicsFatal(false);
//        replyContainer.getContainerProperties().setGroupId(groupId);
//        return new ReplyingKafkaTemplate<>(pf, replyContainer);
//    }*/
//
//    @Bean
//    public Map<String, Object> consumerConfigs() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
//        props.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, JsonDeserializer.class);
//        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName());
//        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
//        return props;
//    }
//
//    @Bean
//    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<Object, Object>> kafkaListenerContainerFactory(KafkaTemplate<Object, Object> kafkaTemplate) {
//        ConcurrentKafkaListenerContainerFactory<Object, Object> factory =
//                new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        factory.setReplyTemplate(kafkaTemplate);
//        return factory;
//    }
//
//    @Bean
//    public ConsumerFactory<Object, Object> consumerFactory() {
//        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
//    }
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, ?> concurrentKafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, ?> factory =
//                new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory());
//        factory.setReplyTemplate(kafkaTemplate());
//        return factory;
//    }
//
//    @Bean
//    public RecordMessageConverter converter() {
//        return new StringJsonMessageConverter();
//    }
//    @Bean
//    public StringJsonMessageConverter jsonConverter() {
//        return new StringJsonMessageConverter();
//    }
//
//}