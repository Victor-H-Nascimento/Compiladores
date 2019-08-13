package maquina.virtual;

public class MaquinaVirtual {

    private static String linha;

    public static void main(String[] args) {
        
        Funcoes c = new Funcoes();
        Arquivo arq = new Arquivo();
 
        arq.Read("/home/victor/Área de Trabalho/assembly.txt");
        

        //arq.getFilaAuxiliar();
        do{
       Object retorno = arq.anda(c.getI());
       linha = String.valueOf(retorno);
        System.out.println(linha);
        c.setI();
        } while(!linha.contains("HLT"));
        
        
            


        //System.out.println(conteudo);
        //String c1 = conteudo.split("")[0];
        //String c2 = conteudo.split(";")[1];
        //String c3 = conteudo.split(";")[2];
        
        //System.out.println(c1);
        
       /* c.START();
        c.setI();
        c.RD();
        c.setI();
        c.RD();
        c.setI();
        c.CME();
        c.setI();
        c.PRN();
        c.setI();*/
        //c.JMP(10);
        
        
        //sempre que executar uma linha, atualizar o i com a funcao setI
        
       // c.TESTE();
    }
    
}
