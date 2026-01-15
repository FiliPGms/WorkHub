package entidades;

import java.io.Serializable;

public class CafePremium implements ServicoAdicional,Serializable{
	
	private static final long serialVersionUID = 1L;
	private String descricao;
	private static final double VALOR = 5.0;
	
	public CafePremium(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public double getValorTotal() {
		return this.VALOR;
	}
	
	@Override
	public String getDescricao() {
		return descricao;
	}
}
