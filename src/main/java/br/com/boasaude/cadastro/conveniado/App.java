package br.com.boasaude.cadastro.conveniado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@Slf4j
public class App extends SpringBootServletInitializer {
	
	@RequestMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE, method = RequestMethod.GET   )
	public String getController() {
		return "Página inicial";
	}
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(App.class);
    }
	  
	public static void main(String[] args) {
		Environment env = SpringApplication.run(App.class, args).getEnvironment();
		log.info("Serviço de cadastro de conveniados Iniciado");
        log.info("Documentação das APIs: http://localhost:{}/swagger-ui.html#/v1/conveniados", env.getProperty("server.port"));
	}

}
