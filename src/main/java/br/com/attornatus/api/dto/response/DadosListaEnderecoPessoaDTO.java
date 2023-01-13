package br.com.attornatus.api.dto.response;

import java.util.List;
import java.util.UUID;

import br.com.attornatus.api.dto.DadosListaEnderecosDTO;
import br.com.attornatus.api.model.EnderecoModel;
import br.com.attornatus.api.model.PessoaModel;

public record DadosListaEnderecoPessoaDTO(UUID id, String nome, List<DadosListaEnderecosDTO> enderecos) {

	public DadosListaEnderecoPessoaDTO(PessoaModel pessoa) {
		this(pessoa.getExternalId(), pessoa.getNome(), EnderecoModel.convertListaPessoaEndereco(pessoa.getEnderecos()));
	}

}
