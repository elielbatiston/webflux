package com.apirest.webflux;

import com.apirest.webflux.document.Playlist;
import com.apirest.webflux.services.PlaylistService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;

@Component
public class PlaylistHandler {

	private final PlaylistService service;

	public PlaylistHandler(final PlaylistService service) {
		this.service = service;
	}

	public Mono<ServerResponse> findAll(final ServerRequest request) {
		return ok()
			.contentType(MediaType.APPLICATION_JSON)
			.body(service.findAll(), Playlist.class);
	}

	public Mono<ServerResponse> findById(final ServerRequest request) {
		final String id = request.pathVariable("id");
		return ok()
			.contentType(MediaType.APPLICATION_JSON)
			.body(service.findById(id), Playlist.class);
	}

	public Mono<ServerResponse> save(final ServerRequest request) {
		final Mono<Playlist> playlist = request.bodyToMono(Playlist.class);
		return ok()
			.contentType(MediaType.APPLICATION_JSON)
			.body(fromPublisher(playlist.flatMap(service::save), Playlist.class));
	}
}
