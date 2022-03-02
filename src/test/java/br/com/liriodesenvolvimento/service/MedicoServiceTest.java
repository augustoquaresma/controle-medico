package br.com.liriodesenvolvimento.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.liriodesenvolvimento.model.Medico;
import br.com.liriodesenvolvimento.model.dto.MedicoResponse;
import br.com.liriodesenvolvimento.repository.IMedico;


@RunWith(MockitoJUnitRunner.class)
public class MedicoServiceTest {
	
	@Spy
	@InjectMocks
	private MedicoService medicos;
	
	@Mock
	private IMedico iMedico;
	
	private Medico generateMedicoForTest() {
		return Medico.builder()
				.nome("Fulano")
				.dataNascimento(new Date())
				.ativo(true)
				.build();
	}
	
	
	@Test
	public void should_return_medico() {
		Mockito.when(iMedico.findById(Mockito.any())).thenReturn(Optional.of(this.generateMedicoForTest()));
		MedicoResponse response = medicos.returnMedicoById(1L);
		Assert.assertEquals(true, response.isAtivo());
		Assert.assertEquals("Fulano", response.getNome());
	}
	
	
	@Test
	public void should_return_all_medicos() {
		Mockito.when(iMedico.findAll()).thenReturn(List.of(this.generateMedicoForTest(), this.generateMedicoForTest()));
		List<MedicoResponse> response = medicos.returnAllMedicos();
		Assert.assertEquals(2, response.size());
		Assert.assertEquals("Fulano", response.get(0).getNome());
	}

}
