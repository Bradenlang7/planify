package com.plannr.config;

import com.plannr.security.AuthorizationSocketInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE + 99)
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final AuthorizationSocketInterceptor authorizationSocketInterceptor;

    public WebSocketConfig(AuthorizationSocketInterceptor authorizationSocketInterceptor) {
        this.authorizationSocketInterceptor = authorizationSocketInterceptor;
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        System.out.println("Registering AuthorizationSocketInterceptor");
        registration.interceptors(authorizationSocketInterceptor); // Register the interceptor
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOrigins("http://localhost:8080", "http://192.168.0.100:19000")
                .withSockJS();
        //NEED TO SPECIFY ALLOWED ORIGINS
    }

}
