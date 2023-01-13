package br.com.attornatus.api.service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.attornatus.api.model.EnderecoModel;
import br.com.attornatus.api.model.PessoaModel;
import br.com.attornatus.api.repository.EnderecoRepository;
import br.com.attornatus.api.repository.PessoaRepository;
import jakarta.transaction.Transactional;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepositorio;

	@Autowired
	private PessoaRepository pessoaRepository;

	/*
	 * @Transactional public List<EnderecoModel> edita(List<EnderecoModel>
	 * enderecos) {
	 * 
	 * List<EnderecoModel> enderecosExistentes =
	 * repository.findByCepInAndLogradouroInAndNumeroIn( enderecos.stream().map(e ->
	 * e.getCep()).collect(Collectors.toList()), enderecos.stream().map(e ->
	 * e.getLogradouro()).collect(Collectors.toList()), enderecos.stream().map(e ->
	 * e.getNumero()).collect(Collectors.toList()));
	 * 
	 * List<EnderecoModel> enderecosRepitidos = enderecos.stream() .filter(e2 ->
	 * enderecosExistentes.stream().anyMatch(e1 -> e1.getCep().equals(e2.getCep())
	 * && e1.getLogradouro().equals(e2.getLogradouro()) &&
	 * e1.getNumero().equals(e2.getNumero()))) .collect(Collectors.toList());
	 * 
	 * enderecos.removeAll(enderecosRepitidos);
	 * 
	 * this.repository.saveAll(enderecos);
	 * 
	 * List<EnderecoModel> lista = new LinkedList<>();
	 * lista.addAll(enderecosExistentes); lista.addAll(enderecos);
	 * 
	 * return lista;
	 * 
	 * }
	 */

	@Transactional
	public PessoaModel cadastra(EnderecoModel endereco, PessoaModel pessoa) {

		EnderecoModel enderecoExistente = this.enderecoRepositorio
				.findByLogradouroAndCepAndNumero(endereco.getLogradouro(), endereco.getCep(), endereco.getNumero());

		if (enderecoExistente != null) {

			for (EnderecoModel e : pessoa.getEnderecos()) {
				if (enderecoExistente.getCep() == e.getCep() && enderecoExistente.getLogradouro() == e.getLogradouro()
						&& enderecoExistente.getNumero() == e.getNumero()
						&& enderecoExistente.getNumero() == e.getNumero())
					return null;
			}

			if (endereco.getEnderecoPrincipal())
				pessoa.setEnderecoPrincipal(enderecoExistente.getExternalId());

			pessoa.adicionarEndereco(enderecoExistente);
			return this.pessoaRepository.save(pessoa);
		}

		if (endereco.getEnderecoPrincipal())
			pessoa.setEnderecoPrincipal(endereco.getExternalId());

		pessoa.adicionarEndereco(this.enderecoRepositorio.save(endereco));
		return this.pessoaRepository.save(pessoa);

	}

	@Transactional
	public EnderecoModel cadastra(EnderecoModel endereco) {
		EnderecoModel enderecoExistente = this.enderecoRepositorio
				.findByLogradouroAndCepAndNumero(endereco.getLogradouro(), endereco.getCep(), endereco.getNumero());

		if (enderecoExistente != null)
			return enderecoExistente;

		return this.enderecoRepositorio.save(endereco);
	}

	public List<EnderecoModel> edita(List<EnderecoModel> enderecos) {

		List<EnderecoModel> enderecosExistentes = new LinkedList<>();
		List<EnderecoModel> enderecosInexistentes = new LinkedList<>();

		for (EnderecoModel endereco : enderecos) {

			EnderecoModel enderecoModel = this.enderecoRepositorio.findByLogradouroAndNumeroAndCepAndCidade(
					endereco.getLogradouro(), endereco.getNumero(), endereco.getCep(), endereco.getCidade());
			if (enderecoModel != null) {
				enderecosExistentes.add(enderecoModel);
				continue;
			}

			enderecosInexistentes.add(endereco);
		}

		this.enderecoRepositorio.saveAll(enderecosInexistentes);

		List<EnderecoModel> enderecosExistentesEInexistentes = Stream.of(enderecosExistentes, enderecosInexistentes)
				.flatMap(x -> x.stream()).collect(Collectors.toList());

		return enderecosExistentesEInexistentes;
	}

	/*
	 * public EnderecoModel edita(EnderecoModel endereco) { EnderecoModel
	 * enderecoExistente =
	 * this.repository.findByLogradouroAndCepAndNumero(endereco.getLogradouro(),
	 * endereco.getCep(), endereco.getNumero());
	 * 
	 * 
	 * 
	 * if (enderecoExistente != null) { return enderecoExistente;
	 * 
	 * 
	 * return this.repository.save(endereco);
	 * 
	 * }
	 * 
	 */

	public EnderecoModel consulta(UUID enderecoPrincipal) {
		return this.enderecoRepositorio.findByExternalId(enderecoPrincipal);
	}

}
