package entidades;

public class Auditorio extends Espaco{
	private static final int CAPACIDADE = 50;
	private static final double VALOR_HORA = 25.0;

	
	public Auditorio(int id, String nome,boolean disponivel) {
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
