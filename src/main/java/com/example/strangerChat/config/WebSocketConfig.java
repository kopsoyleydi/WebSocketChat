package com.example.strangerChat.config;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;


@Log4j2
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Value("spring.rabbitmq.host")
	@NonFinal String host;

	@Value("spring.rabbitmq.host.username")
	@NonFinal String username;

	@Value("spring.rabbitmq.host.password")
	@NonFinal String password;

	public static final String TOPIC_DESTINATION_PREFIX = "/topic";

	public static final String REGISTRY = "/ws";
	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic");
		config.setApplicationDestinationPrefixes("/app");
		config.enableStompBrokerRelay(TOPIC_DESTINATION_PREFIX)
				.setRelayHost(host)
				.setClientLogin(username)
				.setClientPasscode(password)
				.setSystemLogin(username)
				.setSystemPasscode(password);
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint(REGISTRY)
				.withSockJS();
	}
}
