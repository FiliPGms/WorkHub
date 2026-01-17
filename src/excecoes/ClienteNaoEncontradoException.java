package excecoes;

public class ClienteNaoEncontradoException extends Exception {
	
	public ClienteNaoEncontradoException() {
        super("Cliente não encontrado.");
    }
    
    public ClienteNaoEncontradoException(String cpf) {
        super("Cliente com CPF " + cpf + " não foi encontrado.");
    }
}
