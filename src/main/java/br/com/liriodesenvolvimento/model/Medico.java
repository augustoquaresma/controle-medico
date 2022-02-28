package br.com.liriodesenvolvimento.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@Builder
public class Medico {
	
	@Id
	private Long id;
	
	@Column
	private String nome;
	
	@Column
	private Date dataNascimento;
	
	@Column
	private boolean ativo;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "medico_especialista", joinColumns = @JoinColumn(name = "medico_id"),
						inverseJoinColumns = @JoinColumn(name = "especialista_id"))
	private List<Especialidade> especialidades;
	

}
