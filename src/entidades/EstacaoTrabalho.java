package entidades;

public class EstacaoTrabalho extends Espaco{
	
	private static final double VALOR_PADRAO = 15.00;
	 private static final int CAPACIDADE = 150;

	
	 public EstacaoTrabalho(int id, String nome, boolean disponivel) {
	        super(id, nome, disponivel);
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
        return "Estação de Trabalho";
    }
}
