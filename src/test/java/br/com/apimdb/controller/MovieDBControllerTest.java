package br.com.apimdb.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.apimdb.model.dto.TokenResponseDTO;
import br.com.apimdb.service.MovieDBService;

@SpringBootTest
public class MovieDBControllerTest {
	@Autowired
	private MovieDBService movieDBService;

	@Test
	void criarToken() throws IOException {
		TokenResponseDTO token = movieDBService.criarToken();
		assertNotNull(token);
		assertEquals(true, token.isSucesso());
	}

}
