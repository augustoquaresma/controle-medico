package br.com.liriodesenvolvimento.model.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostMedico {
	
	private String nome;
	
	private Date dataNascimento;

	private boolean ativo;
	
	private List<Long> idsEspecialidades = new ArrayList<>();

}
