package br.com.attornatus.api.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.attornatus.api.config.exception.PessoaExistenteValidException;
import br.com.attornatus.api.config.exception.PessoaInexistenteValidException;
import br.com.attornatus.api.model.EnderecoModel;
import br.com.attornatus.api.model.PessoaModel;
import br.com.attornatus.api.repository.PessoaRepository;
import jakarta.transaction.Transactional;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repositorioPessoa;

	@Autowired
	private EnderecoService enderecoServico;

	public PessoaModel consulta(UUID id) throws Exception {

		if (!this.repositorioPessoa.existsByExternalId(id))
			throw new PessoaInexistenteValidException("Pessoa não encontrada!");

		return this.repositorioPessoa.findByExternalId(id);
	}

	@Transactional
	public PessoaModel cadastra(PessoaModel dados) {

		if (this.repositorioPessoa.existsByNomeAndDataDeNascimento(dados.getNome(), dados.getDataDeNascimento()))
			throw new PessoaExistenteValidException("A pessoa já existe!");

		EnderecoModel endereco = this.enderecoServico.cadastra(dados.getEnderecos().get(0));

		if (dados.getEnderecos().get(0).getEnderecoPrincipal())
			dados.setEnderecoPrincipal(endereco.getExternalId());

		dados.setEnderecoPrincipal(endereco.getExternalId());
		dados.setEnderecos(Arrays.asList(endereco));

		return this.repositorioPessoa.save(dados);

	}

	@Transactional
	public PessoaModel edita(PessoaModel dados) {
		if (!this.repositorioPessoa.existsByExternalId(dados.getExternalId()))
			throw new PessoaInexistenteValidException("Pessoa não encontrada!");

		PessoaModel pessoa = this.repositorioPessoa.findByExternalId(dados.getExternalId());
		pessoa.setNome(dados.getNome());
		pessoa.setDataDeNascimento(dados.getDataDeNascimento());
		pessoa.setDataDaUltimaAtualizacao(dados.getDataDaUltimaAtualizacao());

		List<EnderecoModel> enderecos = this.enderecoServico.edita(dados.getEnderecos());

		for (EnderecoModel endereco : enderecos) {
			Optional<EnderecoModel> principal = dados.getEnderecos().stream()
					.filter(e -> e.getCep().equals(endereco.getCep()) && endereco.getNumero().equals(e.getNumero())
							&& endereco.getLogradouro().equals(e.getLogradouro())
							&& endereco.getCidade().equals(e.getCidade()) && e.getEnderecoPrincipal() == true)
					.findFirst();

			if (principal != null) {
				pessoa.setEnderecoPrincipal(endereco.getExternalId());
				break;
			}
		}

		pessoa.setEnderecos(enderecos);
		return this.repositorioPessoa.save(pessoa);

	}

	public Page<PessoaModel> lista(Pageable paginacao) {
		return this.repositorioPessoa.findAll(paginacao);
	}

}
