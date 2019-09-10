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
    Token token = new Token();
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

        String novoCaracter = c.leCaracter(arq);

        while (novoCaracter.contains("0") || novoCaracter.contains("1") || novoCaracter.contains("2") || novoCaracter.contains("3") || novoCaracter.contains("4") || novoCaracter.contains("5") || novoCaracter.contains("6") || novoCaracter.contains("7") || novoCaracter.contains("8") || novoCaracter.contains("9")) {
            caracter = caracter.concat(novoCaracter);
            novoCaracter = c.leCaracter(arq);
        }
        token.setSimbolo("sNumero");
        token.setLexema(caracter);

        return novoCaracter;
    }

    @Override
    public String trataIdentificador(String caracter, Funcoes c, Arquivo arq) {
        String novoCaracter = c.leCaracter(arq);
        char[] auxCaracter = novoCaracter.toCharArray();
        String id = caracter;

        while ((Character.getNumericValue(auxCaracter[0]) >= 65 && Character.getNumericValue(auxCaracter[0]) <= 90) || (Character.getNumericValue(auxCaracter[0]) >= 97 && Character.getNumericValue(auxCaracter[0]) <= 122) || (Character.getNumericValue(auxCaracter[0]) >= 48 && Character.getNumericValue(auxCaracter[0]) <= 57) || novoCaracter.contains("_")) {
            id = id.concat(novoCaracter);
            novoCaracter = c.leCaracter(arq);
            auxCaracter = novoCaracter.toCharArray();

        }

        token.setLexema(id);

        switch (id) {

            case "programa":
                token.setSimbolo("sPrograma");
                break;

            case "se":
                token.setSimbolo("sSe");
                break;

            case "entao":
                token.setSimbolo("sEntao");
                break;

            case "senao":
                token.setSimbolo("sSenao");
                break;

            case "enquanto":
                token.setSimbolo("sEnquanto");
                break;

            case "faca":
                token.setSimbolo("sFaca");
                break;

            case "inicio":
                token.setSimbolo("sInicio");
                break;

            case "fim":
                token.setSimbolo("sFim");
                break;

            case "escreva":
                token.setSimbolo("sEscreva");
                break;

            case "leia":
                token.setSimbolo("sLeia");
                break;

            case "var":
                token.setSimbolo("sVar");
                break;

            case "inteiro":
                token.setSimbolo("sInteiro");
                break;

            case "booleano":
                token.setSimbolo("sBooleano");
                break;

            case "verdadeiro":
                token.setSimbolo("sVerdadeiro");
                break;

            case "falso":
                token.setSimbolo("sFalso");
                break;

            case "procedimento":
                token.setSimbolo("sProcedimento");
                break;

            case "funcao":
                token.setSimbolo("sFuncao");
                break;

            case "div":
                token.setSimbolo("sDiv");
                break;

            case "e":
                token.setSimbolo("sE");
                break;

            case "ou":
                token.setSimbolo("sOu");
                break;

            case "nao":
                token.setSimbolo("sNao");
                break;

            default:
                token.setSimbolo("sIdentificador");
                break;

        }

        return novoCaracter;
    }

    @Override
    public String trataAtribuicao(String caracter, Funcoes c, Arquivo arq) {

        String novoCaracter = c.leCaracter(arq);

        if (novoCaracter.contains("=")) {
            caracter = caracter.concat(novoCaracter);
            token.setSimbolo("sAtribuicao");
        } else {
            token.setSimbolo("sDoisPontos");
        }

        token.setLexema(caracter);

        return novoCaracter;
    }

    @Override
    public String trataOperadorAritmetico(String caracter, Funcoes c, Arquivo arq) {
        String novoCaracter = c.leCaracter(arq);

        switch (caracter) {

            case "+":
                token.setSimbolo("sMais");
                token.setLexema(caracter);
                break;

            case "-":
                token.setSimbolo("sMenos");
                token.setLexema(caracter);
                break;

            case "*":
                token.setSimbolo("sMult");
                token.setLexema(caracter);
                break;

            default:
                System.out.println("Erro no Trata Operador Aritmetico");
                System.err.println("Erro Lexico 2");
                break;

        }

        return novoCaracter;
    }

    @Override
    public String trataOperadorRelacional(String caracter, Funcoes c, Arquivo arq) {
        String novoCaracter = c.leCaracter(arq);

        if (novoCaracter.contains("=")) {
            String relacional = caracter.concat(novoCaracter);

            switch (caracter) {

                case ">":
                    token.setSimbolo("sMaiorIgual");
                    token.setLexema(relacional);
                    break;

                case "<":
                    token.setSimbolo("sMenorIgual");
                    token.setLexema(relacional);
                    break;

                case "!":
                    token.setSimbolo("sDif");
                    token.setLexema(relacional);
                    break;

                default:
                    System.out.println("Erro no Trata Operador Relacional com 2 caracteres");
                    System.err.println("Erro Lexico 3");
                    break;

            }

            novoCaracter = c.leCaracter(arq);// le mais um caracter caso o o igual ter sido processado pelo switch acima
        } else {
            switch (caracter) {

                case ">":
                    token.setSimbolo("sMaior");
                    token.setLexema(caracter);
                    break;

                case "<":
                    token.setSimbolo("sMenor");
                    token.setLexema(caracter);
                    break;

                case "=":
                    token.setSimbolo("sIgual");
                    token.setLexema(caracter);
                    break;

                default:
                    System.out.println("Erro no Trata Operador Relacional com 1 caracter");
                    System.err.println("Erro Lexico 4");
                    break;

            }
        }

        return novoCaracter;
    }

    @Override
    public String trataPontuacao(String caracter, Funcoes c, Arquivo arq) {
        String novoCaracter = c.leCaracter(arq);

        switch (caracter) {

            case ";":
                token.setSimbolo("sPontoVirgula");
                token.setLexema(caracter);
                break;

            case ".":
                token.setSimbolo("sPonto");
                token.setLexema(caracter);
                break;

            case ",":
                token.setSimbolo("sVirgula");
                token.setLexema(caracter);
                break;

            case "(":
                token.setSimbolo("sAbreParenteses");
                token.setLexema(caracter);
                break;

            case ")":
                token.setSimbolo("sFechaParenteses");
                token.setLexema(caracter);
                break;

            default:
                System.out.println("Erro no Trata Pontuacao");
                System.err.println("Erro Lexico 5");
                break;

        }

        return novoCaracter;
    }

}
