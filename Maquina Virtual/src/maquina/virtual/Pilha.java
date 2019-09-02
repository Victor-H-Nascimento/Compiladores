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

    public Pilha() {
    }
    
    

    public void insere(Object objeto) {
        this.pilha.add(objeto);
    }
    
    public Pilha retornaPilha()
    {
        return this;
    }
    
    public void inserePosicaoEspecifica(Object objeto,int x) {
        this.pilha.add(x, objeto);
    }

    public Object remove() {
        return this.pilha.pop();
    }

    public boolean vazia() {
        return this.pilha.isEmpty();
    }

    public int topo() {
        return this.pilha.size() - 1;
    }

    public int busca(int num) {
        return (int) this.pilha.elementAt(num);
    }

    public int retornaTopo() {
        return (int) this.pilha.elementAt(topo());
    }

    public void armazena(int n, int valor) {
        this.pilha.removeElementAt(n);
        //tira item da posicao n
        this.pilha.insertElementAt(valor, n);// adiciona novamente na posicao n
    }

    public void escrever(String frase) {
        System.out.println(frase);
    }

    public int procuraM(int t) {
        return (int) this.pilha.elementAt(t);
    }
    
    public int verificaInsercao(Pilha pilha, int indice){
        //recebe o indice inicial e verifica se o indice aumentou
        if((this.pilha.size()-1) == indice){    //nada foi inserido
            return 0;
        }
        return 1;   //algo ja foi inserido
    }

}
