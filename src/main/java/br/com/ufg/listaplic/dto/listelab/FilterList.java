package br.com.ufg.listaplic.dto.listelab;

import java.util.List;

public class FilterList {

	private String disciplina;
	private Integer nivelDificuldade;
	private String areaDeConhecimento;
	private Integer tempoEsperadoResposta;
	private List<String> tags;

	public String getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(String disciplina) {
		this.disciplina = disciplina;
	}

	public Integer getNivelDificuldade() {
		return nivelDificuldade;
	}

	public void setNivelDificuldade(Integer nivelDificuldade) {
		this.nivelDificuldade = nivelDificuldade;
	}

	public String getAreaDeConhecimento() {
		return areaDeConhecimento;
	}

	public void setAreaDeConhecimento(String areaDeConhecimento) {
		this.areaDeConhecimento = areaDeConhecimento;
	}

	public Integer getTempoEsperadoResposta() {
		return tempoEsperadoResposta;
	}

	public void setTempoEsperadoResposta(Integer tempoEsperadoResposta) {
		this.tempoEsperadoResposta = tempoEsperadoResposta;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}
}
