package entidades;

public class Estacionamento implements ServicoAdicional {
	
	private double horasDuracao;
	private String descricao;
	
	public Estacionamento(double horasDuracao, String descricao) {
		this.horasDuracao = horasDuracao;
		this.descricao = descricao;
	}
	
	public double getValorTotal() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
