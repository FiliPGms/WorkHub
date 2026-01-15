package entidades;

import java.io.Serializable;

public class Locker implements ServicoAdicional,Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final double VALOR = 10.0;
	private String descricao;
	
	public Locker(String descricao) {
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
