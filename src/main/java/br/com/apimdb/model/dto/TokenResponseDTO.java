package br.com.apimdb.model.dto;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author <a href="https://github.com/guilhermegps"> Guilherme GPS </a>
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponseDTO extends ErrorResponseDTO {
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss z")
	@JsonProperty("expires_at")
	protected Date expiresAt;
	@JsonProperty("request_token")
	protected String token;
	
	public boolean isSucesso() {
		return getSuccess()
				&& StringUtils.isNotBlank(token);
	}
}
