package com.rta.source.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;
import org.springframework.cloud.stream.annotation.EnableBinding;

import com.rta.source.dto.CatDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.logging.Level;
import lombok.extern.java.Log;

@Log
@Component
@EnableBinding(CatMetadata.class)
public class CatSource {

        @Autowired @Qualifier("catin")
        private MessageChannel post;

        @Autowired
        ObjectMapper mapper;

        public void send(CatDto catDto) {
                try {
                        Message<?> message = MessageBuilder.withPayload(
                                        mapper.writerWithDefaultPrettyPrinter().writeValueAsString(catDto)
                                )
                                .setHeader("contentType", "application/json")
                                .build();
                        post.send(message);
                } catch (Exception ex) {
                        log.log(Level.SEVERE, "Error trying to send a message to a queue: ", ex);
                }        
        }

}
