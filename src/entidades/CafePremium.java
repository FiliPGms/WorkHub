package entidades;

import java.io.Serializable;

public class CafePremium implements ServicoAdicional,Serializable{
	
	private static final long serialVersionUID = 1L;
	private String descricao;
	private double valor;
	
	public CafePremium(double valor,String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public double getValorTotal() {
		return this.valor;
	}
	
	@Override
	public String getDescricao() {
		return descricao;
	}
}
