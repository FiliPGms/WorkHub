package fronteira;

import java.time.LocalDate;
import java.util.Scanner;

import controle.AdministradorSistema;
import entidades.Cliente;
import excecoes.ClienteJaCadastradoException;
import excecoes.ClienteNaoEncontradoException;
import excecoes.EspacoIndisponivelException;
import excecoes.FalhaPersistenciaException;

public class MenuClientes {

	public void exibirMenuClientes() throws FalhaPersistenciaException {
		Scanner sc = new Scanner(System.in);
		
		AdministradorSistema admSistema = new AdministradorSistema();
		
		System.out.println("--- INTERFACE DO CLIENTE ---");
        System.out.println("1. Cadastrar cliente");
        System.out.println("2. Buscar cliente");
        System.out.println("3. Voltar");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
        
        int opcao = sc.nextInt();
        

        	switch(opcao) {
        	case 0:
        		System.out.println("Saindo do menu...");
        		break;
        	case 1:
        		try {
            		System.out.println("*DADOS DO CLIENTE*");
            		System.out.print("CPF: ");
            		String cpf = sc.next();
            		
            		System.out.print("Email: ");
            		String email = sc.next();
            		
            		System.out.print("Nome: ");
            		String nome = sc.next();
            		
            		
            		System.out.println("Telefone: ");
            		String telefone = sc.next();
            		
            		LocalDate dataCadastro = LocalDate.now();
            		
            		Cliente c = new Cliente(cpf, email, nome, telefone, dataCadastro);
					admSistema.cadastrarCliente(c);
					
					System.out.println("Cadastro concluído com sucesso");
	        		break;
					
				} catch (ClienteJaCadastradoException e) {
					e.printStackTrace();
				} catch (FalhaPersistenciaException e) {
					e.printStackTrace();
				}
        	
        	case 2:
        		System.out.print("Informe o cpf do cliente: ");
        		String cpfCliente = sc.next();
        		try {
					admSistema.buscarCliente(cpfCliente);
				} catch (ClienteNaoEncontradoException e) {
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
	}

}
