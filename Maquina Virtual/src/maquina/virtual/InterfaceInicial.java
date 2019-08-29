/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maquina.virtual;

import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author victor
 */
public class InterfaceInicial extends javax.swing.JFrame {

    /**
     * Creates new form InterfaceInicial
     */
    public InterfaceInicial() {
        initComponents();
        textFIeldEntrada.setEnabled(false);
        textFieldSaida.setEnabled(false);
        botaoExecutar.setEnabled(false);
        botaoContinuar.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        textFIeldEntrada = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        textFieldSaida = new javax.swing.JTextArea();
        botaoExecutar = new javax.swing.JButton();
        botaoContinuar = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        menuFileAbrir = new javax.swing.JMenuItem();
        menuFileExportar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Entrada:");

        jLabel2.setText("Saida:");

        textFIeldEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFIeldEntradaActionPerformed(evt);
            }
        });

        textFieldSaida.setColumns(20);
        textFieldSaida.setRows(5);
        jScrollPane5.setViewportView(textFieldSaida);

        botaoExecutar.setText("Executar");
        botaoExecutar.setMaximumSize(new java.awt.Dimension(100, 29));
        botaoExecutar.setMinimumSize(new java.awt.Dimension(100, 29));
        botaoExecutar.setPreferredSize(new java.awt.Dimension(100, 29));

        botaoContinuar.setText("Continuar");
        botaoContinuar.setMaximumSize(new java.awt.Dimension(100, 29));
        botaoContinuar.setMinimumSize(new java.awt.Dimension(100, 29));
        botaoContinuar.setPreferredSize(new java.awt.Dimension(100, 29));
        botaoContinuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoContinuarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textFIeldEntrada)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE))
                .addGap(69, 69, 69)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(botaoExecutar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoContinuar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(357, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(textFIeldEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoExecutar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoContinuar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        menuFile.setText("File");

        menuFileAbrir.setText("Abrir");
        menuFileAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFileAbrirActionPerformed(evt);
            }
        });
        menuFile.add(menuFileAbrir);

        menuFileExportar.setText("Exportar");
        menuFileExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuFileExportarActionPerformed(evt);
            }
        });
        menuFile.add(menuFileExportar);

        menuBar.add(menuFile);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textFIeldEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFIeldEntradaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFIeldEntradaActionPerformed

    private void botaoContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoContinuarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoContinuarActionPerformed

    private void menuFileAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFileAbrirActionPerformed
        // TODO add your handling code here:
        Arquivo arq = new Arquivo();
        Funcoes c = new Funcoes();
        ArrayList<ListaAuxiliar> filaJMP = new ArrayList();
        MaquinaVirtual mv = new MaquinaVirtual();
        JFileChooser fileChooser = new JFileChooser();//e possivel escolher uma pasta para inicializar (/home/desktop....)
        fileChooser.setFileFilter(new FileNameExtensionFilter("Arquivos txt", "txt"));//alterar para obj
        
        int retornoArquivo = fileChooser.showOpenDialog(null);  //abre a pasta para escolher o arquivo
        if(retornoArquivo == JFileChooser.APPROVE_OPTION){ //arquivo selecionado
            File arquivo = fileChooser.getSelectedFile();
 
           String path = arquivo.getAbsolutePath();
            mv.leArquivo(path, c, arq,filaJMP);
            
            Interface tabelaPreenchida = new Interface(c,mv,filaJMP);
            tabelaPreenchida.setResizable(false);
            tabelaPreenchida.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            tabelaPreenchida.setLocationRelativeTo(this);
            tabelaPreenchida.setVisible(true);
            
            for (int i = 0; i < c.tamanhoFila(); i++) {
                tabelaPreenchida.preencherTabela(c.getItemFila(i),i+1);
            }
            
            for (ListaAuxiliar itemLista : filaJMP) {
                tabelaPreenchida.preencherJumps(itemLista.getInstrucao(),itemLista.getLabel(),itemLista.getIndice());
            }
            
            dispose();
            
        }

        
    }//GEN-LAST:event_menuFileAbrirActionPerformed

    private void menuFileExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFileExportarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuFileExportarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InterfaceInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfaceInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfaceInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfaceInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfaceInicial().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoContinuar;
    private javax.swing.JButton botaoExecutar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenuItem menuFileAbrir;
    private javax.swing.JMenuItem menuFileExportar;
    private javax.swing.JTextField textFIeldEntrada;
    private javax.swing.JTextArea textFieldSaida;
    // End of variables declaration//GEN-END:variables
}
