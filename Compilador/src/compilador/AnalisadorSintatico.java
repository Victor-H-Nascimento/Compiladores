/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author victor
 */
public class AnalisadorSintatico {

    private final AnalisadorLexico analisadorLexico;
    private Token token = null;
    private Stack<TabelaDeSimbolos> pilhaTabelaDeSimbolos = new Stack<TabelaDeSimbolos>();
    private Token tokenAuxiliar = null;
    private SimbolosToken simbolos = new SimbolosToken();
    private String fraseContendoErro = "";
    private boolean errosSintaticos = false;
    private boolean erroNaAtribuicao = true;
    private Stack<Token> pilhaOperadores = new Stack();
    private ArrayList<ElementosPosFixa> filaPosFixa = new ArrayList();
    private int rotuloLabel = 1;
    private int posicaoMemoria = 0;
    private final GeradorCodigo gerador = new GeradorCodigo();

    AnalisadorSintatico(AnalisadorLexico analisadorLexico) throws IOException {
        this.analisadorLexico = analisadorLexico;
        analisaPrograma();
    }

    public Token getToken() {
        return token;
    }

    public int getRotuloLabel() {
        return rotuloLabel;
    }

    public void incrementaRotuloLabel() {
        this.rotuloLabel = this.rotuloLabel + 1;
    }

    public int getPosicaoMemoria() {
        return posicaoMemoria;
    }

    public void incrementaPosicaoMemoria() {
        this.posicaoMemoria = this.posicaoMemoria + 1;
    }
 
    public String getFraseContendoErro() {
        return fraseContendoErro;
    }

    private void analisaPrograma() throws IOException {

        token = analisadorLexico.lexico();

        if (token.getSimbolo().equalsIgnoreCase("sPrograma")) {
            gerador.geraSTART();
            TabelaDeSimbolosProgramaProcedimentos programaTabelaSimbolos = new TabelaDeSimbolosProgramaProcedimentos(token.getLexema());
            pilhaTabelaDeSimbolos.push(programaTabelaSimbolos);
            token = analisadorLexico.lexico();

            if (token.getSimbolo().equalsIgnoreCase("sIdentificador") && !analisadorLexico.contemErrosLexicos() && !errosSintaticos) {
                token = analisadorLexico.lexico();
                //  insere_tabela(token.lexema,”nomedeprograma”,””,””) semantico

                if (token.getSimbolo().equalsIgnoreCase("sPontoVirgula") && !analisadorLexico.contemErrosLexicos() && !errosSintaticos) {
                    analisaBloco();
                    if (token.getSimbolo().equalsIgnoreCase("sPonto")) {
                        gerador.geraHLT();
                        //se acabou arquivo ou é comentário   então sucesso
                        //senao ERRO

                        for (int i = 0; i < filaPosFixa.size(); i++) {

                            System.out.print(filaPosFixa.get(i).getLexema() + " ");

                        }
                        System.out.println("");

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

        //remover da pilha
        //fazer condicao para executar este while, somente se for um fim de um procedimento/funcao.
        while (!pilhaTabelaDeSimbolos.lastElement().isEscopo() && !pilhaTabelaDeSimbolos.isEmpty()) {//fazer logica para que se funcao nao tiver variaveis, nao dar pop nas variaveis de outras funcoes.

            if (pilhaTabelaDeSimbolos.lastElement() instanceof TabelaDeSimbolosFuncoes || pilhaTabelaDeSimbolos.lastElement() instanceof TabelaDeSimbolosProgramaProcedimentos) {
                pilhaTabelaDeSimbolos.pop();
                break;
            } else {
                pilhaTabelaDeSimbolos.pop();
            }

        }

        if ((pilhaTabelaDeSimbolos.lastElement() instanceof TabelaDeSimbolosFuncoes || pilhaTabelaDeSimbolos.lastElement() instanceof TabelaDeSimbolosProgramaProcedimentos) && pilhaTabelaDeSimbolos.lastElement().isEscopo() && !pilhaTabelaDeSimbolos.lastElement().getLexema().equalsIgnoreCase("programa")) {
            pilhaTabelaDeSimbolos.lastElement().setEscopo(false);
        }

    }

    private void analisaEtapaVariaveis() throws IOException {

        if (token.getSimbolo().equalsIgnoreCase("sVar") && !errosSintaticos) {

            token = analisadorLexico.lexico();

            if (token.getSimbolo().equalsIgnoreCase("sIdentificador")) {
                while (token.getSimbolo().equalsIgnoreCase("sIdentificador")) {

                    analisaDeclaracaoVariaveis();

                    if (token.getSimbolo().equalsIgnoreCase("sPontoVirgula") && !analisadorLexico.contemErrosLexicos() && !errosSintaticos) {
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

                if (pesquisaVariavelDuplicada(token.getLexema())) {
                    TabelaDeSimbolosVariaveis variaveisTabelaSimbolos = new TabelaDeSimbolosVariaveis(token.getLexema());
                    pilhaTabelaDeSimbolos.push(variaveisTabelaSimbolos);
                    token = analisadorLexico.lexico();
                    if (token.getSimbolo().equalsIgnoreCase("sVirgula") || token.getSimbolo().equalsIgnoreCase("sDoisPontos")) {

                        if (token.getSimbolo().equalsIgnoreCase("sVirgula")) {
                            token = analisadorLexico.lexico();

                            if (token.getSimbolo().equalsIgnoreCase("sDoisPontos")) {
                                mostraErros("encontrado ':' quando um identificador era");
                            }

                        }

                    } else {
                        mostraErros(", ou :");
                    }

                } else {
                    erroSemanticoVariavelDuplicada();
                }
            } else {
                mostraErros("apos a ',' um identificador e");
            }

        } while (!token.getSimbolo().equalsIgnoreCase("sDoisPontos") && !errosSintaticos);

        token = analisadorLexico.lexico();
        analisaTipo();

    }

    private void analisaTipo() throws IOException {
        if (!token.getSimbolo().equalsIgnoreCase("sInteiro") && !token.getSimbolo().equalsIgnoreCase("sBooleano") && !errosSintaticos) {
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
        if (token.getSimbolo().equalsIgnoreCase("sInicio") && !errosSintaticos) {
            token = analisadorLexico.lexico();
            analisaComandoSimples();

            while (!token.getSimbolo().equalsIgnoreCase("sFim") && !errosSintaticos) {
                if (token.getSimbolo().equalsIgnoreCase("sPontoVirgula")) {
                    token = analisadorLexico.lexico();

                    if (!token.getSimbolo().equalsIgnoreCase("sFim")) {
                        analisaComandoSimples();
                    }

                } else {
                    mostraErros(";");
                }
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
        tokenAuxiliar = token;
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

                if (pesquisaDeclaracaoVariavel(token.getLexema())) {

                    token = analisadorLexico.lexico();
                    if (token.getSimbolo().equalsIgnoreCase("sFechaParenteses") && !errosSintaticos) {
                        token = analisadorLexico.lexico();
                    } else {
                        mostraErros(")");
                    }
                } else {
                    erroSemanticoVariavelIncompativel(token.getLexema());
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

                if (pesquisaDeclaracaoFuncaoVariavel(token.getLexema())) {
                    token = analisadorLexico.lexico();
                    if (token.getSimbolo().equalsIgnoreCase("sFechaParenteses") && !errosSintaticos) {
                        token = analisadorLexico.lexico();
                    } else {
                        mostraErros(")");
                    }
                } else {
                    erroSemanticoVariavelFuncaoIncompativel(token.getLexema());
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
        analisaExpressao();// ver condicao do retorno
        fimInFixa();
        String retorno = verificaPosFixa();

        if (retorno.contentEquals(simbolos.getBooleano())) {
            if (token.getSimbolo().equalsIgnoreCase("sFaca") && !errosSintaticos) {
                //semantico
                token = analisadorLexico.lexico();
                analisaComandoSimples();
                //semantico
            } else {
                mostraErros("faca");
            }
        } else {
            erroTipoExpressao();
        }

    }

    private void analisaSe() throws IOException {
        token = analisadorLexico.lexico();
        analisaExpressao();// ver condicao do retorno
        fimInFixa();
        String retorno = verificaPosFixa();

        if (retorno.contentEquals(simbolos.getBooleano())) {
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

        } else {
            erroTipoExpressao();
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

            if (pesquisaDeclaracaoProcedimento(token.getLexema())) {
                TabelaDeSimbolosProgramaProcedimentos procedimentoTabelaSimbolos = new TabelaDeSimbolosProgramaProcedimentos(token.getLexema());
                pilhaTabelaDeSimbolos.push(procedimentoTabelaSimbolos);

                token = analisadorLexico.lexico();
                if (token.getSimbolo().equalsIgnoreCase("sPontoVirgula") && !errosSintaticos) {
                    analisaBloco();
                } else {
                    mostraErros(";");
                }
            } else {
                erroSemanticoProcedimentoIncompativel(token.getLexema());
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

            if (pesquisaDeclaracaoFuncao(token.getLexema())) {
                TabelaDeSimbolosFuncoes funcaoTabelaSimbolos = new TabelaDeSimbolosFuncoes(token.getLexema());
                pilhaTabelaDeSimbolos.push(funcaoTabelaSimbolos);

                token = analisadorLexico.lexico();
                if (token.getSimbolo().equalsIgnoreCase("sDoisPontos") && !errosSintaticos) {
                    token = analisadorLexico.lexico();
                    if (token.getSimbolo().equalsIgnoreCase("sInteiro") || token.getSimbolo().equalsIgnoreCase("sBooleano") && !errosSintaticos) {
                        //semantico

                        preencheTipoFuncaoProcedimento(token);

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
                erroSemanticoFuncaoIncompativel(token.getLexema());
            }
        } else {
            mostraErros("identificador");
        }
        //semantico
    }

    private void analisaExpressao() throws IOException {
        analisaExpressaoSimples();
        if (token.getSimbolo().equalsIgnoreCase("sMaior") || token.getSimbolo().equalsIgnoreCase("sMaiorIgual") || token.getSimbolo().equalsIgnoreCase("sIgual") || token.getSimbolo().equalsIgnoreCase("sMenor") || token.getSimbolo().equalsIgnoreCase("sMenorIgual") || token.getSimbolo().equalsIgnoreCase("sDiferente")) {
            verificaEAdicionaPilhaOperadores(token);
            token = analisadorLexico.lexico();
            analisaExpressaoSimples();
        }

    }

    private void analisaExpressaoSimples() throws IOException {
        if (token.getSimbolo().equalsIgnoreCase("sMais") || token.getSimbolo().equalsIgnoreCase("sMenos")) {

            if (token.getSimbolo().equalsIgnoreCase("sMais")) {
                token.setLexema("+u");
                token.setSimbolo(simbolos.getMaisUnitario());
            } else {
                token.setLexema("-u");
                token.setSimbolo(simbolos.getMenosUnitario());
            }

            verificaEAdicionaPilhaOperadores(token);//add +u/-u
            token = analisadorLexico.lexico();

        }

        analisaTermo();

        while (token.getSimbolo().equalsIgnoreCase("sMais") || token.getSimbolo().equalsIgnoreCase("sMenos") || token.getSimbolo().equalsIgnoreCase("sOu")) {
            verificaEAdicionaPilhaOperadores(token);//add +/-/ou
            token = analisadorLexico.lexico();
            analisaTermo();
        }

    }

    private void analisaTermo() throws IOException {
        analisaFator();
        while (token.getSimbolo().equalsIgnoreCase("sMultiplicacao") || token.getSimbolo().equalsIgnoreCase("sDivisao") || token.getSimbolo().equalsIgnoreCase("sE")) {
            verificaEAdicionaPilhaOperadores(token);//add */div/e
            token = analisadorLexico.lexico();
            analisaFator();
        }
    }

    private void analisaFator() throws IOException {
        if (token.getSimbolo().equalsIgnoreCase("sIdentificador")) {// se variavel ou funcao
            //semantico
            if (pesquisaDeclaracaoFuncaoVariavel(token.getLexema())) {//lexema
                if (pesquisaTipoFuncao(token)) {
                    analisaChamadaFuncao();
                } else {

                    Operando elemento = new Operando();
                    elemento.setLexema(token.getLexema());
                    pesquisaTipoVariavel(token.getLexema(), elemento);// coloca o tipo da variavel

                    filaPosFixa.add(elemento);//add identificador na saida pos fixa
                    token = analisadorLexico.lexico();
                }
            } else {
                erroSemanticoVariavelFuncaoIncompativel(token.getLexema());
            }

        } else {
            if (token.getSimbolo().equalsIgnoreCase("sNumero")) {
                Operando elemento = new Operando();
                elemento.setLexema(token.getLexema());
                elemento.setTipo(simbolos.getInteiro());

                filaPosFixa.add(elemento);//add numero na saida pos fixa
                token = analisadorLexico.lexico();

            } else if (token.getSimbolo().equalsIgnoreCase("sNao")) {
                verificaEAdicionaPilhaOperadores(token);//nao
                token = analisadorLexico.lexico();
                analisaFator();
            } else if (token.getSimbolo().equalsIgnoreCase("sAbreParenteses")) {
                verificaEAdicionaPilhaOperadores(token);//add abre parenteses
                token = analisadorLexico.lexico();
                analisaExpressao();// ver condicao do retorno
                if (token.getSimbolo().equalsIgnoreCase("sFechaParenteses") && !errosSintaticos) {
                    verificaEAdicionaPilhaOperadores(token);//add fecha parenteses
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

        //verificar se tokenAuxiliar é uma variavel e se já está na tabela de simbolos
        for (int i = pilhaTabelaDeSimbolos.size(); i > 0; i--) {
            if (pilhaTabelaDeSimbolos.elementAt(i - 1).getLexema().contentEquals(tokenAuxiliar.getLexema()) && pilhaTabelaDeSimbolos.elementAt(i - 1) instanceof TabelaDeSimbolosVariaveis) {
                erroNaAtribuicao = false;
                break;
            }
        }

        if (!erroNaAtribuicao) {
            token = analisadorLexico.lexico();
            analisaExpressao();// ver condicao do retorno
            fimInFixa();
            String retorno = verificaPosFixa();
            erroNaAtribuicao = true;
            if (!retorno.contentEquals(pesquisaTipoVariavel(tokenAuxiliar.getLexema()))) {// erro se o tipo da variavel/funcao do lado esquerdo for diferente do tipo da expressao
                erroTipoExpressao();
            }
        } else {
            erroSemanticoLadoEsquerdoAtribuicao();
        }

    }

    private void analisaChamadaProcedimento() {
        //verificar se tokenAuxiliar é um procedimento e está guardado na tabela de simbolos

        for (int i = pilhaTabelaDeSimbolos.size(); i > 0; i--) {
            if (pilhaTabelaDeSimbolos.elementAt(i - 1).getLexema().contentEquals(tokenAuxiliar.getLexema()) && pilhaTabelaDeSimbolos.elementAt(i - 1) instanceof TabelaDeSimbolosProgramaProcedimentos) {
                erroNaAtribuicao = false;
                break;
            }
        }

        if (!erroNaAtribuicao) {
            erroNaAtribuicao = true;
        } else {
            erroSemanticoLadoEsquerdoChamadaProcedimento();
        }

    }

    private void analisaChamadaFuncao() throws IOException {
        token = analisadorLexico.lexico();
    }

    private void mostraErros(String erroEncontrado) {

        if (analisadorLexico.contemErrosLexicos() && !errosSintaticos) {
            System.out.println("Linha " + token.getLinhaCodigo() + " - Erro Léxico: Caracter " + token.getLexema() + " não tem função definida.");
            fraseContendoErro = fraseContendoErro.concat("Linha " + Integer.toString(token.getLinhaCodigo()) + " - Erro Léxico: " + token.getLexema() + " não tem função definida.");
        } else if (!analisadorLexico.contemErrosLexicos() && !errosSintaticos) {
            System.out.println("Linha " + token.getLinhaCodigo() + " - Erro Sintatico: " + erroEncontrado + " esperado");
            fraseContendoErro = ("Linha " + token.getLinhaCodigo() + " - Erro Sintatico: " + erroEncontrado + " esperado");
        }
        errosSintaticos = true;
    }

    private boolean pesquisaVariavelDuplicada(String lexema) {

        int tamanhoPilha = pilhaTabelaDeSimbolos.size();

        while (pilhaTabelaDeSimbolos.elementAt(tamanhoPilha - 1) instanceof TabelaDeSimbolosVariaveis && !pilhaTabelaDeSimbolos.isEmpty() && tamanhoPilha > 1) {

            TabelaDeSimbolosVariaveis item = (TabelaDeSimbolosVariaveis) pilhaTabelaDeSimbolos.elementAt(tamanhoPilha - 1);

            if (item.getLexema().contentEquals(lexema)) {
                return false;
            }

            tamanhoPilha--;
        }

        return true;
    }

    private boolean pesquisaDeclaracaoVariavel(String lexema) {

        for (TabelaDeSimbolos item : pilhaTabelaDeSimbolos) {

            if (item instanceof TabelaDeSimbolosVariaveis) {
                if (item.getLexema().contentEquals(lexema)) {
                    return true;
                }
            }

        }
        return false;
    }

    private void pesquisaTipoVariavel(String lexema, Operando elemento) {

        for (int i = pilhaTabelaDeSimbolos.size(); i > 0; i--) {

            //TabelaDeSimbolosVariaveis item = (TabelaDeSimbolosVariaveis) pilhaTabelaDeSimbolos.get(i - 1);
            if (pilhaTabelaDeSimbolos.get(i - 1) instanceof TabelaDeSimbolosVariaveis && pilhaTabelaDeSimbolos.get(i - 1).getLexema().contentEquals(lexema)) {
                TabelaDeSimbolosVariaveis item = (TabelaDeSimbolosVariaveis) pilhaTabelaDeSimbolos.get(i - 1);
                String tipo = item.getTipo();

                if (tipo.contentEquals("inteiro")) {
                    elemento.setTipo(simbolos.getInteiro());
                } else {
                    elemento.setTipo(simbolos.getBooleano());
                }

                break;
            } else {
                //Erro semantico: nao existe tipo na variavel ou variavel nao foi encontrada
            }

        }

    }

    private String pesquisaTipoVariavel(String lexema) {

        for (int i = pilhaTabelaDeSimbolos.size(); i > 0; i--) {

            //TabelaDeSimbolosVariaveis item = (TabelaDeSimbolosVariaveis) pilhaTabelaDeSimbolos.get(i - 1);
            if (pilhaTabelaDeSimbolos.get(i - 1) instanceof TabelaDeSimbolosVariaveis && pilhaTabelaDeSimbolos.get(i - 1).getLexema().contentEquals(lexema)) {
                TabelaDeSimbolosVariaveis item = (TabelaDeSimbolosVariaveis) pilhaTabelaDeSimbolos.get(i - 1);
                String tipo = item.getTipo();

                if (tipo.contentEquals("inteiro")) {
                    return simbolos.getInteiro();
                } else {
                    return simbolos.getBooleano();
                }
            } 

        }
        return "erro";
    }

    private boolean pesquisaDeclaracaoFuncaoVariavel(String lexema) {
        for (TabelaDeSimbolos item : pilhaTabelaDeSimbolos) {

            if (item instanceof TabelaDeSimbolosVariaveis || item instanceof TabelaDeSimbolosFuncoes) {
                if (item.getLexema().contentEquals(lexema)) {
                    return true;
                }
            }

        }
        return false;
    }

    private boolean pesquisaDeclaracaoProcedimento(String lexema) {
        for (TabelaDeSimbolos item : pilhaTabelaDeSimbolos) {

            if (item instanceof TabelaDeSimbolosProgramaProcedimentos) {
                if (item.getLexema().contentEquals(lexema)) {
                    return false;
                }
            }

        }
        return true;
    }

    private boolean pesquisaDeclaracaoFuncao(String lexema) {
        for (TabelaDeSimbolos item : pilhaTabelaDeSimbolos) {

            if (item instanceof TabelaDeSimbolosFuncoes) {
                if (item.getLexema().contentEquals(lexema)) {
                    return false;
                }
            }

        }
        return true;
    }

    private void preencheTipoFuncaoProcedimento(Token tokenAux) {

        for (TabelaDeSimbolos item : pilhaTabelaDeSimbolos) {

            if (item instanceof TabelaDeSimbolosFuncoes) {
                if (item.getLexema().contentEquals(tokenAux.getLexema())) {

                    if (tokenAux.getSimbolo().contentEquals("sInteiro")) {
                        ((TabelaDeSimbolosFuncoes) item).setTipo("sInteiro");

                    } else {
                        ((TabelaDeSimbolosFuncoes) item).setTipo("sBooleano");
                    }
                }
            }

        }
    }

    private boolean pesquisaTipoFuncao(Token tokenAux) {
        for (TabelaDeSimbolos item : pilhaTabelaDeSimbolos) {

            if (item instanceof TabelaDeSimbolosFuncoes) {
                if (item.getLexema().contentEquals(tokenAux.getLexema())) {

                    if (((TabelaDeSimbolosFuncoes) item).getTipo().contentEquals("sInteiro") || ((TabelaDeSimbolosFuncoes) item).getTipo().contentEquals("sBooleano")) {
                        return true;
                    }
                }
            }

        }

        return false;
    }

    private void erroSemanticoVariavelDuplicada() {
        fraseContendoErro = fraseContendoErro.concat("Linha " + Integer.toString(token.getLinhaCodigo()) + " - Erro Semantico: " + "Variavel duplicada '" + token.getLexema() + "'");
        errosSintaticos = true;
    }

    private void erroSemanticoLadoEsquerdoAtribuicao() {
        fraseContendoErro = fraseContendoErro.concat("Linha " + Integer.toString(token.getLinhaCodigo()) + " - Erro Semantico: " + "Tipo incompatível no lado esquerdo da atribuição");
        errosSintaticos = true;
    }

    private void erroSemanticoLadoEsquerdoChamadaProcedimento() {
        fraseContendoErro = fraseContendoErro.concat("Linha " + Integer.toString(token.getLinhaCodigo()) + " - Erro Semantico: " + "Tipo incompatível. Espera-se procedimento.");
        errosSintaticos = true;
    }

    private void erroSemanticoVariavelIncompativel(String lexema) {
        fraseContendoErro = fraseContendoErro.concat("Linha " + Integer.toString(token.getLinhaCodigo()) + " - Erro Semantico: " + lexema + " é incompatível, espera-se uma variavel.");
        errosSintaticos = true;
    }

    private void erroSemanticoVariavelFuncaoIncompativel(String lexema) {
        fraseContendoErro = fraseContendoErro.concat("Linha " + Integer.toString(token.getLinhaCodigo()) + " - Erro Semantico: " + lexema + " é incompatível, espera-se uma variavel ou uma funcao.");
        errosSintaticos = true;
    }

    private void erroSemanticoProcedimentoIncompativel(String lexema) {
        fraseContendoErro = fraseContendoErro.concat("Linha " + Integer.toString(token.getLinhaCodigo()) + " - Erro Semantico: " + lexema + " é incompatível, espera-se um procedimento.");
        errosSintaticos = true;
    }

    private void erroSemanticoFuncaoIncompativel(String lexema) {
        fraseContendoErro = fraseContendoErro.concat("Linha " + Integer.toString(token.getLinhaCodigo()) + " - Erro Semantico: " + lexema + " é incompatível, espera-se uma funcao.");
        errosSintaticos = true;
    }

    private int getPrioridade(String valor) {
        if (valor.contentEquals("+u") || valor.contentEquals("-u") || valor.contentEquals("nao")) {
            return 5;
        } else if (valor.contentEquals("*") || valor.contentEquals("div")) {
            return 4;
        } else if (valor.contentEquals("+") || valor.contentEquals("-")) {
            return 3;
        } else if (valor.contentEquals(">") || valor.contentEquals("<") || valor.contentEquals(">=") || valor.contentEquals("<=") || valor.contentEquals("=") || valor.contentEquals("!=")) {
            return 2;
        } else if (valor.contentEquals("e")) {
            return 1;
        } else if (valor.contentEquals("ou")) {
            return 0;
        }
        return -1;// se der erro
    }

    @SuppressWarnings("empty-statement")
    private void verificaEAdicionaPilhaOperadores(Token tokenAux) {

        if (pilhaOperadores.isEmpty() || tokenAux.getLexema().contentEquals("(")) {
            pilhaOperadores.add(tokenAux);
        } else if (tokenAux.getLexema().contentEquals(")")) {
            while (!pilhaOperadores.lastElement().getLexema().contentEquals("(")) {
                Token aux = pilhaOperadores.pop();
                Operador elemento = new Operador();
                elemento.setLexema(aux.getLexema());
                filaPosFixa.add(elemento);

            }
            pilhaOperadores.pop();
        } else {

            int prioridadeTokenAux = getPrioridade(tokenAux.getLexema());

            while (!pilhaOperadores.isEmpty() && !pilhaOperadores.lastElement().getLexema().contentEquals("(")) {

                if (prioridadeTokenAux <= getPrioridade(pilhaOperadores.lastElement().getLexema())) {
                    Token aux = pilhaOperadores.pop();
                    Operador elemento = new Operador();
                    elemento.setLexema(aux.getLexema());
                    filaPosFixa.add(elemento);

                } else {
                    break;
                }

            }

            pilhaOperadores.add(tokenAux);

        }

    }

    @SuppressWarnings("empty-statement")
    private void fimInFixa() {

        for (int i = pilhaOperadores.size(); i > 0; i--) {
            Token aux = pilhaOperadores.pop();
            Operador elemento = new Operador();
            elemento.setLexema(aux.getLexema());
            filaPosFixa.add(elemento);
        }

    }

    private String verificaPosFixa() {

        while (filaPosFixa.size() > 1) {
            int indice = 0;
            while (filaPosFixa.get(indice) instanceof Operando) {
                indice++;
            }

            FuncoesPosFixa posFixa = new FuncoesPosFixa();

            int prioridade = getPrioridade(filaPosFixa.get(indice).getLexema());

            switch (prioridade) {

                case 0:
                    posFixa.trataEOu(filaPosFixa, indice);
                    break;

                case 1:
                    posFixa.trataEOu(filaPosFixa, indice);
                    break;
                case 2:
                    if (filaPosFixa.get(indice).getLexema().contentEquals("!=") || filaPosFixa.get(indice).getLexema().contentEquals("=")) {
                        posFixa.trataIgualDiferente(filaPosFixa, indice);
                    } else {
                        posFixa.trataRelacionais(filaPosFixa, indice);
                    }
                    break;
                case 3:
                    posFixa.trataMultDivSomaSub(filaPosFixa, indice);
                    break;
                case 4:
                    posFixa.trataMultDivSomaSub(filaPosFixa, indice);
                    break;
                case 5:
                    if (filaPosFixa.get(indice).getLexema().contentEquals("nao")) {
                        posFixa.trataNaoUnitario(filaPosFixa, indice);
                    } else {
                        posFixa.trataUnitario(filaPosFixa, indice);
                    }
                    break;

            }

        }

        Operando resposta = (Operando) filaPosFixa.get(0);

        filaPosFixa.clear();;// reseta fila da pos fixa apos fim da expressao

        return resposta.getTipo();

    }

    private void erroTipoExpressao() {
        fraseContendoErro = fraseContendoErro.concat("Linha " + Integer.toString(token.getLinhaCodigo()) + " - Erro Semantico: " + "Tipo da expressao é incompatível com o tipo do comando.");
        errosSintaticos = true;
    }

}
