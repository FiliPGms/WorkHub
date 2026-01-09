package entidades;
import java.time.*;
import java.io.Serializable;

public class Cliente implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cpf;
	private String email;
	private String nome;
	private String telefone;
	private LocalDate dataDeCadastro;
	
	public Cliente(String cpf, String email, String nome, String telefone, LocalDate dataDeCadastro) {
		super();
		this.cpf = cpf;
		this.email = email;
		this.nome = nome;
		this.telefone = telefone;
		this.dataDeCadastro = dataDeCadastro;
	}
	
	public String getCpf() {
		return this.cpf;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getTelefone() {
		return this.telefone;
	}
	
	public LocalDate getData() {
		return this.dataDeCadastro;
	}
	
	public void atualizarContato(String e, String t) {
		this.email = e;
		this.telefone = t;
	}
	
	@Override
	public boolean equals(Object o) {
	    if (this == o) return true;
	    if (!(o instanceof Cliente)) return false;
	    Cliente c = (Cliente) o;
	    return cpf.equals(c.cpf);
	}
	
	@Override
	public int hashCode() {
	    return cpf.hashCode();
	}
}
