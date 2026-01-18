package entidades;

import java.io.Serializable;
import java.time.Duration;

public class Estacionamento implements ServicoAdicional,Serializable {

	private static final long serialVersionUID = 1L;
	private static final double valorHora = 5;
	private String descricao;
	private double duracaoHoras;
	
	public Estacionamento(double duracaoHoras) {
		this.descricao = "Estacionamento";
		this.duracaoHoras = duracaoHoras;
	}
	
	@Override
	public double getValorTotal() {
		return this.duracaoHoras * valorHora;
	}
	
	@Override
	public String getDescricao() {
        return descricao + " (" + String.format("%.1f", duracaoHoras) + "h)";
    }
	
	public double getDuracaoHoras() {
	    return duracaoHoras;
	}
	    
	@Override
	public String toString() {
	    return descricao + " (" + String.format("%.1f", duracaoHoras) + "h) - R$ " + String.format("%.2f", getValorTotal());
	}
}
