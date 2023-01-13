package br.com.attornatus.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosEnderecoFormDTO(@NotBlank(message = "O campo endereço está vazio") String logradouro,
		@NotBlank(message = "O campo cep está vazio") @Pattern(regexp = "[0-9]{5}-[0-9]{3}", message = "Formato de cep inválido") String cep,
		@NotBlank(message = "O campo número está vazio") String numero,
		@NotBlank(message = "O campo cidade está vazio") String cidade,
		@NotNull(message = "o campo pricipal está vazio") Boolean principal) {

}
