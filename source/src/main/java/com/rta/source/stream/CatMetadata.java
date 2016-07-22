package com.rta.source.stream;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public interface CatMetadata {
	 
		@Output("catin")
    	MessageChannel post();
}