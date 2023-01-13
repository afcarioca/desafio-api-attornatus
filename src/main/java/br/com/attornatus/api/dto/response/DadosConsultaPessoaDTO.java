package br.com.attornatus.api.dto.response;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.attornatus.api.model.EnderecoModel;
import br.com.attornatus.api.model.PessoaModel;

public record DadosConsultaPessoaDTO(UUID id, String nome,
		@JsonFormat(pattern = "dd-MM-yyyy") LocalDate dataDeNascimento,
		@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss") OffsetDateTime dataDaCriacao,
		@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss") OffsetDateTime dataDaUltimaAtualizacao,
		List<DadosConsultaEnderecoDTO> enderecos) {

	public DadosConsultaPessoaDTO(PessoaModel pessoa) {
		this(pessoa.getExternalId(), pessoa.getNome(), pessoa.getDataDeNascimento(), pessoa.getDataDaCriacao(),
				pessoa.getDataDaUltimaAtualizacao(), EnderecoModel.converteParaConsulta(pessoa.getEnderecos()));

	}

}