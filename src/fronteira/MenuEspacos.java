package fronteira;

import java.util.Scanner;

import controle.AdministradorSistema;
import entidades.Espaco;
import entidades.SalaPrivada;
import entidades.SalaReuniao;
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
        
      
        	switch(opcao) {
        	case 0:
        		System.out.println("Saindo do menu...");
        		break;
        	case 1:
            		System.out.println("*ESCOLHA O ESPAÇO*");
            		System.out.printf("\n1- Sala de reuniao\n2-Sala privada\n3-Auditorio");
            		int tipo = sc.nextInt();
;            		switch(tipo) {
            			case 1:
            				System.out.println("ID: ");
            				int id = sc.nextInt();
            				System.out.println("Nome: ");
                    		String nome = sc.next();
                    		try {
                    			if(admSistema.buscarEspaco(id) != null) {
                    				System.out.println("Espaco indisponivel");
                    				break;
                    			}
                    		} catch (EspacoIndisponivelException e) {
                    			// TODO Auto-generated catch block
                    			System.out.println("Espaço já existente");
                    			e.printStackTrace();
                    		}
                    		
                    		System.out.println("Disponibilidade: ");
                    		boolean disp = sc.nextBoolean();
                    		
                    		
            				Espaco e = new SalaReuniao(id,nome,disp);
            				admSistema.cadastrarEspaco(e);
            				
            			case 2:
            				
            				System.out.println("ID: ");
            				int idPrivada = sc.nextInt();
            				System.out.println("Nome: ");
                    		String nomePrivada = sc.next();
                    		
                    		try {
                    			if(admSistema.buscarEspaco(idPrivada) != null) {
                    				break;
                    			}
                    		} catch (EspacoIndisponivelException e1) {
                    			// TODO Auto-generated catch block
                    			System.out.println("Espaço já existente");
                    			e1.printStackTrace();
                    		}
                    		
                    		System.out.println("Disponibilidade: ");
                    		boolean dispPrivada = sc.nextBoolean();
                    		
                    		
            				Espaco r = new SalaPrivada(idPrivada,nomePrivada,dispPrivada);
            				admSistema.cadastrarEspaco(r);
            		
            			case 3:
            				System.out.println("ID: ");
            				int idAuditorio = sc.nextInt();
            				System.out.println("Nome: ");
                    		String nomeAuditorio = sc.next();
                    		
                    		try {
                    			if(admSistema.buscarEspaco(idAuditorio) != null) {
                    				break;
                    			}
                    		} catch (EspacoIndisponivelException e1) {
                    			// TODO Auto-generated catch block
                    			System.out.println("Espaço já existente");
                    			e1.printStackTrace();
                    		}
                    		
                    		System.out.println("Disponibilidade: ");
                    		boolean dispAuditorio = sc.nextBoolean();
                    		
                    		
            				Espaco a = new SalaPrivada(idAuditorio,nomeAuditorio,dispAuditorio);
            				admSistema.cadastrarEspaco(a);
            				
            		}
            		
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
					e.printStackTrace();
				} catch (ClienteNaoEncontradoException e) {
					e.printStackTrace();
				} catch (EspacoIndisponivelException e) {
					e.printStackTrace();
				}
        		break;
        		
        	default:
        		System.err.println("Opção Inválida.");
        	}
        
        sc.close();
	}
}
