/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maquina.virtual;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.filechooser.FileNameExtensionFilter;

public class InterfaceGrafica extends JFrame{
    private JButton botaoEscolhaArquivo, botaoExecutar;
    private JMenuBar menuBar;
    private JMenu file, executar;
    private JMenuItem abrir, exportar;
    private JPanel painel;
    private JFrame janela;
    private JRadioButton botaoBreakPoint;
    
    public InterfaceGrafica() {
        super("Maquina virtual");
        JFileChooser fileChooser = new JFileChooser();//e possivel escolher uma pasta para inicializar (/home/desktop....)
        fileChooser.setFileFilter(new FileNameExtensionFilter("Arquivos txt", "txt"));//alterar para obj
        int retornoArquivo = fileChooser.showOpenDialog(null);  //abre a pasta para escolher o arquivo
        
        menuBar = new JMenuBar();
        
        //botoes topo da pagina
        botaoEscolhaArquivo = new JButton("Abrir"); 
        ActionListener abrirListener = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(retornoArquivo == JFileChooser.APPROVE_OPTION){ //arquivo selecionado
                    File arquivo = fileChooser.getSelectedFile();
                    //chamar arq.read()   
                }
                else{
                    //fechou janela de selecao de arquivo
                }
            }
        };
        botaoEscolhaArquivo.addActionListener(abrirListener);
        
        
        botaoExecutar = new JButton("Executar");
        ActionListener executarListener = new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //executar
            }
        };
        botaoExecutar.addActionListener(executarListener);
        
        //menu
        //item1 - file
        file = new JMenu("file");
        menuBar.add(file);
        abrir = new JMenuItem("abrir arquivo");
        file.add(abrir);
        exportar = new JMenuItem("exportar arquivo");
        file.add(exportar);
        
        //item2 - botao executar
        menuBar.add(botaoExecutar); //nao sei se e possivel adicionar botao no menubar.
        
        //frame
        JFrame janela = new JFrame();
        janela.add(menuBar);
        janela.setVisible(true);
        
        
        Funcoes c = new Funcoes();
        Arquivo arq = new Arquivo();
        
        
    
    }
    
    public abstract class MenuHandler implements ActionListener, ItemListener {
        public MenuHandler(){
            abrir.addActionListener(this);
            exportar.addActionListener(this);
            botaoExecutar.addActionListener(this);
        }
    };
}

