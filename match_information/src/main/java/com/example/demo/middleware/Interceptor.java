package com.example.demo.middleware;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Enumeration;
import java.util.stream.Collectors;

@Order(value = Ordered.HIGHEST_PRECEDENCE)
@Component
@WebFilter(filterName = "RequestCachingFilter", urlPatterns = "/*")
public class Interceptor extends OncePerRequestFilter {

    private final static Logger LOGGER = LoggerFactory.getLogger(Interceptor.class);


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        Instant start = Instant.now();

        CachedHttpServletRequest cachedHttpServletRequest = new CachedHttpServletRequest(request);
        CachedHttpServletResponse cachedHttpServletResponse = new CachedHttpServletResponse(response);

        LOGGER.info("URL: " + cachedHttpServletRequest.getRequestURI());

        Enumeration<String> headerNames = cachedHttpServletRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            LOGGER.info(headerName + ": " + cachedHttpServletRequest.getHeader(headerName));
        }
        LOGGER.info("REQUEST DATA: " + new BufferedReader(new InputStreamReader(cachedHttpServletRequest.getInputStream(), StandardCharsets.UTF_8)).lines().collect(Collectors.joining("\n")));
        filterChain.doFilter(cachedHttpServletRequest, cachedHttpServletResponse);
        cachedHttpServletResponse.flushBuffer();

        LOGGER.info("RESPONSE DATA: " + new String(cachedHttpServletResponse.getCopy(), response.getCharacterEncoding()));
        LOGGER.info("PROCESS TIME: {} ms.", Duration.between(start, Instant.now()).toMillis());
    }

}