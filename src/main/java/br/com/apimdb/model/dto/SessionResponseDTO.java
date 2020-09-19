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
public class SessionResponseDTO extends ErrorResponseDTO {
	
	@JsonProperty("session_id")
	protected String sessionId;
	
	public boolean isSucesso() {
		return getSuccess()
				&& StringUtils.isNotBlank(sessionId);
	}
}
