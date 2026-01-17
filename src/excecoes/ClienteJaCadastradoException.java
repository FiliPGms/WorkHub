package excecoes;

public class ClienteJaCadastradoException extends Exception {
	    
	    public ClienteJaCadastradoException() {
	        super("Cliente já cadastrado no sistema.");
	    }
	    
	    public ClienteJaCadastradoException(String cpf) {
	        super("Cliente com CPF " + cpf + " já está cadastrado.");
	    }
	}

