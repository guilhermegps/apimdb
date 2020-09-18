package br.com.apimdb.controller;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@GetMapping
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

}
