package br.com.apimdb.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.apimdb.integration.MovieDBAPI;
import br.com.apimdb.model.dto.AuthenticationDTO;
import br.com.apimdb.model.dto.SessionResponseDTO;
import br.com.apimdb.model.dto.TokenResponseDTO;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author <a href="https://github.com/guilhermegps"> Guilherme GPS </a>
 * 
 */
@Service
@RequestScope
public class MovieDBService extends SendDataService<Object> {
	@Value("${api.moviedb.key}")
	private String KEY;
	@Value("${api.moviedb.user}")
	private String USER;
	@Value("${api.moviedb.passwd}")
	private String PASSWD;
	
	private MovieDBAPI api;
	private final ObjectMapper oMapper = new ObjectMapper();
	
	public MovieDBService() {
		super("https://api.themoviedb.org/");
		setApi();
	}

	@Override
	protected void setApi() {
		this.api = super.createApi(MovieDBAPI.class);
	}
	
	public TokenResponseDTO criarToken() throws IOException{
		Call<TokenResponseDTO> token = api.createToken(KEY);
		Response<TokenResponseDTO> response = token.execute();
		
		if (response.isSuccessful())
	        return response.body();
		else if(response.errorBody() != null)
			return oMapper.readValue(response.errorBody().string(), TokenResponseDTO.class);

		throw new IOException("Falha ao obter token");
	}
	
	public TokenResponseDTO validarComLogin(AuthenticationDTO param) throws IOException{
		Call<TokenResponseDTO> token = api.validateWithLogin(KEY, param);
		Response<TokenResponseDTO> response = token.execute();
		
		if (response.isSuccessful())
	        return response.body();
		else if(response.errorBody() != null)
			return oMapper.readValue(response.errorBody().string(), TokenResponseDTO.class);

		throw new IOException("Falha ao obter token");
	}
	
	public SessionResponseDTO criarSessao(AuthenticationDTO param) throws IOException{
		validarComLogin(param);
		Call<SessionResponseDTO> sessao = api.createNewSession(KEY, param);
		Response<SessionResponseDTO> response = sessao.execute();

		if (response.isSuccessful())
	        return response.body();
		else if(response.errorBody() != null)
			return oMapper.readValue(response.errorBody().string(), SessionResponseDTO.class);

		throw new IOException("Falha ao obter sessão");
	}
}
