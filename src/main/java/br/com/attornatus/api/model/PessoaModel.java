package br.com.attornatus.api.model;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.attornatus.api.dto.request.DadosEditaPessoaFormDTO;
import br.com.attornatus.api.dto.request.DadosPessoaFormDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Pessoa")
@Table(name = "TB_PESSOAS")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class PessoaModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pessoa_id")
	private Long id;

	@Column(name = "pessoa_external_id")
	private UUID externalId;

	private String nome;

	@Column(name = "data_de_nascimento")
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate dataDeNascimento;

	@Column(name = "data_da_criacao")
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private OffsetDateTime dataDaCriacao;

	@Column(name = "data_da_ultima_atualizacao")
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private OffsetDateTime dataDaUltimaAtualizacao;

	@ManyToMany
	@JoinTable(name = "TB_PESSOAS_ENDERECOS", joinColumns = { @JoinColumn(name = "PESSOA_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "ENDERECO_ID") })
	private List<EnderecoModel> enderecos;

	@Column(name = "endereco_principal")
	private UUID enderecoPrincipal;

	public PessoaModel(DadosPessoaFormDTO dados) {
		this.externalId = UUID.randomUUID();
		this.nome = dados.nome().toUpperCase().trim();
		this.dataDeNascimento = LocalDate.parse(dados.dataDeNascimento(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		this.dataDaCriacao = OffsetDateTime.now();
		this.dataDaUltimaAtualizacao = OffsetDateTime.now();
		this.enderecos = Arrays.asList(new EnderecoModel(dados.endereco()));

	}

	public PessoaModel(DadosEditaPessoaFormDTO dados, UUID id) {

		if (id != null)
			this.externalId = id;

		if (dados.nome() != null)
			this.nome = dados.nome().toUpperCase().trim();

		if (dados.dataDeNascimento() != null)
			this.dataDeNascimento = LocalDate.parse(dados.dataDeNascimento(),
					DateTimeFormatter.ofPattern("dd-MM-yyyy"));
		if (dados.endereco() != null)
			this.enderecos = EnderecoModel.convertListaEditaPessoaEndereco(dados.endereco());
		
		this.dataDaUltimaAtualizacao = OffsetDateTime.now();

	}

	public void adicionarEndereco(List<EnderecoModel> lista) {
		this.enderecos.addAll(lista);
	}

	public void adicionarEndereco(EnderecoModel enderecoExistente) {
		this.enderecos.add(enderecoExistente);
	}

}
