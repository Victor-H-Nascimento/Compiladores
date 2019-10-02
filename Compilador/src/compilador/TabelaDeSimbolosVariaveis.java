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
    String tipo;
    int memoria;    //endereço

    public TabelaDeSimbolosVariaveis(String lexema, boolean escopo, String tipo, int memoria) {
        super(lexema, escopo);
        this.tipo = tipo;
        this.memoria = memoria;
    }
    
}
