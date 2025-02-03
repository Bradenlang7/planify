package com.plannr.security;

import com.plannr.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Component;

@Order(Ordered.HIGHEST_PRECEDENCE + 99)
@Component
@RequiredArgsConstructor
//Class intercepts the websocket CONNECT request from the client and performs JWT auth prior to sending CONNECTED.
public class AuthorizationSocketInterceptor implements ChannelInterceptor {

    private final JwtUtil jwtUtil; // Utility class for JWT validation

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        System.out.println("Interceptor: Entered preSend");
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        System.out.println("All Headers: " + accessor.toNativeHeaderMap());

        System.out.println("StompHeaderAccessor: " + accessor);
        if (accessor != null && StompCommand.CONNECT.equals(accessor.getCommand())) {
            String authorization = accessor.getFirstNativeHeader("Authorization");

            if (authorization == null || !authorization.startsWith("Bearer ")) {
                throw new IllegalArgumentException("Missing or invalid Authorization header");
            }

            String token = authorization.substring(7);
            String email = jwtUtil.extractEmail(token);

            boolean isValid = jwtUtil.validateToken(token, email);
            System.out.println("isValid: " + isValid);
            if (!isValid) {
                throw new IllegalArgumentException("Invalid Authorization token");
            }

        }


        return message;
    }
}