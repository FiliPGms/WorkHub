package entidades;

public class Auditorio extends Espaco{
	private static final int CAPACIDADE = 50;
	private static final double VALOR_PADRAO = 25.0;

	
	public Auditorio(int id, String nome,boolean disponivel) {
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
        return "Auditorio";
    }
	
}
