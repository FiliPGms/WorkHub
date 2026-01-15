package entidades;

public class SalaReuniao extends Espaco {
	
	private static final int CAPACIDADE = 30;
	private static final double VALOR_HORA = 35.0;

	
	public SalaReuniao(int id, String nome, boolean disponivel) {
		super(id, nome,disponivel);
	}
	
	@Override
	public double getValorHora() {
		return this.VALOR_HORA;
	}
	
	public double getCapacidade() {
		return this.CAPACIDADE;
	}
	
}
