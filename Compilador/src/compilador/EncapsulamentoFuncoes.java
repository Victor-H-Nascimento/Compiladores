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
    public abstract void trataDigito();
    public abstract void trataIdentificador();
    public abstract void trataPalavraReservada();
    public abstract void trataAtribuicao();
    public abstract void trataOperadorAritmetico();
    public abstract void trataOperadorRelacional();
    public abstract void trataPontuacao();
    //fazer o tratamento de erro
}
