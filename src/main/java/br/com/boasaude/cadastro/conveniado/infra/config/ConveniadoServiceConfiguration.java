package br.com.boasaude.cadastro.conveniado.infra.config;

import br.com.boasaude.cadastro.conveniado.core.repository.ConveniadoRepository;
import br.com.boasaude.cadastro.conveniado.core.service.ConveniadoService;
import br.com.boasaude.cadastro.conveniado.core.service.implementation.ConveniadoServiceImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableTransactionManagement
public class ConveniadoServiceConfiguration {

	@Bean
    public ConveniadoService clientService(ConveniadoRepository repository){
        return new ConveniadoServiceImpl(repository);
    }
}
