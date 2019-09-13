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

        arq.Ler("/home/victor/Área de Trabalho/lexico.txt", c);

        caracter = c.leCaracter();
        do {

            while ((caracter.contains("{") || caracter.contains(" ") || caracter.contains("\n") || caracter.contains("\t")) && !c.estaVazia())//!eof
            {

                if (caracter.contains("{")) {
                    //retirando comentarios
                    while (!caracter.contains("}") && !c.estaVazia())//!eof
                    {
                        caracter = c.leCaracter();
                    }

                    if (!c.estaVazia()) {
                        caracter = c.leCaracter(); //lendo logo apos }
                    }
                }
                //retirando espacos
                while (caracter.contains(" ") && !c.estaVazia())//!eof
                {
                    caracter = c.leCaracter();
                }

                //retirando quebra de linha
                while (caracter.contains("\n") && !c.estaVazia())//!eof
                {
                    caracter = c.leCaracter();
                }

                //retirando tabulações
                while (caracter.contains("\t") && !c.estaVazia())//!eof
                {
                    caracter = c.leCaracter();
                }
            }
            if (!c.estaVazia()) {
                pegaToken();
                colocaTokenLista();

            }

        } while (!c.estaVazia());//!eof

        mostraTokens();
    }

    public static void pegaToken() {

        token = new Token();
        char[] auxCaracter = caracter.toCharArray();

        if (caracter.contains("0") || caracter.contains("1") || caracter.contains("2") || caracter.contains("3") || caracter.contains("4") || caracter.contains("5") || caracter.contains("6") || caracter.contains("7") || caracter.contains("8") || caracter.contains("9")) {//se digito
            caracter = c.trataDigito(caracter, c, arq, token);
        } else {
            if (((int) auxCaracter[0] >= 65 && (int) auxCaracter[0] <= 90) || ((int) auxCaracter[0] >= 97 && (int) auxCaracter[0] <= 122)) {// se letra
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

    public static void mostraTokens() {
         System.out.println("**********************************");
        for (Token item : listaToken) {
            System.out.println("Lexema: " + item.getLexema());
            System.out.println("Simbolo: " + item.getSimbolo());
            System.out.println("Linha: " + item.getLinhaCodigo());
            System.out.println("");
        }
         System.out.println("**********************************");
    }

}
