
package maquina.virtual;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author victor
 */
public class Arquivo {

    private Fila filaAuxiliar = new Fila();

    public Fila getFilaAuxiliar() {
        return filaAuxiliar;
    }

    public void setFilaAuxiliar(Fila filaAuxiliar) {
        this.filaAuxiliar = filaAuxiliar;
    }
    
    
    public Arquivo() {//construtor
    }
    
    public Object anda(int t){
       return this.filaAuxiliar.retornaLinha(t);
    }
    
     public void Read(String Caminho){
        
        try {
            FileReader arq = new FileReader(Caminho);
            BufferedReader lerArq = new BufferedReader(arq);
            String linha="";
            try {
                
                do{
                linha = lerArq.readLine();
                filaAuxiliar.insere(linha);
                }while(!linha.contains("HLT"));

                arq.close();
                
            } catch (IOException ex) {
                System.out.println("Erro: Não foi possível ler o arquivo!");
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Erro: Arquivo não encontrado!");
        }
    }
    
    public static boolean Write(String Caminho,String Texto){
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
    }
}
