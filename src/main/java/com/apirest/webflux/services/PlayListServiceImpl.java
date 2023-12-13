package com.apirest.webflux.services;

import com.apirest.webflux.document.Playlist;
import com.apirest.webflux.repository.PlaylistRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PlayListServiceImpl implements PlaylistService {

	private final PlaylistRepository repository;

	public PlayListServiceImpl(final PlaylistRepository repository) {
		this.repository = repository;
	}

	@Override
	public Flux<Playlist> findAll() {
		return repository.findAll();
	}

	@Override
	public Mono<Playlist> findById(final String id) {
		return repository.findById(id);
	}

	@Override
	public Mono<Playlist> save(final Playlist playlist) {
		return repository.save(playlist);
	}
}
