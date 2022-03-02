package br.com.liriodesenvolvimento.model.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EspecialidadeResponse {
	
	private Long id;
	
	private String nome;
	
	private String descricao;
	
	private boolean ativo;
	
	private List<MedicoDTO> medicos = new ArrayList<>();


}