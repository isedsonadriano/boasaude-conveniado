package br.com.boasaude.cadastro.conveniado.core.domain.entity;

import br.com.boasaude.cadastro.conveniado.core.domain.enums.Status;
import br.com.boasaude.cadastro.conveniado.core.domain.vo.TelefoneVO;
import br.com.boasaude.cadastro.conveniado.core.domain.vo.TipoConveniado;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Conveniado {

	private Long id;
	private String nome;
	private String cnpj;
	private TipoConveniado tipo;
	private TelefoneVO telefone;
	private Status status;

}
