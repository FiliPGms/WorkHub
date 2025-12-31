package entidades;
import java.time.*;

public class Cliente {
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
	
	public void atualizarContato(String e, String t) {
		this.email = e;
		this.telefone = t;
	}
}
