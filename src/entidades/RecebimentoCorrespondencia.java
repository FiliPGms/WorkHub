package entidades;

import java.io.Serializable;

public class RecebimentoCorrespondencia implements ServicoAdicional,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String descricao;
	private double valor;
	public RecebimentoCorrespondencia(double valor,String descricao) {
		this.descricao = descricao;
		this.valor = valor;
	}
	
	@Override
	public double getValorTotal() {
		return this.valor;
	}
	
	@Override
	public String getDescricao() {
		return this.descricao;
	}
}
