package br.com.attornatus.api.config.exception;

import java.util.UUID;

public class PessoaInexistenteValidException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PessoaInexistenteValidException(String mensagem) {
		super(mensagem);
	}

}
