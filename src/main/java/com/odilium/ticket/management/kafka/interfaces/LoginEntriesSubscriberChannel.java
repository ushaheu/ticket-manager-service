package com.odilium.ticket.management.kafka.interfaces;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface LoginEntriesSubscriberChannel {
    @Input(value = "login-entries")
    SubscribableChannel input();
}
