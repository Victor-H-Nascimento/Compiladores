/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maquina.virtual;

import static java.lang.String.valueOf;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ettore
 */
public class Interface extends javax.swing.JFrame {

    /**
     * Creates new form Interface
     * @param c
     */
  

   
    public Interface(Funcoes c) {
        initComponents();
        inicializaStatusComponentes();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaExec = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        pilhaDados = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        pilhaJump = new javax.swing.JTable();
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

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabelaExec.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Break point", "Linha", "Instrucao", "Parâmetro 1", "Parâmetro 2"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabelaExec);
        if (tabelaExec.getColumnModel().getColumnCount() > 0) {
            tabelaExec.getColumnModel().getColumn(0).setPreferredWidth(25);
            tabelaExec.getColumnModel().getColumn(1).setPreferredWidth(25);
        }

        pilhaDados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Dados"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(pilhaDados);
        if (pilhaDados.getColumnModel().getColumnCount() > 0) {
            pilhaDados.getColumnModel().getColumn(0).setResizable(false);
        }

        pilhaJump.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "-", "Label", "Indice"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(pilhaJump);
        if (pilhaJump.getColumnModel().getColumnCount() > 0) {
            pilhaJump.getColumnModel().getColumn(0).setResizable(false);
            pilhaJump.getColumnModel().getColumn(1).setResizable(false);
            pilhaJump.getColumnModel().getColumn(2).setResizable(false);
        }

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
        botaoExecutar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botaoExecutarMouseClicked(evt);
            }
        });

        botaoContinuar.setText("Continuar");
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
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textFIeldEntrada)
                            .addComponent(jScrollPane5)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(botaoContinuar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                        .addComponent(botaoExecutar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(textFIeldEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoExecutar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoContinuar))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void menuFileExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFileExportarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menuFileExportarActionPerformed

    private void botaoContinuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoContinuarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botaoContinuarActionPerformed

    private void textFIeldEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFIeldEntradaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFIeldEntradaActionPerformed

    private void menuFileAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuFileAbrirActionPerformed
        
    }//GEN-LAST:event_menuFileAbrirActionPerformed

    private void botaoExecutarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botaoExecutarMouseClicked
        // TODO add your handling code here:
        //mv.executarFuncoes(c, filaJMP); como passar parametros de Interface Inicial para interface?
    }//GEN-LAST:event_botaoExecutarMouseClicked

      public void preencherTabela(String linha, int numLinha) {
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        //System.out.println("Entrou preencher " + linha); //apenas para verificacao
        String instrucao = null, param1 = null, param2 = null, aux;
        DefaultTableModel model = (DefaultTableModel) tabelaExec.getModel();
        
        String[] rowData = new String[tabelaExec.getColumnCount()];   //para adicionar na tabela
        
        for (int i = 1; i < tabelaExec.getColumnCount(); i++) {//1 pois nao queremos centralizar o break point
            tabelaExec.getColumnModel().getColumn(i).setCellRenderer( centerRenderer ); //centraliza o conteudo de cada coluna
        }
        
        //coluna 0 contem o breakpoint.
        if (linha.contains(" ")) {

            
            
                if (linha.contains(",")) {
                    //2 parametros

                    instrucao = linha.split(" ")[0];
                    aux = linha.split(" ")[1];
                    param1 = aux.split(",")[0];
                    param2 = aux.split(",")[1];
                    
                } else {
                    //1 parametro
                    
                    instrucao = linha.split(" ")[0];
                    param1 = linha.split(" ")[1];
                    param2 = null;
                }
                
        }else {
            //sem parametros
                //instrucao = linha;
            
                    instrucao = linha;
                    param1 = null;
                    param2 = null;
                    
        }
        rowData[1] = valueOf(numLinha);
        rowData[2] = instrucao;
        rowData[3] = param1;
        rowData[4] = param2;
        model.addRow(rowData);  //adiciona de fato a tabela 
    }
    
      public void preencherJumps(String instrucao, String label, int indice)
      {
       DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        
        //System.out.println("Entrou preencher " + linha); //apenas para verificacao
       // String instrucao = null, param1 = null, param2 = null, aux;
        DefaultTableModel model = (DefaultTableModel) pilhaJump.getModel();
        String[] rowData = new String[pilhaJump.getColumnCount()];   //para adicionar na tabela
        
        for (int i = 0; i < pilhaJump.getColumnCount(); i++) {
            pilhaJump.getColumnModel().getColumn(i).setCellRenderer( centerRenderer ); //centraliza o conteudo de cada coluna
        }
        
        indice = indice + 1;
        String aux = Integer.toString(indice);
        
        
        rowData[0] = instrucao;
        rowData[1] = label;
        rowData[2] = aux;
        model.addRow(rowData);
        
      }
      
       public void inicializaStatusComponentes()
       {
       //botaoExecutar.setEnabled(false);
       }
      
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoContinuar;
    private javax.swing.JButton botaoExecutar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable2;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenuItem menuFileAbrir;
    private javax.swing.JMenuItem menuFileExportar;
    private javax.swing.JTable pilhaDados;
    private javax.swing.JTable pilhaJump;
    private javax.swing.JTable tabelaExec;
    private javax.swing.JTextField textFIeldEntrada;
    private javax.swing.JTextArea textFieldSaida;
    // End of variables declaration//GEN-END:variables
}
