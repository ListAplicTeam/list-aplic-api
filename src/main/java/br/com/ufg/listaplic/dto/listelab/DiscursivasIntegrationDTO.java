package br.com.ufg.listaplic.dto.listelab;

import java.util.List;
import java.util.UUID;

public class DiscursivasIntegrationDTO {

    private UUID id;
    private String enunciado;
    private String usuario;
    private Long areaDeConhecimento;
    private Integer nivelDificuldade;
    private Integer disciplina;
    private List<String> tags;
    private Integer tipo;
    private Integer TempoMaximoDeResposta;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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

    public Integer getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Integer disciplina) {
        this.disciplina = disciplina;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Integer getTempoMaximoDeResposta() {
        return TempoMaximoDeResposta;
    }

    public void setTempoMaximoDeResposta(Integer tempoMaximoDeResposta) {
        TempoMaximoDeResposta = tempoMaximoDeResposta;
    }
}
