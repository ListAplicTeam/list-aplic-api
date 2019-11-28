package br.com.ufg.listaplic.dto.listelab;

public class QuestoesIntegrationDTO<T extends RespostaEsperadaInterface> {

    private Integer numero;
    private Integer peso;
    private QuestaoIntegrationDTO<T> questao;

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public QuestaoIntegrationDTO<T> getQuestao() {
        return questao;
    }

    public void setQuestao(QuestaoIntegrationDTO<T> questao) {
        this.questao = questao;
    }
}