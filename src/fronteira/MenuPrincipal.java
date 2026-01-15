package fronteira;

import java.util.Scanner;

import controle.AdministradorSistema;
import excecoes.ClienteNaoEncontradoException;
import excecoes.EspacoIndisponivelException;
import excecoes.FalhaPersistenciaException;

public class MenuPrincipal {
	
	private AdministradorSistema admSistema;
	
	public MenuPrincipal() throws FalhaPersistenciaException{
		this.admSistema = new AdministradorSistema();
	}
	
	public void iniciaOperacao() throws FalhaPersistenciaException, ClienteNaoEncontradoException, EspacoIndisponivelException {
		
		Scanner sc = new Scanner(System.in);
		
		
		//Instanciando menus
		MenuClientes menuClientes = new MenuClientes(admSistema);
		MenuEspacos menuEspacos = new MenuEspacos(admSistema);
		MenuRelatorios menuRelatorios = new MenuRelatorios(admSistema);
		MenuReservas menuReservas = new MenuReservas(admSistema);
		
		System.out.println("--- SISTEMA DE RESERVAS ---");
        System.out.println("1. Clientes");
        System.out.println("2. Espaços");
        System.out.println("3. Reservas");
        System.out.println("4. Relatórios");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
        
        int opcao = sc.nextInt();
        
        do {
        	switch(opcao) {
          	case 0:
         		System.out.println("Saindo do menu...");
         		break;
         	case 1:
         		System.out.println("Redirecionando para o menu de Clientes...");
         		menuClientes.exibirMenuClientes();
         		break;
         	
         	case 2:
         		System.out.println("Redirecionando para o menu de Espaços...");
         		menuEspacos.exibirMenuEspacos();
         		break;
         	
         	case 3:
         		System.out.println("Redirecionando para o menu de Reservas...");
         		menuReservas.exibirMenuReservas();
         		break;
         	
         	case 4: 
         		System.out.println("Redirecionando para o menu de Relatórios...");
         		menuRelatorios.exibirMenuRelatorios();
         		break;
         	
         	
         	default:
         		System.err.println("Opção Inválida.");
         }
        	 
       
        }while(opcao!=0);
       
	}

}

