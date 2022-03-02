package br.com.liriodesenvolvimento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.liriodesenvolvimento.model.Especialidade;
import br.com.liriodesenvolvimento.model.dto.EspecialidadeDTO;
import br.com.liriodesenvolvimento.model.dto.EspecialidadeResponse;
import br.com.liriodesenvolvimento.model.dto.PostEspecialidade;
import br.com.liriodesenvolvimento.service.EspecialidadeService;

@CrossOrigin
@RestController
@RequestMapping("/especialidade")
public class EspecialidadeController {
	
	@Autowired
	private EspecialidadeService especialidadeService;
	
	@GetMapping
	public ResponseEntity<List<EspecialidadeDTO>> returnAllEspecialidades(){
		return new ResponseEntity<>(especialidadeService.returnAllEspecialidade(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EspecialidadeDTO> returnEspecialidadeById(@PathVariable Long id){
		return new ResponseEntity<>(especialidadeService.returnEspecialidadeById(id), HttpStatus.OK);
	}
	
	@GetMapping("/{id}/medicos")
	public ResponseEntity<EspecialidadeResponse> returnMedicosWithEspecialidade(@PathVariable Long id){
		return new ResponseEntity<>(especialidadeService.returnMedicosPorEspecialidades(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Especialidade> createdEspecialidade(@RequestBody PostEspecialidade especialidade){
		return new ResponseEntity<>(especialidadeService.saveEspecialidade(especialidade), HttpStatus.CREATED);
	}

}
