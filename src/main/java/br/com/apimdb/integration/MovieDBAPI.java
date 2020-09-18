package br.com.apimdb.integration;

import br.com.apimdb.model.dto.TokenResponseDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDBAPI {

	@GET("3//authentication/token/new")
    Call<TokenResponseDTO> createToken(@Query("api_key") String key);

}
