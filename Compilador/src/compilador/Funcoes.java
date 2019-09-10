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
            retorno = Character.toString((char) aux);
        } catch (IOException ex) {
            Logger.getLogger(Funcoes.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    @Override
    public String trataDigito(String caracter, Funcoes c, Arquivo arq) {
        String numero = caracter;
        String novoCaracter = c.leCaracter(arq);
        Token token = new Token();

        while (novoCaracter.contains("0") || novoCaracter.contains("1") || novoCaracter.contains("2") || novoCaracter.contains("3") || novoCaracter.contains("4") || novoCaracter.contains("5") || novoCaracter.contains("6") || novoCaracter.contains("7") || novoCaracter.contains("8") || novoCaracter.contains("9")) {
            numero = numero.concat(novoCaracter);
            novoCaracter = c.leCaracter(arq);
        }
        // token.setSimbolo("snumero");
        // token.setLexema(numero);

        return novoCaracter;
    }

    @Override
    public String trataIdentificador(String caracter, Funcoes c, Arquivo arq) {
        String novoCaracter = c.leCaracter(arq);
        return novoCaracter;
    }

    @Override
    public String trataPalavraReservada(String caracter, Funcoes c, Arquivo arq) {
        String novoCaracter = c.leCaracter(arq);
        return novoCaracter;
    }

    @Override
    public String trataAtribuicao(String caracter, Funcoes c, Arquivo arq) {
        String novoCaracter = c.leCaracter(arq);
        return novoCaracter;
    }

    @Override
    public String trataOperadorAritmetico(String caracter, Funcoes c, Arquivo arq) {
        String novoCaracter = c.leCaracter(arq);
        return novoCaracter;
    }

    @Override
    public String trataOperadorRelacional(String caracter, Funcoes c, Arquivo arq) {
        String novoCaracter = c.leCaracter(arq);
        return novoCaracter;
    }

    @Override
    public String trataPontuacao(String caracter, Funcoes c, Arquivo arq) {
        String novoCaracter = c.leCaracter(arq);
        return novoCaracter;
    }

}
