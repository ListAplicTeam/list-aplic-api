package br.com.ufg.listaplic.dto.listelab;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListElabKnowledgeAreaResultDTO {

	private String mensagem;
	private Boolean sucesso;
	private List<AreaDoConhecimentoDTO> resultado;

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

	public List<AreaDoConhecimentoDTO> getResultado() {
		return resultado;
	}

	public void setResultado(List<AreaDoConhecimentoDTO> resultado) {
		this.resultado = resultado;
	}
}
