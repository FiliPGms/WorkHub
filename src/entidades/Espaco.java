package entidades;
import java.io.Serializable;

public abstract class Espaco implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (o == null || getClass() != o.getClass()) return false;

	    Espaco espaco = (Espaco) o;
	    return this.id == espaco.id;
	}
	
	
}
