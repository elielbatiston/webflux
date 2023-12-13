package com.apirest.webflux;

import com.apirest.webflux.document.Playlist;
import com.apirest.webflux.repository.PlaylistRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Component
public class DummyData implements CommandLineRunner {

	private final PlaylistRepository repository;

	public DummyData(final PlaylistRepository repository) {
		this.repository = repository;
	}

	@Override
	public void run(String... args) throws Exception {
		repository.deleteAll()
			.thenMany(
				Flux.just(
					"API RET Spring Boot",
					"Deploy de uma aplicação java no IBM Cloud",
					"Java 8",
					"Github",
					"Spring Security",
					"Web Service RESTFULL",
					"Bean no Spring Framework"
				)
				.map(nome -> new Playlist(UUID.randomUUID().toString(), nome))
				.flatMap(repository::save)
			)
			.subscribe(System.out::println);
	}
}
