package br.com.attornatus.api.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.attornatus.api.config.exception.EnderecoExistenteValidException;
import br.com.attornatus.api.dto.DadosListaEnderecosDTO;
import br.com.attornatus.api.dto.request.DadosEnderecoFormDTO;
import br.com.attornatus.api.dto.response.DadosCadastroPessoaDTO;
import br.com.attornatus.api.dto.response.DadosListaEnderecoPessoaDTO;
import br.com.attornatus.api.dto.response.DadosListaEnderecoPrincipalPessoaDTO;
import br.com.attornatus.api.model.EnderecoModel;
import br.com.attornatus.api.model.PessoaModel;
import br.com.attornatus.api.service.EnderecoService;
import br.com.attornatus.api.service.PessoaService;

@RestController
@RequestMapping("/api/pessoa")
public class EnderecoController {

	@Autowired
	private PessoaService servicoPessoa;

	@Autowired
	private EnderecoService servicoEndereco;

	@GetMapping("/{id}/endereco/principal")
	public ResponseEntity<DadosListaEnderecoPrincipalPessoaDTO> listaPrincial(@PathVariable UUID id) throws Exception {
		PessoaModel pessoa = this.servicoPessoa.consulta(id);
		return ResponseEntity.ok(new DadosListaEnderecoPrincipalPessoaDTO(
				this.servicoEndereco.consulta(pessoa.getEnderecoPrincipal()), pessoa));
	}

	@GetMapping("/{id}/endereco")
	public ResponseEntity<DadosListaEnderecoPessoaDTO> lista(@PathVariable UUID id) throws Exception {
		return ResponseEntity.ok(new DadosListaEnderecoPessoaDTO(this.servicoPessoa.consulta(id)));
	}

	@PostMapping("/{id}/endereco")
	public ResponseEntity<DadosCadastroPessoaDTO> cadastra(@RequestBody DadosEnderecoFormDTO dados,
			@PathVariable UUID id, UriComponentsBuilder builder) throws Exception {
		PessoaModel pessoa = this.servicoPessoa.consulta(id);

		if (this.servicoEndereco.cadastra(new EnderecoModel(dados), pessoa) == null)
			throw new EnderecoExistenteValidException("A pessoa já possui este endereço");

		return ResponseEntity.created(builder.path("/pessoa/{id}").buildAndExpand(pessoa.getEnderecos()).toUri())
				.body(new DadosCadastroPessoaDTO(pessoa));
	}

}
