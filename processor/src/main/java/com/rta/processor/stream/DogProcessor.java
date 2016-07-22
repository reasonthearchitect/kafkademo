package com.rta.processor.stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.util.StringUtils;
import java.math.BigDecimal;

import com.rta.processor.dto.CatDto;
import com.rta.processor.dto.DogDto;

@EnableBinding(DogMetadata.class)
public class DogProcessor {

	@Autowired
    ObjectMapper mapper;

	@StreamListener("catin")
  	@SendTo("dogin")
  	public Message<?> handle(CatDto catDto) {

  		DogDto dogDto = new DogDto();
  		dogDto.setName(StringUtils.capitalize(catDto.getName()));
  		dogDto.setAge(new BigDecimal(1));

  		try {
	  		return MessageBuilder.withPayload(
	                    mapper.writeValueAsString(dogDto)
	            )
	            .setHeader("contentType", "application/json")
	            .build();
	    } catch (Exception e) { throw new RuntimeException(e);}
  	}
}