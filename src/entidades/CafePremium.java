package entidades;

import java.io.Serializable;

public class CafePremium implements ServicoAdicional,Serializable{
	
	private static final long serialVersionUID = 1L;
	private String descricao;
	private static final double VALOR = 5.0;
	
	public CafePremium() {
		this.descricao = "Cafe Premium";
	}
	
	@Override
	public double getValorTotal() {
		return VALOR;
	}
	
	@Override
	public String getDescricao() {
		return descricao;
	}
	
	@Override
    public String toString() {
        return descricao + " - R$ " + String.format("%.2f", VALOR);
    }
}
