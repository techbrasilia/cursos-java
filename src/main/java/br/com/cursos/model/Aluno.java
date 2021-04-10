package br.com.cursos.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Aluno {
	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "nome", length = 100, nullable = false)
	@NotBlank(message = "{aluno.nome.obrigatorio}")
	private String nome;
	
	@Column(name = "cpf", length = 11, nullable = false)
	@NotBlank(message = "{aluno.cpf.obrigatorio}")
	private String cpf;

	@Column(name = "matricula", length = 15, nullable = false)
	@NotBlank(message = "{aluno.matricula.obrigatoria}")
	private String matricula;
	
	@Column(name = "dt_nascimento", insertable = false, updatable = false)
	private LocalDate nascidoEm;
	
	public Aluno(String nome, String cpf, String matricula) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.matricula = matricula;
	}

	public Aluno() {}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public LocalDate getNascidoEm() {
		return nascidoEm;
	}

	public void setNascidoEm(LocalDate nascidoEm) {
		this.nascidoEm = nascidoEm;
	}

	public Arquivo getArquivo() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setArquivo(Arquivo arquivo) {
		// TODO Auto-generated method stub
		
	}

}
