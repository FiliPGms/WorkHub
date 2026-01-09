package fronteira;

import java.util.Scanner;

import controle.AdministradorSistema;
import entidades.Espaco;
import excecoes.ClienteNaoEncontradoException;
import excecoes.EspacoIndisponivelException;
import excecoes.FalhaPersistenciaException;

public class MenuEspacos {

	public void exibirMenuEspacos() throws FalhaPersistenciaException {
		Scanner sc = new Scanner(System.in);
		
		AdministradorSistema admSistema = new AdministradorSistema();
		
		System.out.println("--- ESPAÇOS ---");
        System.out.println("1. Cadastrar espaço");
        System.out.println("2. Buscar espaço");
        System.out.println("3. Voltar");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
        
        int opcao = sc.nextInt();
        
        while(opcao >= 0) {
        	switch(opcao) {
        	case 0:
        		System.out.println("Saindo do menu...");
        		break;
        	case 1:
            		System.out.println("*DADOS DO ESPAÇO*");
            		System.out.print("ID: ");
            		int id = sc.nextInt();
            		
            		System.out.print("Nome: ");
            		String nome = sc.next();
            		
            		System.out.print("Valor por hora: ");
            		double valorHora = sc.nextDouble();
            		
            		Espaco espaco = new Espaco(id, nome, valorHora, true);
            		admSistema.cadastrarEspaco(espaco);
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
        		try {
					principal.iniciaOperacao();
				} catch (FalhaPersistenciaException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClienteNaoEncontradoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (EspacoIndisponivelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		break;
        		
        	default:
        		System.err.println("Opção Inválida.");
        	}
        }
        sc.close();
	}
}
