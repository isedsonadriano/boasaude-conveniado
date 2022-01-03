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
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "boasaude_conveniado")
@Getter
@Setter
public class ConveniadoJpa {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	private String nome;

	@NotNull
	private String cnpj;

	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoConveniado tipo;

}
