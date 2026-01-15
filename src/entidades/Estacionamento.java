package entidades;

import java.io.Serializable;
import java.time.Duration;

public class Estacionamento implements ServicoAdicional,Serializable {

	private static final long serialVersionUID = 1L;
	private static final double valorHora = 5;
	private String descricao;
	Reserva r;
	
	public Estacionamento(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public double getValorTotal() {
		return r.calcularDuracaoHoras() * valorHora;
	}
	
	@Override
	public String getDescricao() {
		return this.descricao;
	}
}
