/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maquina.virtual;

/**
 *
 * @author victor
 */
public interface EncapsulamentoFuncoes {
    
    public abstract void TESTE(int x);
    
    public abstract void LDC(int k);
    public abstract void LDV(int n);
    public abstract void ADD();
    public abstract void SUB();
    public abstract void MULT();
    public abstract void DIVI();
    public abstract void INV();
    public abstract void AND();
    public abstract void OR();
    public abstract void NEG();
    public abstract void CME();
    public abstract void CMA();
    public abstract void CEQ();
    public abstract void CDIF();
    public abstract void CMEQ();
    public abstract void CMAQ();
    public abstract void START();
    public abstract void HLT();
    public abstract void STR(int n);
    public abstract void JMP(int t);
    public abstract void JMPF(int t);
    public abstract void NULL();
    public abstract void RD();
    public abstract void PRN();
    public abstract void ALLOC(int m, int n);
    public abstract void DALLOC(int m, int n);
    public abstract void CALL(int t);
    public abstract void RETURN();
    
}
