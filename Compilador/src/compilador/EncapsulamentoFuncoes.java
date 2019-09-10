/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

/**
 *
 * @author victor
 */
public interface EncapsulamentoFuncoes {
    
    
    public abstract String leCaracter(Arquivo arq);
    public abstract String trataDigito(String caracter, Funcoes c, Arquivo arq);
    public abstract String trataIdentificador(String caracter, Funcoes c, Arquivo arq);
    public abstract String trataAtribuicao(String caracter, Funcoes c, Arquivo arq);
    public abstract String trataOperadorAritmetico(String caracter, Funcoes c, Arquivo arq);
    public abstract String trataOperadorRelacional(String caracter, Funcoes c, Arquivo arq);
    public abstract String trataPontuacao(String caracter, Funcoes c, Arquivo arq);
    //fazer o tratamento de erro
}
