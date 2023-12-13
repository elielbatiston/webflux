package com.apirest.webflux.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Playlist {

	@Id
	private String id;
	private String nome;

	public Playlist(final String id, final String nome) {
		this.id = id;
		this.nome = nome;
	}

	public String getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
}
