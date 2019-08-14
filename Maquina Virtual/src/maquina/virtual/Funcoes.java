//A fazer:
// Verificar valores de S, pois caso valor seja -1, nenhuma acao de remover pode acontecer
//Entender e consertar funcoes Alloc e Dalloc
//Pensar em como fazer os Labels da funcao NULL
//Pensar em como estruturar o JMP e o JMPF

package maquina.virtual;
import java.util.Scanner;
/**
 *
 * @author victor
 */
public class Funcoes implements EncapsulamentoFuncoes {

   
    
    //atributos
    private int s;//topo da plha
    private int i;//indice proxima instrucao
    private Pilha pilha = new Pilha();
    private Fila fila = new Fila();
    private Scanner scanner = new Scanner(System.in);
    //construtor
     public Funcoes() 
     {
      this.i = 0;
     }
     
     
     //getters e setter
    public int getI() {
        return i;
    }

    
    public void setI() {
        this.i = this.i + 1;
    }

    
    
    //metodos da fila
    
    public void insereNaFila(Object x) {
        this.fila.insere(x);
  }
    
    public int tamanhoFila(Object x) {
        return this.fila.tamanhoFila();
  }  
    
     public Object anda(int t){
       return this.fila.retornaLinha(t);
    }
    
    //metodos abstratos
    @Override
    public void TESTE() {
        System.out.println("Pilha = " + this.s);
        System.out.println("Fila = " + this.i);
    }
    
     @Override
    public void PRINTAPILHA() {
         for (int j = this.s; j >= 0; j--) {
             PRN();
         }
  
    }
    
    
    
     
    @Override
    public void LDC(int k) {//carrega constante
        //S:=s + 1 ; M [s]: = k 
        this.s = this.pilha.topo() + 1;//posicao do Topo
        this.pilha.insere(k); //M[s]: = k  
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
    int primeiroValor = this.pilha.retornaTopo();
     this.pilha.remove();//remove o topo
    int segundoValor = this.pilha.retornaTopo();
    this.pilha.remove();//duas vezes para tirar os 2 valores somados
    
    int soma = primeiroValor + segundoValor;
    
    this.pilha.insere(soma);
    
    this.s = this.pilha.topo(); //atualiza s
    
    }

    @Override
    public void SUB() {
        //M[s-1]:=M[s-1] - M[s]; s:=s - 1
    int primeiroValor = this.pilha.retornaTopo();
     this.pilha.remove();//remove o topo
    int segundoValor = this.pilha.retornaTopo();
    this.pilha.remove();//duas vezes para tirar os 2 valores somados
    
    int subtracao = primeiroValor - segundoValor;
    
    this.pilha.insere(subtracao);
    
    this.s = this.pilha.topo(); //atualiza s
    }

    @Override
    public void MULT() {
        //M[s-1]:=M[s-1] * M[s]; s:=s - 1
    int primeiroValor = this.pilha.retornaTopo();
     this.pilha.remove();//remove o topo
    int segundoValor = this.pilha.retornaTopo();
    this.pilha.remove();//duas vezes para tirar os 2 valores somados
    
    int multiplicacao = primeiroValor * segundoValor;
    
    this.pilha.insere(multiplicacao);
    
    this.s = this.pilha.topo(); //atualiza s
    }

    @Override
    public void DIVI() {
        //M[s-1]:=M[s-1] / M[s]; s:=s - 1
        
    int primeiroValor = this.pilha.retornaTopo();
    this.pilha.remove();//remove o topo
    int segundoValor = this.pilha.retornaTopo();
    this.pilha.remove();//duas vezes para tirar os 2 valores somados
    
    int divisao = primeiroValor / segundoValor;
    
    this.pilha.insere(divisao);
    
    this.s = this.pilha.topo(); //atualiza s
    }

    @Override
    public void INV() {
    // M[s]:= -M[s] 
        int primeiroValor = this.pilha.retornaTopo();
        int valor = primeiroValor * -1;//inverte
        this.pilha.remove();//remove o topo
        this.pilha.insere(valor);
        
    }

    @Override
    public void AND() {
        //se M [s-1] = 1 e M[s] = 1  então M[s-1]:=1  senão M[s-1]:=0;  s:=s - 1 
    int primeiroValor = this.pilha.retornaTopo();
    this.pilha.remove();//remove o topo
    int segundoValor = this.pilha.retornaTopo();
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
    
    int primeiroValor = this.pilha.retornaTopo();
    this.pilha.remove();//remove o topo
    int segundoValor = this.pilha.retornaTopo();
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
        
    int primeiroValor = this.pilha.retornaTopo();
    this.pilha.remove();//remove o topo
    int segundoValor = this.pilha.retornaTopo();
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
    int primeiroValor = this.pilha.retornaTopo();
    this.pilha.remove();//duas vezes para tirar os 2 valores somados
    int segundoValor = this.pilha.retornaTopo();
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
        
    int primeiroValor = this.pilha.retornaTopo();
    this.pilha.remove();//duas vezes para tirar os 2 valores somados
    int segundoValor = this.pilha.retornaTopo();
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
        
    int primeiroValor = this.pilha.retornaTopo();
    this.pilha.remove();//duas vezes para tirar os 2 valores somados
    int segundoValor = this.pilha.retornaTopo();
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
        
    int primeiroValor = this.pilha.retornaTopo();
    this.pilha.remove();//duas vezes para tirar os 2 valores somados
    int segundoValor = this.pilha.retornaTopo();
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
        
    int primeiroValor = this.pilha.retornaTopo();
    this.pilha.remove();//duas vezes para tirar os 2 valores somados
    int segundoValor = this.pilha.retornaTopo();
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
    int primeiroValor = this.pilha.retornaTopo();
    this.pilha.remove();//remove o topo
    this.pilha.armazena(n,primeiroValor);
    this.s = this.pilha.topo(); //atualiza s
    }

    @Override
    public void JMP(int t) {
        this.i = this.fila.avancaPara(t);
    }

    @Override
    public void JMPF(int t) {
        int primeiroValor = this.pilha.retornaTopo();
        
        if (primeiroValor == 0) {
            this.i = this.fila.avancaPara(t);
        }
        
        else{
            this.i = this.i + 1;
        }
    this.pilha.remove();//remove o topo
    this.s = this.pilha.topo(); //atualiza s
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
        System.out.println(" s-> " + this.s + "       " + "|" + primeiroValor + "|" );
        this.pilha.remove();//remove o topo
        this.s = this.pilha.topo(); //atualiza s
    }

    @Override
    public void ALLOC(int m, int n) {
        //ALLOC     m,n      (Alocar memória): Para k:=0 até n-1 faça {s:=s + 1; M[s]:=M[m+k]}
        for (int k = 0; k <= n - 1; k++) {
            this.s = this.pilha.topo() + 1;
            int aux = this.pilha.procuraM(m + k);
            this.pilha.insere(aux);  
        }
    }

    @Override
    public void DALLOC(int m, int n) {
        //DALLOC  m,n      (Desalocar memória): Para  k:=n-1  até 0  faça       {M[m+k]:=M[s]; s:=s - 1} 
        for (int k = n-1; k > 0; k--) {
            int aux = this.pilha.retornaTopo();
            this.pilha.armazena(m+k,aux);
            this.pilha.remove();
            this.s = this.pilha.topo();
        }
    }

    @Override
    public void CALL(int t) {
        //CALL   t   (Chamar procedimento ou função):  S:=s + 1; M[s]:=i + 1; i:=t 
         this.s = this.pilha.topo() + 1;
         this.pilha.insere(this.i + 1);
         this.i = this.fila.avancaPara(t);
    }

    @Override
    public void RETURN() {
        this.i = this.pilha.topo();
        this.pilha.remove();
        this.s = this.pilha.topo();
    }
    
    
    
    
}
