/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

/**
 *
 * @author 16000465
 */
public class TabelaDeSimbolosVariaveis extends TabelaDeSimbolos {
   
  /* private String lexema;
   private boolean escopo;*/
    private String tipo;
   private int memoria;    //endereço

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getMemoria() {
        return memoria;
    }

    public void setMemoria(int memoria) {
        this.memoria = memoria;
    }

    public TabelaDeSimbolosVariaveis(String lexema, String tipo, int memoria) {//  vai usar esse construtor?
        this.setLexema(lexema);
        this.setEscopo(false);
        this.tipo = tipo;
        this.memoria = memoria;
    }

    public TabelaDeSimbolosVariaveis(String lexema) {
        this.setLexema(lexema);
        this.setEscopo(false);
    }
    
    

   
    
    
    
   
    
}
