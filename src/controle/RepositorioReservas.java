package controle;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.*;
import entidades.Reserva;
import excecoes.FalhaPersistenciaException;
import excecoes.ReservaNaoEncontradaException;

public class RepositorioReservas {
	
	private Map<Integer, Reserva> reservas = new HashMap<>(); //id
	private final String F = "reservas.dat";
	
	public RepositorioReservas() throws FalhaPersistenciaException {
		carregar(); 
	}
	
	public List<Reserva> listar(){
		return new ArrayList<>(reservas.values());
	}
	
	@SuppressWarnings("unchecked")
	public void carregar() throws FalhaPersistenciaException{
		File f = new File(F);
		if(!(f.exists())) {
			reservas = new HashMap<>();
			return;
		}
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))){
			
			reservas = (Map<Integer,Reserva>) ois.readObject();
			
		} catch (IOException | ClassNotFoundException e) {
			throw new FalhaPersistenciaException( "Erro ao carregar reservas.");
		}
	}
	
	
	public void salvarArquivo() throws FalhaPersistenciaException{
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(F))){
			oos.writeObject(reservas);
		} catch(IOException e) {
			throw new FalhaPersistenciaException("Erro ao salvar reservas.");
		}
	}
	
	public Reserva buscar(int idReserva) throws ReservaNaoEncontradaException{
		Reserva r = reservas.get(idReserva);
		if(r == null) {
			throw new ReservaNaoEncontradaException();
		}
		return r;
	}
	
	public void inserir(Reserva r) throws FalhaPersistenciaException{
		reservas.put(r.getId(), r);
		salvarArquivo();
	}
	
	public void remover(int id) throws FalhaPersistenciaException, ReservaNaoEncontradaException{
		 if (!reservas.containsKey(id)) {
		        throw new ReservaNaoEncontradaException();
		    }

		    reservas.remove(id);
		    salvarArquivo();
	}
	
}
