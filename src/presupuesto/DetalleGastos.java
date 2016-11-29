/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presupuesto;

import dao.CuotaObject;
import dao.GastoObject;
import dao.PendientesObject;
import dao.PresupuestoDAO;
import dao.RubrosObject;
import dao.TiposPagoSingleton;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.TableModel;

/**
 *
 * @author MaFuen1
 */
public class DetalleGastos extends javax.swing.JDialog {

    private final int INSERTAR = 1;
    private final int MODIFICAR = 2;
     ImageIcon sumatoriaicon;
    
    
    String[] columnNames = {"IDGASTO","FECHA",
                        "LUGAR","TIPO PAGO","MONTO"           
                        };
    String rowData[][];    
    RubrosObject rubrosobject;
    String path;
            
    CellEditorListener ChangeNotification = new CellEditorListener() {
        public void editingCanceled(ChangeEvent e) {
            System.out.println("The user canceled editing.");
        }

        public void editingStopped(ChangeEvent e) {
            System.out.println("The user stopped editing successfully.");
            modificar();
        }
    };

    
    /**
     * Creates new form DetalleGastos
     */
    public DetalleGastos(java.awt.Frame parent, boolean modal,RubrosObject object,String p) {
        super(parent, modal);
        initComponents();
        sumatoriaicon =new ImageIcon(getClass().getResource("/img/sumatoria.jpg"));
        btn_sumatoria.setIcon(sumatoriaicon);
                
        this.rubrosobject=object;
        this.path=p;
        cargarGastos();
        setLocationRelativeTo(null);
        
        jTable1.getDefaultEditor(String.class).addCellEditorListener(ChangeNotification);

    }
    
     
    public DetalleGastos() {
    
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        lbl_total_gastado = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btn_insertar = new javax.swing.JButton();
        btn_modificar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_pagado = new javax.swing.JButton();
        btn_sumatoria = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lbl_rubro = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lbl_monto_presupuestado = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbl_tipo_pago = new javax.swing.JLabel();
        btn_corregir_saldo_rubro = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        lbl_saldo_rubro = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Listado de gastos incluidos");

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            this.rowData,
            this.columnNames
        ));
        jScrollPane1.setViewportView(jTable1);

        lbl_total_gastado.setFont(new java.awt.Font("Arial Unicode MS", 1, 18)); // NOI18N
        lbl_total_gastado.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_total_gastado.setText("0.00");
        lbl_total_gastado.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel10.setFont(new java.awt.Font("Arial Unicode MS", 1, 18)); // NOI18N
        jLabel10.setText("Total de gastos:");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_insertar.setText("Insertar Gasto");
        btn_insertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_insertarActionPerformed(evt);
            }
        });

        btn_modificar.setText("Modificar Gasto");
        btn_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarActionPerformed(evt);
            }
        });

        btn_eliminar.setText("Eliminar Gasto");
        btn_eliminar.setToolTipText("");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });

        btn_pagado.setLabel("ASIGNAR PAGO DEL SALDO");
        btn_pagado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pagadoActionPerformed(evt);
            }
        });

        btn_sumatoria.setBackground(new java.awt.Color(255, 255, 255));
        btn_sumatoria.setPreferredSize(new java.awt.Dimension(37, 9));
        btn_sumatoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sumatoriaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btn_insertar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_modificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_eliminar))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btn_pagado, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_sumatoria, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_eliminar)
                    .addComponent(btn_modificar)
                    .addComponent(btn_insertar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_pagado)
                    .addComponent(btn_sumatoria, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbl_rubro.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        lbl_rubro.setText("jLabel1");
        lbl_rubro.setRequestFocusEnabled(false);

        jLabel2.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel2.setText("Presupuestado:");

        jLabel1.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel1.setText("Rubro:");

        lbl_monto_presupuestado.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        lbl_monto_presupuestado.setText("jLabel3");

        jLabel4.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        jLabel4.setText("Tipo de pago:");

        lbl_tipo_pago.setFont(new java.awt.Font("Arial Unicode MS", 0, 14)); // NOI18N
        lbl_tipo_pago.setText("jLabel3");

        btn_corregir_saldo_rubro.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btn_corregir_saldo_rubro.setLabel("Corregir Saldo del Rubro");
        btn_corregir_saldo_rubro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_corregir_saldo_rubroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lbl_rubro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(16, 16, 16))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lbl_monto_presupuestado, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 594, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lbl_tipo_pago, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(btn_corregir_saldo_rubro, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_rubro)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(lbl_monto_presupuestado))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_tipo_pago)
                            .addComponent(jLabel4)))
                    .addComponent(btn_corregir_saldo_rubro))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Arial Unicode MS", 1, 18)); // NOI18N
        jLabel3.setText("Saldo del Rubro:");

        lbl_saldo_rubro.setFont(new java.awt.Font("Arial Unicode MS", 0, 18)); // NOI18N
        lbl_saldo_rubro.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_saldo_rubro.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbl_total_gastado, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                            .addComponent(lbl_saldo_rubro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(lbl_total_gastado))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lbl_saldo_rubro, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        // TODO add your handling code here:
        try {
                Utilities utils = new Utilities();
                PresupuestoDAO con = PresupuestoDAO.getInstance();
                
                TableModel model = jTable1.getModel();
                
                int row = jTable1.getSelectedRow();
                int col = jTable1.getSelectedColumn();  
                
                if  (row!=-1 && col!=-1)
                {                                
                    String idgasto = model.getValueAt(row, 0).toString();
                    String nombre = model.getValueAt(row, 2).toString();
                    String monto = utils.priceWithoutCommas(model.getValueAt(row, 4).toString());
                    

                    if(JOptionPane.showConfirmDialog(null, "¿Desea eliminar el gasto '"+nombre+"'?",
                                                            "Warning",
                                                            JOptionPane.OK_CANCEL_OPTION,
                                                            JOptionPane.QUESTION_MESSAGE)
                                                            ==JOptionPane.OK_OPTION){                                                
                    
                       con.sp_eliminar_gasto(idgasto, this.rubrosobject.getIdentificador());
                    
                                           JOptionPane.showMessageDialog(null,
                    "Gasto '"+nombre+"' eliminado.");
                       
                      cargarGastos();
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null,
                    "Debe seleccionar un gasto de la tabla.");
                }                                    
            } catch (Exception ex) {
                DialogError error = new DialogError (null,true,ex);
                error.setVisible(true);
            }          
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void btn_insertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_insertarActionPerformed
        // TODO add your handling code here:
            Gastos gastos = new Gastos (new JFrame(),true,this.INSERTAR,rubrosobject);
            gastos.setVisible(true); 
           
                        
            PresupuestoDAO presupuestodao = null;
            try {
                presupuestodao = PresupuestoDAO.getInstance();
                
                this.rubrosobject = presupuestodao.getRubro (this.rubrosobject.getIdentificador());
                
                
            }catch (SQLException ex) {
                   DialogError error = new DialogError (null,true,ex);
                   error.setVisible(true);
                }
            
            if(!gastos.cancelar)
            cargarGastos();
    }//GEN-LAST:event_btn_insertarActionPerformed

    private void btn_pagadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pagadoActionPerformed
        // TODO add your handling code here:
            PresupuestoDAO presupuestodao = null;
            try {
                presupuestodao = PresupuestoDAO.getInstance();
                
                this.rubrosobject = presupuestodao.getRubro (this.rubrosobject.getIdentificador());
                
                asignarcomoPagado(this.rubrosobject);
                
                this.rubrosobject = presupuestodao.getRubro (this.rubrosobject.getIdentificador());
                
            }catch (Exception ex) {
                DialogError error = new DialogError (null,true,ex);
                error.setVisible(true);
            }
            
            cargarGastos();
            
    }//GEN-LAST:event_btn_pagadoActionPerformed

    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed
        // TODO add your handling code here:
        Utilities utils = new Utilities ();
        TableModel model = jTable1.getModel();
        GastoObject obj = new GastoObject();

        int row = jTable1.getSelectedRow();
        int col = jTable1.getSelectedColumn();  

        if  (row!=-1 && col!=-1)
        {                                
            try {                                
                String idgasto = model.getValueAt(row, 0).toString();
                String fecha = model.getValueAt(row, 1).toString();
                String lugar = model.getValueAt(row, 2).toString();
                String tipo_pago = model.getValueAt(row, 3).toString();
                String monto = utils.priceWithoutCommas(model.getValueAt(row, 4).toString());
                
                obj.setIdgasto(idgasto);
                obj.setFecha(fecha);
                obj.setLugar(lugar);
                obj.setTipo_pago(tipo_pago);
                obj.setMonto(monto);
                
                Gastos gastos = new Gastos (new JFrame(),true,this.MODIFICAR,rubrosobject,obj);
                gastos.setVisible(true);
                
                
                
                PresupuestoDAO presupuestodao = null;
                try {
                    presupuestodao = PresupuestoDAO.getInstance();
                    
                    this.rubrosobject = presupuestodao.getRubro (this.rubrosobject.getIdentificador());
                    
                    
                }catch (Exception ex) {
                    DialogError error = new DialogError (null,true,ex);
                    error.setVisible(true);
                }
                
                cargarGastos();
            }catch (ParseException ex) {
                               DialogError error = new DialogError (null,true,ex);         
                                error.setVisible(true);
                }
        }
        else
        {
            JOptionPane.showMessageDialog(null,
            "Debe seleccionar un gasto de la tabla.");
        } 
    }//GEN-LAST:event_btn_modificarActionPerformed

    private void btn_corregir_saldo_rubroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_corregir_saldo_rubroActionPerformed
        // TODO add your handling code here:
        Utilities utils = new Utilities();
        PresupuestoDAO presupuestodao = null;
            try {
                presupuestodao = PresupuestoDAO.getInstance();
                
                double gastado = Double.parseDouble(utils.priceWithoutCommas(lbl_total_gastado.getText()));
                double monto_presupuestado = Double.parseDouble(utils.priceWithoutCommas(lbl_monto_presupuestado.getText()));
                double saldo = monto_presupuestado - gastado;
                
                lbl_saldo_rubro.setText( utils.priceWithDecimal(saldo) );
                
                presupuestodao.modificarMontosRubro(this.rubrosobject.getIdentificador(), utils.priceWithoutCommas(lbl_monto_presupuestado.getText()), String.valueOf(saldo));
                
                btn_corregir_saldo_rubro.setVisible(false);
                
            } catch (Exception ex) {
               DialogError error = new DialogError (null,true,ex);
               error.setVisible(true);
            }
        
        
    }//GEN-LAST:event_btn_corregir_saldo_rubroActionPerformed

    private void btn_sumatoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sumatoriaActionPerformed
        // TODO add your handling code here:
        btn_sumatoria();
    }//GEN-LAST:event_btn_sumatoriaActionPerformed


    
public void btn_sumatoria(){
        TableModel model = jTable1.getModel();
        Utilities utils = new Utilities();
        
        int rowArr[] = jTable1.getSelectedRows();
        String monto="", saldo="";
        double sum_saldo=0, sum_monto=0, temp=0;
   
        
        for (int i=0; i<rowArr.length ; i++){
        
            if  (rowArr[i]!=-1)
            {                                
                try {
                    monto = jTable1.getValueAt(rowArr[i], 4).toString();
                    
                    
                    monto=utils.priceWithoutCommas(monto);

                    sum_monto = sum_monto + Double.parseDouble(monto);
                    



                } catch (ParseException ex) {
                    DialogError error = new DialogError (null,true,ex);
                    error.setVisible(true);
                }

            }           
        }
        monto = "Monto total disponible: " +utils.priceWithDecimal(sum_monto);
        saldo = "";
        
        JDialogsSumatorio s = new JDialogsSumatorio (null,true,monto,saldo);
        s.setVisible(true);
                
    }      
   
    
    
    public void asignarcomoPagado(RubrosObject obj){
        
        Utilities utils = new Utilities ();
        String tipo_pago;
        
        String fecha = utils.getDate();
        String lugar = obj.getNombre();
        String monto = obj.getSaldo();
        
        try {
            double m = Double.parseDouble(monto);

            if (m!=0){
                /*Si era un pendiente, se debe ir a ver con que tipo de tarjeta se pago.*/                 

                if(JOptionPane.showConfirmDialog(null, "¿Desea asignar como pagado el rubro '"+obj.getNombre()+"'?",
                                                                            "Warning",
                                                                            JOptionPane.OK_CANCEL_OPTION,
                                                                            JOptionPane.QUESTION_MESSAGE)
                                                                            ==JOptionPane.OK_OPTION)
                {

                    PresupuestoDAO presupuestodao = null;

                        presupuestodao = PresupuestoDAO.getInstance();
                        GastoObject gasto = new GastoObject();

                        gasto.setFecha(fecha);
                        gasto.setLugar(lugar);
                        gasto.setMonto(monto);
                        gasto.setIdpresupuesto(obj.getIdpresupuesto());
                        gasto.setIdrubro(obj.getIdentificador());

                        TiposPagoSingleton tipospago_singleton = TiposPagoSingleton.getInstance();
                        String tiposp [] = tipospago_singleton.getTiposp();

                        Object[] possibilities = tiposp;
                        tipo_pago = (String)JOptionPane.showInputDialog(
                                            null,
                                            "Seleccione el tipo de pago:\n",
                                            "Tipo de Pago",
                                            JOptionPane.PLAIN_MESSAGE,
                                            null,
                                            possibilities,
                                            obj.getTipo_pago());


                        if (tipo_pago!=null){
                            
                            
                            presupuestodao.sp_pagar_rubro(obj.getIdentificador(), obj.getIdpresupuesto(), obj.getNombre(), tipo_pago);
                            
                            
                            JOptionPane.showMessageDialog(null,
                "Rubro pagado.");
                            
                            
                        }
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null,
                "'"+obj.getNombre()+"' no tiene saldo pendiente.");
            }
        } catch (Exception ex) {

               DialogError error = new DialogError (null,true,ex);
               error.setVisible(true);                

        }            
    }    
    
    
    public void modificar(){
        try 
        {
            Utilities utils = new Utilities();
            PresupuestoDAO con = PresupuestoDAO.getInstance();
            
            TableModel model = jTable1.getModel();

            int row = jTable1.getSelectedRow();
            int col = jTable1.getSelectedColumn();               

            String idgasto = model.getValueAt(row, 0).toString();  
            String nombre = model.getValueAt(row, 2).toString(); 
            String monto = utils.priceWithoutCommas(model.getValueAt(row, 4).toString());            
            
            GastoObject gasto = con.getGasto(idgasto) ;
            
            if (!nombre.trim().equals(gasto.getLugar()))
            {
                gasto.setLugar(nombre);
                gasto.setMonto(monto);
                con.modificarGasto(gasto);
                this.rubrosobject = con.getRubro(this.rubrosobject.getIdentificador());
                cargarGastos();                
            }
            if (!monto.trim().equals(gasto.getMonto()))
            {                                                
                double saldo = Double.parseDouble(this.rubrosobject.getSaldo());
                double monto_modificado = Double.parseDouble(monto);
                double monto_original = Double.parseDouble(gasto.getMonto());
                double variable = monto_original - monto_modificado;
                
                saldo = saldo + variable;                
                
                gasto.setLugar(nombre);
                gasto.setMonto(monto);
                con.modificarGasto(gasto);
                con.modificarSaldoRubro(gasto.getIdrubro(), String.valueOf(saldo));
                this.rubrosobject = con.getRubro(this.rubrosobject.getIdentificador());
                cargarGastos();
            }            
            

        } catch (Exception ex) {
            DialogError error = new DialogError (null,true,ex);
            error.setVisible(true);
        }
        
    
    }
 

    public void cargarGastos()
    {
        Utilities utils = new Utilities();
        try {
                // TODO add your handling code here:
            PresupuestoDAO con = PresupuestoDAO.getInstance();
            this.rubrosobject = con.getRubro(this.rubrosobject.getIdentificador());
                    
            lbl_rubro.setText(rubrosobject.getNombre());            
            double m1 = Double.parseDouble(this.rubrosobject.getMonto());
            lbl_monto_presupuestado.setText(utils.priceWithDecimal(m1));
            
            double m2 = Double.parseDouble(this.rubrosobject.getSaldo());           
            lbl_saldo_rubro.setText(utils.priceWithDecimal(m2));            
            
            lbl_tipo_pago.setText(this.rubrosobject.getTipo_pago());
                        

            this.rowData=con.getGastosxRubro(Integer.parseInt(rubrosobject.getIdentificador()));

            actualizarTabla(this.rowData);
            
            if (this.rowData!=null){  
                String total_gastado = utils.priceWithDecimal(Double.parseDouble(con.getTotalGastadoxRubro(Integer.parseInt(rubrosobject.getIdentificador()))));
                lbl_total_gastado.setText(total_gastado);
            }
            else
            {
                double total_gastado=0;
                String total_gastado_str = utils.priceWithDecimal(total_gastado);
                lbl_total_gastado.setText(total_gastado_str);
            }
        

        String monto_presupuestado =  lbl_monto_presupuestado.getText();
        String saldo_rubro = lbl_saldo_rubro.getText();
        String total_gastado = lbl_total_gastado.getText();
                
        monto_presupuestado = utils.priceWithoutCommas(monto_presupuestado);
        saldo_rubro = utils.priceWithoutCommas(saldo_rubro);
        total_gastado = utils.priceWithoutCommas(total_gastado);
        
        double mpresupuestado = Double.parseDouble(monto_presupuestado);
        double msaldo = Double.parseDouble(saldo_rubro);
        double mtotal_gastado = Double.parseDouble(total_gastado);
        
        if ((mpresupuestado-mtotal_gastado)!=msaldo)
            btn_corregir_saldo_rubro.setVisible(true);
        else
            btn_corregir_saldo_rubro.setVisible(false);     
        

        if (Double.parseDouble(this.rubrosobject.getSaldo())==0)
        {
            btn_insertar.setEnabled(false);
            btn_pagado.setEnabled(false);
        }
        else
        {
            btn_insertar.setEnabled(true);
            btn_pagado.setEnabled(true);
        }  
        
        } catch (Exception ex) {
                   DialogError error = new DialogError (null,true,ex);
                   error.setVisible(true);
        }         
    }
    

   public void actualizarTabla(Object[][] datos){
       
               
    jTable1.setModel(new javax.swing.table.DefaultTableModel(
         this.rowData,
         this.columnNames
    ){
       boolean[] canEdit = new boolean [] {
         false, false, true, false, true
     };
         @Override
         public boolean isCellEditable(int rowIndex, int columnIndex) {
             return canEdit[columnIndex];
         }   
 });
 
    jTable1.getColumnModel().getColumn(0).setMaxWidth(60);
    jTable1.getColumnModel().getColumn(1).setPreferredWidth(100);
    jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
    jTable1.getColumnModel().getColumn(3).setPreferredWidth(100);
    jTable1.getColumnModel().getColumn(4).setPreferredWidth(100);
   }
    
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_corregir_saldo_rubro;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_insertar;
    private javax.swing.JButton btn_modificar;
    private javax.swing.JButton btn_pagado;
    private javax.swing.JButton btn_sumatoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbl_monto_presupuestado;
    private javax.swing.JLabel lbl_rubro;
    private javax.swing.JLabel lbl_saldo_rubro;
    private javax.swing.JLabel lbl_tipo_pago;
    private javax.swing.JLabel lbl_total_gastado;
    // End of variables declaration//GEN-END:variables
}
