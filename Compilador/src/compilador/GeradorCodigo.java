/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author victor
 */
public class GeradorCodigo {

    PrintWriter gravarArq;
    FileWriter arq;

    public GeradorCodigo() throws IOException {
        this.arq = new FileWriter("saidaCompilador.txt");
        this.gravarArq = new PrintWriter(this.arq);
    }

    public void geraLDC(int valor) {
        EscreveAssembly("LDC " + valor);
    }

    public void geraLDV(int valor) {
        EscreveAssembly("LDV " + valor);
    }

    public void geraADD() {
        EscreveAssembly("ADD");
    }

    public void geraSUB() {
        EscreveAssembly("SUB");
    }

    public void geraMULT() {
        EscreveAssembly("MULT");
    }

    public void geraDIVI() {
        EscreveAssembly("DIVI");
    }

    public void geraINV() {
        EscreveAssembly("INV");
    }

    public void geraAND() {
        EscreveAssembly("AND");
    }

    public void geraOR() {
        EscreveAssembly("OR");
    }

    public void geraNEG() {
        EscreveAssembly("NEG");
    }

    public void geraCME() {
        EscreveAssembly("CME");
    }

    public void geraCMA() {
        EscreveAssembly("CMA");
    }

    public void geraCEQ() {
        EscreveAssembly("CEQ");
    }

    public void geraCDIF() {
        EscreveAssembly("CDIF");
    }

    public void geraCMEQ() {
        EscreveAssembly("CMEQ");
    }

    public void geraCMAQ() {
        EscreveAssembly("CMAQ");
    }

    public void geraSTART() {
        EscreveAssembly("START");
    }

    public void geraHLT() {
        EscreveAssembly("HLT");
    }

    public void geraSTR(int valor) {
        EscreveAssembly("STR " + valor);
    }

    public void geraJMP(int valor) {
        EscreveAssembly("STR " + valor);
    }

    public void geraJMPF(int valor) {
        EscreveAssembly("STR " + valor);
    }

    public void geraNULL(int valor) {
        EscreveAssembly("L" + valor + " NULL");
    }

    public void geraRD() {
        EscreveAssembly("RD");
    }

    public void geraPRN() {
        EscreveAssembly("PRN");
    }

    public void geraALLOC(int indiceInicial, int quantidade) {
        EscreveAssembly("ALLOC " + indiceInicial + "," + quantidade);
    }

    public void geraDALLOC(int indiceInicial, int quantidade) {
        EscreveAssembly("DALLOC " + indiceInicial + "," + quantidade);
    }

    public void geraCALL(int valor) {
        EscreveAssembly("CALL " + valor);
    }

    public void geraRETURN() {
        EscreveAssembly("RETURN");
    }

    public void EscreveAssembly(String Texto) {
        gravarArq.println(Texto.concat("\n"));
    }

}
