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
        this.setTitle("Sistema de gestion de ventas - Farmacia Green leaf");
    
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
        jMenu2 = new javax.swing.JMenu();
        mnuVentasporfecha = new javax.swing.JMenuItem();
        mnuIngresosporfecha = new javax.swing.JMenuItem();
        mnuInventario = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuReporteCliente = new javax.swing.JMenuItem();
        jMenuReporteTrabajador = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        mnuAcercade = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        escritorio.setBackground(new java.awt.Color(0, 51, 51));

        lblNombre.setForeground(new java.awt.Color(204, 255, 204));
        lblNombre.setText("Nombre");
        escritorio.add(lblNombre);
        lblNombre.setBounds(10, 260, 260, 14);

        lblAcceso.setForeground(new java.awt.Color(204, 255, 204));
        lblAcceso.setText("Acceso");
        escritorio.add(lblAcceso);
        lblAcceso.setBounds(10, 280, 230, 14);

        mnuSistema.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/Inicio.png"))); // NOI18N
        mnuSistema.setText("Sistema");

        mnuSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/Salir.png"))); // NOI18N
        mnuSalir.setText("Salir");
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
        mnuRegistros.add(mnuTrabajadores);

        mnuProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/proveedores.png"))); // NOI18N
        mnuProveedores.setText("Proveedores");
        mnuRegistros.add(mnuProveedores);

        mnuArticulos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/icono_paquetes.png"))); // NOI18N
        mnuArticulos.setText("Artículos");
        mnuRegistros.add(mnuArticulos);

        menuBar.add(mnuRegistros);

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
        jMenu3.add(mnuVentas);

        menuBar.add(jMenu3);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/Consultas.png"))); // NOI18N
        jMenu2.setText("Consultas");

        mnuVentasporfecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/pagos.png"))); // NOI18N
        mnuVentasporfecha.setText("Ventas por fecha");
        jMenu2.add(mnuVentasporfecha);

        mnuIngresosporfecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/chart.png"))); // NOI18N
        mnuIngresosporfecha.setText("Ingresos por fecha");
        jMenu2.add(mnuIngresosporfecha);

        mnuInventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/mercancia.png"))); // NOI18N
        mnuInventario.setText("Inventario de mercancía");
        mnuInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuInventarioActionPerformed(evt);
            }
        });
        jMenu2.add(mnuInventario);

        menuBar.add(jMenu2);

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

        menuBar.add(jMenu1);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/Ayuda.png"))); // NOI18N
        jMenu5.setText("Ayuda");

        mnuAcercade.setText("Acerca de...");
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

    private void mnuIngresoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuIngresoActionPerformed
        // TODO add your handling code here:
        
        frmIngreso form =new frmIngreso();
        form.toFront();
        form.setVisible(true);
        
        
    }//GEN-LAST:event_mnuIngresoActionPerformed

    private void mnuInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuInventarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnuInventarioActionPerformed

    private void mnuClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuClientesActionPerformed
        // TODO add your handling code here:
        frmCliente form =new frmCliente();
        escritorio.add(form);
        form.toFront();
        form.setVisible(true);
    }//GEN-LAST:event_mnuClientesActionPerformed

        private Connection connection=new conexion().conectar();
    
    
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
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuItem jMenuReporteCliente;
    private javax.swing.JMenuItem jMenuReporteTrabajador;
    public static javax.swing.JLabel lblAcceso;
    public static javax.swing.JLabel lblNombre;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem mnuAcercade;
    private javax.swing.JMenuItem mnuArticulos;
    private javax.swing.JMenuItem mnuClientes;
    private javax.swing.JMenuItem mnuIngreso;
    private javax.swing.JMenuItem mnuIngresosporfecha;
    private javax.swing.JMenuItem mnuInventario;
    private javax.swing.JMenuItem mnuProveedores;
    private javax.swing.JMenu mnuRegistros;
    private javax.swing.JMenuItem mnuSalir;
    private javax.swing.JMenu mnuSistema;
    private javax.swing.JMenuItem mnuTrabajadores;
    private javax.swing.JMenuItem mnuVentas;
    private javax.swing.JMenuItem mnuVentasporfecha;
    // End of variables declaration//GEN-END:variables

}
