package br.com.attornatus.api.config.exception;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class EntidadeExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		List<String> erros = ex.getBindingResult().getFieldErrors().stream().map(e -> e.getDefaultMessage())
				.collect(Collectors.toList());

		ApiMensagemErro mensagemErro = new ApiMensagemErro(HttpStatus.BAD_REQUEST, erros);

		return new ResponseEntity<>(mensagemErro, mensagemErro.getStatus());
	}

	@ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {

		ApiMensagemErro mensagemErro = new ApiMensagemErro(HttpStatus.BAD_REQUEST, "Uri inválida");

		return new ResponseEntity<>(mensagemErro, mensagemErro.getStatus());

	}

	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {

		ApiMensagemErro mensagemErro = new ApiMensagemErro(HttpStatus.BAD_REQUEST, "Corpo da requisição inválido");

		return new ResponseEntity<>(mensagemErro, mensagemErro.getStatus());

	}

	@ExceptionHandler(PessoaInexistenteValidException.class)
	public ResponseEntity<Object> handlePessoaInexistenteValidException(PessoaInexistenteValidException ex) {

		ApiMensagemErro mensagemErro = new ApiMensagemErro(HttpStatus.BAD_REQUEST, ex.getMessage());

		return new ResponseEntity<>(mensagemErro, mensagemErro.getStatus());
	}

	@ExceptionHandler(PessoaExistenteValidException.class)
	public ResponseEntity<Object> handlePessoaExistenteValidException(PessoaExistenteValidException ex) {

		ApiMensagemErro mensagemErro = new ApiMensagemErro(HttpStatus.BAD_REQUEST, ex.getMessage());

		return new ResponseEntity<>(mensagemErro, mensagemErro.getStatus());
	}
	
	
	@ExceptionHandler(EnderecoExistenteValidException.class)
	public ResponseEntity<Object> handleEmpresaExistenteValidException(EnderecoExistenteValidException ex) {

		ApiMensagemErro mensagemErro = new ApiMensagemErro(HttpStatus.BAD_REQUEST, ex.getMessage());

		return new ResponseEntity<>(mensagemErro, mensagemErro.getStatus());
	}
}
