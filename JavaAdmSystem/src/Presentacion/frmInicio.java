/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Logica.conexion;
import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Mirlu
 */
public class frmInicio extends javax.swing.JFrame {

    /**
     * Creates new form frmInicio
     */
    public frmInicio() {
        initComponents();
        this.setExtendedState(frmInicio.MAXIMIZED_BOTH);
        this.setTitle("Sistema de gestion de ventas - Tienda Green leaf");
        
        

        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        escritorio = new javax.swing.JDesktopPane();
        lblNombre = new javax.swing.JLabel();
        lblAcceso = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        mnuSistema = new javax.swing.JMenu();
        mnuSalir = new javax.swing.JMenuItem();
        mnuRegistros = new javax.swing.JMenu();
        mnuClientes = new javax.swing.JMenuItem();
        mnuTrabajadores = new javax.swing.JMenuItem();
        mnuProveedores = new javax.swing.JMenuItem();
        mnuArticulos = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        mnuIngreso = new javax.swing.JMenuItem();
        mnuVentas = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuReporteCliente = new javax.swing.JMenuItem();
        jMenuReporteTrabajador = new javax.swing.JMenuItem();
        jMenuReporteArticulo = new javax.swing.JMenuItem();
        jMenuReporteProveedores = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        mnuAcercade = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        escritorio.setBackground(new java.awt.Color(0, 51, 0));

        lblNombre.setForeground(new java.awt.Color(204, 255, 204));
        lblNombre.setText("Nombre");
        escritorio.add(lblNombre);
        lblNombre.setBounds(10, 10, 260, 14);

        lblAcceso.setForeground(new java.awt.Color(204, 255, 204));
        lblAcceso.setText("Acceso");
        escritorio.add(lblAcceso);
        lblAcceso.setBounds(10, 30, 230, 14);

        jLabel3.setFont(new java.awt.Font("Dialog", 3, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 255, 204));
        jLabel3.setText("GREEN LEAF C.A.");
        escritorio.add(jLabel3);
        jLabel3.setBounds(160, 300, 560, 210);
        escritorio.add(jLabel1);
        jLabel1.setBounds(610, 110, 0, 0);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/newleaflogo.png"))); // NOI18N
        escritorio.add(jLabel2);
        jLabel2.setBounds(670, 240, 580, 370);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/fondo.jpg"))); // NOI18N
        escritorio.add(jLabel4);
        jLabel4.setBounds(1, -10, 1380, 830);

        mnuSistema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/Inicio.png"))); // NOI18N
        mnuSistema.setText("Sistema");

        mnuSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/Salir.png"))); // NOI18N
        mnuSalir.setText("Cerrar Sesión");
        mnuSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSalirActionPerformed(evt);
            }
        });
        mnuSistema.add(mnuSalir);

        menuBar.add(mnuSistema);

        mnuRegistros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/Archivo.png"))); // NOI18N
        mnuRegistros.setText("Registros");

        mnuClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/clientes.png"))); // NOI18N
        mnuClientes.setText("Clientes");
        mnuClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuClientesActionPerformed(evt);
            }
        });
        mnuRegistros.add(mnuClientes);

        mnuTrabajadores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/trabajadores.png"))); // NOI18N
        mnuTrabajadores.setText("Trabajadores");
        mnuTrabajadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuTrabajadoresActionPerformed(evt);
            }
        });
        mnuRegistros.add(mnuTrabajadores);

        mnuProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/proveedores.png"))); // NOI18N
        mnuProveedores.setText("Proveedores");
        mnuProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuProveedoresActionPerformed(evt);
            }
        });
        mnuRegistros.add(mnuProveedores);

        mnuArticulos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/icono_paquetes.png"))); // NOI18N
        mnuArticulos.setText("Artículos");
        mnuArticulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuArticulosActionPerformed(evt);
            }
        });
        mnuRegistros.add(mnuArticulos);

        menuBar.add(mnuRegistros);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/pagos.png"))); // NOI18N
        jMenu3.setText("Procesos");

        mnuIngreso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/Add_to_Cart-512.png"))); // NOI18N
        mnuIngreso.setText("Ingreso");
        mnuIngreso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuIngresoActionPerformed(evt);
            }
        });
        jMenu3.add(mnuIngreso);

        mnuVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/dolar.png"))); // NOI18N
        mnuVentas.setText("Ventas");
        mnuVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuVentasActionPerformed(evt);
            }
        });
        jMenu3.add(mnuVentas);

        menuBar.add(jMenu3);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/Consultas.png"))); // NOI18N
        jMenu1.setText("Reportes");

        jMenuReporteCliente.setText("Cliente");
        jMenuReporteCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuReporteClienteActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuReporteCliente);

        jMenuReporteTrabajador.setText("Trabajadores");
        jMenuReporteTrabajador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuReporteTrabajadorActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuReporteTrabajador);

        jMenuReporteArticulo.setText("Articulos");
        jMenuReporteArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuReporteArticuloActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuReporteArticulo);

        jMenuReporteProveedores.setText("Proveedores");
        jMenuReporteProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuReporteProveedoresActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuReporteProveedores);

        menuBar.add(jMenu1);

        jMenu2.setText("Comprobantes");

        jMenuItem1.setText("Ingresos");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem2.setText("Ventas");
        jMenu2.add(jMenuItem2);

        menuBar.add(jMenu2);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/Ayuda.png"))); // NOI18N
        jMenu5.setText("Ayuda");

        mnuAcercade.setText("Acerca de...");
        mnuAcercade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuAcercadeActionPerformed(evt);
            }
        });
        jMenu5.add(mnuAcercade);

        menuBar.add(jMenu5);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(escritorio, javax.swing.GroupLayout.PREFERRED_SIZE, 1377, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(escritorio, javax.swing.GroupLayout.PREFERRED_SIZE, 801, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
        
        private Connection connection=new conexion().conectar();
        
        
        
        
        
        
    
    private void mnuIngresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuIngresoActionPerformed
        // TODO add your handling code here:
        
        frmIngreso form =new frmIngreso();
        escritorio.add(form);
        form.toFront();
        form.setVisible(true);
        
        
    }//GEN-LAST:event_mnuIngresoActionPerformed

        
    
    private void mnuClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuClientesActionPerformed
        // TODO add your handling code here:
        frmCliente form =new frmCliente();
        escritorio.add(form);
        form.toFront();
        form.setVisible(true);
    }//GEN-LAST:event_mnuClientesActionPerformed
    
    
  
            
    

    
    
    private void jMenuReporteClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuReporteClienteActionPerformed
        // TODO add your handling code here:
        
        
                //librerias
        Map p= new HashMap();
        JasperReport report;
        JasperPrint print;
        
        
        try {
            report=JasperCompileManager.compileReport(new File("").getAbsolutePath()+ "/src/Reportes/rptcliente.jrxml");
            
            print= JasperFillManager.fillReport(report, p,connection);
            
            JasperViewer view=new JasperViewer(print, false);
            
            view.setTitle("Reporte de Clientes");
            
            view.setVisible(true);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
        
        
        
        
        
        
        
    }//GEN-LAST:event_jMenuReporteClienteActionPerformed

    private void jMenuReporteTrabajadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuReporteTrabajadorActionPerformed
        // TODO add your handling code here:
        
                        //librerias
        Map p= new HashMap();
        JasperReport report;
        JasperPrint print;
        
        
        try {
            report=JasperCompileManager.compileReport(new File("").getAbsolutePath()+ "/src/Reportes/rpttrabajador.jrxml");
            
            print= JasperFillManager.fillReport(report, p,connection);
            
            JasperViewer view=new JasperViewer(print, false);
            
            view.setTitle("Reporte de Clientes");
            
            view.setVisible(true);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
        
        
    }//GEN-LAST:event_jMenuReporteTrabajadorActionPerformed

    private void jMenuReporteArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuReporteArticuloActionPerformed
        // TODO add your handling code here:
        
                                //librerias
        Map p= new HashMap();
        JasperReport report;
        JasperPrint print;
        
        
        try {
            report=JasperCompileManager.compileReport(new File("").getAbsolutePath()+ "/src/Reportes/rptarticulo.jrxml");
            
            print= JasperFillManager.fillReport(report, p,connection);
            
            JasperViewer view=new JasperViewer(print, false);
            
            view.setTitle("Reporte de Articulos");
            
            view.setVisible(true);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
        
        
    }//GEN-LAST:event_jMenuReporteArticuloActionPerformed

    private void jMenuReporteProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuReporteProveedoresActionPerformed
        // TODO add your handling code here:
        
                
                                //librerias
        Map p= new HashMap();
        JasperReport report;
        JasperPrint print;
        
        
        try {
            report=JasperCompileManager.compileReport(new File("").getAbsolutePath()+ "/src/Reportes/rptproveedor.jrxml");
            
            print= JasperFillManager.fillReport(report, p,connection);
            
            JasperViewer view=new JasperViewer(print, false);
            
            view.setTitle("Reporte de Proveedores");
            
            view.setVisible(true);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        
        
    }//GEN-LAST:event_jMenuReporteProveedoresActionPerformed

    private void mnuVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuVentasActionPerformed
        // TODO add your handling code here:
        frmVenta form =new frmVenta();
        escritorio.add(form);
        form.toFront();
        form.setVisible(true);
        
        
    }//GEN-LAST:event_mnuVentasActionPerformed

    private void mnuTrabajadoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuTrabajadoresActionPerformed
        // TODO add your handling code here:
        frmTrabajador form =new frmTrabajador();
        escritorio.add(form);
        form.toFront();
        form.setVisible(true);
        
    }//GEN-LAST:event_mnuTrabajadoresActionPerformed

    private void mnuProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuProveedoresActionPerformed
        // TODO add your handling code here:
        
        
        frmProveedor form =new frmProveedor();
        escritorio.add(form);
        form.toFront();
        form.setVisible(true);
    }//GEN-LAST:event_mnuProveedoresActionPerformed

    private void mnuArticulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuArticulosActionPerformed
        // TODO add your handling code here:
        
        frmArticulo form =new frmArticulo();
        escritorio.add(form);
        form.toFront();
        form.setVisible(true);
    }//GEN-LAST:event_mnuArticulosActionPerformed

    private void mnuAcercadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuAcercadeActionPerformed
        // TODO add your handling code here:
        
        Acercade form =new Acercade();
        escritorio.add(form);
        form.toFront();
        form.setVisible(true);
        
        
    }//GEN-LAST:event_mnuAcercadeActionPerformed

    private void mnuSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSalirActionPerformed
        // TODO add your handling code here:
        
        
        
        
        
        
        frmlogin form = new frmlogin();
        form.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_mnuSalirActionPerformed

    
    //este evento es cuando se abre la ventana, en ese momento, se tomara en cuenta lo que dice en lblAcceso, 
    //para inhabilitar los itemmenu correspondiente a su nivel de acceso
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
         //esto es para ocultar las funciones del admin, a los usuarios        
        if (lblAcceso.getText().equals("Usuario")){

                jMenu1.setEnabled(false); //reportes
                mnuTrabajadores.setEnabled(false); //trabajadores
        }
    }//GEN-LAST:event_formWindowOpened

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        frmComprobanteIngreso form =new frmComprobanteIngreso();
        escritorio.add(form);
        form.toFront();
        form.setVisible(true);
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

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
            java.util.logging.Logger.getLogger(frmInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmInicio().setVisible(true);
            }
        });
        
        
        
                
        
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane escritorio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuReporteArticulo;
    private javax.swing.JMenuItem jMenuReporteCliente;
    private javax.swing.JMenuItem jMenuReporteProveedores;
    private javax.swing.JMenuItem jMenuReporteTrabajador;
    public static javax.swing.JLabel lblAcceso;
    public static javax.swing.JLabel lblNombre;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem mnuAcercade;
    private javax.swing.JMenuItem mnuArticulos;
    private javax.swing.JMenuItem mnuClientes;
    private javax.swing.JMenuItem mnuIngreso;
    private javax.swing.JMenuItem mnuProveedores;
    private javax.swing.JMenu mnuRegistros;
    private javax.swing.JMenuItem mnuSalir;
    private javax.swing.JMenu mnuSistema;
    private javax.swing.JMenuItem mnuTrabajadores;
    private javax.swing.JMenuItem mnuVentas;
    // End of variables declaration//GEN-END:variables

}
