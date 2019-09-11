package compilador;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author victor
 */
public class Arquivo {

    private FileReader arq;
    private BufferedReader lerArq;

    public Arquivo() {//construtor

    }

    public void Open(String caminho, Funcoes c) {

        try {
            this.arq = new FileReader(caminho);

        } catch (FileNotFoundException ex) {
            System.out.println("Erro: Arquivo não encontrado!");
        }
    }

    public int Read() throws IOException {
        lerArq = new BufferedReader(arq);
        return this.lerArq.read();
    }
    
    
     public void Ler(String Caminho, Funcoes c) {

        try {
            FileReader arq = new FileReader(Caminho);
            BufferedReader lerArq = new BufferedReader(arq);
            int linha;
            String linhaInserir = "";
            try {

                do {
                    
                    linha = lerArq.read();
                    c.leArquivo(Character.toString((char) linha));
                    
                } while (linha != -1);

                arq.close();

            } catch (IOException ex) {
                System.out.println("Erro: Não foi possível ler o arquivo!");
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Erro: Arquivo não encontrado!");
        }
    }

}
