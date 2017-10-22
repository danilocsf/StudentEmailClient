package br.com.student.email.client.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import br.com.student.email.client.CustomClientErrorHandler;

@Configuration
public class RestConfig {

	@Bean
	public RestOperations createRestTemplate(final ClientHttpRequestFactory factory) {
		RestTemplate template = new RestTemplate(factory);
		template.setErrorHandler(new CustomClientErrorHandler());
		return template;
	}

	@Bean
	public ClientHttpRequestFactory createClientHttpRequestFactory(
			@Value("${connect.timeout}") final int connectTimeout,
			@Value("${read.timeout}") final int readTimeout) {
		
		HttpComponentsClientHttpRequestFactory factory =  new HttpComponentsClientHttpRequestFactory();
		factory.setReadTimeout(readTimeout);
		factory.setConnectTimeout(connectTimeout);
		return factory;
	}
}
