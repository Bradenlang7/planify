package com.planify.planify.security;

import com.planify.planify.entity.User;
import com.planify.planify.jwt.JwtUtil;
import com.planify.planify.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

@Component
//Class for a custom AuthenticationSuccessHandler. Used in SecurityConfig.
public class OAuth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtUtil jwtUtil;
    private UserService userService;


    public OAuth2AuthenticationSuccessHandler(JwtUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2AuthenticationToken authToken = (OAuth2AuthenticationToken) authentication;
        OAuth2User oauth2User = authToken.getPrincipal();
        System.out.println("Authentication Token: " + authToken);

        Map<String, Object> attributes = oauth2User.getAttributes();
        String email = (String) attributes.get("email");
        User user = userService.getUserByEmail(email);

        if (user != null) {
            String jwt = jwtUtil.generateToken(email, user.getId().toString());
            String userName = user.getUsername();
            String redirectUrl = String.format(
                    "exp://192.168.0.100:8081/?token=%s&userName=%s", //HARDCODED. Needs to be refactored
                    URLEncoder.encode(jwt, "UTF-8"),
                    URLEncoder.encode(userName, "UTF-8")
            );
            System.out.println("redirect url: " + redirectUrl);
            response.sendRedirect(redirectUrl);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"error\":\"User not found. Please register first.\"}");
        }
    }
}