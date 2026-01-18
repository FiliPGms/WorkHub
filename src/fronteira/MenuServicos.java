package fronteira;

import java.util.Scanner;
import controle.AdministradorSistema;
import entidades.CafePremium;
import entidades.Estacionamento;
import entidades.Locker;
import entidades.RecebimentoCorrespondencia;
import entidades.Reserva;
import entidades.ServicoAdicional;
import excecoes.ExibidorErros;
import excecoes.FalhaPersistenciaException;
import excecoes.ReservaNaoEncontradaException;
import excecoes.ServicoInvalidoException;


public class MenuServicos {
	private AdministradorSistema admSistema;
	private Scanner sc;
	
	public MenuServicos(AdministradorSistema admSistema, Scanner sc) {
		this.admSistema = admSistema;
		this.sc = sc;
	}
	
	public void exibirMenuServicos() {
		int opcao = -1;
		
		do {
			try {
				System.out.println("\n╔════════════════════════════════════╗");
				System.out.println("║   GERENCIAMENTO DE SERVIÇOS        ║");
				System.out.println("╚════════════════════════════════════╝");
				System.out.println("1. Adicionar serviço a uma reserva");
				System.out.println("2. Remover serviço de uma reserva");
				System.out.println("3. Ver serviços de uma reserva");
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
						adicionarServico();
						break;
						
					case 2:
						removerServico();
						break;
						
					case 3:
						verServicos();
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
	
	/**
	 * Adiciona serviços adicionais a uma reserva existente
	 */
	private void adicionarServico() {
		try {
			System.out.println("\n--- ADICIONAR SERVIÇO ---");
			System.out.print("Informe o ID da reserva: ");
			int idReserva = sc.nextInt();
			sc.nextLine(); // Limpa o buffer
			
			// Busca a reserva
			Reserva reserva = admSistema.buscarReserva(idReserva);
			
			ExibidorErros.exibirInfo("Reserva encontrada!");
			System.out.println("Cliente: " + reserva.getCliente().getNome());
			System.out.println("Espaço: " + reserva.getEspaco().getNome());
			System.out.println("Data: " + reserva.getDataReserva());
			System.out.println("Duração: " + String.format("%.1f", reserva.calcularDuracaoHoras()) + " horas");
			
			// Loop para adicionar múltiplos serviços
			String continuar = "S";
			
			while(continuar.equalsIgnoreCase("S")) {
				System.out.println("\n--- SERVIÇOS DISPONÍVEIS ---");
				System.out.println("1. Café Premium - R$ 5,00");
				System.out.println("2. Locker (Armário) - R$ 10,00");
				System.out.println("3. Estacionamento - R$ 5,00/hora");
				System.out.println("4. Recebimento de Correspondências - R$ 7,00");
				System.out.print("\nEscolha o serviço: ");
				
				int tipoServico = sc.nextInt();
				sc.nextLine(); // Limpa o buffer
				
				ServicoAdicional servico = null;
				
				switch(tipoServico) {
					case 1:
						servico = new CafePremium();
						break;
						
					case 2:
						servico = new Locker();
						break;
						
					case 3:
						// Estacionamento usa a duração da reserva
						double duracao = reserva.calcularDuracaoHoras();
						servico = new Estacionamento(duracao);
						break;
						
					case 4:
						servico = new RecebimentoCorrespondencia();
						break;
						
					default:
						ExibidorErros.exibir(new Exception("Serviço inválido!"));
						continue;
				}
				
				// Adiciona o serviço
				admSistema.adicionarServico(idReserva, servico);
				
				ExibidorErros.exibirSucesso("Serviço adicionado com sucesso!");
				System.out.println("Serviço: " + servico.getDescricao());
				System.out.println("Valor: R$ " + String.format("%.2f", servico.getValorTotal()));
				
				// Recalcula e exibe o valor total atualizado
				double novoTotal = reserva.calcularValorTotal();
				System.out.println("\n💰 Valor total da reserva atualizado: R$ " + String.format("%.2f", novoTotal));
				
				// Pergunta se quer adicionar mais serviços
				System.out.print("\nDeseja adicionar outro serviço? (S/N): ");
				continuar = sc.nextLine().trim();
			}
			
			ExibidorErros.exibirNavegacao("Retornando ao menu de serviços...");
			
		} catch (ReservaNaoEncontradaException e) {
			ExibidorErros.exibir(e);
			ExibidorErros.exibirAviso("Verifique se o ID da reserva está correto.");
			
		} catch (ServicoInvalidoException e) {
			ExibidorErros.exibir(e);
			
		} catch (FalhaPersistenciaException e) {
			ExibidorErros.exibir(e, "salvar o serviço");
			
		} catch (Exception e) {
			ExibidorErros.exibir(e, "adicionar serviço");
		}
	}
	
	/**
	 * Remove serviços de uma reserva
	 */
	private void removerServico() {
		try {
			System.out.println("\n--- REMOVER SERVIÇO ---");
			System.out.print("Informe o ID da reserva: ");
			int idReserva = sc.nextInt();
			sc.nextLine(); // Limpa o buffer
			
			// Busca a reserva
			Reserva reserva = admSistema.buscarReserva(idReserva);
			
			// Verifica se há serviços
			if(reserva.getServicosAdicionais().isEmpty()) {
				ExibidorErros.exibirAviso("Esta reserva não possui serviços adicionais.");
				return;
			}
			
			// Exibe serviços atuais
			System.out.println("\n--- SERVIÇOS DA RESERVA ---");
			int index = 1;
			for(ServicoAdicional s : reserva.getServicosAdicionais()) {
				System.out.println(index + ". " + s.getDescricao() + " - R$ " + String.format("%.2f", s.getValorTotal()));
				index++;
			}
			
			System.out.print("\nEscolha o número do serviço a remover (0 para cancelar): ");
			int escolha = sc.nextInt();
			sc.nextLine(); // Limpa o buffer
			
			if(escolha == 0) {
				ExibidorErros.exibirAviso("Operação cancelada.");
				return;
			}
			
			if(escolha < 1 || escolha > reserva.getServicosAdicionais().size()) {
				ExibidorErros.exibir(new Exception("Opção inválida!"));
				return;
			}
			
			// Remove o serviço
			ServicoAdicional servicoRemover = reserva.getServicosAdicionais().get(escolha - 1);
			admSistema.removerServico(idReserva, servicoRemover);
			
			ExibidorErros.exibirSucesso("Serviço removido com sucesso!");
			System.out.println("Serviço: " + servicoRemover.getDescricao());
			
			// Recalcula e exibe o valor total atualizado
			double novoTotal = reserva.calcularValorTotal();
			System.out.println("\n💰 Valor total da reserva atualizado: R$ " + String.format("%.2f", novoTotal));
			
		} catch (ReservaNaoEncontradaException e) {
			ExibidorErros.exibir(e);
			ExibidorErros.exibirAviso("Verifique se o ID da reserva está correto.");
			
		} catch (FalhaPersistenciaException e) {
			ExibidorErros.exibir(e, "remover o serviço");
			
		} catch (Exception e) {
			ExibidorErros.exibir(e, "remover serviço");
		}
	}
	
	/**
	 * Exibe todos os serviços de uma reserva
	 */
	private void verServicos() {
		try {
			System.out.println("\n--- VER SERVIÇOS DA RESERVA ---");
			System.out.print("Informe o ID da reserva: ");
			int idReserva = sc.nextInt();
			sc.nextLine(); // Limpa o buffer
			
			// Busca a reserva
			Reserva reserva = admSistema.buscarReserva(idReserva);
			
			System.out.println("\n╔═══════════════════════════════════════╗");
			System.out.println("║      DETALHES DA RESERVA              ║");
			System.out.println("╚═══════════════════════════════════════╝");
			System.out.println("Cliente: " + reserva.getCliente().getNome());
			System.out.println("Espaço: " + reserva.getEspaco().getNome());
			System.out.println("Data: " + reserva.getDataReserva());
			System.out.println("Horário: " + reserva.getHoraInicio() + " às " + reserva.getHoraFim());
			System.out.println("Duração: " + String.format("%.1f", reserva.calcularDuracaoHoras()) + " horas");
			
			// Valor base (apenas espaço)
			double valorBase = reserva.calcularDuracaoHoras() * reserva.getEspaco().getValorHora();
			System.out.println("\n─────────────────────────────────────────");
			System.out.println("Valor do espaço: R$ " + String.format("%.2f", valorBase));
			
			// Serviços adicionais
			if(reserva.getServicosAdicionais().isEmpty()) {
				System.out.println("\n⚠ Nenhum serviço adicional contratado.");
			} else {
				System.out.println("\nServiços Adicionais:");
				double totalServicos = 0;
				
				for(ServicoAdicional s : reserva.getServicosAdicionais()) {
					System.out.println("  • " + s.getDescricao() + " - R$ " + String.format("%.2f", s.getValorTotal()));
					totalServicos += s.getValorTotal();
				}
				
				System.out.println("\nSubtotal serviços: R$ " + String.format("%.2f", totalServicos));
			}
			
			// Valor total
			System.out.println("─────────────────────────────────────────");
			System.out.println(" VALOR TOTAL: R$ " + String.format("%.2f", reserva.calcularValorTotal()));
			System.out.println("─────────────────────────────────────────");
			
		} catch (ReservaNaoEncontradaException e) {
			ExibidorErros.exibir(e);
			ExibidorErros.exibirAviso("Verifique se o ID da reserva está correto.");
			
		} catch (Exception e) {
			ExibidorErros.exibir(e, "exibir serviços");
		}
	}
}

