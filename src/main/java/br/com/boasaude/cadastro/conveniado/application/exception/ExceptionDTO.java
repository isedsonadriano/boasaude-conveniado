package br.com.boasaude.cadastro.conveniado.application.exception;

import lombok.Data;

@Data
public class ExceptionDTO {

	private String erro;
	
	public ExceptionDTO(String erro) {
		this.erro = erro;
	}
}
