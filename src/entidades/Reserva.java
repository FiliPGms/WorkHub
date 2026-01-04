package entidades;

import java.util.ArrayList;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.Temporal;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import excecoes.EspacoIndisponivelException;
public class Reserva implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<ServicoAdicional> servicos;
	private int idReserva;
	private Cliente c;
	private Espaco es;
	private LocalDate dataReserva;
	private LocalTime horaInicio;
	private LocalTime horaFim;
	private double valorTotal;
	
	public Reserva(int idReserva, Cliente c, Espaco es, LocalDate dataReserva,
			LocalTime horaInicio, LocalTime horaFim, double valorTotal) {
		this.servicos = new ArrayList<>();
		this.idReserva = idReserva;
		this.c = c;
		this.es = es;
		this.dataReserva = dataReserva;
		this.horaInicio = horaInicio;
		this.horaFim = horaFim;
		this.valorTotal = valorTotal;
	}
	
	public List<ServicoAdicional> getServicosAdicionais() {
	    return new ArrayList<>(servicos);
	}
	
	public int getId() {
		return this.idReserva;
	}
	public Cliente getCliente() {
		return this.c;
	}
	public Espaco getEspaco() {
		return this.es;
	}
	public void adicionarServico(ServicoAdicional s) {
		servicos.add(s);
	}
	
	public void removerServico(ServicoAdicional s) {
		Iterator<ServicoAdicional> it = servicos.iterator();
		while(it.hasNext()) {
			ServicoAdicional atual = it.next();
			if(atual.equals(s)) {
				it.remove();
				break;
			}
		}
	}
	
	public double calcularDuracaoHoras() {
		return Duration.between(horaInicio, horaFim).toMinutes()/60.0;
	}
	
	public double calcularValorTotal() {
		double horas = Duration.between(horaInicio, horaFim).toMinutes()/60.0;
		double total = horas * es.getValorHora();
		
		for(ServicoAdicional se: servicos) {
			total += se.getValorTotal();
		}
		
		this.valorTotal = total;
		return total;
	}

	public void conflitaCom(Reserva outraReserva) throws EspacoIndisponivelException {
		boolean dataConflitante = this.dataReserva.equals(outraReserva.dataReserva);
		boolean espacoConflitante = this.es.equals(outraReserva.es);
		boolean horarioConflitante = this.horaInicio.isBefore(outraReserva.horaFim) && this.horaFim.isAfter(outraReserva.horaInicio);
		if(dataConflitante && espacoConflitante && horarioConflitante) {
			throw new EspacoIndisponivelException("Espaço já reservado para o horario especifico");
		}
	}
	public String getResumo() {
		return "Data da reserva: " + dataReserva + "/nValor: " + valorTotal + "/nID: " + idReserva +
				"/nEspaco: " + es.getNome() + "/nNome do cliente: " + c.getNome();
	}

	public LocalDate getDataReserva() {
		return this.dataReserva;
	}

	public LocalTime getHoraInicio() {
		return this.horaInicio;
	}
	
	public LocalTime getHoraFim() {
		return this.horaFim;
	}
}
