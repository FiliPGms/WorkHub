package fronteira;

import java.util.Scanner;
import controle.AdministradorSistema;
import entidades.Auditorio;
import entidades.Espaco;
import entidades.EstacaoTrabalho;
import entidades.SalaPrivada;
import entidades.SalaReuniao;
import excecoes.EspacoIndisponivelException;
import excecoes.ExibidorErros;
import excecoes.FalhaPersistenciaException;

public class MenuEspacos {
	
	private AdministradorSistema admSistema;
	private Scanner sc;
	
	public MenuEspacos(AdministradorSistema admSistema, Scanner sc) {
		this.admSistema = admSistema;
		this.sc = sc;
	}
	
	public void exibirMenuEspacos() {
		int opcao = -1;
		
		do {
			try {
				System.out.println("\n╔════════════════════════════════════╗");
				System.out.println("║      GERENCIAMENTO DE ESPAÇOS      ║");
				System.out.println("╚════════════════════════════════════╝");
				System.out.println("1. Cadastrar espaço");
				System.out.println("2. Buscar espaço");
				System.out.println("3. Voltar ao menu principal");
				System.out.println("0. Sair do sistema");
				System.out.print("Escolha uma opção: ");
				
				opcao = sc.nextInt();
				sc.nextLine(); // Limpa o buffer
				
				switch(opcao) {
					case 0:
						ExibidorErros.exibirSucesso("Encerrando o sistema...");
						System.exit(0);
						break;
						
					case 1:
						cadastrarEspaco();
						break;
						
					case 2:
						buscarEspaco();
						break;
						
					case 3:
						ExibidorErros.exibirNavegacao("Voltando ao menu principal...");
						return;
						
					default:
						ExibidorErros.exibir(new Exception("Opção inválida! Tente novamente."));
				}
				
			} catch (Exception e) {
				ExibidorErros.exibir(e);
				sc.nextLine(); // Limpa o buffer
			}
			
		} while(opcao != 0 && opcao != 3);
	}
	
	private void cadastrarEspaco() {
		String continuar = "S";
		
		while(continuar.equalsIgnoreCase("S")) {
			try {
				System.out.println("\n--- CADASTRO DE ESPAÇO ---");
				System.out.println("Escolha o tipo de espaço:");
				System.out.println("1. Estação de Trabalho");
				System.out.println("2. Sala Privada");
				System.out.println("3. Sala de Reunião");
				System.out.println("4. Auditório");
				System.out.print("Opção: ");
				
				int tipo = sc.nextInt();
				sc.nextLine(); //sempre pra limpar o buffer 
				
				System.out.print("\nID do espaço (número): ");
				int id = sc.nextInt();
				sc.nextLine();
				
				// Verifica se o ID já existe
				try {
					admSistema.buscarEspaco(id);
					ExibidorErros.exibir(new Exception("Já existe um espaço cadastrado com este ID!"));
					continue;
				} catch (EspacoIndisponivelException e) {
					// ID não existe, continua
				}
				
				System.out.print("Nome do espaço: ");
				String nome = sc.nextLine().trim();
				
				if(nome.isEmpty()) {
					ExibidorErros.exibirAviso("Nome não pode estar vazio!");
					continue;
				}
				
				
				boolean disponivel = true;
				 
				
				Espaco espaco = null;
				String tipoEspaco = "";
				
				switch(tipo) {
					case 1:
						espaco = new EstacaoTrabalho(id, nome, disponivel);
						tipoEspaco = "Estação de Trabalho";
						break;
					case 2:
						espaco = new SalaPrivada(id, nome, disponivel);
						tipoEspaco = "Sala Privada";
						break;
					case 3:
						espaco = new SalaReuniao(id, nome, disponivel);
						tipoEspaco = "Sala de Reunião";
						break;
					case 4:
						espaco = new Auditorio(id, nome, disponivel);
						tipoEspaco = "Auditório";
						break;
					default:
						ExibidorErros.exibir(new Exception("Tipo de espaço inválido!"));
						continue;
				}
				
				admSistema.cadastrarEspaco(espaco);
				
				ExibidorErros.exibirSucesso("Espaço cadastrado com sucesso!");
				System.out.println("Tipo: " + tipoEspaco);
				System.out.println("ID: " + id);
				System.out.println("Nome: " + nome);
				System.out.println("Disponível: " + (disponivel ? "Sim" : "Não"));
				
			} catch (FalhaPersistenciaException e) {
				ExibidorErros.exibir(e, "salvar os dados");
				
			} catch (Exception e) {
				ExibidorErros.exibir(e, "cadastrar espaço");
				sc.nextLine(); 
			}
			
		
			System.out.print("\nDeseja cadastrar outro espaço? (S/N): ");
			continuar = sc.nextLine().trim();
		}
		
		ExibidorErros.exibirNavegacao("Retornando ao menu de espaços...");
	}
	
	private void buscarEspaco() {
		try {
			System.out.println("\n--- BUSCAR ESPAÇO ---");
			System.out.print("Informe o ID do espaço: ");
			int id = sc.nextInt();
			sc.nextLine();
			
			Espaco espaco = admSistema.buscarEspaco(id);
			
			ExibidorErros.exibirSucesso("Espaço encontrado!");
			System.out.println("─────────────────────────────");
			System.out.println("ID: " + espaco.getId());
			System.out.println("Nome: " + espaco.getNome());
			System.out.println("Tipo: " + espaco.getTipo());
			System.out.println("Valor/hora: R$ " + espaco.getValorHora());
			System.out.println("Disponível: " + (espaco.getDisponibilidade() ? "Sim" : "Não"));
			System.out.println("─────────────────────────────");
			
		} catch (EspacoIndisponivelException e) {
			ExibidorErros.exibir(e);
			ExibidorErros.exibirAviso("Verifique se o ID está correto.");
			
		} catch (Exception e) {
			ExibidorErros.exibir(e, "buscar espaço");
		}
	}
}