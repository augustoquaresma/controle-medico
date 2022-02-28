package br.com.liriodesenvolvimento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.liriodesenvolvimento.model.Especialidade;

public interface IEspecialidade extends JpaRepository<Especialidade, Long> {

}
