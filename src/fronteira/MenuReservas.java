package fronteira;

import java.util.Scanner;

import controle.AdministradorSistema;
import entidades.Espaco;
import excecoes.EspacoIndisponivelException;
import excecoes.FalhaPersistenciaException;

public class MenuReservas {

	public void exibirMenuReservas() throws FalhaPersistenciaException {
		Scanner sc = new Scanner(System.in);
		
		AdministradorSistema admSistema = new AdministradorSistema();
		
		System.out.println("--- RESERVA ---");
        System.out.println("1. Criar Reserva");
        System.out.println("2. Buscar reserva");
        System.out.println("3. Cancelar reserva");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
        
        int opcao = sc.nextInt();
        
        while(opcao >= 0) {
        	switch(opcao) {
        	case 0:
        		System.out.println("Saindo do menu...");
        		break;
        	case 1:
            		System.out.println("*RESERVA*");
            		System.out.print("ID: ");
            		int id = sc.nextInt();
            		
            		
        		break;
        		
        	case 2:
        		System.out.print("Informe o id do Espaço: ");
        		int idEspaco = sc.nextInt();
        		try {
					admSistema.buscarEspaco(idEspaco);
				} catch (EspacoIndisponivelException e) {
					e.printStackTrace();
				}
        		break;
        	
        	case 3: 
        		MenuPrincipal principal = new MenuPrincipal();
        		principal.iniciaOperacao();
        		break;
        		
        	default:
        		System.err.println("Opção Inválida.");
        	}
        }
        sc.close();
	}
}
