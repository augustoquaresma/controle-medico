package br.com.liriodesenvolvimento.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Medico {
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column
	private Long id;
	
	@Column
	private String nome;
	
	@Column
	private Date dataNascimento;
	
	@Column
	private boolean ativo;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "medico_especialista", joinColumns = @JoinColumn(name = "medico_id"),
						inverseJoinColumns = @JoinColumn(name = "especialidade_id"))
	private List<Especialidade> especialidades = new ArrayList<>();
	

}
