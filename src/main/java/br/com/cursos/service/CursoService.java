package br.com.cursos.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import br.com.cursos.model.Curso;
import br.com.cursos.repository.CursoRepository;

@Service
public class CursoService {

	@Autowired
	CursoRepository cursoRepository;
	
	public Curso salvar(Curso curso) {
		return cursoRepository.save(curso);
	}
	
	public List<Curso> todosCursos(){
		return cursoRepository.findAll();
	}
	
	public Curso getCurso(Integer id) {
		return cursoRepository.findById(id).orElseThrow(()-> new EntityNotFoundException());
	}
	
	public void excluir(Integer id) {
		cursoRepository.deleteById(id);
	}
	
	public Curso alterar(Integer id, Curso curso) {
		
		Curso cursoAlterado = getCurso(id);
		if(cursoAlterado != null) {
			cursoAlterado.setNome(curso.getNome());
			cursoAlterado.setPreco(curso.getPreco());
			cursoAlterado.setAtualizadoEm(LocalDateTime.now());
			cursoAlterado.setArquivo(curso.getArquivo());
		
			return salvar(cursoAlterado);
		}
		return cursoRepository.save(curso);
	}
	
	public List<Curso> filtro(Curso filtro) {
		
		ExampleMatcher matcher = ExampleMatcher
				.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING);
		
		Example<Curso> exemplo = Example.of(filtro, matcher);
		
		return cursoRepository.findAll(exemplo);
	}

}
