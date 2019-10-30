package br.com.ufg.listaplic.dto.listelab;

import java.util.List;
import java.util.UUID;

public class DiscursivasIntegrationDTO {

    private UUID id;
    private String enunciado;
    private String usuario;
    private AreaDoConhecimentoDTO areaDeConhecimento;
    private Integer nivelDificuldade;
    private DisciplinaIntegrationDTO disciplina;
    private List<String> tags;
    private Integer tipo;
    private Integer tempoMaximoDeResposta;

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

    public AreaDoConhecimentoDTO getAreaDeConhecimento() {
        return areaDeConhecimento;
    }

    public void setAreaDeConhecimento(AreaDoConhecimentoDTO areaDeConhecimento) {
        this.areaDeConhecimento = areaDeConhecimento;
    }

    public Integer getNivelDificuldade() {
        return nivelDificuldade;
    }

    public void setNivelDificuldade(Integer nivelDificuldade) {
        this.nivelDificuldade = nivelDificuldade;
    }

    public DisciplinaIntegrationDTO getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(DisciplinaIntegrationDTO disciplina) {
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
        return tempoMaximoDeResposta;
    }

    public void setTempoMaximoDeResposta(Integer tempoMaximoDeResposta) {
        this.tempoMaximoDeResposta = tempoMaximoDeResposta;
    }
}
