package br.com.apimdb.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.apimdb.integration.MovieDBAPI;
import br.com.apimdb.model.dto.TokenResponseDTO;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@SpringBootTest
public class MovieDBServiceTest {
	@Value("${api.moviedb.key}")
	private String KEY;
	@Value("${api.moviedb.user}")
	private String USER;
	@Value("${api.moviedb.passwd}")
	private String PASSWD;

	@Test
	void dadosAutenticacaoValidos() throws IOException {
		assertNotNull(KEY);
		assertNotNull(USER);
		assertNotNull(PASSWD);
	}
	
	private MovieDBAPI getApi() {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("https://api.themoviedb.org/")
				.addConverterFactory(JacksonConverterFactory.create())
				.build();
		return retrofit.create(MovieDBAPI.class);
	}

	@Test
	void pedindoTokenSemKey() throws IOException {
		MovieDBAPI api = getApi();
		
		Call<TokenResponseDTO> cotacao = api.createToken("");
		Response<TokenResponseDTO> response = cotacao.execute();
		
		assertEquals(false, response.isSuccessful());
		assertNotNull(response.errorBody());		
	}
}
