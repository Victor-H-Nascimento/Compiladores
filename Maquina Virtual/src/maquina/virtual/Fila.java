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
            System.out.println(this.fila.get(j));
        }
  }
    
    public void insere(Object x) {
        this.fila.add(x);
  }  
  
  public int avancaPara(int t) {
        return t;
  }  
    
  
  public Object retornaLinha(int x) {
        return this.fila.get(x); 
  }
    
}
