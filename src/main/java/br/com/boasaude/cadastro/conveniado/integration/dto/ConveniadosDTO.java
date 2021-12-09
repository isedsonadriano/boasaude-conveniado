package br.com.boasaude.cadastro.conveniado.integration.dto;

import java.util.ArrayList;
import java.util.List;

public class ConveniadosDTO {

	private List<ConveniadoDTO> conveniados;

	public List<ConveniadoDTO> getConveniados() {
		if (conveniados == null) {
			conveniados = new ArrayList<ConveniadoDTO>();
		}
		return conveniados;
	}

	public void setConveniados(List<ConveniadoDTO> conveniados) {
		this.conveniados = conveniados;
	}
}
