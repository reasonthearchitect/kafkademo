package com.rta.sink.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface SinkMetadata {
	
		@Input("dogin")
    	SubscribableChannel read();
	 
}