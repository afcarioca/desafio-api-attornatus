package br.com.attornatus.api.model;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.attornatus.api.dto.DadosEditaEnderecoDTO;
import br.com.attornatus.api.dto.DadosListaEnderecosDTO;
import br.com.attornatus.api.dto.request.DadosEditaEnderecoFormDTO;
import br.com.attornatus.api.dto.request.DadosEnderecoFormDTO;
import br.com.attornatus.api.dto.response.DadosCadastroEnderecoDTO;
import br.com.attornatus.api.dto.response.DadosConsultaEnderecoDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Endereco")
@Table(name = "TB_ENDERECOS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EnderecoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "endereco_id")
	private Long id;

	@Column(name = "endereco_external_id")
	private UUID externalId;

	private String logradouro;
	private String cep;
	private String numero;
	private String cidade;

	@Transient
	private Boolean enderecoPrincipal;

	@Column(name = "data_da_criacao")
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private OffsetDateTime dataDaCriacao;

	@Column(name = "data_da_ultima_atualizacao")
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private OffsetDateTime dataDaUltimaAtualizacao;

	public EnderecoModel(DadosEnderecoFormDTO endereco) {
		this.externalId = UUID.randomUUID();
		this.logradouro = endereco.logradouro().toUpperCase().trim();
		this.cep = endereco.cep();
		this.numero = endereco.numero();
		this.cidade = endereco.cidade();
		this.dataDaCriacao = OffsetDateTime.now();
		this.dataDaUltimaAtualizacao = OffsetDateTime.now();

		this.enderecoPrincipal = endereco.principal();
	}

	public EnderecoModel(DadosEditaEnderecoFormDTO endereco) {
		this.externalId = UUID.randomUUID();

		if (endereco.logradouro() != null)
			this.logradouro = endereco.logradouro().toUpperCase().trim();

		if (endereco.cep() != null)
			this.cep = endereco.cep();

		if (endereco.numero() != null)
			this.numero = endereco.numero();

		if (endereco.cidade() != null)
			this.cidade = endereco.cidade();

		if (endereco.principal() != null)
			this.enderecoPrincipal = endereco.principal();
		
		
		
		this.dataDaCriacao = OffsetDateTime.now();
		this.dataDaUltimaAtualizacao = OffsetDateTime.now();
	}

	/*
	 * public static List<EnderecoModel> converte(DadosEnderecoFormDTO enderecos) {
	 * 
	 * return
	 * enderecos.stream().map(EnderecoModel::new).collect(Collectors.toList()); }
	 * 
	 */

	public static List<DadosCadastroEnderecoDTO> converteModel(List<EnderecoModel> enderecos) {
		return enderecos.stream().map(DadosCadastroEnderecoDTO::new).collect(Collectors.toList());

	}

	public static List<DadosConsultaEnderecoDTO> converteParaConsulta(List<EnderecoModel> enderecos) {
		return enderecos.stream().map(DadosConsultaEnderecoDTO::new).collect(Collectors.toList());

	}

	public static List<EnderecoModel> converteEditaEmpresaDTO(List<DadosEditaEnderecoFormDTO> enderecos) {
		return enderecos.stream().map(EnderecoModel::new).collect(Collectors.toList());
	}

	public static List<DadosEditaEnderecoDTO> converteEditaDTO(List<EnderecoModel> enderecos) {
		return enderecos.stream().map(DadosEditaEnderecoDTO::new).collect(Collectors.toList());
	}

	public static List<DadosListaEnderecosDTO> converteListaEnderecos(List<EnderecoModel> enderecos) {
		return enderecos.stream().map(DadosListaEnderecosDTO::new).collect(Collectors.toList());

	}

	public static List<DadosListaEnderecosDTO> convertListaPessoaEndereco(List<EnderecoModel> enderecos) {
		return enderecos.stream().map(DadosListaEnderecosDTO::new).collect(Collectors.toList());
	}

	public static List<EnderecoModel> convertListaEditaPessoaEndereco(List<DadosEditaEnderecoFormDTO> endereco) {
		return endereco.stream().map(EnderecoModel::new).collect(Collectors.toList());
	}

}
