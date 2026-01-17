package excecoes;

public class EspacoIndisponivelException extends Exception {
	
	 public EspacoIndisponivelException() {
	        super("Espaço indisponível.");
	    }
	    
	    public EspacoIndisponivelException(int id) {
	        super("Espaço com ID " + id + " não foi encontrado ou está indisponível.");
	    }
	    
	    public EspacoIndisponivelException(String mensagem) {
	        super(mensagem);
	    }
}
