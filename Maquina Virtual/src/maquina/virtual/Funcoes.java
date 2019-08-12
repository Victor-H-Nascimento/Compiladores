/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maquina.virtual;
import java.util.Scanner;
/**
 *
 * @author victor
 */
public class Funcoes implements EncapsulamentoFuncoes {

   
    
    //atributos
    private int s;
    Pilha pilha = new Pilha();
    Scanner scanner = new Scanner(System.in);
    //construtor
     public Funcoes() 
     {
      
     }
     
     
     
    //getters e setter

     
    //metodos abstratos
     
     @Override
    public void TESTE(int x) {
         System.out.println("Tamanho da PIlha = " + x);
    }
     
     
    @Override
    public void LDC(int k) {//carrega constante
        //S:=s + 1 ; M [s]: = k 
        this.s = this.pilha.topo() + 1;//posicao do Topo
        this.pilha.insere(k); //M[s]: = k  
        System.out.println(k);
    }

    @Override
    public void LDV(int n) {//carrega valor
        //S:=s + 1 ; M [s]: = M[n] 
        this.s = this.pilha.topo() + 1;//posicao do Topo
        int aux = this.pilha.busca(n);
        this.pilha.insere(aux); //M[s]: = M[n]  
    }

    @Override
    public void ADD() {  //somar  
    //M[s-1]:=M[s-1] + M[s]; s:=s - 1
    int primeiroValor = this.pilha.busca(s);
    int segundoValor = this.pilha.busca(s-1);
    
    int soma = primeiroValor + segundoValor;
    
    this.pilha.remove();//remove o topo
    this.pilha.remove();//duas vezes para tirar os 2 valores somados
    
    this.pilha.insere(soma);
    
    this.s = this.pilha.topo(); //atualiza s
    
    }

    @Override
    public void SUB() {
        //M[s-1]:=M[s-1] - M[s]; s:=s - 1
    int primeiroValor = this.pilha.busca(s);
    int segundoValor = this.pilha.busca(s-1);
    
    int subtracao = primeiroValor - segundoValor;
    
    this.pilha.remove();//remove o topo
    this.pilha.remove();//duas vezes para tirar os 2 valores somados
    
    this.pilha.insere(subtracao);
    
    this.s = this.pilha.topo(); //atualiza s
    }

    @Override
    public void MULT() {
        //M[s-1]:=M[s-1] * M[s]; s:=s - 1
    int primeiroValor = this.pilha.busca(s);
    int segundoValor = this.pilha.busca(s-1);
    
    int multiplicacao = primeiroValor * segundoValor;
    
    this.pilha.remove();//remove o topo
    this.pilha.remove();//duas vezes para tirar os 2 valores somados
    
    this.pilha.insere(multiplicacao);
    
    this.s = this.pilha.topo(); //atualiza s
    }

    @Override
    public void DIVI() {
        //M[s-1]:=M[s-1] / M[s]; s:=s - 1
    int primeiroValor = this.pilha.busca(s-1);
    int segundoValor = this.pilha.busca(s);
    
    int divisao = primeiroValor / segundoValor;
    
    this.pilha.remove();//remove o topo
    this.pilha.remove();//duas vezes para tirar os 2 valores somados
    
    this.pilha.insere(divisao);
    
    this.s = this.pilha.topo(); //atualiza s
    }

    @Override
    public void INV() {
    // M[s]:= -M[s] 
        int primeiroValor = this.pilha.busca(s);
        int valor = primeiroValor * -1;//inverte
        this.pilha.remove();//remove o topo
        this.pilha.insere(valor);
        
    }

    @Override
    public void AND() {
        //se M [s-1] = 1 e M[s] = 1  então M[s-1]:=1  senão M[s-1]:=0;  s:=s - 1 
    int primeiroValor = this.pilha.busca(s);
    int segundoValor = this.pilha.busca(s-1);
   
    this.pilha.remove();//remove o topo
    this.pilha.remove();//duas vezes para tirar os 2 valores 
    
    if(primeiroValor == segundoValor && primeiroValor == 1)
    {
        this.pilha.insere(primeiroValor);
    }
    
    else{
        if(segundoValor == 0)
        {
             this.pilha.insere(segundoValor);
        }
        
        else{
            this.pilha.insere(primeiroValor);
        }
    }
        this.s = this.pilha.topo(); //atualiza s
    
    }

    @Override
    public void OR() {
    //e M[s-1] = 1  ou M[s] = 1  então M[s-1]:=1  senão M[s-1]:=0; s:=s - 1 
    
    int primeiroValor = this.pilha.busca(s);
    int segundoValor = this.pilha.busca(s-1);
   
    this.pilha.remove();//remove o topo
    this.pilha.remove();//duas vezes para tirar os 2 valores 
    
    if(primeiroValor == 1 )
    {
        this.pilha.insere(primeiroValor);
    }
    
    else{
        this.pilha.insere(segundoValor);   
    }
        this.s = this.pilha.topo(); //atualiza s
    }

    @Override
    public void NEG() {
        // M[s]:=1 - M[s] 
        int primeiroValor = this.pilha.busca(s);
        int valor = 1- primeiroValor;//inverte valores binarios
        this.pilha.remove();//remove o topo
        this.pilha.insere(valor);
        
        
    }

    @Override
    public void CME() {
        //se M[s-1] < M[s]  então M[s-1]:=1  senão M[s-1]:=0; s:=s - 1 
        
    int primeiroValor = this.pilha.busca(s);
    int segundoValor = this.pilha.busca(s-1);
    
    this.pilha.remove();//remove o topo
    this.pilha.remove();//duas vezes para tirar os 2 valores somados
    
    if(segundoValor < primeiroValor)
    {
        this.pilha.insere(1);
    }
        
    else{
        this.pilha.insere(0);
    }
    
    this.s = this.pilha.topo(); //atualiza s
    
    }

    @Override
    public void CMA() {
        //se M[s-1] > M[s]  então M[s-1]:=1  senão M[s-1]:=0; s:=s - 1
    int primeiroValor = this.pilha.busca(s);
    int segundoValor = this.pilha.busca(s-1);
    
    this.pilha.remove();//remove o topo
    this.pilha.remove();//duas vezes para tirar os 2 valores somados
    
    if(segundoValor > primeiroValor)
    {
        this.pilha.insere(1);
    }
        
    else{
        this.pilha.insere(0);
    }
    
    this.s = this.pilha.topo(); //atualiza s
    }

    @Override
    public void CEQ() {
        //se M[s-1] = M[s]  então M[s-1]:=1  senão M[s-1]:=0; s:=s - 1
        
        int primeiroValor = this.pilha.busca(s);
    int segundoValor = this.pilha.busca(s-1);
    
    this.pilha.remove();//remove o topo
    this.pilha.remove();//duas vezes para tirar os 2 valores somados
    
    if(primeiroValor == segundoValor)
    {
        this.pilha.insere(1);
    }
        
    else{
        this.pilha.insere(0);
    }
    
    this.s = this.pilha.topo(); //atualiza s
    }

    @Override
    public void CDIF() {
        //se M[s-1] != M[s]  então M[s-1]:=1  senão M[s-1]:=0; s:=s - 1
        
        int primeiroValor = this.pilha.busca(s);
    int segundoValor = this.pilha.busca(s-1);
    
    this.pilha.remove();//remove o topo
    this.pilha.remove();//duas vezes para tirar os 2 valores somados
    
    if(primeiroValor != segundoValor)
    {
        this.pilha.insere(1);
    }
        
    else{
        this.pilha.insere(0);
    }
    
    this.s = this.pilha.topo(); //atualiza s
    }

    @Override
    public void CMEQ() {
        //se M[s-1] <= M[s]  então M[s-1]:=1  senão M[s-1]:=0; s:=s - 1
        
    int primeiroValor = this.pilha.busca(s);
    int segundoValor = this.pilha.busca(s-1);
    
    this.pilha.remove();//remove o topo
    this.pilha.remove();//duas vezes para tirar os 2 valores somados
    
    if(segundoValor <= primeiroValor )
    {
        this.pilha.insere(1);
    }
        
    else{
        this.pilha.insere(0);
    }
    
    this.s = this.pilha.topo(); //atualiza s
    }

    @Override
    public void CMAQ() {
           //se M[s-1] >= M[s]  então M[s-1]:=1  senão M[s-1]:=0; s:=s - 1
        
    int primeiroValor = this.pilha.busca(s);
    int segundoValor = this.pilha.busca(s-1);
    
    this.pilha.remove();//remove o topo
    this.pilha.remove();//duas vezes para tirar os 2 valores somados
    
    if(segundoValor >= primeiroValor )
    {
        this.pilha.insere(1);
    }
        
    else{
        this.pilha.insere(0);
    }
    
    this.s = this.pilha.topo(); //atualiza s
    }

    @Override
    public void START() {
        this.s = -1;
    }

    @Override
    public void HLT() {
        //  “Para a execução da MVD”
        //como parar?
    }

    @Override
    public void STR(int n) {
        //M[n]:=M[s]; s:=s-1
    int primeiroValor = this.pilha.busca(s);
    this.pilha.remove();//remove o topo
    this.pilha.armazena(n,primeiroValor);
    this.s = this.pilha.topo(); //atualiza s
    }

    @Override
    public void JMP(int t) {
    }

    @Override
    public void JMPF(int t) {
    }

    @Override
    public void NULL() {
    }

    @Override
    public void RD() {
        this.s = this.pilha.topo() + 1; //atualiza s
        System.out.println("Entrada de dados: ");
        this.pilha.insere(scanner.nextInt());// entrada de dados
        //confirmar se precisa diferenciar bool e int
    }

    @Override
    public void PRN() {
        
        int primeiroValor = this.pilha.retornaTopo();
        System.out.println(primeiroValor);
        this.pilha.remove();//remove o topo
        this.s = this.pilha.topo(); //atualiza s
    }

    @Override
    public void ALLOC() {
    }

    @Override
    public void DALLOC() {
    }

    @Override
    public void CALL() {
    }

    @Override
    public void RETURN() {
    }
    
    
    
    
}
