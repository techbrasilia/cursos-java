package br.com.cursos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cursos.model.Arquivo;

public interface ArquivoRepository extends JpaRepository<Arquivo, String>{

}
