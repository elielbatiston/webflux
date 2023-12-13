package com.apirest.webflux.controller;

import com.apirest.webflux.document.Playlist;
import com.apirest.webflux.services.PlaylistService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
}
