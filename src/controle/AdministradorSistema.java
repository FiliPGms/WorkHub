package controle;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entidades.Cliente;
import entidades.Espaco;
import entidades.Reserva;
import entidades.ServicoAdicional;
import excecoes.ClienteJaCadastradoException;
import excecoes.ClienteNaoEncontradoException;
import excecoes.EspacoIndisponivelException;
import excecoes.FalhaPersistenciaException;
import excecoes.ReservaNaoEncontradaException;
import excecoes.ServicoInvalidoException;
public class AdministradorSistema {
	
	 private RepositorioClientes repoClientes;
	 private RepositorioEspacos repoEspacos;
	 private RepositorioReservas repoReservas;

	    public AdministradorSistema() throws FalhaPersistenciaException {
	        this.repoClientes = new RepositorioClientes();
	        this.repoEspacos = new RepositorioEspacos();
	        this.repoReservas = new RepositorioReservas();
	    }


	    // CLIENTES


	    public void cadastrarCliente(Cliente c) throws ClienteJaCadastradoException, FalhaPersistenciaException {
	        repoClientes.inserir(c);
	    }

	    public Cliente buscarCliente(String cpf) throws ClienteNaoEncontradoException {
	        Cliente c = repoClientes.buscar(cpf);
	        return c;
	    }

	  
	    // ESPAÇOS

	    
	    public void cadastrarEspaco(Espaco e) throws FalhaPersistenciaException {
	        repoEspacos.inserir(e);
	    }
	    
	    public Espaco buscarEspaco(int idEspaco) throws EspacoIndisponivelException {
	        Espaco e = repoEspacos.buscar(idEspaco);
	        return e;
	    }

	  
	    // RESERVAS
	   

	    public void criarReserva(Reserva novaReserva) throws EspacoIndisponivelException,FalhaPersistenciaException {
	        // verifica conflito com reservas existentes
	        for (Reserva r : repoReservas.listar()) {
	            r.conflitaCom(novaReserva); // lança exceção se conflitar
	        }

	        repoReservas.inserir(novaReserva);
	    }

	    public Reserva buscarReserva(int idReserva)
	            throws ReservaNaoEncontradaException {

	        Reserva r = repoReservas.buscar(idReserva);
	        if (r == null) {
	            throw new ReservaNaoEncontradaException();
	        }
	        return r;
	    }

	    public void cancelarReserva(int idReserva) throws ReservaNaoEncontradaException, FalhaPersistenciaException {
	        repoReservas.remover(idReserva);
	    }

	   
	    // SERVIÇOS ADICIONAIS
	  

	    public void adicionarServico(int idReserva, ServicoAdicional s)
	            throws ReservaNaoEncontradaException,
	                   ServicoInvalidoException,
	                   FalhaPersistenciaException {

	        Reserva r = buscarReserva(idReserva);

	        if (s == null) {
	            throw new ServicoInvalidoException();
	        }

	        r.adicionarServico(s);
	        repoReservas.salvarArquivo();
	    }

	    public void removerServico(int idReserva, ServicoAdicional s)
	            throws ReservaNaoEncontradaException,
	                   FalhaPersistenciaException {

	        Reserva r = buscarReserva(idReserva);
	        r.removerServico(s);
	        repoReservas.salvarArquivo();
	    }
	    
	    /* 
	       RELATÓRIO 1
	       Todas as reservas por cliente (CPF)
	       */

	    public List<Reserva> listarReservasPorCliente(String cpf) throws ClienteNaoEncontradoException {
	        buscarCliente(cpf);
	        List<Reserva> resultado = new ArrayList<>();
	        for (Reserva r : repoReservas.listar()) {
	            if (r.getCliente().getCpf().equals(cpf)) {
	                resultado.add(r);
	            }
	        }

	        return resultado;
	    }
	    
	    /* 
	       RELATÓRIO 2
	       Espaços com nº de reservas e horas usadas
	        */

	    public Map<Espaco, double[]> relatorioUsoEspacos() {

	        Map<Espaco, double[]> relatorio = new HashMap<>();
	        // double[0] = quantidade reservas
	        // double[1] = horas totais

	        for (Reserva r : repoReservas.listar()) {
	            Espaco e = r.getEspaco();

	            relatorio.putIfAbsent(e, new double[]{0, 0});

	            relatorio.get(e)[0]++;

	            double horas =
	                Duration.between(r.getHoraInicio(), r.getHoraFim()).toMinutes() / 60.0;
	            	relatorio.get(e)[1] += horas;
	        }

	        return relatorio;
	    }
	    
	    /*
	       RELATÓRIO 3
	       Receita por dia
	       */

	    public Map<LocalDate, Double> receitaPorDia() {

	        Map<LocalDate, Double> receita = new HashMap<>();

	        for (Reserva r : repoReservas.listar()) {
	            LocalDate data = r.getDataReserva();
	            receita.put(data,receita.getOrDefault(data, 0.0) + r.calcularValorTotal()
	            );
	        }

	        return receita;
	    }
	    
	    /*
	       RELATÓRIO 4
	       Receita por espaço
	      */

	    public Map<Espaco, Double> receitaPorEspaco() {

	        Map<Espaco, Double> receita = new HashMap<>();

	        for (Reserva r : repoReservas.listar()) {
	            Espaco e = r.getEspaco();
	            receita.put(e,receita.getOrDefault(e, 0.0) + r.calcularValorTotal());
	        }

	        return receita;
	    }
	    
	    /* 
	       RELATÓRIO 5
	       Receita por cliente
	        */

	    public Map<Cliente, Double> receitaPorCliente() {

	        Map<Cliente, Double> receita = new HashMap<>();

	        for (Reserva r : repoReservas.listar()) {
	            Cliente c = r.getCliente();
	            receita.put(c, receita.getOrDefault(c, 0.0) + r.calcularValorTotal());
	        }

	        return receita;
	    }
	    
	    /* 
	       RELATÓRIO 6
	       Serviços adicionais (quantidade e valor)
	      */

	    public Map<String, double[]> relatorioServicosAdicionais() {

	        Map<String, double[]> relatorio = new HashMap<>();
	        // double[0] = quantidade
	        // double[1] = valor total

	        for (Reserva r : repoReservas.listar()) {
	            for (ServicoAdicional s : r.getServicosAdicionais()) {

	                String tipo = s.getClass().getSimpleName();

	                relatorio.putIfAbsent(tipo, new double[]{0, 0});

	                relatorio.get(tipo)[0]++;
	                relatorio.get(tipo)[1] += s.getValorTotal();
	            }
	        }

	        return relatorio;
	    }
}
