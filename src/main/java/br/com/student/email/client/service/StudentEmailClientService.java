package br.com.student.email.client.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.student.email.client.StudentEmailClient;
import br.com.student.email.client.exception.ResourceNotFoundException;
import br.com.student.email.client.model.Student;
import br.com.student.email.client.model.StudentEmailInformation;
import br.com.student.email.client.model.StudentGrade;

@Service
public class StudentEmailClientService {

	private static final Logger LOG = LoggerFactory.getLogger(StudentEmailClientService.class);

	@Autowired
	private StudentEmailClient client;

	public void processStudentsEmail() {
		List<Student> students = client.getAllStudents();
		List<StudentEmailInformation> studentsToBeNotified = getAllStudentsToBeNotified(students);

		if (studentsToBeNotified.isEmpty()) {
			LOG.info("Não há alunos com notas abaixo de " + StudentGrade.MIN_GRADE);
		} else {
			LOG.info("Enviando dados dos alunos a serem notificados para o serviço de e-mail...");
			Integer numberOfEmails = client.sendEmails(studentsToBeNotified);
			LOG.info("Quantidade de e-mails enviados: " + numberOfEmails);
		}

	}

	private List<StudentEmailInformation> getAllStudentsToBeNotified(List<Student> students) {
		List<StudentEmailInformation> studentsToBeNotified = new ArrayList<StudentEmailInformation>();
		for (Student student : students) {
			try {
				StudentGrade grade = client.getStudentGrades(student.getCpf());
				StudentEmailInformation studentInfo = createStudentEmailInformation(student, grade);
				if (studentInfo != null) {
					studentsToBeNotified.add(studentInfo);
				}
			} catch (ResourceNotFoundException e) {
				LOG.error("Não foram encontradas notas para o(a) aluno(a) " + student.getName());
			}

		}
		return studentsToBeNotified;
	}

	private StudentEmailInformation createStudentEmailInformation(Student student, StudentGrade studentGrade) {

		StringBuilder disciplineMsg = new StringBuilder();
		StudentEmailInformation studentInfo = null;

		for (String discipline : studentGrade.getGradeByDiscipline().keySet()) {
			Double grade = studentGrade.getGradeByDiscipline().get(discipline);
			if (grade < StudentGrade.MIN_GRADE) {
				disciplineMsg.append(System.lineSeparator()).append(discipline).append(" - ").append(grade);
			}
		}

		if (disciplineMsg.length() > 0) {
			StringBuilder msg = new StringBuilder(student.getName().toUpperCase()).append(System.lineSeparator())
					.append("Você possui nota(s) abaixo de ").append(StudentGrade.MIN_GRADE)
					.append(" na(s) seguinte(s) disciplina(s):").append(disciplineMsg);
			
			studentInfo = new StudentEmailInformation();
			studentInfo.setAddress(student.getAddress());
			studentInfo.setCep(student.getCep());
			studentInfo.setEmail(student.getEmail());
			studentInfo.setName(student.getName());
			studentInfo.setMsg(msg.toString());
			
			LOG.info("Aluno(a)" + student.getName() +" deve ser notificado no e-mail "+student.getEmail());
		}
		return studentInfo;
	}
}
