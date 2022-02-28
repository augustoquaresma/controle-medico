package br.com.liriodesenvolvimento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.liriodesenvolvimento.model.Medico;

public interface IMedico extends JpaRepository<Medico, Long> {

}
