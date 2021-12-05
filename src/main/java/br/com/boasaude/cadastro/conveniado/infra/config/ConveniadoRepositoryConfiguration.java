package br.com.boasaude.cadastro.conveniado.infra.config;

import br.com.boasaude.cadastro.conveniado.core.repository.ConveniadoRepository;
import br.com.boasaude.cadastro.conveniado.core.repository.implementation.ConveniadoRepositoryImpl;
import br.com.boasaude.cadastro.conveniado.infra.repository.ConveniadoRepositoryMySql;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConveniadoRepositoryConfiguration {

    @Bean
    public ConveniadoRepository clientRepository(ConveniadoRepositoryMySql repository) {
        return new ConveniadoRepositoryImpl(repository);
    }

}