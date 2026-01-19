package excecoes;


public class ExibidorErros {
    
    
     //Exibe erro simples com a mensagem da exceção
     //Exemplo: "x Erro: Cliente já cadastrado no sistema."
     
    public static void exibir(Exception e) {
        System.err.println("\n Erro: " + e.getMessage());
    }
    
  
     //Exibe erro com contexto adicional sobre a operação
     //Exemplo: "Erro ao cadastrar cliente: Cliente já cadastrado no sistema."
     
     //Exceção capturada
     //contexto Contexto da operação (ex: "cadastrar cliente", "salvar dados")
     
    public static void exibir(Exception e, String contexto) {
        System.err.println("\n Erro ao " + contexto + ": " + e.getMessage());
    }
    
  
     //Exibe erro com mensagem personalizada e opção de mostrar detalhes
     //e: Exceção capturada
     //mensagem: Mensagem principal a ser exibida
     //exibirDetalhe Se true, exibe também getMessage() como detalhe
     
    public static void exibir(Exception e, String mensagem, boolean exibirDetalhe) {
        System.err.println("\n" + mensagem);
        if(exibirDetalhe) {
            System.err.println("Detalhes: " + e.getMessage());
        }
    }
    
    
     //Exibe aviso (não é erro crítico, mas requer atenção)
     //Exemplo: " Atenção: CPF deve conter 11 dígitos."
     //mensagem:Mensagem de aviso
     
    public static void exibirAviso(String mensagem) {
        System.out.println("\n Atenção: " + mensagem);
    }
    
    
     //Exibe mensagem de sucesso
     //Exemplo: "Cliente cadastrado com sucesso!
     //mensagem:Mensagem de sucesso
    
    public static void exibirSucesso(String mensagem) {
        System.out.println("\n " + mensagem);
    }
    
    
     //Exibe mensagem informativa
     //Exemplo: "Cliente: João da Silva"
      
     //mensagem:Mensagem informativa

    public static void exibirInfo(String mensagem) {
        System.out.println("\n " + mensagem);
    }
    
  
     //Exibe mensagem de navegação
     //Exemplo: "Voltando ao menu principal..."
     //mensagem:Mensagem de navegação
    
    public static void exibirNavegacao(String mensagem) {
        System.out.println("\n " + mensagem);
    }
}
