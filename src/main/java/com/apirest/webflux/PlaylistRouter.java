package com.apirest.webflux;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;

@Configuration
public class PlaylistRouter {

	@Bean
	public RouterFunction<ServerResponse> route(final PlaylistHandler handler) {
		return RouterFunctions
			.route(GET("/playlist-funcional").and(accept(MediaType.APPLICATION_JSON)), handler::findAll)
			.andRoute(GET("/playlist-funcional/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::findById)
			.andRoute(POST("/playlist-funcional").and(accept(MediaType.APPLICATION_JSON)), handler::save);
	}
}
