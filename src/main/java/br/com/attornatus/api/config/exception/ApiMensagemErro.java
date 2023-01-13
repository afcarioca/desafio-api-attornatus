package br.com.attornatus.api.config.exception;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiMensagemErro {

	private HttpStatus status;
	private List<String> erros;

	public ApiMensagemErro(HttpStatus status, String erro) {
		this.status = status;
		this.erros = Arrays.asList(erro);
	}

}
