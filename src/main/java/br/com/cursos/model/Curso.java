package br.com.cursos.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@NamedQuery(name = "Curso.filtraPorNome", query = "select c from Curso c where c.nome = ?1")
@NamedQuery(name = "Curso.filtraTodos", query = "select c from Curso c ")
public class Curso {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name = "nome", length = 100, nullable = false)
	@NotBlank(message = "{curso.nome.obrigatorio}")
	private String nome;
	
	@Column(name = "preco", precision = 12, scale = 2)
	private Double preco;
	
	@Future(message = "{curso.disponivel-a-partir.invalido}")
	@JsonIgnore
	private LocalDate disponivelAPartir;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "arquivo_id")
	private Arquivo arquivo;

	@Column(columnDefinition = "timestamp default current_timestamp", insertable = false, updatable = false)
	private LocalDateTime criadoEm;
	
	@Column(columnDefinition = "timestamp default current_timestamp", insertable = false, updatable = false)
	private LocalDateTime atualizadoEm;
	
	public Curso() {
		
	}
	

	public Curso(String nome, Double preco) {
		super();
		this.nome = nome;
		this.preco = preco;
	}

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

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Arquivo getArquivo() {
		return arquivo;
	}

	public void setArquivo(Arquivo arquivo) {
		this.arquivo = arquivo;
	}

	public LocalDateTime getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(LocalDateTime criadoEm) {
		this.criadoEm = criadoEm;
	}

	public LocalDateTime getAtualizadoEm() {
		return atualizadoEm;
	}

	public void setAtualizadoEm(LocalDateTime atualizadoEm) {
		this.atualizadoEm = atualizadoEm;
	}

	public LocalDate getDisponivelAPartir() {
		return disponivelAPartir;
	}

	public void setDisponivelAPartir(LocalDate disponivelAPartir) {
		this.disponivelAPartir = disponivelAPartir;
	}
	
	
}
