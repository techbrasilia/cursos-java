package br.com.cursos.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.cursos.controller.request.AlunoRequest;
import br.com.cursos.controller.response.AlunoResponse;
import br.com.cursos.model.Aluno;
import br.com.cursos.model.Arquivo;
import br.com.cursos.service.AlunoService;

@RestController
@RequestMapping("/aluno")
@Validated
public class AlunoController {
	
	@Autowired
	private AlunoService alunoService;
	@Autowired
	private ModelMapper mapper;

	@GetMapping
	public List<AlunoResponse> todos(AlunoRequest filtroRequest) {
//		return cursoService.todosCursos();
		
		Aluno filtro = mapper.map(filtroRequest, Aluno.class);
		
		List<Aluno> alunos = alunoService.filtro(filtro);
		
		List<AlunoResponse> alunoResponse = alunos.stream()
				.map((aluno)-> {
					return new AlunoResponse(aluno);
				}).collect(Collectors.toList());
		return alunoResponse;
	}
	
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public AlunoResponse salvar(
			@RequestPart("aluno") @Valid AlunoRequest alunoRequest, 
			@RequestPart(value = "file", required = false) MultipartFile arquivoRequest) {
		
		Arquivo arquivo = getArquivo(arquivoRequest);
		
		Aluno aluno = mapper.map(alunoRequest, Aluno.class);
		aluno.setArquivo(arquivo);

		Aluno alunoSalvo = alunoService.salvar(aluno);
		
		return new AlunoResponse(alunoSalvo);
	}

	@GetMapping("{id}")
	public AlunoResponse getAluno(@PathVariable Integer id) {
		Aluno aluno =  alunoService.getAluno(id);
		return new AlunoResponse(aluno);
	}
	
	@PutMapping(path = "{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public AlunoResponse alterar(@PathVariable Integer id, 
			@RequestPart("aluno") @Valid AlunoRequest alunoRequest, 
			@RequestPart(value = "file", required = false) MultipartFile arquivoRequest) {
		
		Arquivo arquivo = getArquivo(arquivoRequest);
		
		Aluno aluno = mapper.map(alunoRequest, Aluno.class);
		
		if(arquivo != null) {
			aluno.setArquivo(arquivo);			
		}
		
		Aluno alunoAlterado = alunoService.alterar(id, aluno);
		return new AlunoResponse(alunoAlterado);
	}

	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Integer id) {
		alunoService.excluir(id);
	}
	
	private Arquivo getArquivo(MultipartFile arquivoRequest) {
		Arquivo arquivo = null;
		
		try {
			
			if(arquivoRequest != null) {
				String nomeArquivo = arquivoRequest.getOriginalFilename();
				String tipo = arquivoRequest.getContentType();
				byte[] conteudo = arquivoRequest.getBytes();
				
				arquivo = new Arquivo(nomeArquivo, tipo, conteudo);				
			}
		
		} catch (IOException e) {
			System.out.println("Error: "+e.getMessage());
			e.printStackTrace();
		}
		return arquivo;
	}

}
