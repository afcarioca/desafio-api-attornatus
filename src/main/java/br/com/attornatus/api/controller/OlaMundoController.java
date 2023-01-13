package br.com.attornatus.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oi")
public class OlaMundoController {

	@GetMapping
	public String oi() {
		return "Olá, Mundo!";
	}

}
