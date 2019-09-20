package compilador;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author victor
 */
public class InterfaceEditor extends javax.swing.JFrame {

    String path;
    /**
     * Creates new form InterfaceEditor
     */
    FileWriter arq;

    public InterfaceEditor() throws IOException {
        this.path = "EditorTexto.txt";
        this.arq = new FileWriter(path);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPaneEditorTexto = new javax.swing.JScrollPane();
        jTextAreaDeCodigo = new javax.swing.JTextArea();
        textArea1 = new java.awt.TextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuArquivo = new javax.swing.JMenu();
        jMenuItemAbrir = new javax.swing.JMenuItem();
        jMenuExecução = new javax.swing.JMenu();
        jMenuItemCompilar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextAreaDeCodigo.setColumns(20);
        jTextAreaDeCodigo.setRows(5);
        jTextAreaDeCodigo.setToolTipText("");
        jScrollPaneEditorTexto.setViewportView(jTextAreaDeCodigo);

        textArea1.setBackground(new java.awt.Color(238, 238, 238));
        textArea1.setCursor(new java.awt.Cursor(java.awt.Cursor.E_RESIZE_CURSOR));
        textArea1.setEditable(false);
        textArea1.setEnabled(false);
        textArea1.setForeground(new java.awt.Color(255, 0, 0));

        jMenuArquivo.setText("Arquivo");

        jMenuItemAbrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemAbrir.setText("Abrir");
        jMenuItemAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAbrirActionPerformed(evt);
            }
        });
        jMenuArquivo.add(jMenuItemAbrir);

        jMenuBar1.add(jMenuArquivo);

        jMenuExecução.setText("Execução");

        jMenuItemCompilar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        jMenuItemCompilar.setText("Compilar");
        jMenuItemCompilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCompilarActionPerformed(evt);
            }
        });
        jMenuExecução.add(jMenuItemCompilar);

        jMenuBar1.add(jMenuExecução);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPaneEditorTexto, javax.swing.GroupLayout.DEFAULT_SIZE, 1024, Short.MAX_VALUE)
                    .addComponent(textArea1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPaneEditorTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textArea1, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAbrirActionPerformed
        // TODO add your handling code here:
        JFileChooser abrirArquivo = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Entrada de dados", "txt");
        abrirArquivo.setFileFilter(filter);
        abrirArquivo.setFileSelectionMode(JFileChooser.OPEN_DIALOG);
        int retornoOpenDialog = abrirArquivo.showOpenDialog(null);
        
        File arquivo = abrirArquivo.getSelectedFile();
        String path1 = arquivo.getAbsolutePath();
        
        try {
            System.out.println(Files.readAllLines(Paths.get(path1)));
        } catch (IOException ex) {
            Logger.getLogger(InterfaceEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<String> lista = null;
        try {
            //Files.readAllLines(path);
            lista = Files.readAllLines(Paths.get(path1));
            
            //System.out.println(conteudo);
            /*path = abrirArquivo.getSelectedFile().getAbsolutePath();
            System.out.println(path);*/
        } catch (IOException ex) {
            Logger.getLogger(InterfaceEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
        int i = 0;
        String comando = "";
        do {
            comando = comando + lista.get(i) + "\n";
            i++;
        } while (!"fim.".equals(lista.get(i)));
        comando = comando + lista.get(i) + "\n"; //para pegar o "fim."
        
        
        //String comando = lista.toString();
        jTextAreaDeCodigo.setText(comando);
        //aqui, o comando 'e uma string gigante de uma linha so, vou ver algum jeito de pegarmos linha por linha e colocar um /n antes de inserir na string comando
        

    }//GEN-LAST:event_jMenuItemAbrirActionPerformed

    private void jMenuItemCompilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCompilarActionPerformed
        // TODO add your handling code here:

        PrintWriter gravarArq = new PrintWriter(arq);
        gravarArq.println(jTextAreaDeCodigo.getText());
        
        try {
            arq.close();
        } catch (IOException ex) {
            Logger.getLogger(InterfaceEditor.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            AnalisadorLexico analisadorLexico = new AnalisadorLexico(path);
        } catch (IOException ex) {
            Logger.getLogger(InterfaceEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemCompilarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenuArquivo;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jMenuExecução;
    private javax.swing.JMenuItem jMenuItemAbrir;
    private javax.swing.JMenuItem jMenuItemCompilar;
    private javax.swing.JScrollPane jScrollPaneEditorTexto;
    private javax.swing.JTextArea jTextAreaDeCodigo;
    private java.awt.TextArea textArea1;
    // End of variables declaration//GEN-END:variables
}
