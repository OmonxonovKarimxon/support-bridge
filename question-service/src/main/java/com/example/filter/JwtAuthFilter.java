package com.example.filter;

import com.example.dto.UserAccountDto;
import com.example.proxy.AuthProxy;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashSet;


@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private AuthProxy authProxy;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                     HttpServletResponse response,
                                     FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        UserAccountDto auth;

        try {
            ResponseEntity<UserAccountDto> userProfile = authProxy.getUserProfile(authHeader);
            auth = userProfile.getBody();
        } catch (RuntimeException e) {
            throw new BadCredentialsException(e.getMessage());
        }

        if (auth == null) {
            throw new BadCredentialsException("Something went wrong");
        }

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(auth.getId(), null, new HashSet<>());
        authToken.setDetails(auth);
        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request, response);
    }
}

