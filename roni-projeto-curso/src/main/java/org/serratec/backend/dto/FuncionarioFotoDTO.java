package org.serratec.backend.dto;

public class FuncionarioFotoDTO {

	private String nome;
	private String url;

	public FuncionarioFotoDTO() {
		// TODO Auto-generated constructor stub
	}

	public FuncionarioFotoDTO(String nome, String url) {
		this.nome = nome;
		this.url = url;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
