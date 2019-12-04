package br.com.ufg.listaplic.dto.listelab;

import java.util.List;
import java.util.UUID;

public class ListIntegrationDTO {

    private UUID id;
    private String titulo;
    private String usuario;
    private Boolean prontaParaAplicacao;
	private Integer nivelDeDificuldade;
    private List<QuestoesIntegrationDTO<RespostaEsperadaDiscursivaDTO>> questoesDiscursiva;
    private List<QuestoesIntegrationDTO<RespostaEsperadaMultiplaEscolhaDTO>> questoesMultiplaEscolha;
    private List<QuestoesIntegrationDTO<RespostaEsperadaVerdadeiroOuFalsoDTO>> questoesVerdadeiroOuFalso;
    private List<QuestoesIntegrationDTO<RespostaEsperadaAssociacaoDeColunasDTO>> questoesAssociacaoDeColunas;

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

    public List<QuestoesIntegrationDTO<RespostaEsperadaDiscursivaDTO>> getQuestoesDiscursiva() {
        return questoesDiscursiva;
    }

    public void setQuestoesDiscursiva(List<QuestoesIntegrationDTO<RespostaEsperadaDiscursivaDTO>> questoesDiscursiva) {
        this.questoesDiscursiva = questoesDiscursiva;
    }

    public List<QuestoesIntegrationDTO<RespostaEsperadaMultiplaEscolhaDTO>> getQuestoesMultiplaEscolha() {
        return questoesMultiplaEscolha;
    }

    public void setQuestoesMultiplaEscolha(List<QuestoesIntegrationDTO<RespostaEsperadaMultiplaEscolhaDTO>> questoesMultiplaEscolha) {
        this.questoesMultiplaEscolha = questoesMultiplaEscolha;
    }

    public List<QuestoesIntegrationDTO<RespostaEsperadaVerdadeiroOuFalsoDTO>> getQuestoesVerdadeiroOuFalso() {
        return questoesVerdadeiroOuFalso;
    }

    public void setQuestoesVerdadeiroOuFalso(List<QuestoesIntegrationDTO<RespostaEsperadaVerdadeiroOuFalsoDTO>> questoesVerdadeiroOuFalso) {
        this.questoesVerdadeiroOuFalso = questoesVerdadeiroOuFalso;
    }

    public List<QuestoesIntegrationDTO<RespostaEsperadaAssociacaoDeColunasDTO>> getQuestoesAssociacaoDeColunas() {
        return questoesAssociacaoDeColunas;
    }

    public void setQuestoesAssociacaoDeColunas(List<QuestoesIntegrationDTO<RespostaEsperadaAssociacaoDeColunasDTO>> questoesAssociacaoDeColunas) {
        this.questoesAssociacaoDeColunas = questoesAssociacaoDeColunas;
    }

	public Integer getNivelDeDificuldade() {
		return nivelDeDificuldade;
	}

	public void setNivelDeDificuldade(Integer nivelDeDificuldade) {
		this.nivelDeDificuldade = nivelDeDificuldade;
	}
}
