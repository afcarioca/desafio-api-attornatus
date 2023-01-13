package br.com.attornatus.api.dto.request;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosEditaPessoaFormDTO(
		@NotBlank(message = "O nome é obrigatório") @Pattern(regexp = "^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$", message = "Caracteres especiais não são permitidos") String nome,
		@NotBlank(message = "A data de nascimento é obrigatória") @Pattern(regexp = "^\\d{2}-\\d{2}-\\d{4}$", message = "Data de nascimento inválida") String dataDeNascimento,
		@NotEmpty(message = "A lista de endereços não deve estar vazia") @Valid List<DadosEditaEnderecoFormDTO> endereco) {

}
