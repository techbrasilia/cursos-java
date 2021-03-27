package br.com.cursos.controller.response;

import java.util.List;

public class ResponseError {
	
	private List<String> erros;
	
	public ResponseError(List<String> errors) {
		super();
		this.erros = errors;
	}

	public List<String> getErros() {
		return erros;
	}

	public void setErros(List<String> erros) {
		this.erros = erros;
	}
	
	

}
