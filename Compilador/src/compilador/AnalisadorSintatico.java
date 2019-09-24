/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import java.io.IOException;

/**
 *
 * @author victor
 */
public class AnalisadorSintatico {

    private final AnalisadorLexico analisadorLexico;
    private Token token;

    AnalisadorSintatico(AnalisadorLexico analisadorLexico) throws IOException {
        this.analisadorLexico = analisadorLexico;
        analisaPrograma();
    }

    private void analisaPrograma() throws IOException {
        token = analisadorLexico.lexico();
        if (token.getSimbolo().equalsIgnoreCase("sPrograma")) {
            token = analisadorLexico.lexico();

            if (token.getSimbolo().equalsIgnoreCase("sIdentificador")) {
                token = analisadorLexico.lexico();
                //  insere_tabela(token.lexema,”nomedeprograma”,””,””) semantico

                if (token.getSimbolo().equalsIgnoreCase("sPontoVirgula")) {
                    analisaBloco();
                    if (token.getSimbolo().equalsIgnoreCase("sPonto")) {
                        //se acabou arquivo ou é comentário   então sucesso
                        //senao ERRO
                    } else {
                        System.out.println("Linha " + token.getLinhaCodigo() + " - Erro Sintatico: ´.´ esperado");
                    }
                } else {
                    System.out.println("Linha " + token.getLinhaCodigo() + " - Erro Sintatico: ´;´ esperado");
                }

            } else {
                System.out.println("Linha " + token.getLinhaCodigo() + " - Erro Sintatico: identificador esperado");
            }
        } else {
            System.out.println("Linha " + token.getLinhaCodigo() + " - Erro Sintatico: palavra-chave 'programa' esperada");
        }

    }

    private void analisaBloco() throws IOException {
        token = analisadorLexico.lexico();
        analisaEtapaVariaveis();
        analisaSubRotinas();
        analisaComandos();
    }

    private void analisaEtapaVariaveis() throws IOException {

        if (token.getSimbolo().equalsIgnoreCase("sVar")) {
            token = analisadorLexico.lexico();
            if (token.getSimbolo().equalsIgnoreCase("sIdentificador")) {
                while (token.getSimbolo().equalsIgnoreCase("sIdentificador")) {

                    analisaVariaveis();

                    if (token.getSimbolo().equalsIgnoreCase("sPontoVirgula")) {
                        token = analisadorLexico.lexico();
                    } else {
                        System.out.println("Linha " + token.getLinhaCodigo() + " - Erro Sintatico: ´;´ esperado");
                    }
                }
            } else {
                System.out.println("Linha " + token.getLinhaCodigo() + " - Erro Sintatico: identificador esperado");
            }
        }
    }

    private void analisaVariaveis() throws IOException {

        do {
            if (token.getSimbolo().equalsIgnoreCase("sIdentificador")) {
                //codigo semantico aqui

                token = analisadorLexico.lexico();
                if (token.getSimbolo().equalsIgnoreCase("sVirgula") || token.getSimbolo().equalsIgnoreCase("sDoisPontos")) {

                    if (token.getSimbolo().equalsIgnoreCase("sVirgula")) {
                        token = analisadorLexico.lexico();

                        if (token.getSimbolo().equalsIgnoreCase("sDoisPontos")) {
                            System.out.println("Linha " + token.getLinhaCodigo() + " - Erro Sintatico: identificador esperado");
                        }

                    }

                } else {
                    System.out.println("Linha " + token.getLinhaCodigo() + " - Erro Sintatico: ´,´ ou ´:´ esperado");
                }

            } else {
                System.out.println("Linha " + token.getLinhaCodigo() + " - Erro Sintatico: identificador esperado");
            }
        } while (token.getSimbolo().equalsIgnoreCase("sDoisPontos"));

        token = analisadorLexico.lexico();
        analisaTipo();

    }

    private void analisaTipo() throws IOException {
        if (!token.getSimbolo().equalsIgnoreCase("sInteiro") && !token.getSimbolo().equalsIgnoreCase("sBooleano")) {
            System.out.println("Linha " + token.getLinhaCodigo() + " - Erro Sintatico: tipo 'inteiro' ou 'booleano' esperado");
        } else {
            // senão coloca_tipo_tabela(token.lexema) semantico
            token = analisadorLexico.lexico();
        }

    }

    private void analisaComandos() throws IOException {
        if (token.getSimbolo().equalsIgnoreCase("sInicio")) {
            token = analisadorLexico.lexico();
            analisaComandoSimples();

            while (!token.getSimbolo().equalsIgnoreCase("sFim")) {
                if (token.getSimbolo().equalsIgnoreCase("sPontoVirgula")) {
                    token = analisadorLexico.lexico();

                    if (!token.getSimbolo().equalsIgnoreCase("sFim")) {
                        analisaComandoSimples();
                    }

                } else {
                    System.out.println("Linha " + token.getLinhaCodigo() + " - Erro Sintatico: ';' esperado");
                }
            }
            token = analisadorLexico.lexico();
        } else {
            System.out.println("Linha " + token.getLinhaCodigo() + " - Erro Sintatico: palavra-chave 'inicio' esperada");
        }
    }

    private void analisaComandoSimples() throws IOException {
        if (token.getSimbolo().equalsIgnoreCase("sIdentificador")){
            analisaAtributosChamadaProcedimento();
        }
        else if(token.getSimbolo().equalsIgnoreCase("sSe")){
            analisaSe();
        }
        else if(token.getSimbolo().equalsIgnoreCase("sEnquanto")){
            analisaEnquanto();
        }
        else if(token.getSimbolo().equalsIgnoreCase("sLeia")){
            analisaLeia();
        }
        else if(token.getSimbolo().equalsIgnoreCase("sEscreva")){
            analisaEscreva();
        }
        else{
            analisaComandos();
        }
    }
    
    
    private void analisaAtributosChamadaProcedimento() throws IOException {
        token = analisadorLexico.lexico();
        if (token.getSimbolo().equalsIgnoreCase("sAtribuicao")) {
            analisaAtribuicao();
        }
        else{
            chamadaProcedimento();  //temos que implementar, nao tem no livro
        }
    }
    
    private void analisaLeia() {
        
    }
    
    private void analisaSubRotinas() {
    }


    private void analisaSe() {
        
    }

    private void analisaEnquanto() {
        
    }

    

    private void analisaEscreva() {
        
    }

    private void analisaAtribuicao() {
        
    }

    private void chamadaProcedimento() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

}
