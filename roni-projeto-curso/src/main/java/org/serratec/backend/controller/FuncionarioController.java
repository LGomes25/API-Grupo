package org.serratec.backend.controller;

import java.io.IOException;
import java.util.List;

import org.serratec.backend.dto.FuncionarioFotoDTO;
import org.serratec.backend.dto.FuncionarioResponseDTO;
import org.serratec.backend.entity.Foto;
import org.serratec.backend.entity.Funcionario;
import org.serratec.backend.service.FotoService;
import org.serratec.backend.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioService service;
	
	@Autowired
	private FotoService fotoService;
	

	@GetMapping
	public List<FuncionarioResponseDTO> listar() {
		return service.listar();
	}

	@GetMapping("/pagina")
	public Page<FuncionarioResponseDTO> listarPorPagina(@PageableDefault(page = 1, size = 10, sort = { "nome",
			"salario" }, direction = Direction.ASC) Pageable pageable) {
		return service.listarPorPagina(pageable);
	}

	@GetMapping("/faixa")
	public Page<FuncionarioResponseDTO> listarPorFaixaSalarial(@RequestParam(defaultValue = "1000") Double faixa1,
			@RequestParam(defaultValue = "2000") Double faixa2, Pageable pageable) {
		return service.listarPorPaginaFaixaSalarial(faixa1, faixa2, pageable);
	}

	@GetMapping("/nome")
	public Page<FuncionarioResponseDTO> listarPorNome(@RequestParam(defaultValue = "") String nome, Pageable pageable) {
		return service.listarPorNome(nome, pageable);
	}
	
	@GetMapping("/{id}/foto")
	public ResponseEntity<byte[]> buscarFoto(@PathVariable Long id){
		Foto foto =		fotoService.buscarPorIdFuncionario(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", foto.getTipo());
		headers.add("Content-length", String.valueOf(foto.getDados().length));
		return new ResponseEntity<>(foto.getDados(), headers,HttpStatus.OK);
	}
	
	@PostMapping
	public FuncionarioFotoDTO inserir(@RequestPart MultipartFile file, @RequestPart Funcionario funcionario) throws IOException {
		return service.inserir(funcionario, file);
	}
}
