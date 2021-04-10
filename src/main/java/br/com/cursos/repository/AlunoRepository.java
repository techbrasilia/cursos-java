package br.com.cursos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.cursos.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
	
	public List<Aluno> findByNome(String nome);

	public List<Aluno> findByNomeLike(String nome);

	public List<Aluno> findByNomeLikeOrderByNome(String nome);

	@Query("select c from Aluno c where c.nome = ?1")
	public List<Aluno> buscaPorNome(String nome);
	
	@Query( value = "select * from Aluno where nome = ?1", nativeQuery = true)
	public List<Aluno> buscaPorNomeNativo(String nome);

//	public List<Aluno> filtro(Curso filtro);
	
}	
