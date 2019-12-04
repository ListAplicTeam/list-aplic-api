package br.com.ufg.listaplic.model;

public enum ApplicationListStatus {

    NAO_INICIADA,
    EM_ANDAMENTO,
    ENCERRADA;

    public boolean isNaoIniciada() {
        return this.equals(NAO_INICIADA);
    }

    public boolean isEmAndamento() {
        return this.equals(EM_ANDAMENTO);
    }

    public boolean isEncerrada() {
        return this.equals(ENCERRADA);
    }
}
