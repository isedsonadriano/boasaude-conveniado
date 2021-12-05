package br.com.boasaude.cadastro.conveniado.application.conveniado.dto.response.v1;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConveniadoResponse {

	@ApiModelProperty(notes = "Id do conveniado", name = "id", required = true, value = "Teste Id")
	private Long id;
	
	@ApiModelProperty(notes = "Nome do conveniado", name = "nome", required = true, value = "Teste nome")
	private String nome;

	@ApiModelProperty(notes = "Cpf do conveniado", name = "cpf", required = true, value = "Teste Cpf")
	private String cpf;

}
