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
public class TabelaDeSimbolosProgramaProcedimentos extends TabelaDeSimbolos {
    
    public TabelaDeSimbolosProgramaProcedimentos(String lexema) {
        this.setLexema(lexema);
        this.setEscopo(true);
    }
    
}
