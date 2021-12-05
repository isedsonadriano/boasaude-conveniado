package br.com.boasaude.cadastro.conveniado.infra.repository.orm;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.boasaude.cadastro.conveniado.core.domain.vo.TipoConveniado;


@Entity
@Table(name = "boasaude_conveniado")
public class ConveniadoJpa {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	private String nome;

	@NotNull
	private String cpf;

	@NotNull
	@Enumerated(EnumType.STRING)
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

	public void setNome(String name) {
		this.nome = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String password) {
		this.cpf = password;
	}

	public TipoConveniado getTipo() {
		return tipo;
	}

	public void setTipo(TipoConveniado gender) {
		this.tipo = gender;
	}

}
