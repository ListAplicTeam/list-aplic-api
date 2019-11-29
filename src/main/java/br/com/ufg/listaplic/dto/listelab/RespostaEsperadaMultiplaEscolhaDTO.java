package br.com.ufg.listaplic.dto.listelab;

public class RespostaEsperadaMultiplaEscolhaDTO implements RespostaEsperadaInterface {

	private String descricao;
	private Boolean correta;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getCorreta() {
		return correta;
	}

	public void setCorreta(Boolean correta) {
		this.correta = correta;
	}
}
