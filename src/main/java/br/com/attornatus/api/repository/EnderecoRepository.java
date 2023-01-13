package br.com.attornatus.api.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.attornatus.api.model.EnderecoModel;

public interface EnderecoRepository extends JpaRepository<EnderecoModel, Long> {

	List<EnderecoModel> findByCepInAndLogradouroInAndNumeroIn(List<String> collect, List<String> collect2,
			List<String> collect3);

	EnderecoModel findByLogradouroAndCepAndNumero(String logradouro, String cep, String numero);

	EnderecoModel findByExternalId(UUID enderecoPrincipal);

	EnderecoModel findByLogradouroAndNumeroAndCepAndCidade(String logradouro, String numero, String cep, String cidade);

}
