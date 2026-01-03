package entidades;

import java.io.Serializable;

public class CafePremium implements ServicoAdicional,Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String descricao;
	
	public CafePremium(String descricao) {
		this.descricao = descricao;
	}
	

	public double getValorTotal() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String getDescricao() {
		return "...";
	}
}
