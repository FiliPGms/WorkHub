package fronteira;

import java.time.LocalDate;
import java.util.Scanner;
import controle.AdministradorSistema;
import entidades.Cliente;
import excecoes.ClienteJaCadastradoException;
import excecoes.ClienteNaoEncontradoException;
import excecoes.ExibidorErros;
import excecoes.FalhaPersistenciaException;

public class MenuClientes {
	
	private AdministradorSistema admSistema;
	private Scanner sc;
	
	public MenuClientes(AdministradorSistema admSistema, Scanner sc) {
		this.admSistema = admSistema;
		this.sc = sc;
	}
	
	public void exibirMenuClientes() {
		int opcao = -1;
		
		do {
			try {
				System.out.println("\n╔════════════════════════════════════╗");
				System.out.println("║      GERENCIAMENTO DE CLIENTES     ║");
				System.out.println("╚════════════════════════════════════╝");
				System.out.println("1. Cadastrar cliente");
				System.out.println("2. Buscar cliente");
				System.out.println("3. Voltar ao menu principal");
				System.out.println("0. Sair do sistema");
				System.out.print("Escolha uma opção: ");
				
				opcao = sc.nextInt();
				sc.nextLine(); 
				
				switch(opcao) {
					case 0:
						ExibidorErros.exibirSucesso("Encerrando o sistema...");
						System.exit(0);
						break;
						
					case 1:
						cadastrarCliente();
						break;
						
					case 2:
						buscarCliente();
						break;
						
					case 3:
						ExibidorErros.exibirNavegacao("Voltando ao menu principal...");
						return;
						
					default:
						ExibidorErros.exibir(new Exception("Opção inválida! Tente novamente."));
				}
				
			} catch (Exception e) {
				ExibidorErros.exibir(e);
				sc.nextLine(); 
			}
			
		} while(opcao != 0 && opcao != 3);
	}
	
	private void cadastrarCliente() {
		String continuar = "S";
		
		while(continuar.equalsIgnoreCase("S")) {
			try {
				System.out.println("\n--- CADASTRO DE CLIENTE ---");
				
				System.out.print("CPF (apenas números): ");
				String cpf = sc.nextLine().trim();
				
				if(cpf.isEmpty() || cpf.length() != 11) {
					ExibidorErros.exibirAviso("CPF deve conter 11 dígitos.");
					continue;
				}
				
				System.out.print("Nome completo: ");
				String nome = sc.nextLine().trim();
				
				if(nome.isEmpty()) {
					ExibidorErros.exibirAviso("Nome não pode estar vazio!");
					continue;
				}
				
				System.out.print("Email: ");
				String email = sc.nextLine().trim();
				
				if(email.isEmpty() || !email.contains("@")) {
					ExibidorErros.exibirAviso("Email inválido!");
					continue;
				}
				
				System.out.print("Telefone: ");
				String telefone = sc.nextLine().trim();
				
				if(telefone.isEmpty()) {
					ExibidorErros.exibirAviso("Telefone não pode estar vazio!");
					continue;
				}
				
				LocalDate dataCadastro = LocalDate.now();
				
				Cliente c = new Cliente(cpf, email, nome, telefone, dataCadastro);
				admSistema.cadastrarCliente(c);
				
				ExibidorErros.exibirSucesso("Cliente cadastrado com sucesso!");
				System.out.println("Nome: " + nome);
				System.out.println("CPF: " + cpf);
				System.out.println("Data de cadastro: " + dataCadastro);
				
			} catch (ClienteJaCadastradoException e) {
				ExibidorErros.exibir(e);
				
			} catch (FalhaPersistenciaException e) {
				ExibidorErros.exibir(e, "salvar os dados");
				
			} catch (Exception e) {
				ExibidorErros.exibir(e, "cadastrar cliente");
			}
			
			
			System.out.print("\nDeseja cadastrar outro cliente? (S/N): ");
			continuar = sc.nextLine().trim();
		}
		
		ExibidorErros.exibirNavegacao("Retornando ao menu de clientes...");
	}
	
	private void buscarCliente() {
		try {
			System.out.println("\n--- BUSCAR CLIENTE ---");
			System.out.print("Informe o CPF do cliente: ");
			String cpf = sc.nextLine().trim();
			
			Cliente cliente = admSistema.buscarCliente(cpf);
			
			ExibidorErros.exibirSucesso("Cliente encontrado!");
			System.out.println("─────────────────────────────");
			System.out.println("Nome: " + cliente.getNome());
			System.out.println("CPF: " + cliente.getCpf());
			System.out.println("Email: " + cliente.getEmail());
			System.out.println("Telefone: " + cliente.getTelefone());
			System.out.println("Data de cadastro: " + cliente.getDataCadastro());
			System.out.println("─────────────────────────────");
			
		} catch (ClienteNaoEncontradoException e) {
			ExibidorErros.exibir(e);
			ExibidorErros.exibirAviso("Verifique se o CPF está correto.");
			
		} catch (Exception e) {
			ExibidorErros.exibir(e, "buscar cliente");
		}
	}
}