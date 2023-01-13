package br.com.attornatus.api.dto.response;

import br.com.attornatus.api.model.EnderecoModel;
import br.com.attornatus.api.model.PessoaModel;

public record DadosListaEnderecoPrincipalPessoaDTO(String nome, String logradouro, String cep, String numero,
		String cidade) {

	public DadosListaEnderecoPrincipalPessoaDTO(EnderecoModel endereco, PessoaModel pessoa) {
		this(pessoa.getNome(), endereco.getLogradouro(), endereco.getCep(), endereco.getNumero(), endereco.getCidade());
	}
}
