package entidades;

public class RecebimentoCorrespondencia implements ServicoAdicional {
	private String descricao;
	public RecebimentoCorrespondencia(String descricao) {
		this.descricao = descricao;
	}
	
	public double getValorTotal() {
		return 0;
	}
	
	public String getDescricao() {
		return "...";
	}
}
