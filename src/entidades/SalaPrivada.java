package entidades;

public class SalaPrivada extends Espaco {
	
	private static final int CAPACIDADE = 20;
	private static final double VALOR_PADRAO = 35.0;


	public SalaPrivada(int id, String nome, boolean disponivel) {
		super(id, nome,disponivel);
	}
	
	@Override 
	public double getValorHora() {
		return this.VALOR_PADRAO;
	}
	public double getCapacidade() {
		return this.CAPACIDADE;
	}
	
	@Override
    public String getTipo() {
        return "Sala Privada";
    }
	
}
