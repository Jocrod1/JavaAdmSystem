/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Datos.vdetalle_ingreso;
import Datos.vingreso;
import Logica.conexion;
import Logica.fingreso;
import java.io.File;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
public class frmIngreso extends javax.swing.JFrame {
    

    
    
    public static String idtrabajador;
    public static String CodArticulo;
    public static String idProveedor;
    
    /**
     * Creates new form frmIngreso
     */
    
    String [] titulos = {"Articulo", "Precio de Compra", "Precio de Venta", "Stock Inicial", "Stock Actual", "Subtotal"};
    
    DefaultTableModel Detalles = new DefaultTableModel(null,titulos);
    String [] Registrar = new String[6];
    int Row;
    
    String accion = "Guardar";
    
    List<vdetalle_ingreso> ListDetalles = new ArrayList<>();
    
    double TotalPagado;
    int idingreso;
    
    
    
    
    public frmIngreso() {
        initComponents();
        
        mostrar();
        
        inhabilitar();
        inhabilitardetalle();
        
        jTable1.setModel(Detalles);
        jTable1.setEnabled(false);

    }
    
    
    void inhabilitar (){
        txtProveedor.setEnabled(false);
        dateChooserCombo1.setEnabled(false);
        
        btnGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnAnular.setEnabled(false);
        BtnBuscarProveedor.setEnabled(false);
        btnImprimir.setEnabled(false);
    }
    
    
    
    void habilitar (){
        txtProveedor.setEnabled(true);
        dateChooserCombo1.setEnabled(true);
        
        btnGuardar.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnAnular.setEnabled(true);
        btnImprimir.setEnabled(true);
        BtnBuscarProveedor.setEnabled(true);
    }    

    
    void limpiar(){
    
        txtProveedor.setText("");
        Calendar today = Calendar.getInstance();
        today.setTime(new Date());
        dateChooserCombo1.setSelectedDate(today);
    }
    
    void habilitardetalle(){
        txtArticulo.setEnabled(true);
        txtPrecioCompra.setEnabled(true);
        txtPrecioVenta.setEnabled(true);
        txtStockInicial.setEnabled(true);
        
        btnQuitar.setEnabled(true);
        btnAgregar.setEnabled(true);
        BtnBuscarArticulo.setEnabled(true);
    }
    void inhabilitardetalle(){
        txtArticulo.setEnabled(false);
        txtPrecioCompra.setEnabled(false);
        txtPrecioVenta.setEnabled(false);
        txtStockInicial.setEnabled(false);
        
        btnQuitar.setEnabled(false);
        btnAgregar.setEnabled(false);
        BtnBuscarArticulo.setEnabled(false);
    }
    void limpiardetalle(){
        txtArticulo.setText("");
        txtPrecioCompra.setText("");
        txtPrecioVenta.setText("");
        txtStockInicial.setText("");
        CodArticulo = "";
    }
    void ActualizarTotalPagado(){
        TotalPagado = 0;
        if(Detalles.getRowCount()> 0){
            for(int i = Detalles.getRowCount() - 1; i>-1; i--){
                    TotalPagado += Double.parseDouble(jTable1.getValueAt(i,5).toString());
            }
        }
        lblTotalPagado.setText(Double.toString(TotalPagado) + "BsS");
    }
    void limpiartabladetalles(){
        if(Detalles.getRowCount()> 0){
            for(int i = Detalles.getRowCount() - 1; i>-1; i--){
                Detalles.removeRow(i);
            }
        }
    }
    void OcultarColumnas(){
        tablalistado.getColumnModel().getColumn(0).setMaxWidth(0);
        tablalistado.getColumnModel().getColumn(0).setMinWidth(0);
        tablalistado.getColumnModel().getColumn(0).setPreferredWidth(0);
    }
    

    void mostrar (){
        
        try {
            DefaultTableModel modelo;
            fingreso func= new fingreso();
            modelo = func.mostrar();
            
            tablalistado.setModel(modelo);
            OcultarColumnas();
            lblTotal.setText(Integer.toString(func.totalregistros));
            
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(rootPane, e);
        }
        
    }
    
        void mostrarentrefecha (String buscar1, String buscar2){
        
        try {
            DefaultTableModel modelo;
            fingreso func= new fingreso();
            modelo = func.buscarentrefechas(buscar1, buscar2);
            
            tablalistado.setModel(modelo);
            OcultarColumnas();
            lblTotal.setText(Integer.toString(func.totalregistros));
            
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(rootPane, e);
        }
        
    }
    void mostrardetalle (int identity){
        
        try {
            DefaultTableModel modelo;
            fingreso func= new fingreso();
            Detalles = func.MostrarDetalle(identity);
            
            jTable1.setModel(Detalles);
            //ocultar_columnas();
            ActualizarTotalPagado();
            
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(rootPane, e);
        }
    }
    void InsertarDetalle(int identity, fingreso FI){
        for(int i = 0; i < ListDetalles.size(); i++){
            vdetalle_ingreso a = ListDetalles.get(i);
            a.setId_ingreso(identity);
            if(!FI.insertarDetalle(a)){
                return;
            }
        }
    }
    boolean PuedeAgregar(int idarticulo){
        if(Detalles.getRowCount()<= 0)
            return true;
        for(int i = 0; i < ListDetalles.size(); i++){
            int a = ListDetalles.get(i).getId_articulo();
            if(a == idarticulo)
                return false;
        }
        return true;
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txtStockInicial = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtArticulo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        BtnBuscarArticulo = new javax.swing.JButton();
        txtPrecioVenta = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtPrecioCompra = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnQuitar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txtProveedor = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        BtnBuscarProveedor = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        dateChooserCombo1 = new datechooser.beans.DateChooserCombo();
        jLabel15 = new javax.swing.JLabel();
        btnAnular = new javax.swing.JButton();
        lblTotalPagado = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablalistado = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        dateChooserCombo2 = new datechooser.beans.DateChooserCombo();
        dateChooserCombo3 = new datechooser.beans.DateChooserCombo();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 0));
        jLabel5.setText("Ingresos");

        btnNuevo.setBackground(new java.awt.Color(255, 255, 255));
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(255, 255, 255));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/guardar.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnImprimir.setBackground(new java.awt.Color(255, 255, 255));
        btnImprimir.setText("Imprimir");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(204, 255, 153));

        txtStockInicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStockInicialActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 0));
        jLabel8.setText("Stock Inicial");

        txtArticulo.setEditable(false);

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 0));
        jLabel4.setText("Artículo");

        BtnBuscarArticulo.setText("Buscar");
        BtnBuscarArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarArticuloActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 51, 0));
        jLabel13.setText("Precio Venta");

        jLabel14.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 51, 0));
        jLabel14.setText("Precio Compra");

        btnAgregar.setText("Agregar");
        btnAgregar.setToolTipText("");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnQuitar.setText("Quitar");
        btnQuitar.setToolTipText("");
        btnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtArticulo)
                    .addComponent(txtStockInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(BtnBuscarArticulo)
                .addGap(36, 36, 36)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtPrecioCompra)
                    .addComponent(txtPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 320, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnQuitar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(32, 32, 32))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtPrecioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(btnAgregar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnQuitar)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnBuscarArticulo))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtStockInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.setEnabled(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        txtProveedor.setEditable(false);

        jLabel6.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 0));
        jLabel6.setText("Proveedor");

        BtnBuscarProveedor.setText("Buscar");
        BtnBuscarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarProveedorActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 51, 0));
        jLabel11.setText("Fecha");

        jLabel15.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 51, 0));
        jLabel15.setText("Total pagado:");

        btnAnular.setBackground(new java.awt.Color(255, 255, 255));
        btnAnular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/eliminar.png"))); // NOI18N
        btnAnular.setText("Anular");
        btnAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularActionPerformed(evt);
            }
        });

        lblTotalPagado.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        lblTotalPagado.setForeground(new java.awt.Color(0, 51, 0));
        lblTotalPagado.setText("total");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(txtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnBuscarProveedor)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)
                        .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(301, 301, 301)
                        .addComponent(jLabel5)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAnular, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTotalPagado, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(105, 105, 105))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCancelar, btnGuardar, btnNuevo});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel5)
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BtnBuscarProveedor))
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAnular, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnGuardar)
                        .addComponent(btnCancelar)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTotalPagado, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnCancelar, btnGuardar, btnNuevo});

        jTabbedPane1.addTab("Ingresos Almacén", jPanel2);

        jPanel4.setBackground(new java.awt.Color(0, 102, 0));
        jPanel4.setForeground(new java.awt.Color(204, 255, 204));

        jLabel9.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 255, 204));
        jLabel9.setText("Listado de Ingresos");

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
        tablalistado.setEnabled(false);
        tablalistado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablalistadoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablalistado);

        jLabel10.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 255, 204));
        jLabel10.setText("Fecha Inicio:");

        btnBuscar.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/buscar.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(204, 255, 204));
        jLabel1.setText("Cantidad de Registros: ");

        lblTotal.setForeground(new java.awt.Color(204, 255, 204));
        lblTotal.setText("jLabel11");

        jLabel16.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(204, 255, 204));
        jLabel16.setText("Fecha Fin:");

        btnSalir.setBackground(new java.awt.Color(255, 255, 255));
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/salir.gif"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1023, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateChooserCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateChooserCombo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotal)
                .addGap(110, 110, 110))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel9))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGap(63, 63, 63)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(dateChooserCombo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(dateChooserCombo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnBuscar)
                                .addComponent(btnSalir)))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblTotal))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Listado de Ingresos", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        // TODO add your handling code here:
        limpiar();
        habilitar();
        limpiardetalle();
        habilitardetalle();
        btnAnular.setEnabled(false);
        btnImprimir.setEnabled(false);
        accion="Guardar";
        ListDetalles.clear();
        limpiartabladetalles();
        ActualizarTotalPagado();
        btnQuitar.setEnabled(false);
        btnNuevo.setEnabled(false);
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        limpiar();
        limpiardetalle();
        inhabilitar();
        inhabilitardetalle();
        limpiartabladetalles();
        ActualizarTotalPagado();
        ListDetalles.clear();
        limpiartabladetalles();
        accion="";
        btnNuevo.setEnabled(true);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtStockInicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStockInicialActionPerformed
        // TODO add your handling code here:
        txtStockInicial.transferFocus();
    }//GEN-LAST:event_txtStockInicialActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
        if(txtArticulo.getText().length() == 0){
            JOptionPane.showConfirmDialog(rootPane, "Debes Seleccionar un Articulo");
            BtnBuscarArticulo.requestFocus();
            return;
        }
        if(txtPrecioCompra.getText().length() == 0){
            JOptionPane.showConfirmDialog(rootPane, "Debes Especificar un Precio de Compra");
            txtPrecioCompra.requestFocus();
            return;
        }
        if(txtPrecioVenta.getText().length() == 0){
            JOptionPane.showConfirmDialog(rootPane, "Debes Especificar un Precio de Venta");
            txtPrecioVenta.requestFocus();
            return;
        }
        if(txtStockInicial.getText().length() == 0){
            JOptionPane.showConfirmDialog(rootPane, "Debes Especificar un Stock Inicial");
            txtStockInicial.requestFocus();
            return;
        }
        if(!PuedeAgregar(Integer.parseInt(CodArticulo)) && accion.equals("Guardar")){
            JOptionPane.showConfirmDialog(rootPane, "Debes Selecciar un articulo no este ya agregado");
            BtnBuscarArticulo.requestFocus();
            return;
        }
        
        vdetalle_ingreso DI = new vdetalle_ingreso();
        
        Registrar[0] = txtArticulo.getText();
        DI.setId_articulo(Integer.parseInt(CodArticulo));
        Registrar[1] = txtPrecioCompra.getText();
        DI.setPrecio_compra(Double.parseDouble(Registrar[1]));
        Registrar[2] = txtPrecioVenta.getText();
        DI.setPrecio_venta(Double.parseDouble(Registrar[2]));
        Registrar[3] = txtStockInicial.getText();
        DI.setStock_inicial(Integer.parseInt(Registrar[3]));
        DI.setStock_actual(Integer.parseInt(Registrar[3]));
        Registrar[4] = Registrar[3];
        double a = DI.getPrecio_compra() * DI.getStock_inicial();
        Registrar[5] = Double.toString(a);
        
        if(DI.getPrecio_venta() < DI.getPrecio_compra()){
            JOptionPane.showConfirmDialog(rootPane, "Debes Especificar un Precio de venta mayor al de Compra");
            txtPrecioVenta.requestFocus();
            return;
        }
        
        if(accion.equals("Guardar")){
        Detalles.addRow(Registrar);
        ListDetalles.add(DI);
        }
        else if (accion.equals("Editar")){
            jTable1.setValueAt(Registrar[0], Row, 0);
            jTable1.setValueAt(Registrar[1], Row, 1);
            jTable1.setValueAt(Registrar[2], Row, 2);
            jTable1.setValueAt(Registrar[3], Row, 3);
            jTable1.setValueAt(Registrar[4], Row, 4);
            jTable1.setValueAt(Registrar[5], Row, 5);
            ListDetalles.set(Row, DI);
        }
        limpiardetalle();
        ActualizarTotalPagado();
        accion = "Guardar";
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        if(Detalles.getRowCount()<= 0)
            return;
        if(accion.equals("Anular"))
                return;
        Row = jTable1.rowAtPoint(evt.getPoint());
        
        txtArticulo.setText(jTable1.getValueAt(Row,0).toString());
        txtPrecioCompra.setText(Double.toString(ListDetalles.get(Row).getPrecio_compra()));
        txtPrecioVenta.setText(Double.toString(ListDetalles.get(Row).getPrecio_venta()));
        txtStockInicial.setText(jTable1.getValueAt(Row,3).toString());
        CodArticulo = Integer.toString(ListDetalles.get(Row).getId_articulo());
        
        accion = "Editar";
        btnQuitar.setEnabled(true);
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
        // TODO add your handling code here:
        if(accion.equals("Editar")){
            Detalles.removeRow(Row);
            limpiardetalle();
            accion = "Guardar";
            btnQuitar.setEnabled(false);
            ListDetalles.remove(Row);
        }
    }//GEN-LAST:event_btnQuitarActionPerformed

    private void BtnBuscarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarArticuloActionPerformed
        // TODO add your handling code here:
        btnQuitar.setEnabled(false);
        frmvistaarticulo frm = new frmvistaarticulo();
        frm.toFront();
        frm.setVisible(true);
    }//GEN-LAST:event_BtnBuscarArticuloActionPerformed

    private void BtnBuscarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarProveedorActionPerformed
        // TODO add your handling code here:
        
        frmvistaproveedor frm = new frmvistaproveedor();
        frm.toFront();
        frm.setVisible(true);
        
        
        
    }//GEN-LAST:event_BtnBuscarProveedorActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        fingreso func= new fingreso();
        
        
        Date fecha1 = new Date();
        
        fecha1= dateChooserCombo2.getCurrent().getTime();
        
        DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        
        String fecha1completa =dateformat.format(fecha1);

        

        Date fecha2 = new Date();
        
        fecha2= dateChooserCombo3.getCurrent().getTime();
        
        String fecha2completa =dateformat.format(fecha2);

        mostrarentrefecha(fecha1completa, fecha2completa);
        
                
        //JOptionPane.showConfirmDialog(rootPane, "la fecha "+fecha1completa + "y la fecha" +fecha2completa);
  
        
    }//GEN-LAST:event_btnBuscarActionPerformed

            private Connection connection=new conexion().conectar();
    
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        if(Detalles.getRowCount()<= 0){
            JOptionPane.showConfirmDialog(rootPane, "Debes agregar ingresos");
            BtnBuscarArticulo.requestFocus();
            return;
        }
        if(txtProveedor.getText().length() == 0){
            JOptionPane.showConfirmDialog(rootPane, "Debes ingresar un Proveedor");
            BtnBuscarProveedor.requestFocus();
            return;
        }
        
        fingreso FI = new fingreso();
        
        vingreso VI = new vingreso();
        
        VI.setId_trabajador(Integer.parseInt(idtrabajador));
        VI.setId_proveedor(Integer.parseInt(idProveedor));
        Date fecha = new Date();
        fecha= dateChooserCombo1.getCurrent().getTime();
        DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        String fechacompleta =dateformat.format(fecha);
        VI.setFecha(fechacompleta);
        VI.setPrecio_total(TotalPagado);
        if(FI.insertar(VI)){
            JOptionPane.showMessageDialog(rootPane, "Se guardo correctamente, se mostrará el comprobante");

        }
        
        int identity = FI.Obteneridentity();
            
        InsertarDetalle(identity, FI);
        
        limpiar();
        limpiardetalle();
        limpiartabladetalles();
        accion="Guardar";
        ListDetalles.clear();
        inhabilitar();
        inhabilitardetalle();
        ActualizarTotalPagado();
        mostrar();
        
        //reporte
        if(FI.Obteneridentity()==0)
        {
            JOptionPane.showMessageDialog(rootPane, "No existe ningún comprobante");
        }
        else{

                Map p = new HashMap();
                p.put("idingreso", FI.Obteneridentity());
                JasperReport report;
                JasperPrint print;

                try {
                    report = JasperCompileManager.compileReport(new File("").getAbsolutePath() + "/src/Reportes/rptingreso.jrxml");

                    print = JasperFillManager.fillReport(report, p, connection);

                    JasperViewer view = new JasperViewer(print, false);

                    view.setTitle("Comprobante de Ingreso");

                    view.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
        
        
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularActionPerformed
        // TODO add your handling code here:
        if(accion.equals("Anular")){
            fingreso FI =  new fingreso();
            vingreso VI = new vingreso();
            
            VI.setId_ingreso(idingreso);
            
            FI.Anular(VI);
            
            limpiar();
            limpiardetalle();
            inhabilitar();
            inhabilitardetalle();
            limpiartabladetalles();
            ActualizarTotalPagado();
            ListDetalles.clear();
            limpiartabladetalles();
            jTable1.setModel(Detalles);
            accion="";
            btnNuevo.setEnabled(true);
            
            mostrar();
            btnAnular.setEnabled(false);
            btnImprimir.setEnabled(false);

        }
        
    }//GEN-LAST:event_btnAnularActionPerformed

    private void tablalistadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablalistadoMouseClicked
        // TODO add your handling code here:
        accion="Anular";
        limpiar();
        limpiardetalle();
        limpiartabladetalles();
        ListDetalles.clear();
        inhabilitar();
        inhabilitardetalle();
        Row = tablalistado.rowAtPoint(evt.getPoint());
        
        int identity = Integer.parseInt(tablalistado.getValueAt(Row,0).toString());
        
        mostrardetalle(identity);
        
        ActualizarTotalPagado();
        
        idingreso = identity;
        
        jTable1.requestFocus();
        jTabbedPane1.setSelectedIndex(0);
        btnNuevo.setEnabled(false);
        btnCancelar.setEnabled(true);
        btnAnular.setEnabled(true);
        btnImprimir.setEnabled(true);
    }//GEN-LAST:event_tablalistadoMouseClicked

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        // TODO add your handling code here:
        
         fingreso func=new fingreso();

            
        try { 

            func.ObtenerComprobante(idingreso);
            
        } catch (Exception e) {
              JOptionPane.showMessageDialog(rootPane, "Error en la conexion");
              return;
        }
        

                   //reporte
                Map p = new HashMap();
                p.put("idingreso", idingreso);
                JasperReport report;
                JasperPrint print;

                try {
                    report = JasperCompileManager.compileReport(new File("").getAbsolutePath() + "/src/Reportes/rptingreso.jrxml");

                    print = JasperFillManager.fillReport(report, p, connection);

                    JasperViewer view = new JasperViewer(print, false);

                    view.setTitle("Comprobante de Ingreso");

                    view.setVisible(true);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                } 
        

        
        
        
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed
    
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
            java.util.logging.Logger.getLogger(frmIngreso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmIngreso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmIngreso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmIngreso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmIngreso().setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnBuscarArticulo;
    private javax.swing.JButton BtnBuscarProveedor;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAnular;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JButton btnSalir;
    public static datechooser.beans.DateChooserCombo dateChooserCombo1;
    public static datechooser.beans.DateChooserCombo dateChooserCombo2;
    public static datechooser.beans.DateChooserCombo dateChooserCombo3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotalPagado;
    public static javax.swing.JTable tablalistado;
    public static javax.swing.JTextField txtArticulo;
    private javax.swing.JTextField txtPrecioCompra;
    private javax.swing.JTextField txtPrecioVenta;
    public static javax.swing.JTextField txtProveedor;
    private javax.swing.JTextField txtStockInicial;
    // End of variables declaration//GEN-END:variables
}
