package br.com.apimdb.model.dto;

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
public class ErrorResponseDTO {

	@JsonProperty("status_code")
	protected Integer statusCode;
	@JsonProperty("status_message")
	protected String statusMessage;
	@JsonProperty("success")
	protected Boolean success;
}
