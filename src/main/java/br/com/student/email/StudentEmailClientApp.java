package br.com.student.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.student.email.client.service.StudentEmailClientService;

/**
 * Aplicação client responsável por obter os dados dos alunos
 * e encaminhar para o server processar o envio de e-mails.
 * @author Danilo Ferreira 
 */
@SpringBootApplication
public class StudentEmailClientApp implements CommandLineRunner{
	
	@Autowired
	private StudentEmailClientService service;
	
	public static void main(String[] args) {
		SpringApplication.run(StudentEmailClientApp.class, args);
	}

	
	public void run(String... args) throws Exception {
		service.processStudentsEmail();	
	}
}
