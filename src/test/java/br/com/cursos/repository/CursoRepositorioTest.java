package br.com.cursos.repository;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;

import br.com.cursos.model.Curso;

@SpringBootTest
public class CursoRepositorioTest {

	@Autowired
	private CursoRepository respositorio;
	
	@Test
	void findByNomeSimples() {
		List<Curso> cursos = respositorio.findByNome("Curso de Java Avançado");
		Assertions.assertThat(cursos).hasSize(1);
	}
	
	@Test
	void findByNomeLike() {
		List<Curso> cursos = respositorio.findByNomeLike("%Java%");
		Assertions.assertThat(cursos).hasSize(2);
	}
	
	@Test
	void findByNomeLikeOrderByNome() {
		List<Curso> cursos = respositorio.findByNomeLikeOrderByNome("%Curso%");
		Assertions.assertThat(cursos).hasSize(4);
	}
	
	@Test
	void buscaPorNome() {
		List<Curso> cursos = respositorio.buscaPorNome("Curso de PHP Básico");
		Assertions.assertThat(cursos).hasSize(1);
	}
	
	@Test
	void filtraPorNome() {
		List<Curso> cursos = respositorio.filtraPorNome("Curso de PHP Básico");
		Assertions.assertThat(cursos).hasSize(1);
	}
	
	@Test
	void findByExample() {
		Curso filtro = new Curso();
		filtro.setNome("java");
		
		ExampleMatcher matcher = ExampleMatcher
				.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING);
		
		Example<Curso> exemplo = Example.of(filtro, matcher);
		
		List<Curso> cursos = respositorio.findAll(exemplo);
		Assertions.assertThat(cursos).hasSizeGreaterThanOrEqualTo(1);
	}
}
