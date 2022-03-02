package br.com.liriodesenvolvimento.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.liriodesenvolvimento.exception.NotFoundException;
import br.com.liriodesenvolvimento.model.Especialidade;
import br.com.liriodesenvolvimento.model.Medico;
import br.com.liriodesenvolvimento.model.dto.MedicoResponse;
import br.com.liriodesenvolvimento.model.dto.PostMedico;
import br.com.liriodesenvolvimento.repository.IEspecialidade;
import br.com.liriodesenvolvimento.repository.IMedico;

@Service
public class MedicoService {
	
	@Autowired
	private IMedico imedico;
	
	@Autowired
	private IEspecialidade iespecialidade;
	
	@Transactional(readOnly = true)
	public List<Medico> returnMedicosPorEspecialidade(Especialidade especialidade){
		List<Medico> medicos = imedico.findAll();
		List<Medico> retorno = new ArrayList<>();
		medicos.forEach(medico-> {
			medico.getEspecialidades().forEach(esp ->{
				if (esp.getId().equals(especialidade.getId())) {
					retorno.add(medico);
				}
			});
		});
		return retorno;
	}
	
	
	@Transactional(readOnly = true)
	public List<MedicoResponse> returnAllMedicos(){
		List<Medico> medicos = imedico.findAll();
		List<MedicoResponse> retorno = new ArrayList<>();
		medicos.forEach(medico -> {
			MedicoResponse medicoRetorno  = MedicoResponse.builder()
									.id(medico.getId())
									.nome(medico.getNome())
									.dataNascimento(medico.getDataNascimento())
									.build();
			medico.getEspecialidades().forEach(esp -> {
				medicoRetorno.getEspecialidades().add(esp.getNome());			
			});
			retorno.add(medicoRetorno);
		});
		return retorno;
	}
	
	@Transactional(readOnly = true)
	public MedicoResponse returnMedicoById(Long id) {
		Medico medico = imedico.findById(id).orElseThrow(() -> new NotFoundException("Médico não encontrado!"));
		MedicoResponse volta = MedicoResponse.builder()
			 	.id(medico.getId())
			 	.nome(medico.getNome())
			 	.dataNascimento(medico.getDataNascimento())
			 	.ativo(medico.isAtivo())			 	
				.build();
		medico.getEspecialidades().forEach(esp -> {
			volta.getEspecialidades().add(esp.getNome());			
		});
		return volta;
	}
	
	@Transactional
	public Medico saveMedico(PostMedico post) {
		List<Especialidade> especialidades = new ArrayList<>();
		post.getIdsEspecialidades().forEach(id -> {
			especialidades.add(iespecialidade.findById(id).orElseThrow(() -> new NotFoundException("Especialidade não encontrada!")));
		});
		Medico medico = Medico.builder()
										.nome(post.getNome())
										.ativo(post.isAtivo())
										.dataNascimento(post.getDataNascimento())
										.especialidades(especialidades)
										.build();
		return imedico.save(medico);
	}
	
	@Transactional
	public MedicoResponse updateMedico(PostMedico post, Long id) {
		Medico medico = imedico.findById(id).orElseThrow(() -> new NotFoundException("Médico não encontrado!"));
		List<Especialidade> especialidades = new ArrayList<>();
		post.getIdsEspecialidades().forEach(idEspecialidade -> {
			especialidades.add(iespecialidade.findById(id).orElseThrow(() -> new NotFoundException("Especialidade não encontrada!")));
		});
		medico.setNome(post.getNome());
		medico.setDataNascimento(post.getDataNascimento());
		medico.setAtivo(post.isAtivo());
		medico.getEspecialidades().clear();
		medico.getEspecialidades().addAll(especialidades);
		medico = imedico.save(medico);
		
		MedicoResponse volta = MedicoResponse.builder()
			 	.id(medico.getId())
			 	.nome(medico.getNome())
			 	.dataNascimento(medico.getDataNascimento())
			 	.ativo(medico.isAtivo())			 	
				.build();
		medico.getEspecialidades().forEach(esp -> {
			volta.getEspecialidades().add(esp.getNome());			
		});
		return volta;
		
	}
	
	@Transactional
	public void deleteMedico(Long id) {
		Medico medico = imedico.findById(id).orElseThrow(() -> new NotFoundException("Médico não encontrado!"));
		imedico.delete(medico);
	}
	
	

}
