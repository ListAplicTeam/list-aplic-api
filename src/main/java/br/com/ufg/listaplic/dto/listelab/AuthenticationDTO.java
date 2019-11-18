package br.com.ufg.listaplic.dto.listelab;

public class AuthenticationDTO {

    private String mensagem;

    private Boolean sucesso;

    private UserIntegrationDTO resultado;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Boolean getSucesso() {
        return sucesso;
    }

    public void setSucesso(Boolean sucesso) {
        this.sucesso = sucesso;
    }

    public UserIntegrationDTO getResultado() {
        return resultado;
    }

    public void setResultado(UserIntegrationDTO resultado) {
        this.resultado = resultado;
    }
}
