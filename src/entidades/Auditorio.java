package entidades;

public class Auditorio extends Espaco{
	private int capacidade;
	
	public Auditorio(int capacidade,int id, String nome, double valorHora, boolean disponivel) {
		super(id, nome, valorHora, disponivel);
		this.capacidade = capacidade;
	}
	
}
