package br.com.attornatus.api.repository;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.attornatus.api.model.PessoaModel;

public interface PessoaRepository extends JpaRepository<PessoaModel, Long> {

	PessoaModel findByExternalId(UUID id);

	boolean existsByExternalId(UUID id);

	boolean existsByNomeAndDataDeNascimento(String nome, LocalDate dataDeNascimento);

	Page<PessoaModel> findAll(Pageable paginacao);

}
