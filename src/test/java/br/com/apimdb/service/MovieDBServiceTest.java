package br.com.apimdb.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.apimdb.model.dto.TokenResponseDTO;

@SpringBootTest
public class MovieDBServiceTest {
	@Value("${api.moviedb.key}")
	private String KEY;
	@Autowired
	private MovieDBService movieDBService;

	@Test
	void criarToken() throws IOException {
		assertNotNull(KEY);
		TokenResponseDTO token = movieDBService.criarToken();
		assertNotNull(token);
		assertEquals(true, token.isSucesso());
	}

}
