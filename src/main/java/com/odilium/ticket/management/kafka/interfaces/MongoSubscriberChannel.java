package com.odilium.ticket.management.kafka.interfaces;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MongoSubscriberChannel {

    @Input(value = "ticket-manager-logs")
    SubscribableChannel input();
}
