package entidades;
import java.io.Serializable;

public abstract class Espaco implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String nome;
	private boolean disponivel;
	
	public Espaco(int id, String nome, boolean disponivel) {
		super();
		this.id = id;
		this.nome = nome;
		this.disponivel = disponivel;
	}
	
	public abstract double getValorHora();
	
	public String getNome() {
		return this.nome;
	}
	
	public boolean getDisponibilidade(){
		return this.disponivel;
	}
	
	public int getId() {
		return this.id;
	}
	
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (!(o instanceof Espaco)) return false;
	    Espaco e = (Espaco) o;
	    return id == e.id;
	}

	@Override
	public int hashCode() {
	    return Integer.hashCode(id);
	}
	
	
}
