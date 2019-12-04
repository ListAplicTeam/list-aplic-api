package br.com.ufg.listaplic.dto.listelab;

public class RespostaEsperadaAssociacaoDeColunasDTO implements RespostaEsperadaInterface {

	private ColunaDTO colunaPrincipal;
	private ColunaDTO colunaAssociada;

	public ColunaDTO getColunaPrincipal() {
		return colunaPrincipal;
	}

	public void setColunaPrincipal(ColunaDTO colunaPrincipal) {
		this.colunaPrincipal = colunaPrincipal;
	}

	public ColunaDTO getColunaAssociada() {
		return colunaAssociada;
	}

	public void setColunaAssociada(ColunaDTO colunaAssociada) {
		this.colunaAssociada = colunaAssociada;
	}

}
