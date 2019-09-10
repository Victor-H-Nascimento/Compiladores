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
    public abstract void trataDigito(String caracter);
    public abstract void trataIdentificador(String caracter);
    public abstract void trataPalavraReservada(String caracter);
    public abstract void trataAtribuicao(String caracter);
    public abstract void trataOperadorAritmetico(String caracter);
    public abstract void trataOperadorRelacional(String caracter);
    public abstract void trataPontuacao(String caracter);
    //fazer o tratamento de erro
}
