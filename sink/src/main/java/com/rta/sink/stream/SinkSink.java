package com.rta.sink.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import com.rta.sink.dto.DogDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.logging.Level;
import lombok.extern.java.Log;

@Log
@EnableBinding(SinkMetadata.class)
public class SinkSink {

    @Autowired
    ObjectMapper mapper;

    @StreamListener("dogin")
    public void sink(DogDto dogDto) {
        
        try {
            log.log(Level.INFO, "\n" + this.mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dogDto));
        } catch (Exception ex) {
            log.log(Level.SEVERE, "Exception thrown and could not map.", ex);
        }
    }
}
