package maquina.virtual;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author victor
 */
public class Arquivo {

    public Arquivo() {//construtor
    }

    public void Read(String Caminho, Funcoes c) {

        try {
            FileReader arq = new FileReader(Caminho);
            BufferedReader lerArq = new BufferedReader(arq);
            String linha = "";
            try {

                do {
                    linha = lerArq.readLine();

                    c.insereNaFila(linha);
                } while (!linha.contains("HLT"));

                arq.close();

            } catch (IOException ex) {
                System.out.println("Erro: Não foi possível ler o arquivo!");
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Erro: Arquivo não encontrado!");
        }
    }

    public void EnderecaJMP(Funcoes c, ArrayList<ListaAuxiliar> fila) {

        for (int x = 0; x < c.tamanhoFila(c); x++) {
            String linhaComJMP = c.getItemFila(x);

            /*
                Transformar os parametros L%d (Ex. L3) em um valor inteiro que aponte para o indice da fila principal
                Valor de L%d esta em label
             */
            if (linhaComJMP.contains("JMP") || linhaComJMP.contains("JMPF")) {
                String label = linhaComJMP.split(" ")[1];

                for (int i = 0; i < c.tamanhoFila(c); i++) {
                    String a = c.getItemFila(i);

                    if (a.startsWith(label.concat(" "))) {
                        ListaAuxiliar struct = new ListaAuxiliar();
                        struct.setLabel(label);
                        struct.setIndice(i);
                        System.out.println(struct.getLabel() + " " + struct.getIndice());
                        fila.add(struct);
                    }
                }

            } 

        }

    }

    /*public static boolean Write(String Caminho,String Texto){
        try {
            FileWriter arq = new FileWriter(Caminho);
            PrintWriter gravarArq = new PrintWriter(arq);
            gravarArq.println(Texto);
            gravarArq.close();
            return true;
        }catch(IOException e){
            System.out.println(e.getMessage());
            return false;
        }
    }*/
}
