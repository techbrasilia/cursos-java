package br.com.cursos.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.cursos.controller.request.CursoRequest;
import br.com.cursos.controller.response.CursoResponse;
import br.com.cursos.model.Arquivo;
import br.com.cursos.model.Curso;
import br.com.cursos.service.CursoService;

@RestController
@RequestMapping("/curso")
@Validated
public class CursoController {
	
	@Autowired
	private CursoService cursoService;
	@Autowired
	private ModelMapper mapper;

	@GetMapping
	public List<CursoResponse> todos(CursoRequest filtroRequest) {
//		return cursoService.todosCursos();
		
		Curso filtro = mapper.map(filtroRequest, Curso.class);
		
		List<Curso> cursos = cursoService.filtro(filtro);
		
		List<CursoResponse> cursoResponse = cursos.stream()
				.map((curso)-> {
					return new CursoResponse(curso);
				}).collect(Collectors.toList());
		return cursoResponse;
	}
	
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public CursoResponse salvar(
			@RequestPart("curso") @Valid CursoRequest cursoRequest, 
			@RequestPart(value = "file", required = false) MultipartFile arquivoRequest) {
		
		Arquivo arquivo = getArquivo(arquivoRequest);
		
		Curso curso = mapper.map(cursoRequest, Curso.class);
		curso.setArquivo(arquivo);

		Curso cursoSalvo = cursoService.salvar(curso);
		
		return new CursoResponse(cursoSalvo);
	}

	@GetMapping("{id}")
	public CursoResponse getCurso(@PathVariable Integer id) {
		Curso curso =  cursoService.getCurso(id);
		return new CursoResponse(curso);
	}
	
	@PutMapping(path = "{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public CursoResponse alterar(@PathVariable Integer id, 
			@RequestPart("curso") @Valid CursoRequest cursoRequest, 
			@RequestPart(value = "file", required = false) MultipartFile arquivoRequest) {
		
		Arquivo arquivo = getArquivo(arquivoRequest);
		
		Curso curso = mapper.map(cursoRequest, Curso.class);
		
		if(arquivo != null) {
			curso.setArquivo(arquivo);			
		}
		
		Curso cursoAlterado = cursoService.alterar(id, curso);
		return new CursoResponse(cursoAlterado);
	}

	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Integer id) {
		cursoService.excluir(id);
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
