package com.algaworks.algafood;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;


@SpringBootTest
public class CadastroCozinhaIntegrationTests {

	@Autowired
	private CadastroCozinhaService cadastroCozinha;
	
	@Test
	public void deveAtribuirId_QuandoCadastrarCozinhaComDadosCorretos() {
		
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome("Chinesa");
		
		novaCozinha = cadastroCozinha.salvar(novaCozinha);
		
		assertThat(novaCozinha).isNotNull();
		assertThat(novaCozinha.getId()).isNotNull();
	}
	
	@Test
	public void deveFalhar_QuandoCadastrarCozinhaSemNome() {

		assertThrows(ConstraintViolationException.class, () -> {

			Cozinha novaCozinha = new Cozinha();
			novaCozinha.setNome(null);

			novaCozinha = cadastroCozinha.salvar(novaCozinha);

		});
	}
	
	@Test
	public void deveFalhar_QuandoExcluirCozinhaEmUso() {
		
		assertThrows(ConstraintViolationException.class, () ->{
			
			cadastroCozinha.excluir(1L);
		});
		
	}
	
	@Test
	public void deveFalhar_QuandoExcluirCozinhaInexistente() {
		
		assertThrows(ConstraintViolationException.class, () -> {
			
			cadastroCozinha.excluir(100L);

		});
	}
	
	/*@Test(expected = EntidadeEmUsoException.class)
    public void deveFalhar_QuandoExcluirCozinhaEmUso() {
        cadastroCozinha.excluir(1L);
    }
	
	@Test(expected = CozinhaNaoEncontradaException.class)
    public void deveFalhar_QuandoExcluirCozinhaInexistente() {
        cadastroCozinha.excluir(100L);
    }  */
	

}

