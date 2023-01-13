package br.com.attornatus.api.dto.response;

import java.time.OffsetDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.attornatus.api.model.EnderecoModel;

public record DadosCadastroEnderecoDTO(UUID id, String logradouro, String cep, String numero, String cidade,
		@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss") OffsetDateTime dataDaCriacao,
		@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss") OffsetDateTime dataDaUltimaAtualizacao) {

	public DadosCadastroEnderecoDTO(EnderecoModel endereco) {
		this(endereco.getExternalId(), endereco.getLogradouro(), endereco.getCep(), endereco.getNumero(),
				endereco.getCidade(), endereco.getDataDaCriacao(), endereco.getDataDaUltimaAtualizacao());
	}

}
