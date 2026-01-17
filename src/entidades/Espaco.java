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
	
	public Espaco(int id, String nome, boolean disponivel) {
		super();
		this.id = id;
		this.nome = nome;
		this.disponivel = disponivel;
		this.valorHora = getValorHora();
	}
	
	public abstract double getValorHora();
	
	public abstract String getTipo();
	
	public String getDescricaoCompleta() {
        return getTipo() + " - " + nome;
    }
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
	    this.nome = nome;
	}
	
	public void setValorHora(double valorHora) {
	    this.valorHora = valorHora;
	 }
	
	public boolean getDisponibilidade(){
		return this.disponivel;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
	
	@Override
    public String toString() {
        return String.format("%s [ID: %d, Nome: %s, Valor/hora: R$ %.2f, Disponível: %s]",
                getTipo(), id, nome, valorHora, disponivel ? "Sim" : "Não");
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
