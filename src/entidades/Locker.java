package entidades;

import java.io.Serializable;

public class Locker implements ServicoAdicional,Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final double VALOR = 10.0;
	private String descricao;
	
	public Locker() {
		this.descricao = "Locker (Armario)";
	}
	
	@Override
	public double getValorTotal() {
		return VALOR;
	}
	
	@Override
	public String getDescricao() {
		return this.descricao;
	}
	
	 @Override
	public String toString() {
	    return descricao + " - R$ " + String.format("%.2f", VALOR);
	}
}
