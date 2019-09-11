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
    
    
    public abstract String leCaracter();
    public abstract String trataDigito(String caracter, Funcoes c, Arquivo arq,Token token);
    public abstract String trataIdentificador(String caracter, Funcoes c, Arquivo arq,Token token);
    public abstract String trataAtribuicao(String caracter, Funcoes c, Arquivo arq,Token token);
    public abstract String trataOperadorAritmetico(String caracter, Funcoes c, Arquivo arq,Token token);
    public abstract String trataOperadorRelacional(String caracter, Funcoes c, Arquivo arq,Token token);
    public abstract String trataPontuacao(String caracter, Funcoes c, Arquivo arq,Token token);
    //fazer o tratamento de erro
}
