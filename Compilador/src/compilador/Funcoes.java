/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author victor
 */
public class Funcoes implements EncapsulamentoFuncoes {
    
    
    //atributos
     private ArrayList<Token> listaToken = new ArrayList();
     private int aux;
     
    @Override
    public String leCaracter(Arquivo arq) {
        String retorno = "";
         try {
            aux = arq.Read();
            retorno = Character.toString ((char) aux);
         } catch (IOException ex) {
             Logger.getLogger(Funcoes.class.getName()).log(Level.SEVERE, null, ex);
         }
         return retorno;
    }

    @Override
    public void trataDigito(String caracter) {
    }

    @Override
    public void trataIdentificador(String caracter) {
    }

    @Override
    public void trataPalavraReservada(String caracter) {
    }

    @Override
    public void trataAtribuicao(String caracter) {
    }

    @Override
    public void trataOperadorAritmetico(String caracter) {
    }

    @Override
    public void trataOperadorRelacional(String caracter) {
    }

    @Override
    public void trataPontuacao(String caracter) {
        
    }
    
}
