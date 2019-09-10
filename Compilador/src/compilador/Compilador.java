/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author victor
 */
public class Compilador {

    private static Arquivo arq = new Arquivo();
    private static Funcoes c = new Funcoes();
    private static String caracter;

    public Compilador() {

    }

    public static void main(String[] args) throws FileNotFoundException, IOException {

        arq.Open("/home/victor/Área de Trabalho/lexico.txt", c);

        caracter = c.leCaracter(arq);

        do {

            while (caracter.contains("{") || caracter.contains(" ") || caracter.contains("\n") || caracter.contains("\t"))//!eof
            {

                if (caracter.contains("{")) {
                    //retirando comentarios
                    while (!caracter.contains("}"))//!eof
                    {
                        caracter = c.leCaracter(arq);
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

            }
            if (caracter.contains("!eof")) {//possiveis erros do lexico ocorrerao aqui, precisamos validar quando simbolos como @ chegam no caracter
                pegaToken();
                colocaTokenLista();
            }

        } while (true);//!eof

    }

    public static void pegaToken() {
        
         char[] auxCaracter = caracter.toCharArray();
         
        if (caracter.contains("0") || caracter.contains("1") || caracter.contains("2") || caracter.contains("3") || caracter.contains("4") || caracter.contains("5") || caracter.contains("6") || caracter.contains("7") || caracter.contains("8") || caracter.contains("9")) {//se digito
            c.trataDigito(caracter,c,arq);
        }
        else{
        
            if ( (Character.getNumericValue(auxCaracter[0]) >= 65 && Character.getNumericValue(auxCaracter[0]) <= 90) || (Character.getNumericValue(auxCaracter[0]) >= 97 && Character.getNumericValue(auxCaracter[0]) <= 122)) {// se letra
                c.trataIdentificador(caracter);
            }
            
            else{
                if (caracter.contains(":")) {// se :
                    c.trataAtribuicao(caracter);
                }
                
                else
                {
                    if (caracter.contains("+") || caracter.contains("-") || caracter.contains("*")) {//se +,-,*
                        c.trataOperadorAritmetico(caracter);
                    }
                    
                    else
                    {
                        if (caracter.contains(">") || caracter.contains("<") || caracter.contains("=") || caracter.contains("!")) {// se >,<,=,!
                            c.trataOperadorRelacional(caracter);
                        }
                        
                        else
                        {
                            if (caracter.contains(";") || caracter.contains(",") || caracter.contains("(") || caracter.contains(")") || caracter.contains(".")) {
                                c.trataPontuacao(caracter);
                            }
                            
                            else{
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

    }
}
