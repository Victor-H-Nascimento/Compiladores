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
public class TabelaDeSimbolos {
    
    String lexema;
    boolean escopo;
    
    public TabelaDeSimbolos(String lexema, boolean escopo) {
        this.lexema = lexema;
        this.escopo = escopo;
        
    }

    
    
}
