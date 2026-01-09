package fronteira;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

import controle.AdministradorSistema;
import entidades.Cliente;
import entidades.Espaco;
import entidades.Reserva;
import excecoes.ClienteNaoEncontradoException;
import excecoes.EspacoIndisponivelException;
import excecoes.FalhaPersistenciaException;
import excecoes.ReservaNaoEncontradaException;

public class MenuReservas {
	
	private Cliente clienteEncontrado;
	private Espaco espacoEncontrado;

	public void exibirMenuReservas() throws FalhaPersistenciaException, ClienteNaoEncontradoException, EspacoIndisponivelException {
		Scanner sc = new Scanner(System.in);
		
		AdministradorSistema admSistema = new AdministradorSistema();
		
		System.out.println("--- RESERVA ---");
        System.out.println("1. Criar Reserva");
        System.out.println("2. Buscar reserva");
        System.out.println("3. Cancelar reserva");
        System.out.println("4. Voltar");
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
            		
            		System.out.print("CPF do cliente: ");
            		String cpf = sc.next();
            		
            		clienteEncontrado = admSistema.buscarCliente(cpf);
            		
            		System.out.print("ID do Espaço: ");
            	    int idEspaco = sc.nextInt();
            	    
            	    espacoEncontrado = admSistema.buscarEspaco(idEspaco);
            	    
            	    System.out.print("Data da reserva (aaaa-mm-dd): ");
            	    LocalDate data = LocalDate.parse(sc.next());
            		
            	    System.out.print("Hora de Início (hh:mm): ");
            	    LocalTime inicio = LocalTime.parse(sc.next());
            	    
            	    System.out.print("Hora de Fim (hh:mm): ");
            	    LocalTime fim = LocalTime.parse(sc.next());
            	    
            	    Reserva r = new Reserva(id, clienteEncontrado, espacoEncontrado, data, inicio, fim, idEspaco);
            	    
            	    System.out.print("Valor Total: ");
            	    r.calcularValorTotal();
            	    
            	    
            	    admSistema.criarReserva(r);
            	    
				break;
        		
        	case 2:
        		System.out.print("Informe o id da Reserva: ");
        		int idReserva = sc.nextInt();
        		try {
					admSistema.buscarEspaco(idReserva);
				} catch (EspacoIndisponivelException e) {
					e.printStackTrace();
				}
        		break;
        		
        	case 3:
        		System.out.print("Informe o id da Reserva a ser cancelada: ");
        		int reservaCancelada = sc.nextInt();
        		try {
					admSistema.cancelarReserva(reservaCancelada);
				} catch (ReservaNaoEncontradaException e) {
					e.printStackTrace();
				} catch (FalhaPersistenciaException e) {
					e.printStackTrace();
				}
        	
        	case 4: 
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
