package br.com.attornatus.api.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.attornatus.api.dto.request.DadosEditaPessoaFormDTO;
import br.com.attornatus.api.dto.request.DadosPessoaFormDTO;
import br.com.attornatus.api.dto.response.DadosCadastroPessoaDTO;
import br.com.attornatus.api.dto.response.DadosConsultaPessoaDTO;
import br.com.attornatus.api.dto.response.DadosEditaPessoaDTO;
import br.com.attornatus.api.dto.response.DadosListaPessoasDTO;
import br.com.attornatus.api.model.PessoaModel;
import br.com.attornatus.api.service.PessoaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

	@Autowired
	private PessoaService servicoPessoa;

	@GetMapping
	public ResponseEntity<Page<DadosListaPessoasDTO>> lista(
			@PageableDefault(sort = { "nome" }, size = 2) Pageable paginacao) {
		return ResponseEntity.ok(this.servicoPessoa.lista(paginacao).map(DadosListaPessoasDTO::new));

	}

	@GetMapping("/{id}")
	public ResponseEntity<DadosConsultaPessoaDTO> consulta(@PathVariable UUID id) throws Exception {
		return ResponseEntity.ok(new DadosConsultaPessoaDTO(this.servicoPessoa.consulta(id)));
	}

	@PostMapping
	public ResponseEntity<DadosCadastroPessoaDTO> cadastra(@RequestBody @Valid DadosPessoaFormDTO dados,
			UriComponentsBuilder builder) {

		PessoaModel pessoa = this.servicoPessoa.cadastra(new PessoaModel(dados));

		return ResponseEntity.created(builder.path("/api/pessoas/{id}").buildAndExpand(pessoa.getExternalId()).toUri())
				.body(new DadosCadastroPessoaDTO(pessoa));

	}

	@PutMapping("/{id}")
	public ResponseEntity<DadosEditaPessoaDTO> edita(@RequestBody @Valid DadosEditaPessoaFormDTO dados,
			@PathVariable UUID id) {
		return ResponseEntity.ok(new DadosEditaPessoaDTO(this.servicoPessoa.edita(new PessoaModel(dados, id))));

	}

}
