/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author victor
 */
public class Compilador {

    private static Arquivo arq = new Arquivo();
    private static Funcoes c = new Funcoes();
    private static ArrayList<Token> listaToken = new ArrayList();
    private static Token token;
    private static String caracter;

    public Compilador() {

    }

    public static void main(String[] args) throws FileNotFoundException, IOException {

        arq.Open("/home/victor/Área de Trabalho/lexico.txt", c);

        caracter = c.leCaracter(arq);
        System.out.println("Caracter lido1 : " + caracter);
        do {

            while (caracter.contains("{") || caracter.contains(" ") || caracter.contains("\n") || caracter.contains("\t"))//!eof
            {

                if (caracter.contains("{")) {
                    //retirando comentarios
                    while (!caracter.contains("}"))//!eof
                    {
                        caracter = c.leCaracter(arq);
                        //int i = Integer.parseInt(caracter);
                        //System.out.println("valor decimal" + i);
                        System.out.println("comentario : " + caracter);
                    }
                    caracter = c.leCaracter(arq); //lendo logo apos }
                }
                //retirando espacoes
                while (caracter.contains(" "))//!eof
                {
                    caracter = c.leCaracter(arq);
                }

                //retirando quebra de linha
                while (caracter.contains("\n"))//!eof
                {
                    caracter = c.leCaracter(arq);
                }

                //retirando tabulações
                while (caracter.contains("\t"))//!eof
                {
                    caracter = c.leCaracter(arq);
                }
                System.out.println("Caracter lido2 : " + caracter);
            }
            if (caracter.contains("!eof")) {//possiveis erros do lexico ocorrerao aqui, precisamos validar quando simbolos como @ chegam no caracter
                System.out.println("Pega token : " + caracter);
                pegaToken();
                colocaTokenLista();
            }

        } while (true);//!eof

    }

    public static void pegaToken() {

        token = new Token();
        char[] auxCaracter = caracter.toCharArray();

        if (caracter.contains("0") || caracter.contains("1") || caracter.contains("2") || caracter.contains("3") || caracter.contains("4") || caracter.contains("5") || caracter.contains("6") || caracter.contains("7") || caracter.contains("8") || caracter.contains("9")) {//se digito
            caracter = c.trataDigito(caracter, c, arq, token);
        } else {
            if ((Character.getNumericValue(auxCaracter[0]) >= 65 && Character.getNumericValue(auxCaracter[0]) <= 90) || (Character.getNumericValue(auxCaracter[0]) >= 97 && Character.getNumericValue(auxCaracter[0]) <= 122)) {// se letra
                caracter = c.trataIdentificador(caracter, c, arq, token);
            } else {
                if (caracter.contains(":")) {// se :
                    caracter = c.trataAtribuicao(caracter, c, arq, token);
                } else {
                    if (caracter.contains("+") || caracter.contains("-") || caracter.contains("*")) {//se +,-,*
                        caracter = c.trataOperadorAritmetico(caracter, c, arq, token);
                    } else {
                        if (caracter.contains(">") || caracter.contains("<") || caracter.contains("=") || caracter.contains("!")) {// se >,<,=,!
                            caracter = c.trataOperadorRelacional(caracter, c, arq, token);
                        } else {
                            if (caracter.contains(";") || caracter.contains(",") || caracter.contains("(") || caracter.contains(")") || caracter.contains(".")) {
                                caracter = c.trataPontuacao(caracter, c, arq, token);
                            } else {
                                System.out.println("Erro. Nenhum tratamento chamado");
                                System.err.println("Erro Léxico 1");
                            }
                        }
                    }
                }
            }
        }
    }

    public static void colocaTokenLista() {
        listaToken.add(token);
        
    }
}
