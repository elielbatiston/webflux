package com.apirest.webflux.controller;

import com.apirest.webflux.document.Playlist;
import com.apirest.webflux.services.PlaylistService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;

@RestController
@RequestMapping("playlist")
public class PlaylistController {

	private final PlaylistService service;

	public PlaylistController(final PlaylistService service) {
		this.service = service;
	}

	@GetMapping
	public Flux<Playlist> getPlaylist() {
		return service.findAll();
	}

	@GetMapping("{id}")
	public Mono<Playlist> getPlaylistId(@PathVariable final String id) {
		return service.findById(id);
	}

	@PostMapping
	public Mono<Playlist> save(@RequestBody final Playlist playlist) {
		return service.save(playlist);
	}

	@GetMapping(value = "events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Tuple2<Long, Playlist>> getPlaylistByEvents()  {
		final Flux<Long> interval = Flux.interval(Duration.ofSeconds(10));
		final Flux<Playlist> events = service.findAll();
		System.out.println("Passou aqui events");
		return Flux.zip(interval, events);
	}

}
