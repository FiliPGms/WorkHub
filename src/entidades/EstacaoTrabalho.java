package entidades;

public class EstacaoTrabalho extends Espaco{
	
	 private static final double VALOR_HORA = 30.0;
	 private static final int CAPACIDADE = 150;

	
	public EstacaoTrabalho(int id, String nome,boolean disponivel) {
		super(id,nome,disponivel);
	}
	
	@Override
	public double getValorHora() {
		return this.VALOR_HORA;
	}
	
	 public double getCapacidade() {
			return this.CAPACIDADE;
		}
}
