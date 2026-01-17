package excecoes;

public class FalhaPersistenciaException extends Exception {

	 public FalhaPersistenciaException() {
	        super("Erro ao salvar ou carregar dados.");
	    }
	    
	    public FalhaPersistenciaException(String mensagem) {
	        super(mensagem);
	    }
	    
	    public FalhaPersistenciaException(String mensagem, Throwable causa) {
	        super(mensagem, causa);
	    }
}
