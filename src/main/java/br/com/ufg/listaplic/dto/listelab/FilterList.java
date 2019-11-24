package br.com.ufg.listaplic.dto.listelab;

import java.util.List;

public class FilterList {

	private String subjectCode;
	private String difficultyLevel;
	private String knowledgeAreaCode;
	private Integer answerTime;
	private List<String> tags;

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(String difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public String getKnowledgeAreaCode() {
		return knowledgeAreaCode;
	}

	public void setKnowledgeAreaCode(String knowledgeAreaCode) {
		this.knowledgeAreaCode = knowledgeAreaCode;
	}

	public Integer getAnswerTime() {
		return answerTime;
	}

	public void setAnswerTime(Integer answerTime) {
		this.answerTime = answerTime;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}
}
