/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maquina.virtual;

import java.util.ArrayList;

/**
 *
 * @author victor
 */
public class Fila {
    
    ArrayList<Object> fila = new ArrayList();

    public ArrayList<Object> getFila() {
        return fila;
    }

    public void setFila(ArrayList<Object> fila) {
        this.fila = fila;
    }
    
    public void percorre() {
    
        for (int j =0; j< this.fila.size(); j++) {
            System.out.println("Percorre "+this.fila.get(j));
        }
  }
    
     public String getItemFila(int j) {
        return String.valueOf(this.fila.get(j));
    }
     
     
    
    public int tamanhoFila() {
    return this.fila.size();
  }
    
    public void insere(Object x) {
        this.fila.add(x);
  }  
  
  public int avancaPara(int t) {
        return t;
  }  
   
  
    
}
