package br.com.attornatus.api.config.exception;

public class PessoaExistenteValidException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PessoaExistenteValidException(String mensagem) {
		super(mensagem);
	}

}
