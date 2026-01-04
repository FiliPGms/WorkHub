package entidades;

import java.io.Serializable;

public class Locker implements ServicoAdicional,Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int quantidade;
	private String descricao;
	
	public Locker(int quantidade, String descricao) {
		this.quantidade = quantidade;
		this.descricao = descricao;
	}
	
	public double getValorTotal() {
		return 0;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
}
