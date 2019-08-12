/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maquina.virtual;

import java.util.Stack;

/**
 *
 * @author victor
 */
public class Pilha {
    
     Stack pilha = new Stack();
     
  public void insere(Object objeto) {
    this.pilha.add(objeto);
  }

  public Object remove() {
    return this.pilha.remove(this.pilha.size() - 1);
  }

  public boolean vazia() {
    return this.pilha.isEmpty();
  }
  
  public int topo() {
    return this.pilha.size()-1;
  }
  
   public int busca(int num) {
       
    return (int) this.pilha.elementAt(num);
  }
  
   public int retornaTopo(){
       return (int) this.pilha.elementAt(topo());
   }
   
   
   public void armazena(int n, int valor) {
     this.pilha.add(n, valor);
  }

   public void escrever(String frase) {
        System.out.println(frase);
    }
  
}