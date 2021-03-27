package br.com.cursos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.cursos.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Integer> {
	
	public List<Curso> findByNome(String nome);

	public List<Curso> findByNomeLike(String nome);

	public List<Curso> findByNomeLikeOrderByNome(String nome);

	public List<Curso> findByNomeLikeOrderByNomeAscPrecoDesc(String nome);

	public List<Curso> findByNomeAndPreco(String nome, Double preco);
	
	@Query("select c from Curso c where c.nome = ?1")
	public List<Curso> buscaPorNome(String nome);
	
	@Query( value = "select * from Curso where nome = ?1", nativeQuery = true)
	public List<Curso> buscaPorNomeNativo(String nome);

	public List<Curso> filtraPorNome(String nome);
	
//	public List<Curso> filtro(Curso filtro);

}
