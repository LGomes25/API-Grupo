package org.serratec.backend.service;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.serratec.backend.dto.FuncionarioFotoDTO;
import org.serratec.backend.dto.FuncionarioResponseDTO;
import org.serratec.backend.entity.Funcionario;
import org.serratec.backend.exception.FuncionarioException;
import org.serratec.backend.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.transaction.Transactional;

@Service
public class FuncionarioService {

	@Autowired
	private FotoService fotoService;

	@Autowired
	private FuncionarioRepository repository;

	public List<FuncionarioResponseDTO> listar() {
		List<Funcionario> funcionarios = repository.findAll();
		return funcionarios.stream().map(f -> new FuncionarioResponseDTO(f)).collect(Collectors.toList());
	}

	public Page<FuncionarioResponseDTO> listarPorPagina(Pageable pageable) {
		Page<Funcionario> funcionarios = repository.findAll(pageable);
		return funcionarios.map(f -> new FuncionarioResponseDTO(f));
	}

	public Page<FuncionarioResponseDTO> listarPorPaginaFaixaSalarial(Double faixa1, Double faixa2, Pageable pageable) {
		Page<Funcionario> funcionarios = repository.findBySalarioBetween(faixa1, faixa2, pageable);
		return funcionarios.map(f -> new FuncionarioResponseDTO(f));
	}

	public Page<FuncionarioResponseDTO> listarPorNome(String nome, Pageable pageable) {
		Page<Funcionario> funcionarios = repository.findByNomeContainingIgnoringCase(nome, pageable);
		return funcionarios.map(f -> new FuncionarioResponseDTO(f));
	}

	public FuncionarioFotoDTO adicionarImagemUri(Funcionario funcionario) {
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/funcionarios/{id}/foto")
				.buildAndExpand(funcionario.getId()).toUri();

		FuncionarioFotoDTO dto = new FuncionarioFotoDTO();
		dto.setNome(funcionario.getNome());
		dto.setUrl(uri.toString());
		return dto;
	}

	public FuncionarioFotoDTO buscar(Long id) {
		Optional<Funcionario> funcionario = repository.findById(id);
		if (funcionario.isPresent()) {
			return adicionarImagemUri(funcionario.get());
		}
		throw new FuncionarioException("Funcionario não encontrado");
	}

	@Transactional
	public FuncionarioFotoDTO inserir(Funcionario funcionario, MultipartFile file) throws IOException {
		funcionario = repository.save(funcionario);
		fotoService.inserir(funcionario, file);
		return adicionarImagemUri(funcionario);
	}

}
