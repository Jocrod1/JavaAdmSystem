/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Datos.vtrabajador;
import Logica.ftrabajador;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Raimon
 */
public class frmlogin extends javax.swing.JFrame {

  
    /**
     * Creates new form frmlogin
     */
    public frmlogin() {
        initComponents();
        this.setTitle("Acceso al Sistema");
        this.setLocationRelativeTo(null);
        
        
        
        
        //this.tablalistado.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtusuario = new javax.swing.JTextField();
        txtcontraseña = new javax.swing.JPasswordField();
        btningresar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablalistado = new javax.swing.JTable();
        lblpregunta = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablacontraseña = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cbCi = new javax.swing.JComboBox<>();

        jLabel3.setText("jLabel3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));

        jLabel1.setText("Cedula");

        jLabel2.setText("Contraseña");

        txtusuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtusuarioKeyPressed(evt);
            }
        });

        txtcontraseña.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcontraseñaKeyPressed(evt);
            }
        });

        btningresar.setText("Ingresar");
        btningresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btningresarActionPerformed(evt);
            }
        });

        btnsalir.setText("Salir");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        tablalistado.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tablalistado);

        lblpregunta.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        lblpregunta.setForeground(new java.awt.Color(102, 102, 102));
        lblpregunta.setText("¿Olvidó su Contraseña?");
        lblpregunta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblpreguntaMouseClicked(evt);
            }
        });

        tablacontraseña.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tablacontraseña);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/greenlogin.png"))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 0));
        jLabel5.setText("Tienda Homeopática Green Leaf ");

        cbCi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "V-", "E-" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblpregunta)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnsalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btningresar)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(cbCi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(txtcontraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(jLabel5)
                        .addGap(76, 76, 76)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(cbCi, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtusuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtcontraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btningresar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnsalir)
                        .addGap(18, 18, 18)
                        .addComponent(lblpregunta))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(42, 42, 42))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btningresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btningresarActionPerformed

        
        // TODO add your handling code here:
        
        try {
            DefaultTableModel modelo;
            ftrabajador func= new ftrabajador();
            vtrabajador dts= new vtrabajador();
            
            String TipoCi = cbCi.getSelectedItem().toString();
            String NumCi = txtusuario.getText();
        
            
            
            dts.setId_trabajador(TipoCi+NumCi);
            dts.setContraseña(txtcontraseña.getText());
            
            modelo=func.usuario(dts.getId_trabajador(), dts.getContraseña());
            

            
            tablalistado.setModel(modelo);
            
            if(func.totalregistros>0)
            {
                JOptionPane.showMessageDialog(rootPane,"Bienvenido al Sistema","Acceso al Sistema", JOptionPane.INFORMATION_MESSAGE);

                
                
                frmInicio form=new frmInicio();
                form.toFront();
                form.setVisible(true);
                
                frmInicio.lblNombre.setText(tablalistado.getValueAt(0, 2).toString());
                frmInicio.lblAcceso.setText(tablalistado.getValueAt(0, 3).toString());
                
               
                
                frmIngreso.idtrabajador= (tablalistado.getValueAt(0, 0).toString());
                frmVenta.idtrabajador=(tablalistado.getValueAt(0, 0).toString());
                //se cierra el form aca
                this.dispose();
               
                
            }
            else
            {
                JOptionPane.showMessageDialog(rootPane,"Acceso Denegado","Acceso al Sistema", JOptionPane.ERROR_MESSAGE);
            }
            
            
        } catch (Exception e) {
        }
        
        
        
    }//GEN-LAST:event_btningresarActionPerformed

    private void lblpreguntaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblpreguntaMouseClicked
        // TODO add your handling code here:
        
        try {
            DefaultTableModel modelo;
            ftrabajador func= new ftrabajador();
            vtrabajador dts= new vtrabajador();
            
            
            
            
            dts.setId_trabajador(txtusuario.getText());
            //dts.setContraseña(txtcontraseña.getText());
            
            
            modelo=func.contraseñaolvidada(dts.getId_trabajador());
            

            
            tablacontraseña.setModel(modelo);
            
            if(func.totalregistros>0)
            {
                //JOptionPane.showMessageDialog(rootPane,"Bienvenido al Sitema","Acceso al Sistema", JOptionPane.INFORMATION_MESSAGE);

                frmcontraseña form=new frmcontraseña();
                form.toFront();
                form.setVisible(true);
                frmcontraseña.txtcontraseña.setText(tablacontraseña.getValueAt(0, 1).toString());
                frmcontraseña.pregunta.setText(tablacontraseña.getValueAt(0, 2).toString());
                frmcontraseña.txtsecreta.setText(tablacontraseña.getValueAt(0, 3).toString());

                
                
                //se cierra el form aca
                //this.dispose();

                
            }
            else if (func.totalregistros==0)
            {
                JOptionPane.showMessageDialog(rootPane,"Ingrese un Usuario existente!","Acceso al Sistema", JOptionPane.ERROR_MESSAGE);
            }
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
        
       
    }//GEN-LAST:event_lblpreguntaMouseClicked

    private void txtcontraseñaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcontraseñaKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
        
            this.btningresar.doClick();
        }
        
    }//GEN-LAST:event_txtcontraseñaKeyPressed

    private void txtusuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtusuarioKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
        
            this.btningresar.doClick();
        }
    }//GEN-LAST:event_txtusuarioKeyPressed

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnsalirActionPerformed

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
            java.util.logging.Logger.getLogger(frmlogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmlogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmlogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmlogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmlogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btningresar;
    private javax.swing.JButton btnsalir;
    private javax.swing.JComboBox<String> cbCi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblpregunta;
    private javax.swing.JTable tablacontraseña;
    private javax.swing.JTable tablalistado;
    private javax.swing.JPasswordField txtcontraseña;
    private javax.swing.JTextField txtusuario;
    // End of variables declaration//GEN-END:variables
}
