package br.com.attornatus.api.config.exception;

public class EnderecoExistenteValidException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EnderecoExistenteValidException(String mesangem) {
		super(mesangem);
	}
}
