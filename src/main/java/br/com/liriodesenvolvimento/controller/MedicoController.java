package br.com.liriodesenvolvimento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.liriodesenvolvimento.model.Medico;
import br.com.liriodesenvolvimento.model.dto.MedicoResponse;
import br.com.liriodesenvolvimento.model.dto.PostMedico;
import br.com.liriodesenvolvimento.service.MedicoService;

@CrossOrigin
@RestController
@RequestMapping("/medico")
public class MedicoController {
	
	@Autowired
	private MedicoService medicos;
	
	@GetMapping
	public ResponseEntity<List<MedicoResponse>> returnAllMedicos(){
		return new ResponseEntity<>(medicos.returnAllMedicos(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<MedicoResponse> returnMedicoById(@PathVariable Long id){
		return new ResponseEntity<>(medicos.returnMedicoById(id), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Medico> createdMedico(@RequestBody PostMedico medico){
		return new ResponseEntity<>(medicos.saveMedico(medico), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<MedicoResponse> updateMedico(@PathVariable Long id, @RequestBody PostMedico medico){
		return new ResponseEntity<>(medicos.updateMedico(medico, id), HttpStatus.CREATED);
	}

}
