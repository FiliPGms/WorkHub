package entidades;

public abstract class Espaco {
	private int id;
	private String nome;
	private double valorHora;
	private boolean disponivel;
	
	public Espaco(int id, String nome, double valorHora, boolean disponivel) {
		super();
		this.id = id;
		this.nome = nome;
		this.valorHora = valorHora;
		this.disponivel = disponivel;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public boolean getDisponibilidade(){
		return this.disponivel;
	}
	
	public int getId() {
		return this.id;
	}
	
	public double getValorHora() {
		return this.valorHora;
	}
	
	
	
	
}
