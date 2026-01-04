package excecoes;

public class ClienteNaoEncontradoException extends Exception {
	
	public ClienteNaoEncontradoException() {
		System.err.println("Cliente nao foi encontrado");
	}
}
