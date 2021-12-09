package br.com.boasaude.cadastro.conveniado.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.boasaude.cadastro.conveniado.integration.dto.ConveniadosDTO;


@FeignClient(name = "ConsultaConveniados", url = "${api.external.middleware.sgps.conveniados}")
public interface ConsultaConveniados {

	@GetMapping()
	ConveniadosDTO consulta();
}
