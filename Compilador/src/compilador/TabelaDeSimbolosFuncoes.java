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
public class TabelaDeSimbolosFuncoes extends TabelaDeSimbolos {
    String tipo;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    
    public TabelaDeSimbolosFuncoes(String lexema, String tipo) {//  vai usar esse construtor?
        this.setLexema(lexema);
        this.setEscopo(true);
        this.tipo = tipo;
    }

    public TabelaDeSimbolosFuncoes(String lexema) {
        this.setLexema(lexema);
        this.setEscopo(true);
    }
    
    
    
}
