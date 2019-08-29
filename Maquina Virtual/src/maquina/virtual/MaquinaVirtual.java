package maquina.virtual;

import java.util.ArrayList;
import javax.swing.JFrame;

public class MaquinaVirtual {

    private static String linha;
    //private static Funcoes c;
    //private static Arquivo arq;

    public static void main(String[] args) {

        //Funcoes c = new Funcoes();
        //Arquivo arq = new Arquivo();
        InterfaceInicial inicial = new InterfaceInicial();
        //inicial.setSize(630,500);
        inicial.setResizable(false);
        inicial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inicial.setLocationRelativeTo(null);
        inicial.setVisible(true);
        //Fila filaJMP = new Fila();

    }

    public void leArquivo(String arquivoSelecionado, Funcoes c, Arquivo arq, ArrayList<ListaAuxiliar> filaJMP) {
        arq.Read(arquivoSelecionado, c);
        arq.EnderecaJMP(c, filaJMP);
    }

    public void executarFuncoes(Funcoes c, ArrayList<ListaAuxiliar> filaJMP) {
        String nomeFuncao;
        String primeiroParametro, segundoParametro;
        String aux;

        do {
            String retorno = c.getItemFila(c.getI());
            linha = String.valueOf(retorno);
            System.out.println("Lido " + linha);

            if (linha.contains(" ")) {

                if (linha.contains(",")) {
                    //2 parametros

                    nomeFuncao = linha.split(" ")[0];
                    aux = linha.split(" ")[1];
                    primeiroParametro = aux.split(",")[0];
                    segundoParametro = aux.split(",")[1];
                    System.out.println(nomeFuncao);
                    System.out.println(primeiroParametro);
                    System.out.println(segundoParametro);

                    switch (nomeFuncao) {
                        case "ALLOC":
                            c.ALLOC(Integer.parseInt(primeiroParametro), Integer.parseInt(segundoParametro));
                            break;
                        case "DALLOC":
                            c.DALLOC(Integer.parseInt(primeiroParametro), Integer.parseInt(segundoParametro));
                            break;
                        default:
                            System.err.println("Erro: Nenhuma funcao com 2 parametros foi chamada");
                    }

                } else {
                    //1 parametro

                    nomeFuncao = linha.split(" ")[0];
                    primeiroParametro = linha.split(" ")[1];
                    System.out.println(nomeFuncao);
                    System.out.println(primeiroParametro);

                    if ("NULL".equals(primeiroParametro)) {//Se ler alguma linha de NULL, Ex. L1 NULL
                        c.NULL();
                    } else {
                        switch (nomeFuncao) {
                            case "CALL":
                                //Funcao call nao entra no if, pq nao 

                                for (ListaAuxiliar itemLista : filaJMP) {
                                    if (itemLista.getLabel().equals(primeiroParametro) && itemLista.getInstrucao().contains("CALL")) {
                                        System.out.println("Call para posicao " + itemLista.getIndice());
                                        c.CALL(itemLista.getIndice());
                                    }
                                }
                                break;
                            case "JMP":

                                for (ListaAuxiliar itemLista : filaJMP) {
                                    if (itemLista.getLabel().equals(primeiroParametro) && itemLista.getInstrucao().contains("JMP")) {
                                        c.JMP(itemLista.getIndice());
                                    }
                                }
                                break;
                            case "JMPF":
                                for (ListaAuxiliar itemLista : filaJMP) {
                                    if (itemLista.getLabel().equals(primeiroParametro) && itemLista.getInstrucao().contains("JMPF")) {
                                        c.JMPF(itemLista.getIndice());
                                    }
                                }
                                break;
                            case "LDC":
                                c.LDC(Integer.parseInt(primeiroParametro));
                                break;
                            case "LDV":
                                c.LDV(Integer.parseInt(primeiroParametro));
                                break;
                            case "STR":
                                c.STR(Integer.parseInt(primeiroParametro));
                                break;
                            default:
                                System.out.println("Erro: Nenhuma funcao com 1 parametro foi chamada " + nomeFuncao + " " + primeiroParametro);
                        }
                    }

                }
            } else {
                //sem parametros
                //chamar a funcao
                System.out.println(linha);

                switch (linha) {
                    case "ADD":
                        c.ADD();
                        break;
                    case "AND":
                        c.AND();
                        break;
                    case "CDIF":
                        c.CDIF();
                        break;
                    case "CEQ":
                        c.CEQ();
                        break;
                    case "CMA":
                        c.CMA();
                        break;
                    case "CMAQ":
                        c.CMAQ();
                        break;
                    case "CME":
                        c.CME();
                        break;
                    case "CMEQ":
                        c.CMEQ();
                        break;
                    case "DIVI":
                        c.DIVI();
                        break;
                    case "HLT":
                        c.HLT();
                        break;
                    case "INV":
                        c.INV();
                        break;
                    case "MULT":
                        c.MULT();
                        break;
                    case "NEG":
                        c.NEG();
                        break;
                    case "NULL":
                        c.NULL();
                        break;
                    case "OR":
                        c.OR();
                        break;
                    case "PRN":
                        c.PRN();
                        break;
                    case "RD":
                        c.RD();
                        break;
                    case "RETURN":
                        c.RETURN();
                        break;
                    case "START":
                        c.START();
                        break;
                    case "SUB":
                        c.SUB();
                        break;
                    case "PRINTAPILHA":
                        c.PRINTAPILHA();
                        break;

                    default:
                        System.err.println("Erro: Nenhuma funcao sem parametros foi chamada");
                }
            }

            c.insereNaFila(linha);
            c.setI();
            c.PRINTAPILHA();
            System.out.println("" + "******************************************************************************************" + "");
        } while (!linha.contains("HLT"));
        //sempre que executar uma linha, atualizar o i com a funcao setI 
    }
    
     public String executarFuncoes2(Funcoes c, ArrayList<ListaAuxiliar> filaJMP) {
        String nomeFuncao = null;
        String primeiroParametro, segundoParametro;
        String aux;

        //do {
            String retorno = c.getItemFila(c.getI());
            linha = String.valueOf(retorno);
            System.out.println("Lido " + linha);

            if (linha.contains(" ")) {

                if (linha.contains(",")) {
                    //2 parametros

                    nomeFuncao = linha.split(" ")[0];
                    aux = linha.split(" ")[1];
                    primeiroParametro = aux.split(",")[0];
                    segundoParametro = aux.split(",")[1];
                    System.out.println(nomeFuncao);
                    System.out.println(primeiroParametro);
                    System.out.println(segundoParametro);

                    switch (nomeFuncao) {
                        case "ALLOC":
                            c.ALLOC(Integer.parseInt(primeiroParametro), Integer.parseInt(segundoParametro));
                            break;
                        case "DALLOC":
                            c.DALLOC(Integer.parseInt(primeiroParametro), Integer.parseInt(segundoParametro));
                            break;
                        default:
                            System.err.println("Erro: Nenhuma funcao com 2 parametros foi chamada");
                    }

                } else {
                    //1 parametro

                    nomeFuncao = linha.split(" ")[0];
                    primeiroParametro = linha.split(" ")[1];
                    System.out.println(nomeFuncao);
                    System.out.println(primeiroParametro);

                    if ("NULL".equals(primeiroParametro)) {//Se ler alguma linha de NULL, Ex. L1 NULL
                        c.NULL();
                    } else {
                    
                        switch (nomeFuncao) {
                            case "CALL":
                                //Funcao call nao entra no if, pq nao 
                                
                                for (ListaAuxiliar itemLista : filaJMP) {
                                    if (itemLista.getLabel().equals(primeiroParametro) && itemLista.getInstrucao().contains("CALL")) {
                                        System.out.println("Call para posicao " + itemLista.getIndice());
                                        c.CALL(itemLista.getIndice());
                                    }
                                }
                                break;
                            case "JMP":

                                for (ListaAuxiliar itemLista : filaJMP) {
                                    if (itemLista.getLabel().equals(primeiroParametro) && itemLista.getInstrucao().contains("JMP")) {
                                        c.JMP(itemLista.getIndice());
                                    }
                                }
                                break;
                            case "JMPF":
                                for (ListaAuxiliar itemLista : filaJMP) {
                                    if (itemLista.getLabel().equals(primeiroParametro) && itemLista.getInstrucao().contains("JMPF")) {
                                        c.JMPF(itemLista.getIndice());
                                    }
                                }
                                break;
                            case "LDC":
                                c.LDC(Integer.parseInt(primeiroParametro));
                                break;
                            case "LDV":
                                c.LDV(Integer.parseInt(primeiroParametro));
                                break;
                            case "STR":
                                c.STR(Integer.parseInt(primeiroParametro));
                                break;
                            default:
                                System.out.println("Erro: Nenhuma funcao com 1 parametro foi chamada " + nomeFuncao + " " + primeiroParametro);
                        }
                    }

                }
            } else {
                //sem parametros
                //chamar a funcao
                System.out.println(linha);
                nomeFuncao = linha;
                
                switch (linha) {
                    case "ADD":
                        c.ADD();
                        break;
                    case "AND":
                        c.AND();
                        break;
                    case "CDIF":
                        c.CDIF();
                        break;
                    case "CEQ":
                        c.CEQ();
                        break;
                    case "CMA":
                        c.CMA();
                        break;
                    case "CMAQ":
                        c.CMAQ();
                        break;
                    case "CME":
                        c.CME();
                        break;
                    case "CMEQ":
                        c.CMEQ();
                        break;
                    case "DIVI":
                        c.DIVI();
                        break;
                    case "HLT":
                        c.HLT();
                        break;
                    case "INV":
                        c.INV();
                        break;
                    case "MULT":
                        c.MULT();
                        break;
                    case "NEG":
                        c.NEG();
                        break;
                    case "NULL":
                        c.NULL();
                        break;
                    case "OR":
                        c.OR();
                        break;
                    case "PRN":
                        c.PRN();
                        break;
                    case "RD":
                        c.RD();
                        break;
                    case "RETURN":
                        c.RETURN();
                        break;
                    case "START":
                        c.START();
                        break;
                    case "SUB":
                        c.SUB();
                        break;
                    case "PRINTAPILHA":
                        c.PRINTAPILHA();
                        break;

                    default:
                        System.err.println("Erro: Nenhuma funcao sem parametros foi chamada");
                }
            }

            c.insereNaFila(linha);
            c.setI();
            c.PRINTAPILHA();
            System.out.println("" + "******************************************************************************************" + "");
        //} while (!linha.contains("HLT"));
        //sempre que executar uma linha, atualizar o i com a funcao setI 
        
          return nomeFuncao;
    }
    
    
}
