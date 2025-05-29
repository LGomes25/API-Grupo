package org.serratec.bakcend.config;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {

	@Value("${dominio.openapi.dev-url}")
	private String devUrl;
	@Value("${dominio.openapi.prod-url}")
	private String prodUrl;

	@Bean
	OpenAPI myOpenAPI() {
		Server devServer = new Server();
		devServer.setUrl(devUrl);
		devServer.setDescription("URL do Dominador de desenvolvimento de produções");
		Server prodServer = new Server();
		prodServer.setUrl(prodUrl);
		prodServer.setDescription("URL do Dominador de produções");
		Contact contact = new Contact();
		contact.setEmail("leonardo@meudominio.com.br");
		contact.setName("Grays");
		contact.setUrl("https://www.meudominio.com.br");
		License apacheLicense = new License().name("Confederations License")
				.url("https://www.apache.org/licenses/LICENSE-2.0");
		Info info = new Info().title("Sistema de Colonização Intergalática").version("1.0").contact(contact)
				.description("Invadir e Dominar não é opção!!").termsOfService("https://www.meudominio.com.br/termos")
				.license(apacheLicense);
		return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
	}

}	

