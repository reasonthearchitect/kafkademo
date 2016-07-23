package com.rta.processor.test.stream

import com.fasterxml.jackson.databind.ObjectMapper
import com.rta.processor.dto.CatDto
import com.rta.processor.dto.DogDto
import com.rta.processor.stream.DogProcessor
import spock.lang.Specification

import org.springframework.messaging.Message

/**
 * Created by rparry on 2016-07-22.
 */
class DogProcessorSpec extends Specification {

    DogProcessor dogProcessor;

    def setup() {
        this.dogProcessor = new DogProcessor();
        this.dogProcessor.mapper = new ObjectMapper();
    }

    def "simple test to make sure that the dog object is returned"() {

        setup:
        CatDto cat = [name: "bobo"];

        when:
        Message message = this.dogProcessor.handle(cat);
        def dogDto = new ObjectMapper().readValue(message.getPayload(), DogDto.class);

        then:
        message != null
        dogDto.name  == "Bobo";
    }
}
