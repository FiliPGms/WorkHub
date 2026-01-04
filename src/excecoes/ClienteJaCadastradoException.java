package excecoes;

public class ClienteJaCadastradoException extends Exception {

	public ClienteJaCadastradoException () {
		System.err.println("Cliente ja foi cadastrado");
	}
}
