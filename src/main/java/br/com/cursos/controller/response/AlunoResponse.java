package br.com.cursos.controller.response;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.cursos.model.Aluno;
import br.com.cursos.model.Arquivo;

public class AlunoResponse {
	
	private Aluno aluno;

	public AlunoResponse(Aluno aluno) {
		super();
		this.aluno = aluno;
	}
	
	public Integer getId() {
		return aluno.getId();
	}
	
	public String getNome() {
		return aluno.getNome();
	}
	
	public String getCpf() {
		return aluno.getCpf();
	}
	
	public String getMatricula() {
		return aluno.getMatricula();
	}
	
	public String getArquivoURL() {
		
		Arquivo imagem = aluno.getArquivo();
		
		if ( imagem == null )
			return null;
					
		String url = ServletUriComponentsBuilder
			.fromCurrentContextPath()
			.path("/arquivo/download/" + imagem.getId())
			.toUriString();
		
		return url;
	}

}
