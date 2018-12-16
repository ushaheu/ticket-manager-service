package com.odilium.ticket.management;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class LoginEntryWebsocketConfiguration extends AbstractWebSocketMessageBrokerConfigurer {

    @Value(value = "${stomp.endpoint}")
    private String stompEndpoint;

    @Value(value = "${stomp.destination.prefix}")
    private String stompDestinationPrefix;

    @Value(value = "${activemq.broker.ip}")
    private String brokerRelayHost;

    @Value(value = "${activemq.broker.port}")
    private int brokerRelayPort;

    @Value(value = "${broker.heartbeat.interval}")
    private long heartBeatInterval;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry){
        registry.enableStompBrokerRelay(stompDestinationPrefix)
                .setRelayHost(brokerRelayHost)
                .setRelayPort(brokerRelayPort);
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(stompEndpoint).setAllowedOrigins("*").withSockJS();
    }

}
