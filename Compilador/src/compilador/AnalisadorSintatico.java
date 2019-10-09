/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import java.io.IOException;
import java.util.Stack;

/**
 *
 * @author victor
 */
public class AnalisadorSintatico {

    private final AnalisadorLexico analisadorLexico;
    private Token token;
    private Stack <TabelaDeSimbolos> pilhaTabelaDeSimbolos = new Stack<TabelaDeSimbolos>();  
    private String fraseContendoErro = "";
    private  boolean errosSintaticos = false;

    AnalisadorSintatico(AnalisadorLexico analisadorLexico) throws IOException {
        this.analisadorLexico = analisadorLexico;
        analisaPrograma();
    }

    public String getFraseContendoErro() {
        return fraseContendoErro;
    }

    private void analisaPrograma() throws IOException {

        token = analisadorLexico.lexico();

        if (token.getSimbolo().equalsIgnoreCase("sPrograma")) {
            TabelaDeSimbolosProgramaProcedimentos programaTabelaSimbolos = new TabelaDeSimbolosProgramaProcedimentos(token.getLexema());
            pilhaTabelaDeSimbolos.push(programaTabelaSimbolos);

            token = analisadorLexico.lexico();

            if (token.getSimbolo().equalsIgnoreCase("sIdentificador") && !analisadorLexico.contemErrosLexicos()  && !errosSintaticos) {
                token = analisadorLexico.lexico();
                //  insere_tabela(token.lexema,”nomedeprograma”,””,””) semantico

                if (token.getSimbolo().equalsIgnoreCase("sPontoVirgula") && !analisadorLexico.contemErrosLexicos()  && !errosSintaticos) {
                    analisaBloco();
                    if (token.getSimbolo().equalsIgnoreCase("sPonto")) {
                        //se acabou arquivo ou é comentário   então sucesso
                        //senao ERRO
                    } else {
                        mostraErros(".");
                    }
                } else {
                    mostraErros(";");
                }

            } else {
                mostraErros("identificador");
            }
        } else {
            mostraErros("programa");
        }

    }

    private void analisaBloco() throws IOException {
        token = analisadorLexico.lexico();
        analisaEtapaVariaveis();
        analisaSubRotinas();
        analisaComandos();
    }

    private void analisaEtapaVariaveis() throws IOException {

        if (token.getSimbolo().equalsIgnoreCase("sVar") && !errosSintaticos) {

            
                token = analisadorLexico.lexico();
          

            if (token.getSimbolo().equalsIgnoreCase("sIdentificador")) {
                while (token.getSimbolo().equalsIgnoreCase("sIdentificador")) {

                    analisaDeclaracaoVariaveis();

                    if (token.getSimbolo().equalsIgnoreCase("sPontoVirgula") && !analisadorLexico.contemErrosLexicos()  && !errosSintaticos) {
                        token = analisadorLexico.lexico();
                    } else {
                        mostraErros(";");
                    }
                }
            } else {
                mostraErros("identificador");
            }
        }
    }

    private void analisaDeclaracaoVariaveis() throws IOException {

        do {
            if (token.getSimbolo().equalsIgnoreCase("sIdentificador")) {
                TabelaDeSimbolosVariaveis variaveisTabelaSimbolos = new TabelaDeSimbolosVariaveis(token.getLexema());
                pilhaTabelaDeSimbolos.push(variaveisTabelaSimbolos);

                token = analisadorLexico.lexico();
                if (token.getSimbolo().equalsIgnoreCase("sVirgula") || token.getSimbolo().equalsIgnoreCase("sDoisPontos")) {

                    if (token.getSimbolo().equalsIgnoreCase("sVirgula")) {
                        token = analisadorLexico.lexico();

                        if (token.getSimbolo().equalsIgnoreCase("sDoisPontos")) {
                            mostraErros("identificador");
                        }

                    }

                } else {
                    mostraErros(", ou :");
                }

            } else {
                mostraErros("identificador");
            }
        } while (!token.getSimbolo().equalsIgnoreCase("sDoisPontos")  && !errosSintaticos);

        token = analisadorLexico.lexico();
        analisaTipo();

    }

    private void analisaTipo() throws IOException {
        if (!token.getSimbolo().equalsIgnoreCase("sInteiro") && !token.getSimbolo().equalsIgnoreCase("sBooleano")  && !errosSintaticos) {
            mostraErros("inteiro ou booleano");
        } else {
            // senão coloca_tipo_tabela(token.lexema) semantico
            
                for (TabelaDeSimbolos item : pilhaTabelaDeSimbolos) {
                    
                    if (item instanceof TabelaDeSimbolosVariaveis) {
                        if (((TabelaDeSimbolosVariaveis) item).getTipo().contentEquals("")) {
                            ((TabelaDeSimbolosVariaveis) item).setTipo(token.getLexema());
                        }
                    }
                    
            }
            
        }

        token = analisadorLexico.lexico();

    }

    private void analisaComandos() throws IOException {
        if (token.getSimbolo().equalsIgnoreCase("sInicio")  && !errosSintaticos) {
            token = analisadorLexico.lexico();
            analisaComandoSimples();

            while (!token.getSimbolo().equalsIgnoreCase("sFim")  && !errosSintaticos) {
                if (token.getSimbolo().equalsIgnoreCase("sPontoVirgula")) {
                    token = analisadorLexico.lexico();

                    if (!token.getSimbolo().equalsIgnoreCase("sFim")) {
                        analisaComandoSimples();
                    }

                } else {
                    mostraErros(";");
                }
            }
            
            //remover da pilha
            
            
            while(!pilhaTabelaDeSimbolos.lastElement().isEscopo())
            {
                pilhaTabelaDeSimbolos.pop();
            }
            
            
            if ((pilhaTabelaDeSimbolos.lastElement() instanceof TabelaDeSimbolosFuncoes ||pilhaTabelaDeSimbolos.lastElement() instanceof TabelaDeSimbolosProgramaProcedimentos) && pilhaTabelaDeSimbolos.lastElement().isEscopo()) {
                pilhaTabelaDeSimbolos.lastElement().setEscopo(false);
            }
            
            
            
            token = analisadorLexico.lexico();
        } else {
            mostraErros("inicio");
        }
    }

    private void analisaComandoSimples() throws IOException {
        if (token.getSimbolo().equalsIgnoreCase("sIdentificador")) {
            analisaAtribuicaoOuChamadaProcedimento();
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

    private void analisaAtribuicaoOuChamadaProcedimento() throws IOException {
        token = analisadorLexico.lexico();
        if (token.getSimbolo().equalsIgnoreCase("sAtribuicao")) {
            analisaAtribuicao();
        } else {
            analisaChamadaProcedimento();  //temos que implementar, nao tem no livro
        }
    }

    private void analisaLeia() throws IOException {
        token = analisadorLexico.lexico();
        if (token.getSimbolo().equalsIgnoreCase("sAbreParenteses") && !errosSintaticos) {
            token = analisadorLexico.lexico();
            if (token.getSimbolo().equalsIgnoreCase("sIdentificador") && !errosSintaticos) {
                //semantico
                token = analisadorLexico.lexico();
                if (token.getSimbolo().equalsIgnoreCase("sFechaParenteses") && !errosSintaticos) {
                    token = analisadorLexico.lexico();
                } else {
                    mostraErros(")");
                }
            } else {
                mostraErros("identificador");
            }
        } else {
            mostraErros("(");
        }
    }

    private void analisaEscreva() throws IOException {
        token = analisadorLexico.lexico();
        if (token.getSimbolo().equalsIgnoreCase("sAbreParenteses") && !errosSintaticos) {
            token = analisadorLexico.lexico();
            if (token.getSimbolo().equalsIgnoreCase("sIdentificador") && !errosSintaticos) {
                //lexico
                token = analisadorLexico.lexico();
                if (token.getSimbolo().equalsIgnoreCase("sFechaParenteses") && !errosSintaticos) {
                    token = analisadorLexico.lexico();
                } else {
                    mostraErros(")");
                }
            } else {
                mostraErros("identificador");
            }
        } else {
            mostraErros("(");
        }
    }

    private void analisaEnquanto() throws IOException {
        //semantico
        token = analisadorLexico.lexico();
        analisaExpressao();
        if (token.getSimbolo().equalsIgnoreCase("sFaca") && !errosSintaticos) {
            //semantico
            token = analisadorLexico.lexico();
            analisaComandoSimples();
            //semantico
        } else {
            mostraErros("faca");
        }
    }

    private void analisaSe() throws IOException {
        token = analisadorLexico.lexico();
        analisaExpressao();
        if (token.getSimbolo().equalsIgnoreCase("sEntao") && !errosSintaticos) {
            token = analisadorLexico.lexico();
            analisaComandoSimples();
            if (token.getSimbolo().equalsIgnoreCase("sSenao")) {
                token = analisadorLexico.lexico();
                analisaComandoSimples();
            }
        } else {
            mostraErros("entao");
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
            if (token.getSimbolo().equalsIgnoreCase("sPontoVirgula") && !errosSintaticos) {
                token = analisadorLexico.lexico();
            } else {
                mostraErros(";");
            }
        }
        if (flag == 1) {
            //semantico
        }
    }

    private void analisaDeclaracaoProcedimento() throws IOException {
        token = analisadorLexico.lexico();
        //semantico
        if (token.getSimbolo().equalsIgnoreCase("sIdentificador") && !errosSintaticos) {
            //semantico
            
            TabelaDeSimbolosProgramaProcedimentos procedimentoTabelaSimbolos = new TabelaDeSimbolosProgramaProcedimentos(token.getLexema());
            pilhaTabelaDeSimbolos.push(procedimentoTabelaSimbolos);
            
            token = analisadorLexico.lexico();
            if (token.getSimbolo().equalsIgnoreCase("sPontoVirgula") && !errosSintaticos) {
                analisaBloco();
            } else {
                mostraErros(";");
            }
        } else {
            mostraErros("identificador");
        }
        //semantico
    }

    private void analisaDeclaracaoFuncao() throws IOException {
        token = analisadorLexico.lexico();
        //semantico
        if (token.getSimbolo().equalsIgnoreCase("sIdentificador") && !errosSintaticos) {
            //semantico
            
            TabelaDeSimbolosFuncoes funcaoTabelaSimbolos = new TabelaDeSimbolosFuncoes(token.getLexema());
            pilhaTabelaDeSimbolos.push(funcaoTabelaSimbolos);
            
            token = analisadorLexico.lexico();
            if (token.getSimbolo().equalsIgnoreCase("sDoisPontos") && !errosSintaticos) {
                token = analisadorLexico.lexico();
                if (token.getSimbolo().equalsIgnoreCase("sInteiro") || token.getSimbolo().equalsIgnoreCase("sBooleano") && !errosSintaticos) {
                    //semantico
                    token = analisadorLexico.lexico();
                    if (token.getSimbolo().equalsIgnoreCase("sPontoVirgula")) {
                        analisaBloco();
                    }
                } else {
                    mostraErros("inteiro ou booleano");
                }
            } else {
                mostraErros(":");
            }
        } else {
            mostraErros("identificador");
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

        }

        analisaTermo();

        while (token.getSimbolo().equalsIgnoreCase("sMais") || token.getSimbolo().equalsIgnoreCase("sMenos") || token.getSimbolo().equalsIgnoreCase("sOu")) {
            token = analisadorLexico.lexico();
            analisaTermo();
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
                } else {
                    token = analisadorLexico.lexico();
                }
            } else {
                System.out.println("Linha " + token.getLinhaCodigo() + " - Erro Semantico: n sei qual");
            }

        } else {
            if (token.getSimbolo().equalsIgnoreCase("sNumero")) {
                token = analisadorLexico.lexico();

            } else if (token.getSimbolo().equalsIgnoreCase("sNao")) {
                token = analisadorLexico.lexico();
                analisaFator();
            } else if (token.getSimbolo().equalsIgnoreCase("sAbreParentesis")) {
                token = analisadorLexico.lexico();
                analisaExpressao();
                if (token.getSimbolo().equalsIgnoreCase("sFechaParentesis") && !errosSintaticos) {
                    token = analisadorLexico.lexico();
                } else {
                    mostraErros(")");
                }

            } else if ((token.getLexema().equalsIgnoreCase("verdadeiro") || token.getLexema().equalsIgnoreCase("falso")) && !errosSintaticos) {
                token = analisadorLexico.lexico();
            } else {
                mostraErros("verdadeiro ou falso");
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

    private void mostraErros(String erroEncontrado) {
        
        if (analisadorLexico.contemErrosLexicos() &&  !errosSintaticos) {
            System.out.println("Linha " + token.getLinhaCodigo() + " - Erro Léxico: Caracter " + token.getLexema() + " não tem função definida.");
            fraseContendoErro = fraseContendoErro.concat("Linha " + Integer.toString(token.getLinhaCodigo()) + " - Erro Léxico: " + token.getLexema() + " não tem função definida.");
        } else if(!analisadorLexico.contemErrosLexicos() &&  !errosSintaticos) {
            System.out.println("Linha " + token.getLinhaCodigo() + " - Erro Sintatico: " + erroEncontrado + " esperado");
            fraseContendoErro = ("Linha " + token.getLinhaCodigo() + " - Erro Sintatico: " + erroEncontrado + " esperado");
        }
         errosSintaticos = true;
    }

    

}
