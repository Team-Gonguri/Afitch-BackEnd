package com.editor.security;

import com.editor.exception.ErrorResponse;
import com.editor.security.exceptions.SecurityExceptionType;
import com.editor.security.exceptions.NotBearerTokenException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = request.getHeader("Authorization");
            if (token != null) {
                String parsingToken = jwtProvider.parseToken(token);
                SecurityContextHolder.getContext().setAuthentication(jwtProvider.getAuthentication(parsingToken));
            }
            filterChain.doFilter(request, response);
        } catch (SignatureException e) {
            sendErrorMessage(response, SecurityExceptionType.SIGNATURE_EXCEPTION);
        } catch (MalformedJwtException e) {
            sendErrorMessage(response, SecurityExceptionType.MALFORMED_TOKEN);
        } catch (ExpiredJwtException e) {
            sendErrorMessage(response, SecurityExceptionType.EXPIRED_TOKEN);
        } catch (NotBearerTokenException e) {
            sendErrorMessage(response, SecurityExceptionType.NOT_BEARER_FORMAT);
        }
    }

    private void sendErrorMessage(HttpServletResponse response, SecurityExceptionType type) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(
                objectMapper.writeValueAsString(new ErrorResponse(type.getMessage(), type.getCode()))
        );
    }
}
