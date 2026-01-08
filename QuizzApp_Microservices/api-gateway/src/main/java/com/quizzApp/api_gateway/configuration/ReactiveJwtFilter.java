package com.quizzApp.api_gateway.configuration;

import com.quizzApp.api_gateway.Service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;


@Component
public class ReactiveJwtFilter implements WebFilter {

    @Autowired
    private JwtService jwtService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain)
    {
        String path = exchange.getRequest().getURI().getPath();

        if (path.contains("/auth")) {
            System.out.println("Skipping JWT for: " + path);
            return chain.filter(exchange);
        }

        String reqHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        System.out.println("Header in filter: " + reqHeader);

        if(reqHeader==null || !reqHeader.startsWith("Bearer "))
        {
            System.out.println("❌ No valid Authorization header");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        String token = reqHeader.substring(7);

        try {
            System.out.println("🔍 Validating token...");
            jwtService.validateToken(token);
            System.out.println("✅ Token validation passed");
//            return chain.filter(exchange);
        } catch (Exception e) {
            System.out.println("🔥 Token validation failed:");
            e.printStackTrace();
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        System.out.println("➡️ Forwarding request to service...");
        return chain.filter(exchange)
                .doOnError(err -> {
                    System.out.println("💥 Error after filter chain:");
                    err.printStackTrace();
                })
                .doOnSuccess(v -> System.out.println("🎯 Response completed"));
    }
}

