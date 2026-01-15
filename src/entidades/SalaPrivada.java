package entidades;

public class SalaPrivada extends Espaco {
	
	private static final int CAPACIDADE = 20;
	private static final double VALOR_HORA = 35.0;


	public SalaPrivada(int id, String nome, boolean disponivel) {
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
