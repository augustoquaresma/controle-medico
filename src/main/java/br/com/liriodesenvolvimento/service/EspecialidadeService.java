package br.com.liriodesenvolvimento.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.liriodesenvolvimento.exception.NotFoundException;
import br.com.liriodesenvolvimento.model.Especialidade;
import br.com.liriodesenvolvimento.model.Medico;
import br.com.liriodesenvolvimento.model.dto.EspecialidadeDTO;
import br.com.liriodesenvolvimento.model.dto.EspecialidadeResponse;
import br.com.liriodesenvolvimento.model.dto.MedicoDTO;
import br.com.liriodesenvolvimento.model.dto.PostEspecialidade;
import br.com.liriodesenvolvimento.repository.IEspecialidade;

@Service
public class EspecialidadeService {
	
	@Autowired
	private IEspecialidade iespecialidade;
	
	@Autowired
	private MedicoService medicoService;
	
	@Transactional(readOnly = true)
	public Especialidade returnByIdInternal(Long id){
		return iespecialidade.findById(id).orElseThrow(() -> new NotFoundException("Especialidade não encontrada!"));
	}
	
	
	@Transactional(readOnly = true)
	public List<EspecialidadeDTO> returnAllEspecialidade(){
		List<Especialidade> especialidades = iespecialidade.findAll();
		List<EspecialidadeDTO> retorno = new ArrayList<>();
		especialidades.forEach(esp -> {
			EspecialidadeDTO volta = EspecialidadeDTO.builder()
									 	.id(esp.getId())
									 	.nome(esp.getNome())
									 	.descricao(esp.getDescricao())
									 	.ativo(esp.isAtivo())
										.build();
			retorno.add(volta);
		});
		return retorno;
	}
	
	
	@Transactional(readOnly = true)
	public EspecialidadeDTO returnEspecialidadeById(Long id) {
		Especialidade esp = iespecialidade.findById(id).orElseThrow(() -> new NotFoundException("Especialidade não encontrada!"));
		EspecialidadeDTO volta = EspecialidadeDTO.builder()
			 	.id(esp.getId())
			 	.nome(esp.getNome())
			 	.descricao(esp.getDescricao())
			 	.ativo(esp.isAtivo())
				.build();
		return volta;
	}
	
	@Transactional
	public Especialidade saveEspecialidade(PostEspecialidade post) {
		Especialidade especialidade = Especialidade.builder()
										.nome(post.getNome())
										.descricao(post.getDescricao())
										.ativo(post.isAtivo())
										.build();
		return iespecialidade.save(especialidade);
	}
	
	@Transactional
	public EspecialidadeDTO updateEspecialidade(PostEspecialidade post, Long id) {
		Especialidade esp = iespecialidade.findById(id).orElseThrow(() -> new NotFoundException("Especialidade não encontrada!"));
		esp.setDescricao(post.getDescricao());
		esp.setNome(post.getDescricao());
		esp.setAtivo(post.isAtivo());
		esp = iespecialidade.save(esp);
		return EspecialidadeDTO.builder()
			 	.id(esp.getId())
			 	.nome(esp.getNome())
			 	.descricao(esp.getDescricao())
			 	.ativo(esp.isAtivo())
				.build();
		
	}
	
	@Transactional(readOnly = true)
	public EspecialidadeResponse returnMedicosPorEspecialidades(Long idEspecialidade) {
		Especialidade esp = iespecialidade.findById(idEspecialidade).orElseThrow(() -> new NotFoundException("Especialidade não encontrada!"));
		EspecialidadeResponse retorno = EspecialidadeResponse.builder()
															.id(esp.getId())
															.nome(esp.getNome())
															.descricao(esp.getDescricao())
															.medicos(new ArrayList<>())
															.build();
		List<Medico> medicos = medicoService.returnMedicosPorEspecialidade(esp);
		if (medicos.isEmpty()) {
			throw new NotFoundException("Não existem médicos nessa especialidade!");
		}
		medicos.forEach( med -> {
			MedicoDTO volta = MedicoDTO.builder()
								.id(med.getId())
								.nome(med.getNome())
								.build();
			retorno.getMedicos().add(volta);
		});
		return retorno;
	}
	
	@Transactional
	public void deleteEspecialidade(Long id) {
		Especialidade esp = iespecialidade.findById(id).orElseThrow(() -> new NotFoundException("Especialidade não encontrada!"));
		iespecialidade.delete(esp);
	}
	
	
	
	
	
	

}
