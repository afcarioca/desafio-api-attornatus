package br.com.attornatus.api.dto;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.attornatus.api.model.EnderecoModel;
import jakarta.validation.constraints.NotBlank;

public record DadosEditaEnderecoDTO(@NotBlank(message = "O campo logradouro está vazio") String logradouro,
		@NotBlank(message = "O campo cep está vazio") String cep,
		@NotBlank(message = "O campo número está vazio") String numero,
		@NotBlank(message = "O campo cidade está vazio") String cidade,
		@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss") OffsetDateTime dataDaCriacao,
		@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss") OffsetDateTime dataDaUltimaAtualizacao) {

	public DadosEditaEnderecoDTO(EnderecoModel endereco) {
		this(endereco.getLogradouro(), endereco.getCep(), endereco.getNumero(), endereco.getCidade(),
				endereco.getDataDaCriacao(), endereco.getDataDaUltimaAtualizacao());
	}

}
