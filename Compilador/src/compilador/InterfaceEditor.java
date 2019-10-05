package compilador;

import java.awt.Color;
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
      //  this.arq = new FileWriter(path);
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
        jTextAreaDeErros = new java.awt.TextArea();
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

        jTextAreaDeErros.setBackground(new java.awt.Color(238, 238, 238));
        jTextAreaDeErros.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTextAreaDeErros.setEditable(false);
        jTextAreaDeErros.setEnabled(false);
        jTextAreaDeErros.setForeground(new java.awt.Color(255, 0, 0));

        jMenuArquivo.setText("Arquivo");

        jMenuItemAbrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
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
                    .addComponent(jTextAreaDeErros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jScrollPaneEditorTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextAreaDeErros, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        abrirArquivo.showOpenDialog(null);

        String caminho = abrirArquivo.getSelectedFile().getAbsolutePath();

        List<String> lista = null;
        try {
            lista = Files.readAllLines(Paths.get(caminho));
            String comando = "";

            for (String linha : lista) {
                comando = comando.concat(linha).concat("\n");
            }

            jTextAreaDeCodigo.setText(comando);//aqui, o comando 'e uma string gigante de uma linha so, vou ver algum jeito de pegarmos linha por linha e colocar um /n antes de inserir na string comando

        } catch (IOException ex) {
            Logger.getLogger(InterfaceEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItemAbrirActionPerformed

    private void jMenuItemCompilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCompilarActionPerformed
        try {
            // TODO add your handling code here:
            this.arq = new FileWriter(path);
        } catch (IOException ex) {
            Logger.getLogger(InterfaceEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
        PrintWriter gravarArq = new PrintWriter(arq);
        gravarArq.println(jTextAreaDeCodigo.getText());

        try {
            arq.close();
        } catch (IOException ex) {
            Logger.getLogger(InterfaceEditor.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            AnalisadorLexico analisadorLexico = new AnalisadorLexico(path);
            AnalisadorSintatico sintatico = new AnalisadorSintatico(analisadorLexico);
            
            jTextAreaDeErros.setForeground(Color.RED);
            jTextAreaDeErros.setText(sintatico.getFraseContendoErro());
            
            
            
            /*String linhaEcaracter = analisadorLexico.erroLexicoNaLinha();
            
            if (linhaEcaracter.contains("Sem erros")) {
                jTextAreaDeErros.setForeground(Color.BLUE);
                jTextAreaDeErros.setText("Construido com sucesso!");
            } else {
                String numeroLinha = linhaEcaracter.split(" ")[0];
                String caracterComErro = linhaEcaracter.split(" ")[1];
                jTextAreaDeErros.setForeground(Color.RED);
                jTextAreaDeErros.setText("Linha " + numeroLinha + " - Erro Léxico: Caracter " + caracterComErro + " não tem função definida.");
            }*/

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
    private java.awt.TextArea jTextAreaDeErros;
    // End of variables declaration//GEN-END:variables
}
