package entidades;

import java.io.Serializable;

public class RecebimentoCorrespondencia implements ServicoAdicional,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String descricao;
	private static final double VALOR = 7.0;
	public RecebimentoCorrespondencia(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public double getValorTotal() {
		return this.VALOR;
	}
	
	@Override
	public String getDescricao() {
		return this.descricao;
	}
}
