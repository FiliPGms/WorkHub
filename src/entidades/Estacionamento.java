package entidades;

import java.io.Serializable;

public class Estacionamento implements ServicoAdicional,Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double horasDuracao;
	private String descricao;
	
	public Estacionamento(double horasDuracao, String descricao) {
		this.horasDuracao = horasDuracao;
		this.descricao = descricao;
	}
	
	public double getValorTotal() {
		return 0;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
