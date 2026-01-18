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
	
	public RecebimentoCorrespondencia() {
        this.descricao = "Recebimento de Correspondências";
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
