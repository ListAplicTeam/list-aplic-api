package br.com.ufg.listaplic.dto.listelab;

import java.util.List;
import java.util.UUID;

public class ListIntegrationDTO {

    private UUID id;
    private String titulo;
    private String usuario;
    private Boolean prontaParaAplicacao;
    private List<QuestaoIntegrationDTO> questoes;

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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Boolean getProntaParaAplicacao() {
        return prontaParaAplicacao;
    }

    public void setProntaParaAplicacao(Boolean prontaParaAplicacao) {
        this.prontaParaAplicacao = prontaParaAplicacao;
    }

    public List<QuestaoIntegrationDTO> getQuestoes() {
        return questoes;
    }

    public void setQuestoes(List<QuestaoIntegrationDTO> questoes) {
        this.questoes = questoes;
    }
}
