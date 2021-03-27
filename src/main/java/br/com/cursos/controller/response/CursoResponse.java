package br.com.cursos.controller.response;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.cursos.model.Arquivo;
import br.com.cursos.model.Curso;

public class CursoResponse {

	private Curso curso;

	public CursoResponse(Curso curso) {
		super();
		this.curso = curso;
	}
	
	public Integer getId() {
		return curso.getId();
	}
	
	public String getNome() {
		return curso.getNome();
	}
	
	public Double getPreco() {
		return curso.getPreco();
	}
	
	public String getArquivoURL() {
		
		Arquivo imagem = curso.getArquivo();
		
		if ( imagem == null )
			return null;
					
		String url = ServletUriComponentsBuilder
			.fromCurrentContextPath()
			.path("/arquivo/download/" + imagem.getId())
			.toUriString();
		
		return url;
	}
	
}
