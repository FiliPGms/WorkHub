package entidades;

import java.io.Serializable;

public class RecebimentoCorrespondencia implements ServicoAdicional,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String descricao;
	public RecebimentoCorrespondencia(String descricao) {
		this.descricao = descricao;
	}
	
	public double getValorTotal() {
		return 0;
	}
	
	public String getDescricao() {
		return "...";
	}
}
