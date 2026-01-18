package fronteira;

import java.util.Scanner;
import controle.AdministradorSistema;
import excecoes.ExibidorErros;
import excecoes.FalhaPersistenciaException;

public class MenuPrincipal {
	
	private AdministradorSistema admSistema;
	private Scanner sc;
	
	public MenuPrincipal() throws FalhaPersistenciaException {
		this.admSistema = new AdministradorSistema();
		this.sc = new Scanner(System.in);
	}
	
	public void iniciaOperacao() {
		
		// Instanciando menus uma única vez
		MenuClientes menuClientes = new MenuClientes(admSistema, sc);
		MenuEspacos menuEspacos = new MenuEspacos(admSistema, sc);
		MenuRelatorios menuRelatorios = new MenuRelatorios(admSistema, sc);
		MenuReservas menuReservas = new MenuReservas(admSistema, sc);
		MenuServicos menuServicos = new MenuServicos(admSistema, sc);
		
		int opcao = -1;
		
		do {
			try {
				System.out.println("\n╔════════════════════════════════════╗");
				System.out.println("║   SISTEMA DE RESERVAS - WORKHUB   ║");
				System.out.println("╚════════════════════════════════════╝");
				System.out.println("1. Clientes");
				System.out.println("2. Espaços");
				System.out.println("3. Reservas");
				System.out.println("4. Serviços Adicionais");
				System.out.println("5. Relatórios");
				System.out.println("0. Sair");
				System.out.print("Escolha uma opção: ");
				
				opcao = sc.nextInt();
				sc.nextLine(); // Limpa o buffer
				
				switch(opcao) {
					case 0:
						ExibidorErros.exibirSucesso("Encerrando o sistema...");
						System.out.println("Obrigado por usar o WorkHub!");
						break;
					
					case 1:
						menuClientes.exibirMenuClientes();
						break;
					
					case 2:
						menuEspacos.exibirMenuEspacos();
						break;
					
					case 3:
						menuReservas.exibirMenuReservas();
						break;
					
					case 4:
						menuServicos.exibirMenuServicos();
						break;
					case 5:
						menuRelatorios.exibirMenuRelatorios();
					
					default:
						ExibidorErros.exibir(new Exception("Opção inválida! Tente novamente."));
				}
			} catch (Exception e) {
				ExibidorErros.exibir(e, "processar a operação");
				sc.nextLine(); // Limpa o buffer em caso de erro
			}
			
		} while(opcao != 0);
		
		sc.close();
	}
}