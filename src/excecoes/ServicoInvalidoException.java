package excecoes;

public class ServicoInvalidoException extends Exception{
	
	public ServicoInvalidoException() {
        super("Serviço adicional inválido.");
    }
    
    public ServicoInvalidoException(String mensagem) {
        super(mensagem);
    }
}
