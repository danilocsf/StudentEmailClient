package br.com.student.email.client.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentGrade {
	
	public static final Double MIN_GRADE = 7d; 
	
	private String cpf;

	@JsonProperty("notas")
	private Map<String, Double> gradeByDiscipline = new HashMap<String, Double>();

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Map<String, Double> getGradeByDiscipline() {
		return gradeByDiscipline;
	}

	public void setGradeByDiscipline(Map<String, Double> gradeByDiscipline) {
		this.gradeByDiscipline = gradeByDiscipline;
	}
		
}
