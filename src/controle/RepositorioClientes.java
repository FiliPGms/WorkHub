package controle;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import entidades.Cliente;
import entidades.Reserva;
import excecoes.FalhaPersistenciaException;

public class RepositorioClientes {
	
	private Map<String, Cliente> clientes; //cpf
	private final String F = "clientes.dat";
	
	public RepositorioClientes() throws FalhaPersistenciaException {
		carregar();
	}
	
	@SuppressWarnings("unchecked")
	public void carregar() throws FalhaPersistenciaException{
		File f = new File(F);
		if(!f.exists()) {
			clientes = new HashMap<>();
			return;
		}
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))){
			
				clientes = (Map<String,Cliente>) ois.readObject();
			
		} catch (IOException | ClassNotFoundException e) {
			throw new FalhaPersistenciaException( "Erro ao carregar os clientes.");
		}
	}
}
