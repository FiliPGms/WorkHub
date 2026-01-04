package controle;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import entidades.Cliente;
import entidades.Espaco;
import excecoes.ClienteNaoEncontradoException;
import excecoes.EspacoIndisponivelException;
import excecoes.FalhaPersistenciaException;

public class RepositorioEspacos {
	private Map<Integer, Espaco> espacos = new HashMap<>(); //id
	private final String F = "espacos.dat";
	
	public RepositorioEspacos() throws FalhaPersistenciaException {
		carregar();
	}
	
	@SuppressWarnings("unchecked")
	public void carregar() throws FalhaPersistenciaException{
		File f = new File(F);
		if(!f.exists()) {
			espacos = new HashMap<>();
			return;
		}
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))){
			
				espacos = (Map<Integer,Espaco>) ois.readObject();
			
		} catch (IOException | ClassNotFoundException e) {
			throw new FalhaPersistenciaException( "Erro ao carregar os espacos.");
		}
	}
	
	public void salvarArquivo() throws FalhaPersistenciaException{
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(F))){
			oos.writeObject(espacos);
		} catch(IOException e) {
			throw new FalhaPersistenciaException("Erro ao salvar espacos.");
		}
	}
	
	public Espaco buscar(int id) throws EspacoIndisponivelException {
		Espaco e = espacos.get(id);
		if(e==null) {
			throw new EspacoIndisponivelException("Espaco nao encontrado");
		}
		return e;
	}
	
	public void inserir(Espaco e) throws FalhaPersistenciaException{
		espacos.put(e.getId(), e);
		salvarArquivo();
	}
	
	public void remover(int id) throws FalhaPersistenciaException, EspacoIndisponivelException{
		if (!espacos.containsKey(id)) {
	        throw new EspacoIndisponivelException("Espaco nao encontrado");
	    }
	 espacos.remove(id);
	 salvarArquivo();
	}
}
