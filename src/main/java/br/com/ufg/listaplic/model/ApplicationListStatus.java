package br.com.ufg.listaplic.model;

public enum ApplicationListStatus {
    NAO_INICIADA,
    EM_ANDAMENTO,
    ENCERRADA;

    public boolean isEmAndamento() {
        return this.equals(EM_ANDAMENTO);
    }

    public boolean isEncerrada() {
        return this.equals(ENCERRADA);
    }
}
