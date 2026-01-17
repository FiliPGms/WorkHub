package excecoes;

/**
 * Classe utilitária responsável por padronizar a exibição de mensagens no sistema
 * Centraliza a formatação de erros, avisos e mensagens de sucesso
 */
public class ExibidorErros {
    
    /**
     * Exibe erro simples com a mensagem da exceção
     * Exemplo: "✗ Erro: Cliente já cadastrado no sistema."
     * 
     * @param e Exceção capturada
     */
    public static void exibir(Exception e) {
        System.err.println("\n✗ Erro: " + e.getMessage());
    }
    
    /**
     * Exibe erro com contexto adicional sobre a operação
     * Exemplo: "✗ Erro ao cadastrar cliente: Cliente já cadastrado no sistema."
     * 
     * @param e Exceção capturada
     * @param contexto Contexto da operação (ex: "cadastrar cliente", "salvar dados")
     */
    public static void exibir(Exception e, String contexto) {
        System.err.println("\n✗ Erro ao " + contexto + ": " + e.getMessage());
    }
    
    /**
     * Exibe erro com mensagem personalizada e opção de mostrar detalhes
     * 
     * @param e Exceção capturada
     * @param mensagem Mensagem principal a ser exibida
     * @param exibirDetalhe Se true, exibe também getMessage() como detalhe
     */
    public static void exibir(Exception e, String mensagem, boolean exibirDetalhe) {
        System.err.println("\n✗ " + mensagem);
        if(exibirDetalhe) {
            System.err.println("Detalhes: " + e.getMessage());
        }
    }
    
    /**
     * Exibe aviso (não é erro crítico, mas requer atenção)
     * Exemplo: "⚠ Atenção: CPF deve conter 11 dígitos."
     * 
     * @param mensagem Mensagem de aviso
     */
    public static void exibirAviso(String mensagem) {
        System.out.println("\n⚠ Atenção: " + mensagem);
    }
    
    /**
     * Exibe mensagem de sucesso
     * Exemplo: "✓ Cliente cadastrado com sucesso!"
     * 
     * @param mensagem Mensagem de sucesso
     */
    public static void exibirSucesso(String mensagem) {
        System.out.println("\n✓ " + mensagem);
    }
    
    /**
     * Exibe mensagem informativa
     * Exemplo: "ℹ Cliente: João da Silva"
     * 
     * @param mensagem Mensagem informativa
     */
    public static void exibirInfo(String mensagem) {
        System.out.println("\nℹ " + mensagem);
    }
    
    /**
     * Exibe mensagem de navegação
     * Exemplo: "↩ Voltando ao menu principal..."
     * 
     * @param mensagem Mensagem de navegação
     */
    public static void exibirNavegacao(String mensagem) {
        System.out.println("\n↩ " + mensagem);
    }
}
