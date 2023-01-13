package br.com.attornatus.api.dto.request;

import java.time.OffsetDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.attornatus.api.model.EnderecoModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosEditaEnderecoFormDTO(@NotBlank(message = "O campo logradouro está vazio") String logradouro,
		@NotBlank(message = "O campo cep está vazio") String cep,
		@NotBlank(message = "O campo número está vazio") String numero,
		@NotBlank(message = "O campo cidade está vazio") String cidade,
		@NotNull(message = "O campo principal está vazio") Boolean principal) {

	public DadosEditaEnderecoFormDTO(EnderecoModel endereco) {
		this(endereco.getLogradouro(), endereco.getCep(), endereco.getNumero(), endereco.getCidade(),
				endereco.getEnderecoPrincipal());
	}

}