/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author victor
 */
public class Funcoes implements EncapsulamentoFuncoes {

    //atributos
    
    SimbolosToken simbolos = new SimbolosToken();
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
    public String trataDigito(String caracter, Funcoes c, Arquivo arq, Token token) {

        String novoCaracter = c.leCaracter(arq);

        while (novoCaracter.contains("0") || novoCaracter.contains("1") || novoCaracter.contains("2") || novoCaracter.contains("3") || novoCaracter.contains("4") || novoCaracter.contains("5") || novoCaracter.contains("6") || novoCaracter.contains("7") || novoCaracter.contains("8") || novoCaracter.contains("9")) {
            caracter = caracter.concat(novoCaracter);
            novoCaracter = c.leCaracter(arq);
        }
        token.setSimbolo(simbolos.getNumero());
        token.setLexema(caracter);

        return novoCaracter;
    }

    @Override
    public String trataIdentificador(String caracter, Funcoes c, Arquivo arq, Token token) {
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
                token.setSimbolo(simbolos.getPrograma());
                break;

            case "se":
                token.setSimbolo(simbolos.getSe());
                break;

            case "entao":
                token.setSimbolo(simbolos.getEntao());
                break;

            case "senao":
                token.setSimbolo(simbolos.getSenao());
                break;

            case "enquanto":
                token.setSimbolo(simbolos.getEnquanto());
                break;

            case "faca":
                token.setSimbolo(simbolos.getFaca());
                break;

            case "inicio":
                token.setSimbolo(simbolos.getInicio());
                break;

            case "fim":
                token.setSimbolo(simbolos.getFim());
                break;

            case "escreva":
                token.setSimbolo(simbolos.getEscreva());
                break;

            case "leia":
                token.setSimbolo(simbolos.getLeia());
                break;

            case "var":
                token.setSimbolo(simbolos.getVar());
                break;

            case "inteiro":
                token.setSimbolo(simbolos.getInteiro());
                break;

            case "booleano":
                token.setSimbolo(simbolos.getBooleano());
                break;

            case "verdadeiro":
                token.setSimbolo(simbolos.getVerdadeiro());
                break;

            case "falso":
                token.setSimbolo(simbolos.getFalso());
                break;

            case "procedimento":
                token.setSimbolo(simbolos.getProcedimento());
                break;

            case "funcao":
                token.setSimbolo(simbolos.getFuncao());
                break;

            case "div":
                token.setSimbolo(simbolos.getDivisao());
                break;

            case "e":
                token.setSimbolo(simbolos.getE());
                break;

            case "ou":
                token.setSimbolo(simbolos.getOu());
                break;

            case "nao":
                token.setSimbolo(simbolos.getNao());
                break;

            default:
                token.setSimbolo(simbolos.getIdentificador());
                break;

        }

        return novoCaracter;
    }

    @Override
    public String trataAtribuicao(String caracter, Funcoes c, Arquivo arq, Token token) {

        String novoCaracter = c.leCaracter(arq);

        if (novoCaracter.contains("=")) {
            caracter = caracter.concat(novoCaracter);
            token.setSimbolo(simbolos.getAtribuicao());
        } else {
            token.setSimbolo(simbolos.getDoisPontos());
        }

        token.setLexema(caracter);

        return novoCaracter;
    }

    @Override
    public String trataOperadorAritmetico(String caracter, Funcoes c, Arquivo arq, Token token) {
        String novoCaracter = c.leCaracter(arq);

        switch (caracter) {

            case "+":
                token.setSimbolo(simbolos.getMais());
                token.setLexema(caracter);
                break;

            case "-":
                token.setSimbolo(simbolos.getMenos());
                token.setLexema(caracter);
                break;

            case "*":
                token.setSimbolo(simbolos.getMultiplicacao());
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
    public String trataOperadorRelacional(String caracter, Funcoes c, Arquivo arq, Token token) {
        String novoCaracter = c.leCaracter(arq);

        if (novoCaracter.contains("=")) {
            String relacional = caracter.concat(novoCaracter);

            switch (caracter) {

                case ">":
                    token.setSimbolo(simbolos.getMaiorIgual());
                    token.setLexema(relacional);
                    break;

                case "<":
                    token.setSimbolo(simbolos.getMenorIgual());
                    token.setLexema(relacional);
                    break;

                case "!":
                    token.setSimbolo(simbolos.getDiferente());
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
                    token.setSimbolo(simbolos.getMaior());
                    token.setLexema(caracter);
                    break;

                case "<":
                    token.setSimbolo(simbolos.getMenor());
                    token.setLexema(caracter);
                    break;

                case "=":
                    token.setSimbolo(simbolos.getIgual());
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
    public String trataPontuacao(String caracter, Funcoes c, Arquivo arq, Token token) {
        String novoCaracter = c.leCaracter(arq);

        switch (caracter) {

            case ";":
                token.setSimbolo(simbolos.getPontoVirgula());
                token.setLexema(caracter);
                break;

            case ".":
                token.setSimbolo(simbolos.getPonto());
                token.setLexema(caracter);
                break;

            case ",":
                token.setSimbolo(simbolos.getVirgula());
                token.setLexema(caracter);
                break;

            case "(":
                token.setSimbolo(simbolos.getAbreParenteses());
                token.setLexema(caracter);
                break;

            case ")":
                token.setSimbolo(simbolos.getFechaParenteses());
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
