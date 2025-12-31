package entidades;

public class SalaReuniao extends Espaco {
	
	private int capacidade;
	
	public SalaReuniao(int capacidade,int id, String nome, double valorHora, boolean disponivel) {
		super(id, nome, valorHora, disponivel);
		this.capacidade = capacidade;
	}
	
}
