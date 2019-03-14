/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Datos.vdetalle_ingreso;
import Datos.vdetalle_venta;
import Datos.vventa;
import Logica.conexion;
import Logica.fingreso;
import Logica.fventa;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Mirlu
 */
public class frmVenta extends javax.swing.JInternalFrame {
    public static String idtrabajador;
    public static String CodDetalleIngreso;
    public static String idProveedor;
    /**
     * Creates new form frmIngreso
     */
    
    String [] titulos = {"Articulo", "Cantidad", "Precio", "Subtotal"};
    
    DefaultTableModel Detalles = new DefaultTableModel(null,titulos);
    String [] Registrar = new String[4];
    int Row;
    
    String accion = "Guardar";
    
    List<vdetalle_venta> ListDetalles = new ArrayList<>();
    List<vdetalle_ingreso> ListDetallesingreso = new ArrayList<>();
    double Subtotal;
    double Impuesto = 12d;
    double Total;
    
    int idventa;
    public List<Integer> ListIDs = new ArrayList<>();
    
    
    
    
    public frmVenta() {
        initComponents();
        
        mostrar();
        
        inhabilitar();
        inhabilitardetalle();
        
        lblSubtotal.setText("");
        lblTotalP.setText("");
        
        jTable1.setModel(Detalles);
        jTable1.setEnabled(false);
    }
    
    
    void inhabilitar (){
        TxtClienteCedula.setEnabled(false);
        TxtClienteNombre.setEnabled(false);
        dateChooserCombo1.setEnabled(false);
        TxtImpuesto.setEnabled(false);
        
        btnGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);
        btnAnular.setEnabled(false);
        btnImprimir.setEnabled(false);
        BtnBuscarProveedor.setEnabled(false);
    }
    
    
    
    void habilitar (){
        TxtClienteCedula.setEnabled(true);
        TxtClienteNombre.setEnabled(true);
        dateChooserCombo1.setEnabled(true);
        TxtImpuesto.setEnabled(true);
        
        btnGuardar.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnAnular.setEnabled(true);
        btnImprimir.setEnabled(true);
        BtnBuscarProveedor.setEnabled(true);
    }    

    
    void limpiar(){
    
        TxtClienteCedula.setText("");
        TxtClienteNombre.setText("");
        TxtImpuesto.setText("");
        Calendar today = Calendar.getInstance();
        today.setTime(new Date());
        dateChooserCombo1.setSelectedDate(today);
    }
    
    void habilitardetalle(){
        txtCodigoArticulo.setEnabled(true);
        txtNombreArticulo.setEnabled(true);
        txtPrecioCompra.setEnabled(true);
        txtPrecioVenta.setEnabled(true);
        txtStockInicial.setEnabled(true);
        txtcantidad.setEnabled(true);
        
        btnQuitar.setEnabled(true);
        btnAgregar.setEnabled(true);
        BtnBuscarArticulo.setEnabled(true);
    }
    void inhabilitardetalle(){
        txtCodigoArticulo.setEnabled(false);
        txtNombreArticulo.setEnabled(false);
        txtPrecioCompra.setEnabled(false);
        txtPrecioVenta.setEnabled(false);
        txtStockInicial.setEnabled(false);
        txtcantidad.setEnabled(false);
        
        btnQuitar.setEnabled(false);
        btnAgregar.setEnabled(false);
        BtnBuscarArticulo.setEnabled(false);
    }
    void limpiardetalle(){
        txtCodigoArticulo.setText("");
        txtNombreArticulo.setText("");
        txtPrecioCompra.setText("");
        txtPrecioVenta.setText("");
        txtStockInicial.setText("");
        CodDetalleIngreso = "";
        txtcantidad.setText("");
    }
    void ActualizarTotalPagado(){
        Subtotal = 0;
        
        if(TxtImpuesto.getText().length() == 0)
            Impuesto = 0;
        else
            Impuesto = Double.parseDouble(TxtImpuesto.getText());
        
        double impuesto = Impuesto / 100;
        if(Detalles.getRowCount()> 0){
            for(int i = Detalles.getRowCount() - 1; i>-1; i--){
                    Subtotal += Double.parseDouble(jTable1.getValueAt(i,3).toString());
            }
        }
        lblSubtotal.setText(Double.toString(Subtotal) + "BsS");
        Total = Subtotal * (impuesto + 1);
        lblTotalP.setText(Double.toString(Total) + "BsS");
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
            fventa func= new fventa();
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
            fventa func= new fventa();
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
            fventa func= new fventa();
            Detalles = func.MostrarDetalle(identity);
            
            jTable1.setModel(Detalles);
            //ocultar_columnas();
            ActualizarTotalPagado();
            ListIDs = func.ListIDs;
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(rootPane, e);
        }
    }
    void InsertarDetalle(int identity, fventa FV){
        try{
        for(int i = 0; i < ListDetalles.size(); i++){
            vdetalle_venta a = ListDetalles.get(i);
            a.setId_venta(identity);
            if(!FV.insertarDetalle(a) || !FV.ActualizarStock(a, -a.getCantidad())){
                JOptionPane.showConfirmDialog(rootPane, "ERROR");
                return;
            }
        }
        }
        catch (Exception e) {
            JOptionPane.showConfirmDialog(rootPane, e);
        }
    }
    boolean PuedeAgregar(int idarticulo){
        if(Detalles.getRowCount()<= 0)
            return true;
        for(int i = 0; i < ListDetalles.size(); i++){
            int a = ListDetalles.get(i).getId_detalle_ingreso();
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
        btnCancelar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txtStockInicial = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCodigoArticulo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        BtnBuscarArticulo = new javax.swing.JButton();
        txtPrecioVenta = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtPrecioCompra = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        btnQuitar = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        txtcantidad = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtNombreArticulo = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        TxtClienteCedula = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        BtnBuscarProveedor = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        dateChooserCombo1 = new datechooser.beans.DateChooserCombo();
        jLabel15 = new javax.swing.JLabel();
        lblSubtotal = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        TxtClienteNombre = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        TxtImpuesto = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        lblTotalP = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        btnAnular = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();
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
        btnSalir2 = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconifiable(true);
        setMaximizable(true);

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 0));
        jLabel5.setText("Ventas");

        btnNuevo.setBackground(new java.awt.Color(255, 255, 255));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/nuevo.GIF"))); // NOI18N
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

        btnCancelar.setBackground(new java.awt.Color(255, 255, 255));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/cancelar.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(204, 255, 153));

        txtStockInicial.setEditable(false);
        txtStockInicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStockInicialActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 0));
        jLabel8.setText("Disponibilidad");

        txtCodigoArticulo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoArticuloKeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 0));
        jLabel4.setText("Artículo:");

        BtnBuscarArticulo.setText("Buscar");
        BtnBuscarArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarArticuloActionPerformed(evt);
            }
        });

        txtPrecioVenta.setEditable(false);

        jLabel13.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 51, 0));
        jLabel13.setText("Precio Venta");

        txtPrecioCompra.setEditable(false);

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

        jLabel17.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 51, 0));
        jLabel17.setText("Cantidad");

        txtcantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcantidadActionPerformed(evt);
            }
        });
        txtcantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcantidadKeyPressed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 51, 0));
        jLabel20.setText("Codigo");

        jLabel21.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 51, 0));
        jLabel21.setText("Nombre");

        txtNombreArticulo.setEditable(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel21)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel20)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtCodigoArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnBuscarArticulo))
                    .addComponent(txtNombreArticulo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtPrecioCompra)
                    .addComponent(txtPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtStockInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnQuitar))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                        .addComponent(btnAgregar)))
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
                                .addComponent(btnAgregar))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtStockInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnQuitar)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtCodigoArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnBuscarArticulo)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(txtNombreArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        TxtClienteCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtClienteCedulaKeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 0));
        jLabel6.setText("Cliente:");

        BtnBuscarProveedor.setText("Buscar");
        BtnBuscarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarProveedorActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 51, 0));
        jLabel11.setText("Fecha");

        dateChooserCombo1.setCalendarPreferredSize(new java.awt.Dimension(333, 200));

        jLabel15.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 51, 0));
        jLabel15.setText("Subtotal:");

        lblSubtotal.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        lblSubtotal.setForeground(new java.awt.Color(0, 51, 0));
        lblSubtotal.setText("total");

        jLabel7.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 51, 0));
        jLabel7.setText("Cedula");

        TxtClienteNombre.setEditable(false);

        jLabel12.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 51, 0));
        jLabel12.setText("Nombre");

        jLabel18.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 51, 0));
        jLabel18.setText("Impuesto");

        lblTotalP.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        lblTotalP.setForeground(new java.awt.Color(0, 51, 0));
        lblTotalP.setText("total");

        jLabel19.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 51, 0));
        jLabel19.setText("Total:");

        btnAnular.setBackground(new java.awt.Color(255, 255, 255));
        btnAnular.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/eliminar.png"))); // NOI18N
        btnAnular.setText("Anular");
        btnAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularActionPerformed(evt);
            }
        });

        btnImprimir.setBackground(new java.awt.Color(255, 255, 255));
        btnImprimir.setText("Imprimir");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTotalP, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(105, 105, 105))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(10, 10, 10)
                                .addComponent(TxtClienteNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel7)
                                .addGap(10, 10, 10)
                                .addComponent(TxtClienteCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(BtnBuscarProveedor)
                                .addGap(29, 29, 29)
                                .addComponent(jLabel11))
                            .addComponent(jLabel18))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dateChooserCombo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TxtImpuesto)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(320, 320, 320)
                        .addComponent(jLabel5)))
                .addContainerGap(522, Short.MAX_VALUE))
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
                        .addComponent(TxtClienteCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BtnBuscarProveedor)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateChooserCombo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TxtImpuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TxtClienteNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnGuardar)
                        .addComponent(btnCancelar)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTotalP, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnAnular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnCancelar, btnGuardar, btnNuevo});

        jTabbedPane1.addTab("Ventas", jPanel2);

        jPanel4.setBackground(new java.awt.Color(0, 102, 0));
        jPanel4.setForeground(new java.awt.Color(204, 255, 204));

        jLabel9.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 255, 204));
        jLabel9.setText("Listado de Ventas");

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

        dateChooserCombo2.setCalendarPreferredSize(new java.awt.Dimension(333, 200));

        dateChooserCombo3.setCalendarPreferredSize(new java.awt.Dimension(333, 200));

        btnSalir2.setBackground(new java.awt.Color(255, 255, 255));
        btnSalir2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Files/salir.gif"))); // NOI18N
        btnSalir2.setText("Salir");
        btnSalir2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalir2ActionPerformed(evt);
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
                        .addGap(18, 18, 18)
                        .addComponent(btnSalir2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(45, Short.MAX_VALUE))
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
                                .addComponent(btnSalir2)))))
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

        jTabbedPane1.addTab("Listado de Ventas", jPanel1);

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
        // TODO add your handling code here
        limpiar();
        habilitar();
        limpiardetalle();
        habilitardetalle();
        btnAnular.setEnabled(false);
        btnImprimir.setEnabled(false);
        btnGuardar.setText("Guardar");
        accion="Guardar";
        ListDetalles.clear();
        limpiartabladetalles();
        ActualizarTotalPagado();
        btnQuitar.setEnabled(false);
        Impuesto = 12d;
        TxtImpuesto.setText(Double.toString(Impuesto));
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
        if(txtNombreArticulo.getText().length() == 0){
            JOptionPane.showConfirmDialog(rootPane, "Debes Seleccionar un Articulo");
            BtnBuscarArticulo.requestFocus();
            return;
        }
        if(txtcantidad.getText().length() == 0){
            JOptionPane.showConfirmDialog(rootPane, "Debes Especificar una cantidad a vender");
            txtcantidad.requestFocus();
            return;
        }
        if(!PuedeAgregar(Integer.parseInt(CodDetalleIngreso)) && accion.equals("Guardar")){
            JOptionPane.showConfirmDialog(rootPane, "Debes Selecciar un articulo que no este ya agregado");
            BtnBuscarArticulo.requestFocus();
            return;
        }
        vdetalle_venta DV = new vdetalle_venta();
        vdetalle_ingreso DI = new vdetalle_ingreso();
        
        Registrar[0] = txtNombreArticulo.getText();
        DV.setId_detalle_ingreso(Integer.parseInt(CodDetalleIngreso));
        Registrar[2] = txtPrecioVenta.getText();
        DV.setPrecio(Double.parseDouble(Registrar[2]));
        Registrar[1] = txtcantidad.getText();
        DV.setCantidad(Integer.parseInt(Registrar[1]));
        double a = DV.getPrecio() * DV.getCantidad();
        Registrar[3] = Double.toString(a);
        DI.setStock_actual(Integer.parseInt(txtStockInicial.getText()));
        DI.setPrecio_compra(Double.parseDouble(txtPrecioCompra.getText()));
        
        
        if(DV.getCantidad() > DI.getStock_actual()){
            JOptionPane.showConfirmDialog(rootPane, "Debes Poner una cantidad menor o igual a la disponibilidad");
            txtcantidad.requestFocus();
            return;
        }
        if(accion.equals("Guardar")){
        Detalles.addRow(Registrar);
        ListDetalles.add(DV);
        ListDetallesingreso.add(DI);
        }
        else if (accion.equals("Editar")){
            jTable1.setValueAt(Registrar[0], Row, 0);
            jTable1.setValueAt(Registrar[1], Row, 1);
            jTable1.setValueAt(Registrar[2], Row, 2);
            jTable1.setValueAt(Registrar[3], Row, 3);
            ListDetalles.set(Row, DV);
            ListDetallesingreso.set(Row, DI);
        }
        limpiardetalle();
        ActualizarTotalPagado();
        accion = "Guardar";
        txtCodigoArticulo.requestFocus();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        Row = jTable1.rowAtPoint(evt.getPoint());
        
        txtNombreArticulo.setText(jTable1.getValueAt(Row,0).toString());
        txtPrecioVenta.setText(jTable1.getValueAt(Row,2).toString());
        txtcantidad.setText(jTable1.getValueAt(Row,1).toString());
        txtStockInicial.setText(Integer.toString(ListDetallesingreso.get(Row).getStock_actual()));
        txtPrecioCompra.setText(Double.toString(ListDetallesingreso.get(Row).getPrecio_compra()));
        CodDetalleIngreso = Integer.toString(ListDetalles.get(Row).getId_detalle_ingreso());
        
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
            ListDetallesingreso.remove(Row);
        }
    }//GEN-LAST:event_btnQuitarActionPerformed

    private void BtnBuscarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarArticuloActionPerformed
        // TODO add your handling code here:
        
        try {

            DefaultTableModel modelo;
            fventa func= new fventa();

            modelo=func.BuscarArticuloNombre(txtCodigoArticulo.getText());

            if(func.totalregistros>0)
            {
                String id, articulo, disponible, precioC, precioV;
            
                id = modelo.getValueAt(0, 0).toString();
                articulo = modelo.getValueAt(0, 1).toString();
                disponible = modelo.getValueAt(0, 3).toString();
                precioC = modelo.getValueAt(0, 4).toString();
                precioV = modelo.getValueAt(0, 5).toString();
            
                CodDetalleIngreso = id;
                txtNombreArticulo.setText(articulo);
                txtStockInicial.setText(disponible);
                txtPrecioCompra.setText(precioC);
                txtPrecioVenta.setText(precioV);           
                btnQuitar.setEnabled(false);
                txtcantidad.requestFocus();
            }
            else
            {
                if(txtCodigoArticulo.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(rootPane,"Introduzca el Codigo","Acceso al Sistema", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(rootPane,"No existe un articulo en el stock con este Codigo","Acceso al Sistema", JOptionPane.INFORMATION_MESSAGE);
                }

            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
    }//GEN-LAST:event_BtnBuscarArticuloActionPerformed

    private void txtcantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcantidadActionPerformed

    private void BtnBuscarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarProveedorActionPerformed
        // TODO add your handling code here:
        
        try {
            
            DefaultTableModel modelo;
            fventa func= new fventa();
            
            
            modelo=func.BuscarCliente(TxtClienteCedula.getText());
            
            if(func.totalregistros>0)
            {
                TxtClienteNombre.setText(modelo.getValueAt(0, 1).toString());
                
            }
            else
            {
                if(TxtClienteCedula.getText().equals(""))
                {
                     JOptionPane.showMessageDialog(rootPane,"Introduzca la Cedula","Acceso al Sistema", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                                    
                JOptionPane.showMessageDialog(rootPane,"El Cliente no está registrado","Acceso al Sistema", JOptionPane.ERROR_MESSAGE);
                frmvistacliente frm = new frmvistacliente();
                frm.txtCedulaCliente.setText(TxtClienteCedula.getText());
                frm.toFront();
                frm.setVisible(true);
                }

            }
            
            
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
        
        
    }//GEN-LAST:event_BtnBuscarProveedorActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        
        fventa func= new fventa();
        
        
        Date fecha1 = new Date();
        
        fecha1= dateChooserCombo2.getCurrent().getTime();
        
        DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        
        String fecha1completa =dateformat.format(fecha1);

        

        Date fecha2 = new Date();
        
        fecha2= dateChooserCombo3.getCurrent().getTime();
        
        String fecha2completa =dateformat.format(fecha2);

        mostrarentrefecha(fecha1completa, fecha2completa);
        
        
        
    }//GEN-LAST:event_btnBuscarActionPerformed

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
        
        lblSubtotal.setText(tablalistado.getValueAt(Row,5).toString() + "BsS");
        lblTotalP.setText(tablalistado.getValueAt(Row,7).toString() + "BsS");
        
        idventa = identity;
        
        jTable1.requestFocus();
        jTabbedPane1.setSelectedIndex(0);
        btnNuevo.setEnabled(false);
        btnCancelar.setEnabled(true);
        btnAnular.setEnabled(true);
        btnImprimir.setEnabled(true);
    }//GEN-LAST:event_tablalistadoMouseClicked

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        
        if(Detalles.getRowCount()<= 0){
            JOptionPane.showConfirmDialog(rootPane, "Debes agregar ingresos");
            BtnBuscarArticulo.requestFocus();
            return;
        }
        if(TxtClienteNombre.getText().length() == 0){
            JOptionPane.showConfirmDialog(rootPane, "Debes ingresar un Cliente");
            BtnBuscarProveedor.requestFocus();
            return;
        }
        if(TxtImpuesto.getText().length() == 0){
            JOptionPane.showConfirmDialog(rootPane, "Debes ingresar el Impuesto");
            TxtImpuesto.requestFocus();
            return;
        }
        
        fventa FV = new fventa();
        
        vventa VV = new vventa();
        
        VV.setId_trabajador(Integer.parseInt(idtrabajador));
        VV.setId_cliente(Integer.parseInt(TxtClienteCedula.getText()));
        Date fecha = new Date();
        fecha= dateChooserCombo1.getCurrent().getTime();
        DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        String fechacompleta =dateformat.format(fecha);
        VV.setFecha(fechacompleta);
        VV.setSubtotal(Subtotal);
        VV.setImpuesto(Double.parseDouble(TxtImpuesto.getText()));
        VV.setTotal(Total);
        if(FV.insertar(VV)){
            JOptionPane.showMessageDialog(rootPane, "Se guardo correctamente, se mostrará el comprobante");

        }
        
        int identity = FV.Obteneridentity();
            
        InsertarDetalle(identity, FV);
        
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
        if(FV.Obteneridentity()==0)
        {
            JOptionPane.showMessageDialog(rootPane, "No existe ningúna factura");
        }
        else{

                Map p = new HashMap();
                p.put("idventa", FV.Obteneridentity());
                JasperReport report;
                JasperPrint print;

                try {
                    report = JasperCompileManager.compileReport(new File("").getAbsolutePath() + "/src/Reportes/rptfactura.jrxml");

                    print = JasperFillManager.fillReport(report, p, connection);

                    JasperViewer view = new JasperViewer(print, false);

                    view.setTitle("Factura de Venta");

                    view.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
        
         
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnSalir2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalir2ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnSalir2ActionPerformed

    
            private Connection connection=new conexion().conectar();
   
   
    private void btnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularActionPerformed
        // TODO add your handling code here:
        if(accion.equals("Anular")){
            fventa FI =  new fventa();
            vventa VI = new vventa();

            VI.setId_venta(idventa);

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

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        // TODO add your handling code here:

        fventa func=new fventa();

        try {

            func.ObtenerComprobante(idventa);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Error en la conexion");
            return;
        }

        //reporte
        Map p = new HashMap();
        p.put("idventa", idventa);
        JasperReport report;
        JasperPrint print;

        try {
            report = JasperCompileManager.compileReport(new File("").getAbsolutePath() + "/src/Reportes/rptfactura.jrxml");

            print = JasperFillManager.fillReport(report, p, connection);

            JasperViewer view = new JasperViewer(print, false);

            view.setTitle("Factura de Venta");

            view.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }

        btnImprimir.setEnabled(false);

    }//GEN-LAST:event_btnImprimirActionPerformed

    private void txtCodigoArticuloKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoArticuloKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            BtnBuscarArticulo.doClick();
        }
    }//GEN-LAST:event_txtCodigoArticuloKeyPressed

    private void TxtClienteCedulaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtClienteCedulaKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            BtnBuscarProveedor.doClick();
        }
    }//GEN-LAST:event_TxtClienteCedulaKeyPressed

    private void txtcantidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcantidadKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            btnAgregar.doClick();
        }
    }//GEN-LAST:event_txtcantidadKeyPressed

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
            java.util.logging.Logger.getLogger(frmVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmVenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmVenta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnBuscarArticulo;
    private javax.swing.JButton BtnBuscarProveedor;
    public static javax.swing.JTextField TxtClienteCedula;
    public static javax.swing.JTextField TxtClienteNombre;
    public static javax.swing.JTextField TxtImpuesto;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAnular;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JButton btnSalir2;
    private datechooser.beans.DateChooserCombo dateChooserCombo1;
    private datechooser.beans.DateChooserCombo dateChooserCombo2;
    private datechooser.beans.DateChooserCombo dateChooserCombo3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
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
    public static javax.swing.JLabel lblSubtotal;
    private javax.swing.JLabel lblTotal;
    public static javax.swing.JLabel lblTotalP;
    private javax.swing.JTable tablalistado;
    public static javax.swing.JTextField txtCodigoArticulo;
    public static javax.swing.JTextField txtNombreArticulo;
    public static javax.swing.JTextField txtPrecioCompra;
    public static javax.swing.JTextField txtPrecioVenta;
    public static javax.swing.JTextField txtStockInicial;
    public static javax.swing.JTextField txtcantidad;
    // End of variables declaration//GEN-END:variables
}
