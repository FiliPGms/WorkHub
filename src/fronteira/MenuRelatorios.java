package fronteira;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import controle.AdministradorSistema;
import entidades.Cliente;
import entidades.Espaco;
import entidades.Reserva;
import excecoes.ClienteNaoEncontradoException;
import excecoes.ExibidorErros;

public class MenuRelatorios {
	
	private AdministradorSistema admSistema;
	private Scanner sc;
	
	public MenuRelatorios(AdministradorSistema admSistema, Scanner sc) {
		this.admSistema = admSistema;
		this.sc = sc;
	}
	
	public void exibirMenuRelatorios() {
		int opcao = -1;
		
		do {
			try {
				System.out.println("\n╔════════════════════════════════════╗");
				System.out.println("║          RELATÓRIOS WORKHUB        ║");
				System.out.println("╚════════════════════════════════════╝");
				System.out.println("1. Reservas por cliente");
				System.out.println("2. Utilização de espaços");
				System.out.println("3. Receita por dia");
				System.out.println("4. Receita por espaço");
				System.out.println("5. Receita por cliente");
				System.out.println("6. Faturamento de serviços adicionais");
				System.out.println("7. Voltar ao menu principal");
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
						relatorioReservasPorCliente();
						break;
						
					case 2:
						relatorioUtilizacaoEspacos();
						break;
						
					case 3:
						relatorioReceitaPorDia();
						break;
						
					case 4:
						relatorioReceitaPorEspaco();
						break;
						
					case 5:
						relatorioReceitaPorCliente();
						break;
						
					case 6:
						relatorioServicosAdicionais();
						break;
						
					case 7:
						ExibidorErros.exibirNavegacao("Voltando ao menu principal...");
						return;
						
					default:
						ExibidorErros.exibir(new Exception("Opção inválida! Tente novamente."));
				}
				
			} catch (Exception e) {
				ExibidorErros.exibir(e);
				sc.nextLine(); // Limpa o buffer
			}
			
		} while(opcao != 0 && opcao != 7);
	}
	
	private void relatorioReservasPorCliente() {
		try {
			System.out.println("\n╔═══════════════════════════════════════╗");
			System.out.println("║     RELATÓRIO: RESERVAS POR CLIENTE   ║");
			System.out.println("╚═══════════════════════════════════════╝");
			
			System.out.print("Digite o CPF do cliente: ");
			String cpf = sc.nextLine().trim();
			
			List<Reserva> reservas = admSistema.listarReservasPorCliente(cpf);
			
			if(reservas.isEmpty()) {
				ExibidorErros.exibirAviso("Nenhuma reserva encontrada para este cliente.");
				return;
			}
			
			Cliente cliente = admSistema.buscarCliente(cpf);
			System.out.println("\nCliente: " + cliente.getNome() + " (CPF: " + cpf + ")");
			System.out.println("Total de reservas: " + reservas.size());
			System.out.println("\n─────────────────────────────────────────");
			
			double totalGasto = 0;
			for(Reserva r : reservas) {
				System.out.println("\n• Reserva ID: " + r.getId());
				System.out.println("  Espaço: " + r.getEspaco().getNome());
				System.out.println("  Data: " + r.getDataReserva());
				System.out.println("  Horário: " + r.getHoraInicio() + " às " + r.getHoraFim());
				System.out.println("  Valor: R$ " + String.format("%.2f", r.calcularValorTotal()));
				totalGasto += r.calcularValorTotal();
			}
			
			System.out.println("\n─────────────────────────────────────────");
			System.out.println("TOTAL GASTO: R$ " + String.format("%.2f", totalGasto));
			System.out.println("─────────────────────────────────────────");
			
		} catch (ClienteNaoEncontradoException e) {
			ExibidorErros.exibir(e);
		} catch (Exception e) {
			ExibidorErros.exibir(e, "gerar relatório");
		}
	}
	
	private void relatorioUtilizacaoEspacos() {
		System.out.println("\n╔═══════════════════════════════════════╗");
		System.out.println("║    RELATÓRIO: UTILIZAÇÃO DE ESPAÇOS   ║");
		System.out.println("╚═══════════════════════════════════════╝");
		
		Map<Espaco, double[]> relatorio = admSistema.relatorioUsoEspacos();
		
		if(relatorio.isEmpty()) {
			ExibidorErros.exibirAviso("Nenhuma reserva registrada no sistema.");
			return;
		}
		
		System.out.println("\n─────────────────────────────────────────");
		System.out.printf("%-20s %-15s %-15s%n", "ESPAÇO", "Nº RESERVAS", "HORAS TOTAIS");
		System.out.println("─────────────────────────────────────────");
		
		for(Map.Entry<Espaco, double[]> entry : relatorio.entrySet()) {
			Espaco espaco = entry.getKey();
			double[] dados = entry.getValue();
			
			System.out.printf("%-20s %-15.0f %-15.2f%n", 
				espaco.getNome(), 
				dados[0], // quantidade de reservas
				dados[1]  // horas totais
			);
		}
		
		System.out.println("─────────────────────────────────────────");
	}
	
	private void relatorioReceitaPorDia() {
		System.out.println("\n╔═══════════════════════════════════════╗");
		System.out.println("║      RELATÓRIO: RECEITA POR DIA       ║");
		System.out.println("╚═══════════════════════════════════════╝");
		
		Map<LocalDate, Double> receita = admSistema.receitaPorDia();
		
		if(receita.isEmpty()) {
			ExibidorErros.exibirAviso("Nenhuma receita registrada no sistema.");
			return;
		}
		
		System.out.println("\n─────────────────────────────────────────");
		System.out.printf("%-20s %-20s%n", "DATA", "RECEITA");
		System.out.println("─────────────────────────────────────────");
		
		double totalGeral = 0;
		for(Map.Entry<LocalDate, Double> entry : receita.entrySet()) {
			System.out.printf("%-20s R$ %-18.2f%n", 
				entry.getKey(), 
				entry.getValue()
			);
			totalGeral += entry.getValue();
		}
		
		System.out.println("─────────────────────────────────────────");
		System.out.printf("TOTAL GERAL: R$ %.2f%n", totalGeral);
		System.out.println("─────────────────────────────────────────");
	}
	
	private void relatorioReceitaPorEspaco() {
		System.out.println("\n╔═══════════════════════════════════════╗");
		System.out.println("║     RELATÓRIO: RECEITA POR ESPAÇO     ║");
		System.out.println("╚═══════════════════════════════════════╝");
		
		Map<Espaco, Double> receita = admSistema.receitaPorEspaco();
		
		if(receita.isEmpty()) {
			ExibidorErros.exibirAviso("Nenhuma receita registrada no sistema.");
			return;
		}
		
		System.out.println("\n─────────────────────────────────────────");
		System.out.printf("%-20s %-20s%n", "ESPAÇO", "RECEITA");
		System.out.println("─────────────────────────────────────────");
		
		double totalGeral = 0;
		for(Map.Entry<Espaco, Double> entry : receita.entrySet()) {
			System.out.printf("%-20s R$ %-18.2f%n", 
				entry.getKey().getNome(), 
				entry.getValue()
			);
			totalGeral += entry.getValue();
		}
		
		System.out.println("─────────────────────────────────────────");
		System.out.printf("TOTAL GERAL: R$ %.2f%n", totalGeral);
		System.out.println("─────────────────────────────────────────");
	}
	
	private void relatorioReceitaPorCliente() {
		System.out.println("\n╔═══════════════════════════════════════╗");
		System.out.println("║    RELATÓRIO: RECEITA POR CLIENTE     ║");
		System.out.println("╚═══════════════════════════════════════╝");
		
		Map<Cliente, Double> receita = admSistema.receitaPorCliente();
		
		if(receita.isEmpty()) {
			ExibidorErros.exibirAviso("Nenhuma receita registrada no sistema.");
			return;
		}
		
		System.out.println("\n─────────────────────────────────────────");
		System.out.printf("%-20s %-15s %-15s%n", "CLIENTE", "CPF", "RECEITA");
		System.out.println("─────────────────────────────────────────");
		
		double totalGeral = 0;
		for(Map.Entry<Cliente, Double> entry : receita.entrySet()) {
			Cliente cliente = entry.getKey();
			System.out.printf("%-20s %-15s R$ %-13.2f%n", 
				cliente.getNome(), 
				cliente.getCpf(),
				entry.getValue()
			);
			totalGeral += entry.getValue();
		}
		
		System.out.println("─────────────────────────────────────────");
		System.out.printf("TOTAL GERAL: R$ %.2f%n", totalGeral);
		System.out.println("─────────────────────────────────────────");
	}
	
	private void relatorioServicosAdicionais() {
		System.out.println("\n╔═══════════════════════════════════════╗");
		System.out.println("║   RELATÓRIO: SERVIÇOS ADICIONAIS      ║");
		System.out.println("╚═══════════════════════════════════════╝");
		
		Map<String, double[]> relatorio = admSistema.relatorioServicosAdicionais();
		
		if(relatorio.isEmpty()) {
			ExibidorErros.exibirAviso("Nenhum serviço adicional registrado no sistema.");
			return;
		}
		
		System.out.println("\n─────────────────────────────────────────");
		System.out.printf("%-25s %-15s %-15s%n", "SERVIÇO", "QUANTIDADE", "VALOR TOTAL");
		System.out.println("─────────────────────────────────────────");
		
		double totalGeral = 0;
		for(Map.Entry<String, double[]> entry : relatorio.entrySet()) {
			double[] dados = entry.getValue();
			System.out.printf("%-25s %-15.0f R$ %-13.2f%n", 
				entry.getKey(), 
				dados[0], // quantidade
				dados[1]  // valor total
			);
			totalGeral += dados[1];
		}
		
		System.out.println("─────────────────────────────────────────");
		System.out.printf("TOTAL GERAL: R$ %.2f%n", totalGeral);
		System.out.println("─────────────────────────────────────────");
	}
}