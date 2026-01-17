package fronteira;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import controle.AdministradorSistema;
import entidades.Cliente;
import entidades.Espaco;
import entidades.Reserva;
import excecoes.ClienteNaoEncontradoException;
import excecoes.EspacoIndisponivelException;
import excecoes.ExibidorErros;
import excecoes.FalhaPersistenciaException;
import excecoes.ReservaNaoEncontradaException;

public class MenuReservas {
	
	private AdministradorSistema admSistema;
	private Scanner sc;
	
	public MenuReservas(AdministradorSistema admSistema, Scanner sc) {
		this.admSistema = admSistema;
		this.sc = sc;
	}
	
	public void exibirMenuReservas() {
		int opcao = -1;
		
		do {
			try {
				System.out.println("\n╔════════════════════════════════════╗");
				System.out.println("║      GERENCIAMENTO DE RESERVAS     ║");
				System.out.println("╚════════════════════════════════════╝");
				System.out.println("1. Criar reserva");
				System.out.println("2. Buscar reserva");
				System.out.println("3. Cancelar reserva");
				System.out.println("4. Voltar ao menu principal");
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
						criarReserva();
						break;
						
					case 2:
						buscarReserva();
						break;
						
					case 3:
						cancelarReserva();
						break;
						
					case 4:
						ExibidorErros.exibirNavegacao("Voltando ao menu principal...");
						return;
						
					default:
						ExibidorErros.exibir(new Exception("Opção inválida! Tente novamente."));
				}
				
			} catch (Exception e) {
				ExibidorErros.exibir(e);
				sc.nextLine(); // Limpa o buffer
			}
			
		} while(opcao != 0 && opcao != 4);
	}
	
	private void criarReserva() {
		String continuar = "S";
		
		while(continuar.equalsIgnoreCase("S")) {
			try {
				System.out.println("\n--- CRIAR RESERVA ---");
				
				System.out.print("ID da reserva (número): ");
				int id = sc.nextInt();
				sc.nextLine(); // Limpa o buffer
				
				// Verifica se já existe uma reserva com este ID
				try {
					admSistema.buscarReserva(id);
					ExibidorErros.exibir(new Exception("Já existe uma reserva com este ID!"));
					continue;
				} catch (ReservaNaoEncontradaException e) {
					// ID não existe, pode continuar
				}
				
				System.out.print("CPF do cliente: ");
				String cpf = sc.nextLine().trim();
				
				Cliente cliente = null;
				try {
					cliente = admSistema.buscarCliente(cpf);
					ExibidorErros.exibirInfo("Cliente encontrado: " + cliente.getNome());
				} catch (ClienteNaoEncontradoException e) {
					ExibidorErros.exibir(e);
					ExibidorErros.exibirAviso("Cadastre o cliente antes de criar a reserva.");
					continue;
				}
				
				System.out.print("ID do espaço: ");
				int idEspaco = sc.nextInt();
				sc.nextLine(); // Limpa o buffer
				
				Espaco espaco = null;
				try {
					espaco = admSistema.buscarEspaco(idEspaco);
					ExibidorErros.exibirInfo("Espaço encontrado: " + espaco.getNome());
					
					if(!espaco.getDisponibilidade()) {
						ExibidorErros.exibir(new Exception("Este espaço não está disponível no momento!"));
						continue;
					}
				} catch (EspacoIndisponivelException e) {
					ExibidorErros.exibir(e);
					continue;
				}
				
				System.out.print("Data da reserva (aaaa-mm-dd): ");
				String dataStr = sc.nextLine().trim();
				LocalDate data = null;
				
				try {
					data = LocalDate.parse(dataStr);
					
					if(data.isBefore(LocalDate.now())) {
						ExibidorErros.exibir(new Exception("A data da reserva não pode ser no passado!"));
						continue;
					}
				} catch (DateTimeParseException e) {
					ExibidorErros.exibir(e, "Formato de data inválido! Use: aaaa-mm-dd", true);
					continue;
				}
				
				System.out.print("Hora de início (hh:mm): ");
				String inicioStr = sc.nextLine().trim();
				LocalTime inicio = null;
				
				try {
					inicio = LocalTime.parse(inicioStr);
				} catch (DateTimeParseException e) {
					ExibidorErros.exibir(e, "Formato de hora inválido! Use: hh:mm", true);
					continue;
				}
				
				System.out.print("Hora de fim (hh:mm): ");
				String fimStr = sc.nextLine().trim();
				LocalTime fim = null;
				
				try {
					fim = LocalTime.parse(fimStr);
				} catch (DateTimeParseException e) {
					ExibidorErros.exibir(e, "Formato de hora inválido! Use: hh:mm", true);
					continue;
				}
				
				if(fim.isBefore(inicio) || fim.equals(inicio)) {
					ExibidorErros.exibir(new Exception("A hora de fim deve ser posterior à hora de início!"));
					continue;
				}
				
				Reserva reserva = new Reserva(id, cliente, espaco, data, inicio, fim, espaco.getValorHora());
				
				admSistema.criarReserva(reserva);
				
				double valorTotal = reserva.calcularValorTotal();
				
				ExibidorErros.exibirSucesso("Reserva criada com sucesso!");
				System.out.println("─────────────────────────────");
				System.out.println("ID da Reserva: " + id);
				System.out.println("Cliente: " + cliente.getNome());
				System.out.println("Espaço: " + espaco.getNome());
				System.out.println("Data: " + data);
				System.out.println("Horário: " + inicio + " às " + fim);
				System.out.println("Valor Total: R$ " + String.format("%.2f", valorTotal));
				System.out.println("─────────────────────────────");
				
			} catch (EspacoIndisponivelException e) {
				ExibidorErros.exibir(e);
				ExibidorErros.exibirAviso("Este horário já está reservado para o espaço selecionado.");
				
			} catch (FalhaPersistenciaException e) {
				ExibidorErros.exibir(e, "salvar a reserva");
				
			} catch (Exception e) {
				ExibidorErros.exibir(e, "criar reserva");
				sc.nextLine(); // Limpa o buffer
			}
			
			// Pergunta se quer continuar criando reservas
			System.out.print("\nDeseja criar outra reserva? (S/N): ");
			continuar = sc.nextLine().trim();
		}
		
		ExibidorErros.exibirNavegacao("Retornando ao menu de reservas...");
	}
	
	private void buscarReserva() {
		try {
			System.out.println("\n--- BUSCAR RESERVA ---");
			System.out.print("Informe o ID da reserva: ");
			int id = sc.nextInt();
			sc.nextLine(); // Limpa o buffer
			
			Reserva reserva = admSistema.buscarReserva(id);
			
			ExibidorErros.exibirSucesso("Reserva encontrada!");
			System.out.println("─────────────────────────────");
			System.out.println("ID: " + reserva.getId());
			System.out.println("Cliente: " + reserva.getCliente().getNome());
			System.out.println("CPF: " + reserva.getCliente().getCpf());
			System.out.println("Espaço: " + reserva.getEspaco().getNome());
			System.out.println("Data: " + reserva.getDataReserva());
			System.out.println("Horário: " + reserva.getHoraInicio() + " às " + reserva.getHoraFim());
			System.out.println("Valor Total: R$ " + String.format("%.2f", reserva.calcularValorTotal()));
			
			if(!reserva.getServicosAdicionais().isEmpty()) {
				System.out.println("\nServiços Adicionais:");
				reserva.getServicosAdicionais().forEach(s -> 
					System.out.println("  - " + s.getDescricao() + ": R$ " + String.format("%.2f", s.getValorTotal()))
				);
			}
			System.out.println("─────────────────────────────");
			
		} catch (ReservaNaoEncontradaException e) {
			ExibidorErros.exibir(e);
			ExibidorErros.exibirAviso("Verifique se o ID está correto.");
			
		} catch (Exception e) {
			ExibidorErros.exibir(e, "buscar reserva");
		}
	}
	
	private void cancelarReserva() {
		try {
			System.out.println("\n--- CANCELAR RESERVA ---");
			System.out.print("Informe o ID da reserva a ser cancelada: ");
			int id = sc.nextInt();
			sc.nextLine(); // Limpa o buffer
			
			// Busca a reserva antes de cancelar para confirmar
			Reserva reserva = admSistema.buscarReserva(id);
			
			System.out.println("\nReserva encontrada:");
			System.out.println("Cliente: " + reserva.getCliente().getNome());
			System.out.println("Espaço: " + reserva.getEspaco().getNome());
			System.out.println("Data: " + reserva.getDataReserva());
			
			System.out.print("\nConfirma o cancelamento? (S/N): ");
			String confirmacao = sc.nextLine().trim();
			
			if(confirmacao.equalsIgnoreCase("S")) {
				admSistema.cancelarReserva(id);
				ExibidorErros.exibirSucesso("Reserva cancelada com sucesso!");
			} else {
				ExibidorErros.exibirAviso("Cancelamento abortado.");
			}
			
		} catch (ReservaNaoEncontradaException e) {
			ExibidorErros.exibir(e);
			ExibidorErros.exibirAviso("Verifique se o ID está correto.");
			
		} catch (FalhaPersistenciaException e) {
			ExibidorErros.exibir(e, "cancelar a reserva");
			
		} catch (Exception e) {
			ExibidorErros.exibir(e, "cancelar reserva");
		}
	}
}