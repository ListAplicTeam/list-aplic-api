package br.com.ufg.listaplic.dto.listelab;

import java.util.List;
import java.util.UUID;

public class ListIntegrationDTO {

    private UUID id;
    private String titulo;
    private Long areaDeConhecimento;
    private Integer nivelDificuldade;
    private List<String> tags;
    private Integer disciplina;
    private List<DiscursivasIntegrationDTO> discursivas;
    private List<ObjetivasIntegrationDTO> objetivas;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getAreaDeConhecimento() {
        return areaDeConhecimento;
    }

    public void setAreaDeConhecimento(Long areaDeConhecimento) {
        this.areaDeConhecimento = areaDeConhecimento;
    }

    public Integer getNivelDificuldade() {
        return nivelDificuldade;
    }

    public void setNivelDificuldade(Integer nivelDificuldade) {
        this.nivelDificuldade = nivelDificuldade;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Integer getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Integer disciplina) {
        this.disciplina = disciplina;
    }

    public List<DiscursivasIntegrationDTO> getDiscursivas() {
        return discursivas;
    }

    public void setDiscursivas(List<DiscursivasIntegrationDTO> discursivas) {
        this.discursivas = discursivas;
    }

    public List<ObjetivasIntegrationDTO> getObjetivas() {
        return objetivas;
    }

    public void setObjetivas(List<ObjetivasIntegrationDTO> objetivas) {
        this.objetivas = objetivas;
    }
}
