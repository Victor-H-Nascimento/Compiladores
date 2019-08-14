
package maquina.virtual;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author victor
 */
public class Arquivo {

   

    public Arquivo() {//construtor
    }
    
   
    
     public void Read(String Caminho, Funcoes c){
        
        try {
            FileReader arq = new FileReader(Caminho);
            BufferedReader lerArq = new BufferedReader(arq);
            String linha="";
            try {
                
                do{
                linha = lerArq.readLine();
               
                c.insereNaFila(linha);
                    System.err.println(linha);
                }while(!linha.contains("HLT"));

                arq.close();
                
            } catch (IOException ex) {
                System.out.println("Erro: Não foi possível ler o arquivo!");
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Erro: Arquivo não encontrado!");
        }
    }
    
     
     public void EnderecaJMP(Funcoes c, Fila fila,int tamanho){
     
         
         for (int x = 0; x < tamanho; x++) {
          Object retorno = c.anda(x);
          String linhaUnica = String.valueOf(retorno);
          
           /*
                Transformar os parametros L%d (Ex. L3) em um valor inteiro que aponte para o indice da fila principal
                Valor de L%d esta em label
                */
                
                    if (linhaUnica.contains("JMP")) {
                         String label = linhaUnica.split(" ")[1];
                         
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
