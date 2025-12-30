package entidades;

public class Locker implements ServicoAdicional {
	
	private int quantidade;
	private String descricao;
	
	public Locker(int quantidade, String descricao) {
		this.quantidade = quantidade;
		this.descricao = descricao;
	}
	
	public double getValorTotal() {
		return 0;
	}
	
	public String getDescricao() {
		return "...";
	}
}
