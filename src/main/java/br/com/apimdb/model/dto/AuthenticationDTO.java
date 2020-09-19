package br.com.apimdb.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author <a href="https://github.com/guilhermegps"> Guilherme GPS </a>
 * 
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDTO {
	private String username;
	private String password;
	@JsonProperty("request_token")
	private String token;
}
