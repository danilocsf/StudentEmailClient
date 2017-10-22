package br.com.student.email.client.configuration.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:webservices.properties")
public class WebServicesConfiguration {

	@Value("${get.all.students.path}")
	private String allStudentsPath;
	
	@Value("${get.student.grades.path}")
	private String studentGradesPath;
		
	@Value("${send.email.path}")
	private String sendEmailPath;

	public String getAllStudentsPath() {
		return allStudentsPath;
	}

	public void setAllStudentsPath(String allStudentsPath) {
		this.allStudentsPath = allStudentsPath;
	}

	public String getStudentGradesPath() {
		return studentGradesPath;
	}

	public void setStudentGradesPath(String studentGradesPath) {
		this.studentGradesPath = studentGradesPath;
	}

	public String getSendEmailPath() {
		return sendEmailPath;
	}

	public void setSendEmailPath(String sendEmailPath) {
		this.sendEmailPath = sendEmailPath;
	}
	
	
}
