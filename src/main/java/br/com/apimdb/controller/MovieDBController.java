package br.com.apimdb.controller;


import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apimdb.model.dto.AuthenticationDTO;
import br.com.apimdb.model.dto.SessionResponseDTO;
import br.com.apimdb.model.dto.TokenResponseDTO;
import br.com.apimdb.service.MovieDBService;

/**
 * @author <a href="https://github.com/guilhermegps"> Guilherme GPS </a>
 * 
 */
@RestController
@RequestMapping("/mdb")
public class MovieDBController {
	@Autowired
	private MovieDBService movieDBService;
	
	@GetMapping("/token")
	public Object criarToken() {
		TokenResponseDTO responseDTO = null;
		try {
			responseDTO = movieDBService.criarToken();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(responseDTO!=null && responseDTO.isSucesso())
			return responseDTO;
					
		return null;
	}
	
	@PostMapping("/sessao")
	public Object criarSessao(AuthenticationDTO param) {
		SessionResponseDTO responseDTO = null;
		try {
			if(StringUtils.isBlank(param.getToken()))
				param.setToken(movieDBService.criarToken().getToken());
			responseDTO = movieDBService.criarSessao(param);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(responseDTO!=null && responseDTO.isSucesso())
			return responseDTO;
					
		return null;
	}
	
	@GetMapping("/dados/conta")
	public Object dadosConta() {
		Map<String, Object> responseDTO = null;
		try {
			responseDTO = movieDBService.dadosConta();
		} catch (IOException e) {
			e.printStackTrace();
		}
					
		return responseDTO;
	}

}
