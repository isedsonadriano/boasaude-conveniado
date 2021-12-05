package br.com.boasaude.cadastro.conveniado.core.domain.entity;

import br.com.boasaude.cadastro.conveniado.core.domain.vo.TipoConveniado;

public class Conveniado {

	private Long id;
	private String nome;
	private String cpf;
	private TipoConveniado tipo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public TipoConveniado getTipo() {
		return tipo;
	}

	public void setTipo(TipoConveniado tipo) {
		this.tipo = tipo;
	}

}
