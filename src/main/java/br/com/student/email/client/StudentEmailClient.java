package br.com.student.email.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

import br.com.student.email.client.configuration.properties.WebServicesConfiguration;
import br.com.student.email.client.model.Student;
import br.com.student.email.client.model.StudentEmailInformation;
import br.com.student.email.client.model.StudentGrade;

@Component
public class StudentEmailClient {
	
	@Autowired
	private RestOperations restOperations;
	
	@Autowired
	private WebServicesConfiguration properties;
	
	/**
	 * Obtém todos os alunos
	 * @return todos os alunos
	 */
	public List<Student> getAllStudents() {
		ResponseEntity<List<Student>> studentResponse = restOperations.exchange(properties.getAllStudentsPath(),
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {
				});
		return studentResponse.getBody();
	}
	
	/**
	 * Obtém as notas de um determinado aluno
	 * @param cpf do aluno
	 * @return as notas de um determinado aluno
	 */
	public StudentGrade getStudentGrades(final String cpf) {
		return restOperations.getForObject(properties.getStudentGradesPath(), StudentGrade.class, cpf);
	}
	
	/**
	 * Envia os dados dos alunos para o serviço de e-mails
	 * @param dados dos alunos
	 * @return quantidade de e-mails enviados
	 */
	public Integer sendEmails(final List<StudentEmailInformation> students) {
		return restOperations.postForObject(properties.getSendEmailPath(), students, Integer.class);
	}
}
