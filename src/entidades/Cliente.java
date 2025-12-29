package entidades;
import java.time.*;

public class Cliente {
	private String cpf;
	private String email;
	private String nome;
	private String telefone;
	private String dataDeCadastro;
	
	public Cliente(String cpf, String email, String nome, String telefone, String dataDeCadastro) {
		super();
		this.cpf = cpf;
		this.email = email;
		this.nome = nome;
		this.telefone = telefone;
		this.dataDeCadastro = dataDeCadastro;
	}
	
	
}
