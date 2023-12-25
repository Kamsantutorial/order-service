//package com.example.orderservice.config;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.example.orderservice.dto.UserDTO;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.ThreadLocalRandom;
//
//@Component
//public class KafkaConsumer {
//
//    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
//
//    @KafkaListener(
//            topics = "${kafka.request.topic}"
//            , groupId = "${kafka.group.id}",
//            autoStartup = "false"
//    )
//    public void receivedMessage(UserDTO userDTO) throws JsonProcessingException {
////        ObjectMapper mapper = new ObjectMapper();
////        String jsonString = mapper.writeValueAsString(userDTO);
//        logger.info("Json message received using Kafka listener {}", userDTO);
//    }
//
//    @KafkaListener(topics = "${kafka.request.topic}", groupId = "${kafka.group.id}",  autoStartup = "false")
//    @SendTo
//    public UserDTO handle(UserDTO userDTO) {
//        System.out.println("Calculating Result...");
//        double total = ThreadLocalRandom.current().nextDouble(2.5, 9.9);
//        return userDTO;
//    }
//}
