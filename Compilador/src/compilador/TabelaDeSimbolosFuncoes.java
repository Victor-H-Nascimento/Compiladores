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

    public TabelaDeSimbolosFuncoes(String lexema, boolean escopo, String tipo) {
        super(lexema, escopo);
        this.tipo = tipo;
    }
    
}
