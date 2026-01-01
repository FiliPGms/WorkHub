package excecoes;

public class ClienteNaoEncontradoException extends Exception {
	
	public ClienteNaoEncontradoException(String descricao) {
		super(descricao);
	}
}
