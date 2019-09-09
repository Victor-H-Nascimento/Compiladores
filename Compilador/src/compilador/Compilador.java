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
                    
                    //retirando espacoes
                    while(caracter.contains(" "))//!eof
                    {
                        caracter = c.leCaracter(arq);
                    }
                 
                }
                
                if (caracter.contains("!eof")) {
                    pegaToken();
                    colocaTokenLista();
                }

            }

        } while (true);//!eof

        //pegaToken();
        //colocarTokenLista();
    }

    public static void pegaToken() {

    }

    public static void colocaTokenLista() {

    }
}
