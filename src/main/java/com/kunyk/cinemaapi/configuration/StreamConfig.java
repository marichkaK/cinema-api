package com.kunyk.cinemaapi.configuration;

import com.kunyk.cinemaapi.messaging.stream.TicketStream;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBinding(TicketStream.class)
public class StreamConfig {

}
