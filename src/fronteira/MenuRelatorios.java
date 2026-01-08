package fronteira;

import java.util.Scanner;

import controle.AdministradorSistema;
import excecoes.ClienteNaoEncontradoException;

public class MenuRelatorios {

	private AdministradorSistema admSistema;
	
	public void exibirMenuRelatorios() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("--- RELATÓRIOS ---");
        System.out.println("1. Reservas por cliente");
        System.out.println("2. Utilização de Espaços");
        System.out.println("3. Receita por Dia");
        System.out.println("4. Receita por Espaço");
        System.out.println("5. Receita por Cliente");
        System.out.println("6. Faturamento de Serviços Adicionais");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
        
        int opcao = sc.nextInt();
        
        while (opcao >= 0) {
        	switch(opcao) {
    		case 0:
    			System.out.println("Saindo do menu...");
    			break;
    		case 1:
    			System.out.println("Digite o cpf do cliente: ");
    			String cpf = sc.next();
				try {
					admSistema.listarReservasPorCliente(cpf);
				} catch (ClienteNaoEncontradoException e) {
					e.printStackTrace();
				}
				break;
				
    		case 2:
    			admSistema.relatorioUsoEspacos();
    			break;
    			
    		case 3:
    			admSistema.receitaPorDia();
    			break;

    		case 4:
    			admSistema.receitaPorEspaco();
    			break;
    		
    		case 5:
    			admSistema.receitaPorCliente();
    			break;
    		case 6:
    			admSistema.relatorioServicosAdicionais();
    			break;
    		default:
    			System.err.println("Opção Inválida");
        	}
        }
        sc.close();
	}
}
