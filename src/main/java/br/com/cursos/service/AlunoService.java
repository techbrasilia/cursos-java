package br.com.cursos.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import br.com.cursos.model.Aluno;
import br.com.cursos.repository.AlunoRepository;

@Service
public class AlunoService {
	
	@Autowired
	AlunoRepository alunoRepository;
	
	public Aluno salvar(Aluno aluno) {
		return alunoRepository.save(aluno);
	}
	
	public List<Aluno> todosAluno(){
		return alunoRepository.findAll();
	}
	
	public Aluno getAluno(Integer id) {
		return alunoRepository.findById(id).orElseThrow(()-> new EntityNotFoundException());
	}
	
	public void excluir(Integer id) {
		alunoRepository.deleteById(id);
	}
	
	public Aluno alterar(Integer id, Aluno aluno) {
		
		Aluno alunoAlterado = getAluno(id);
		if(alunoAlterado != null) {
			alunoAlterado.setNome(aluno.getNome());
			alunoAlterado.setCpf(aluno.getCpf());
			alunoAlterado.setMatricula(aluno.getMatricula());
			alunoAlterado.setArquivo(aluno.getArquivo());
		
			return salvar(alunoAlterado);
		}
		return alunoRepository.save(aluno);
	}
	
	public List<Aluno> filtro(Aluno filtro) {
		
		ExampleMatcher matcher = ExampleMatcher
				.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING);
		
		Example<Aluno> exemplo = Example.of(filtro, matcher);
		
		return alunoRepository.findAll(exemplo);
	}
}
