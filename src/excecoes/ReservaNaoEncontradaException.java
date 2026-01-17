package excecoes;

public class ReservaNaoEncontradaException extends Exception {
	 
	 public ReservaNaoEncontradaException() {
	        super("Reserva não encontrada.");
	    }
	    
	    public ReservaNaoEncontradaException(int id) {
	        super("Reserva com ID " + id + " não foi encontrada.");
	    }
}
