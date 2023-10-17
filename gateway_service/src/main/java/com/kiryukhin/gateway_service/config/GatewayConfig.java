package com.kiryukhin.gateway_service.config;

import com.kiryukhin.gateway_service.filter.CustomFilter;
import com.kiryukhin.gateway_service.filter.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GatewayConfig {
    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    GlobalFilter customFilter(){
        return new CustomFilter();
    }

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth", r -> r.path("/auth/**").filters(f -> f.filter(jwtAuthFilter)).uri("lb://auth-service"))
                .route("portfolio", r -> r.path("/portfolio/**").filters(f -> f.filter(jwtAuthFilter)).uri("lb://portfolio-service"))
                .route("performance", r -> r.path("/performance/**").filters(f -> f.filter(jwtAuthFilter)).uri("lb://performance-service"))
                .build();
    }
}
