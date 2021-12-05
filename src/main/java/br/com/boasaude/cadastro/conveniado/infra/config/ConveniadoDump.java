package br.com.boasaude.cadastro.conveniado.infra.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import com.github.javafaker.Faker;

import br.com.boasaude.cadastro.conveniado.core.domain.entity.Conveniado;
import br.com.boasaude.cadastro.conveniado.core.domain.vo.TipoConveniado;
import br.com.boasaude.cadastro.conveniado.core.service.ConveniadoService;
import br.com.boasaude.cadastro.conveniado.core.util.Paginador;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class ConveniadoDump {
	
	private static final int QUANTIDADE_CONVENIADOS_DUMP = 50;

	@Autowired
	private Faker faker;
	
	@Autowired
	private ConveniadoService conveniadoService;
	
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
		for (int i = 0; i < QUANTIDADE_CONVENIADOS_DUMP; i++) {
			Conveniado conveniado = new Conveniado();
			conveniado.setId(faker.random().nextLong());
			conveniado.setNome(faker.company().name());
			conveniado.setTipo(TipoConveniado.getRandomTipoConveniado());
			conveniado.setCpf(String.valueOf(faker.random().nextLong()));
			
			this.conveniadoService.salvar(conveniado);
		}
	}

}
