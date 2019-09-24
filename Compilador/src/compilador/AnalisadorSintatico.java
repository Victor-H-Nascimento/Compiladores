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

                    analisaDeclaracaoVariaveis();

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

    private void analisaDeclaracaoVariaveis() throws IOException {

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
        if (token.getSimbolo().equalsIgnoreCase("sIdentificador")) {
            analisaAtributosChamadaProcedimento();
        } else if (token.getSimbolo().equalsIgnoreCase("sSe")) {
            analisaSe();
        } else if (token.getSimbolo().equalsIgnoreCase("sEnquanto")) {
            analisaEnquanto();
        } else if (token.getSimbolo().equalsIgnoreCase("sLeia")) {
            analisaLeia();
        } else if (token.getSimbolo().equalsIgnoreCase("sEscreva")) {
            analisaEscreva();
        } else {
            analisaComandos();
        }
    }

    private void analisaAtributosChamadaProcedimento() throws IOException {
        token = analisadorLexico.lexico();
        if (token.getSimbolo().equalsIgnoreCase("sAtribuicao")) {
            analisaAtribuicao();
        } else {
            analisaChamadaProcedimento();  //temos que implementar, nao tem no livro
        }
    }

    private void analisaLeia() throws IOException {
        token = analisadorLexico.lexico();
        if (token.getSimbolo().equalsIgnoreCase("sAbreParentesis")) {
            token = analisadorLexico.lexico();
            if (token.getSimbolo().equalsIgnoreCase("sIdentificador")) {
                //semantico
                token = analisadorLexico.lexico();
                if (token.getSimbolo().equalsIgnoreCase("sFechaParentesis")) {
                    token = analisadorLexico.lexico();
                } else {
                    System.out.println("Linha " + token.getLinhaCodigo() + " - Erro Sintatico: ')' esperado");
                }
            } else {
                System.out.println("Linha " + token.getLinhaCodigo() + " - Erro Sintatico: identificador esperado");
            }
        } else {
            System.out.println("Linha " + token.getLinhaCodigo() + " - Erro Sintatico: '(' esperado");
        }
    }

    private void analisaEscreva() throws IOException {
        token = analisadorLexico.lexico();
        if (token.getSimbolo().equalsIgnoreCase("sAbreParentesis")) {
            token = analisadorLexico.lexico();
            if (token.getSimbolo().equalsIgnoreCase("sIdentificador")) {
                //lexico
                token = analisadorLexico.lexico();
                if (token.getSimbolo().equalsIgnoreCase("sFechaParentesis")) {
                    token = analisadorLexico.lexico();
                } else {
                    System.out.println("Linha " + token.getLinhaCodigo() + " - Erro Sintatico: ')' esperado");
                }
            } else {
                System.out.println("Linha " + token.getLinhaCodigo() + " - Erro Sintatico: identificador esperado");
            }
        } else {
            System.out.println("Linha " + token.getLinhaCodigo() + " - Erro Sintatico: '(' esperado");
        }
    }

    private void analisaEnquanto() throws IOException {
        //semantico
        token = analisadorLexico.lexico();
        analisaExpressao();
        if (token.getSimbolo().equalsIgnoreCase("sFaca")) {
            //semantico
            token = analisadorLexico.lexico();
            analisaComandoSimples();
            //semantico
        } else {
            System.out.println("Linha " + token.getLinhaCodigo() + " - Erro Sintatico: 'faca' esperado");
        }
    }

    private void analisaSe() throws IOException {
        token = analisadorLexico.lexico();
        analisaExpressao();
        if (token.getSimbolo().equalsIgnoreCase("sEntao")) {
            token = analisadorLexico.lexico();
            analisaComandoSimples();
            if (token.getSimbolo().equalsIgnoreCase("sSenao")) {
                token = analisadorLexico.lexico();
                analisaComandoSimples();
            }
        } else {
            System.out.println("Linha " + token.getLinhaCodigo() + " - Erro Sintatico: 'entao' esperado");
        }
    }

    private void analisaSubRotinas() throws IOException {
        //semantico
        int flag = 0;
        if (token.getSimbolo().equalsIgnoreCase("sProcedimento") || token.getSimbolo().equalsIgnoreCase("sFuncao")) {
            //semantico
        }
        while (token.getSimbolo().equalsIgnoreCase("sProcedimento") || token.getSimbolo().equalsIgnoreCase("sFuncao")) {
            if (token.getSimbolo().equalsIgnoreCase("sProcedimento")) {
                analisaDeclaracaoProcedimento();
            } else {
                analisaDeclaracaoFuncao();
            }
            if (token.getSimbolo().equalsIgnoreCase("sPontoVirgula")) {
                token = analisadorLexico.lexico();
            } else {
                System.out.println("Linha " + token.getLinhaCodigo() + " - Erro Sintatico: ';' esperado");
            }
        }
        if (flag == 1) {
            //semantico
        }
    }

    private void analisaDeclaracaoProcedimento() throws IOException {
        token = analisadorLexico.lexico();
        //semantico
        if (token.getSimbolo().equalsIgnoreCase("sIdentificador")) {
            //semantico
            token = analisadorLexico.lexico();
            if (token.getSimbolo().equalsIgnoreCase("sPontoVirgula")) {
                analisaBloco();
            } else {
                System.out.println("Linha " + token.getLinhaCodigo() + " - Erro Sintatico: ';' esperado");
            }
        } else {
            System.out.println("Linha " + token.getLinhaCodigo() + " - Erro Sintatico: identificador esperado");
        }
        //semantico
    }

    private void analisaDeclaracaoFuncao() throws IOException {
        token = analisadorLexico.lexico();
        //semantico
        if (token.getSimbolo().equalsIgnoreCase("sIdentificador")) {
            //semantico
            token = analisadorLexico.lexico();
            if (token.getSimbolo().equalsIgnoreCase("sDoisPontos")) {
                token = analisadorLexico.lexico();
                if (token.getSimbolo().equalsIgnoreCase("sInteiro") || token.getSimbolo().equalsIgnoreCase("sBooleano")) {
                    //semantico
                    token = analisadorLexico.lexico();
                    if (token.getSimbolo().equalsIgnoreCase("sPontoVirgula")) {
                        analisaBloco();
                    }
                } else {
                    System.out.println("Linha " + token.getLinhaCodigo() + " - Erro Sintatico: 'inteiro' ou 'booleano' esperado");
                }
            } else {
                System.out.println("Linha " + token.getLinhaCodigo() + " - Erro Sintatico: ':' esperado");
            }
        } else {
            System.out.println("Linha " + token.getLinhaCodigo() + " - Erro Sintatico: identificador esperado");
        }
        //semantico
    }

    private void analisaExpressao() throws IOException {
        analisaExpressaoSimples();
        if (token.getSimbolo().equalsIgnoreCase("sMaior") || token.getSimbolo().equalsIgnoreCase("sMaiorIgual") || token.getSimbolo().equalsIgnoreCase("sIgual") || token.getSimbolo().equalsIgnoreCase("sMenor") || token.getSimbolo().equalsIgnoreCase("sMenorIgual") || token.getSimbolo().equalsIgnoreCase("sDiferente")) {
            token = analisadorLexico.lexico();
            analisaExpressaoSimples();
        }
    }

    private void analisaExpressaoSimples() throws IOException {
        if (token.getSimbolo().equalsIgnoreCase("sMais") || token.getSimbolo().equalsIgnoreCase("sMenos")) {
            token = analisadorLexico.lexico();
            analisaTermo();
            while (token.getSimbolo().equalsIgnoreCase("sMais") || token.getSimbolo().equalsIgnoreCase("sMenos") || token.getSimbolo().equalsIgnoreCase("sOu")) {
                token = analisadorLexico.lexico();
                analisaTermo();
            }
        }
    }

    private void analisaTermo() throws IOException {
        analisaFator();
        while (token.getSimbolo().equalsIgnoreCase("sMultiplicacao") || token.getSimbolo().equalsIgnoreCase("sDivisao") || token.getSimbolo().equalsIgnoreCase("sE")) {
            token = analisadorLexico.lexico();
            analisaFator();
        }
    }

    private void analisaFator() throws IOException {
        if (token.getSimbolo().equalsIgnoreCase("sIdentificador")) {// se variavel ou funcao
            //semantico
            if (true) {//lexema
                if (true) {
                    analisaChamadaFuncao();
                }
                else{
                    token = analisadorLexico.lexico();
                }
            }
            else{
                System.out.println("Linha " + token.getLinhaCodigo() + " - Erro Semantico: n sei qual");
            }
            
        } else {
            if (token.getSimbolo().equalsIgnoreCase("sNumero")) {
                token = analisadorLexico.lexico();
                
            }
            else if(token.getSimbolo().equalsIgnoreCase("sNao")){
                token = analisadorLexico.lexico();
                analisaFator();
            }
            else if(token.getSimbolo().equalsIgnoreCase("sAbreParentesis")){
                token = analisadorLexico.lexico();
                analisaExpressao();
                if (token.getSimbolo().equalsIgnoreCase("sFechaParentesis")) {
                    token = analisadorLexico.lexico();
                }
                else{
                    System.out.println("Linha " + token.getLinhaCodigo() + " - Erro Sintatico: ')' esperado");
                }
                
            }
            else if(token.getLexema().equalsIgnoreCase("verdadeiro") || token.getLexema().equalsIgnoreCase("falso")){
                token = analisadorLexico.lexico();
            }
            else{
                System.out.println("Linha " + token.getLinhaCodigo() + " - Erro Sintatico: 'verdadeiro' ou 'falso' esperados");
            }
        }
    }

    private void analisaAtribuicao() throws IOException {
        token = analisadorLexico.lexico();
        analisaExpressao();
    }

    private void analisaChamadaProcedimento() {
        //nada acontece no sintatico, apenas no semantico
    }

    private void analisaChamadaFuncao() throws IOException {
            token = analisadorLexico.lexico();
    }

}
