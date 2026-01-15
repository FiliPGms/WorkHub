package fronteira;

import java.util.Scanner;

import controle.AdministradorSistema;
import entidades.Auditorio;
import entidades.Espaco;
import entidades.SalaPrivada;
import entidades.SalaReuniao;
import excecoes.ClienteNaoEncontradoException;
import excecoes.EspacoIndisponivelException;
import excecoes.FalhaPersistenciaException;

public class MenuEspacos {
	
	private AdministradorSistema admSistema;

    public MenuEspacos(AdministradorSistema admSistema) {
        this.admSistema = admSistema;
    }

	public void exibirMenuEspacos() throws FalhaPersistenciaException {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("--- ESPAÇOS ---");
        System.out.println("1. Cadastrar espaço");
        System.out.println("2. Buscar espaço");
        System.out.println("3. Voltar");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
        
        int opcao = sc.nextInt();
        
      
        	do {
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
                				
                				try {
                        			admSistema.buscarEspaco(id);
                        		    System.out.println("Espaço já existente.");
                        		    break;
                        		} catch (EspacoIndisponivelException e) {
                        			//vai continuar o loop
                        		}
                				
                				System.out.println("Nome: ");
                        		String nome = sc.next();
                        		
                        		
                        		System.out.println("Disponibilidade: ");
                        		boolean disp = sc.nextBoolean();
                        		
                        		
                				Espaco e = new SalaReuniao(id,nome,disp);
                				admSistema.cadastrarEspaco(e);
                				break;
                				
                			case 2:
                				
                				System.out.println("ID: ");
                				int idPrivada = sc.nextInt();
                				
                				try {
                					admSistema.buscarEspaco(idPrivada);
                					System.out.println("Espaço ja existente");
                        		} catch (EspacoIndisponivelException e1) {
                        			//continua o loop
                        		}
                				
                				System.out.println("Nome: ");
                        		String nomePrivada = sc.next();
                        		
                        		
                        		
                        		System.out.println("Disponibilidade: ");
                        		boolean dispPrivada = sc.nextBoolean();
                        		
                        		
                				Espaco r = new SalaPrivada(idPrivada,nomePrivada,dispPrivada);
                				admSistema.cadastrarEspaco(r);
                				break;
                		
                			case 3:
                				System.out.println("ID: ");
                				int idAuditorio = sc.nextInt();
                				
                				try {
                					admSistema.buscarEspaco(idAuditorio);
                					System.out.println("Espaço ja existente");
                        		} catch (EspacoIndisponivelException e1) {
                        			//continua o loop
                        		}
                				
                				System.out.println("Nome: ");
                        		String nomeAuditorio = sc.next();
                        		
                        		
                        		
                        		System.out.println("Disponibilidade: ");
                        		boolean dispAuditorio = sc.nextBoolean();
                        		
                        		
                				Espaco a = new Auditorio(idAuditorio,nomeAuditorio,dispAuditorio);
                				admSistema.cadastrarEspaco(a);
                				break;
                				
                		}
                		
            		break;
            		
            	case 2:
            		System.out.print("Informe o id do Espaço: ");
            		int idEspaco = sc.nextInt();
            		try {
    					admSistema.buscarEspaco(idEspaco);
    				} catch (EspacoIndisponivelException e) {
    					System.out.println(e.getMessage());
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
        	}while(opcao!=0);
        
       
	}
}
