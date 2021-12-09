package br.com.boasaude.cadastro.conveniado.infra.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import br.com.boasaude.cadastro.conveniado.core.domain.entity.Conveniado;
import br.com.boasaude.cadastro.conveniado.core.domain.vo.TipoConveniado;
import br.com.boasaude.cadastro.conveniado.core.service.ConveniadoService;
import br.com.boasaude.cadastro.conveniado.core.util.Paginador;
import br.com.boasaude.cadastro.conveniado.integration.ConsultaConveniados;
import br.com.boasaude.cadastro.conveniado.integration.dto.ConveniadoDTO;
import br.com.boasaude.cadastro.conveniado.integration.dto.ConveniadosDTO;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class ConveniadoDump {
	
	@Autowired
	private ConveniadoService conveniadoService;
	
	@Autowired
	private ConsultaConveniados consultaConveniados;
	
	@Autowired
	protected ModelMapper modelMapper;
	
	
	@EventListener(classes = ContextRefreshedEvent.class )
	public void iniciarBancoDeDados () {
		log.info("Início inserção conveniados");
		if(isBancoVazio()) {
			inserirConveniadosDb();
		}
		
		log.info("Fim inserção conveniados");
	}

	private boolean isBancoVazio() {
		return this.conveniadoService.capturarTodos(new Paginador()).size() == 0;
	}

	private void inserirConveniadosDb() {
		ConveniadosDTO conveniados = consultaConveniados.consulta();
		for (ConveniadoDTO dto : conveniados.getConveniados()) {
			Conveniado conveniado = new Conveniado();
			conveniado.setCpf(dto.getCpf());
			conveniado.setId(dto.getId());
			conveniado.setNome(dto.getNome());
			conveniado.setCpf(conveniado.getCpf());
			conveniado.setTipo(TipoConveniado.HOSPITAL);
			this.conveniadoService.salvar(conveniado);
		}
	}

}
