package entidades;

import java.io.Serializable;
import java.time.Duration;

public class Estacionamento implements ServicoAdicional,Serializable {

	private static final long serialVersionUID = 1L;
	private double valorHora;
	private String descricao;
	Reserva r;
	
	public Estacionamento(double valorHora, String descricao) {
		this.valorHora = valorHora;
		this.descricao = descricao;
	}
	
	@Override
	public double getValorTotal() {
		double horas = Duration.between(r.getHoraInicio(), r.getHoraFim()).toMinutes()/60.0;
		return horas * this.valorHora;
	}
	
	@Override
	public String getDescricao() {
		return this.descricao;
	}
}
