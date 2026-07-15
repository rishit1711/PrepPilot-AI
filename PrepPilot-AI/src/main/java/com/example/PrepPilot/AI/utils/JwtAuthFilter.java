package com.example.PrepPilot.AI.utils;

import com.example.PrepPilot.AI.entity.User;
import com.example.PrepPilot.AI.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        try {

            final String header = request.getHeader("Authorization");

            if (header == null || !header.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            String token = header.substring(7);

            Long userId = jwtService.GetUserIdFromToken(token);

            if (userId != null &&
                    SecurityContextHolder.getContext().getAuthentication() == null) {

                User user = userService.getUserById(userId);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                user,
                                null,
                                user.getAuthorities()
                        );

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            filterChain.doFilter(request, response);

        } catch (ExpiredJwtException e) {

            sendError(response, HttpServletResponse.SC_UNAUTHORIZED,
                    "JWT Token has expired");

        } catch (JwtException | IllegalArgumentException e) {

            sendError(response, HttpServletResponse.SC_UNAUTHORIZED,
                    "Invalid JWT Token");

        }
    }

    private void sendError(HttpServletResponse response,
                           int status,
                           String message) throws IOException {

        response.setStatus(status);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        response.getWriter().write("""
                {
                    "status": %d,
                    "message": "%s"
                }
                """.formatted(status, message));
    }
}