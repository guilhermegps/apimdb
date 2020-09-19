package br.com.apimdb.integration;

import br.com.apimdb.model.dto.AuthenticationDTO;
import br.com.apimdb.model.dto.SessionResponseDTO;
import br.com.apimdb.model.dto.TokenResponseDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MovieDBAPI {

	@GET("3/authentication/token/new")
    Call<TokenResponseDTO> createToken(@Query("api_key") String key);
	
	@POST("3/authentication/token/validate_with_login")
    Call<TokenResponseDTO> validateWithLogin(@Query("api_key") String key, @Body AuthenticationDTO param);
	
	@POST("3/authentication/session/new")
    Call<SessionResponseDTO> createNewSession(@Query("api_key") String key, @Body AuthenticationDTO param);

}
