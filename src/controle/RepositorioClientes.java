package controle;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import entidades.Cliente;
import excecoes.ClienteJaCadastradoException;
import excecoes.ClienteNaoEncontradoException;
import excecoes.FalhaPersistenciaException;

public class RepositorioClientes {
	
	private Map<String, Cliente> clientes = new HashMap<>();//cpf
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
	
	public void salvarArquivo() throws FalhaPersistenciaException{
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(F))){
			oos.writeObject(clientes);
		} catch(IOException e) {
			throw new FalhaPersistenciaException("Erro ao salvar clientes.");
		}
	}
	
	public Cliente buscar(String cpf) throws ClienteNaoEncontradoException {
		Cliente c = clientes.get(cpf);
		if(c == null) {
			throw new ClienteNaoEncontradoException();
		}
		return c;
	}
	
	public void inserir(Cliente c) throws ClienteJaCadastradoException, FalhaPersistenciaException {
		if(clientes.containsKey(c.getCpf())) {
			throw new ClienteJaCadastradoException();
		}
		
		clientes.put(c.getCpf(), c);
		salvarArquivo();
	}
	
	public void remover(String cpf) throws ClienteNaoEncontradoException, FalhaPersistenciaException{
		 if (!clientes.containsKey(cpf)) {
		        throw new ClienteNaoEncontradoException();
		    }
		 clientes.remove(cpf);
		 salvarArquivo();
	}
}

