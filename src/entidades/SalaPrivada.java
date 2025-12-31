package entidades;

public class SalaPrivada extends Espaco {
	
	private int capacidade;

	public SalaPrivada(int capacidade,int id, String nome, double valorHora, boolean disponivel) {
		super(id, nome, valorHora, disponivel);
		this.capacidade = capacidade;
	}
	
}
