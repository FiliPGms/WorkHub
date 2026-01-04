package entidades;

import java.io.Serializable;

public class Locker implements ServicoAdicional,Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double valor;
	private String descricao;
	
	public Locker(double valor, String descricao) {
		this.valor = valor;
		this.descricao = descricao;
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
