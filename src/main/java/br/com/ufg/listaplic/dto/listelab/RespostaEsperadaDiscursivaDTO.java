package br.com.ufg.listaplic.dto.listelab;

public class RespostaEsperadaDiscursivaDTO implements RespostaEsperadaInterface {

	private Integer peso;
	private String descricao;

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
