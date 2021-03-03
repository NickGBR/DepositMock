package ru.interns.mock.demo.mvd.config;

import lombok.val;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import ru.interns.mock.demo.mvd.dto.impl.RequestDTO;


@Configuration
public class ActiveConfig {

    @Bean
    public ActiveMQTopic simpleTopic() {
        return new ActiveMQTopic("mvd_topic");
    }




//    @Bean
//    public MappingJackson2MessageConverter messageConverter() {
//        val messageConverter = new MappingJackson2MessageConverter();
//        messageConverter.setTypeIdPropertyName("content-type");
//        messageConverter.setTypeIdMappings(Collections.singletonMap("Person", RequestDTO.class));
//        return messageConverter;
//    }
}