/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presupuesto;

import dao.DepositoObject;
import dao.PresupuestoDAO;
import dao.PresupuestoObject;
import dao.RubrosObject;
import dao.TiposPagoSingleton;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

/**
 *
 * @author MaFuen1
 */
public class Consultas extends javax.swing.JDialog {

    boolean atras;    
    PresupuestoObject presupuesto;
    String tipo_p;
    String[] columnNames = {
                    "ID","LUGAR","TIPO PAGO","MONTO" ,"PAGADO"       
                    };
    String[] columnNames2 = {"TARJETA","MONTO","(*) DEPOSITO"};        
    String rowData[][];   
    String tiposp[];
    ImageIcon pagadoicon;
    ImageIcon sumatoriaicon;
    
    /**
     * Creates new form Consultas
     */
    public Consultas(java.awt.Frame parent, boolean modal, PresupuestoObject pre) {
        super(parent, modal);
        initComponents();
        this.presupuesto = pre;
        this.atras=false;
        pagadoicon = new ImageIcon(getClass().getResource("/img/pagado.jpg"));
        btn_pagado.setIcon(pagadoicon);
        sumatoriaicon =new ImageIcon(getClass().getResource("/img/sumatoria.jpg"));
        btn_sumatoria.setIcon(sumatoriaicon);
         btn_pagado.setVisible(false);
         btn_atras.setVisible(false);
         
         
         sumatoria();         
         setLocationRelativeTo(null);
         
         
                  
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lbl_monto = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btn_atras = new javax.swing.JButton();
        btn_pagado = new javax.swing.JButton();
        btn_sumatoria = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tarjetas de Credito");

        jPanel2.setBackground(new java.awt.Color(255, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Arial Unicode MS", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Tarjetas de Crédito");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Arial Unicode MS", 1, 24)); // NOI18N
        jLabel2.setText("TOTAL:");

        lbl_monto.setFont(new java.awt.Font("Arial Unicode MS", 1, 24)); // NOI18N
        lbl_monto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_monto.setText("0.00");
        lbl_monto.setBorder(javax.swing.BorderFactory.createEtchedBorder());

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
        jScrollPane1.setViewportView(jTable1);

        btn_atras.setLabel("<< Atras");
        btn_atras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_atrasActionPerformed(evt);
            }
        });

        btn_pagado.setBackground(new java.awt.Color(255, 255, 255));
        btn_pagado.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn_pagado.setBorderPainted(false);
        btn_pagado.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);
        btn_pagado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pagadoActionPerformed(evt);
            }
        });

        btn_sumatoria.setBackground(new java.awt.Color(255, 255, 255));
        btn_sumatoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sumatoriaActionPerformed(evt);
            }
        });

        jButton1.setLabel("Ver Detalle");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_pagado, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_sumatoria, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(281, 281, 281)
                .addComponent(btn_atras)
                .addContainerGap(506, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 990, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                            .addGap(148, 148, Short.MAX_VALUE)
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lbl_monto, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap()))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(267, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_pagado, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_sumatoria, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_atras)
                        .addComponent(jButton1)))
                .addGap(39, 39, 39))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(32, 32, 32)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl_monto)
                        .addComponent(jLabel2))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jTable1MouseClicked

    
    public void detalleTarjeta(){
          try {                              
                Utilities utils = new Utilities ();
                PresupuestoDAO con = PresupuestoDAO.getInstance();
                
                TableModel model = jTable1.getModel();
                
                int row = jTable1.getSelectedRow();
                int col = jTable1.getSelectedColumn();  
                
                if  (row!=-1 && col!=-1)
                {                  
                    
                    Object obj = model.getValueAt(row, 0);
                    if (obj!=null){
                        tipo_p = obj.toString();

                    
                        int idpresupuesto = Integer.parseInt(presupuesto.getIdpresupuesto());

                        this.rowData=con.getGastosPresupuestoxTipoPago(idpresupuesto, tipo_p);
                        
                        if (this.rowData.length==0){
                        JOptionPane.showMessageDialog(null,
                            "Los rubros asignados a "+tipo_p+" aun no han sido pagados. Por eso no se muestran.");
                        }
                        else
                        {
                            
                            this.atras=true;
                            btn_pagado.setVisible(true);
                            btn_atras.setVisible(true);                            
                          

                            jTable1.setModel(new javax.swing.table.DefaultTableModel(
                                    this.rowData,
                                    this.columnNames
                            ){
                                    @Override
                                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                                        return false;
                                    }   
                            });

                            jTable1.getColumnModel().getColumn(0).setPreferredWidth(10);
                            jTable1.getColumnModel().getColumn(1).setPreferredWidth(100);
                            jTable1.getColumnModel().getColumn(2).setPreferredWidth(50);
                            jTable1.getColumnModel().getColumn(3).setPreferredWidth(50);
                            jTable1.getColumnModel().getColumn(4).setPreferredWidth(50);


                            String  str_monto;   
                            int registros = rowData.length; 
                            double tmp_monto,monto=0;
                            for (int i=0; i<registros; i++)
                            {
                                str_monto = (String) rowData [i][3];

                                if (str_monto!=null){
                                    try {
                                        tmp_monto = Double.parseDouble(utils.priceWithoutCommas(str_monto));                                        
                                        monto = monto + tmp_monto;                                                            
                                    } catch (ParseException ex) {
                                        ex.printStackTrace();
                                    }
                                }                         

                            }                                                

                            lbl_monto.setText(utils.priceWithDecimal(monto));  
                        }                        
                    }     
                }
                else
                {
                JOptionPane.showMessageDialog(null,
                        "Seleccione una tarjeta para ver el detalle.");
                }                
            }catch (Exception ex) {
                DialogError error = new DialogError (null,true,ex);
                error.setVisible(true);
            }        
    }
    
    

    

    public void ActualizarDetalleTabla(){
          try {                              
                Utilities utils = new Utilities ();
                PresupuestoDAO con = PresupuestoDAO.getInstance();
                
 
   
                        int idpresupuesto = Integer.parseInt(presupuesto.getIdpresupuesto());

                        this.rowData=con.getGastosPresupuestoxTipoPago(idpresupuesto, tipo_p);

                        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                                this.rowData,
                                this.columnNames
                        ){
                                @Override
                                public boolean isCellEditable(int rowIndex, int columnIndex) {
                                    return false;
                                }   
                        });

                        jTable1.getColumnModel().getColumn(0).setPreferredWidth(10);
                        jTable1.getColumnModel().getColumn(1).setPreferredWidth(100);
                        jTable1.getColumnModel().getColumn(2).setPreferredWidth(50);
                        jTable1.getColumnModel().getColumn(3).setPreferredWidth(50);
                        jTable1.getColumnModel().getColumn(4).setPreferredWidth(50);


                        String  str_monto;   
                        int registros = rowData.length; 
                        double tmp_monto,monto=0;
                        for (int i=0; i<registros; i++)
                        {
                            str_monto = (String) rowData [i][3];

                            if (str_monto!=null){
                                try {
                                    tmp_monto = Double.parseDouble(utils.priceWithoutCommas(str_monto));                                        
                                    monto = monto + tmp_monto;                                                            
                                } catch (ParseException ex) {
                                    ex.printStackTrace();
                                }
                            }                         

                        }                                                

                        lbl_monto.setText(utils.priceWithDecimal(monto));           
                    
                      }catch (Exception ex) {
                DialogError error = new DialogError (null,true,ex);
                error.setVisible(true);
            }   
      
    }    
    
    private void btn_atrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_atrasActionPerformed
        // TODO add your handling code here:
        if (this.atras){
            sumatoria();
            this.atras=false;
             btn_pagado.setVisible(false);
             btn_atras.setVisible(false);
          
        }
    }//GEN-LAST:event_btn_atrasActionPerformed

    private void btn_pagadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pagadoActionPerformed
        // TODO add your handling code here:

        btn_pagar();
    }//GEN-LAST:event_btn_pagadoActionPerformed

    
    public void btn_pagar(){
        TableModel model = jTable1.getModel();
        Utilities utils = new Utilities();
        String tipo_pago;
        
        int rowArr[] = jTable1.getSelectedRows();
        
      
        
        if (rowArr.length ==0)        
        {
        JOptionPane.showMessageDialog(null,
                    "Debe seleccionar un rubro de la tabla.");
        }         
        else
        {
            String eval = jTable1.getValueAt( jTable1.getSelectedRow() , 4).toString();  
            if (!eval.equals("POR ASIGNAR"))
            {
                JOptionPane.showMessageDialog(null,
                    "El rubro ya esta asignado.");
            }              
            else
            {
                                    
                for (int i=0; i<rowArr.length ; i++){

                if  (rowArr[i]!=-1)
                {                                
                    try {                                
                        String idrubro = jTable1.getValueAt(rowArr[i], 0).toString();
                        String nombre = jTable1.getValueAt(rowArr[i], 2).toString();
                        
                        String monto = jTable1.getValueAt(rowArr[i], 3).toString();
                        monto= utils.priceWithoutCommas(monto);
                        
                        PresupuestoDAO presupuestodao = null;
                        try {
                            presupuestodao = PresupuestoDAO.getInstance();
                            
                            
                            TiposPagoSingleton tipospago_singleton = TiposPagoSingleton.getInstance();
                            String tiposp [] = tipospago_singleton.getTipospDebito();
                            
                            Object[] possibilities = tiposp;//{"EFECTIVO", "DEBITO BCR", "DEBITO BNCR", "CREDOMATIC VISA", "CREDOMATIC AMERICAN EXPRESS", "POPULAR VISA", "DAVIVIENDA VISA","CREDIX","KUIKI"};
                            tipo_pago = (String)JOptionPane.showInputDialog(
                                    null,
                                    "Seleccione el tipo de pago:\n",
                                    "Tipo de Pago",
                                    JOptionPane.PLAIN_MESSAGE,
                                    null,
                                    possibilities,
                                    "");
                            
                            
                            if (tipo_pago!=null){
                                presupuestodao.insertarGastoDetalle(this.presupuesto.getIdpresupuesto(),idrubro, tipo_pago, Double.parseDouble(monto));
                                
                                JOptionPane.showMessageDialog(null,"El pago ha sido asignado.");
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null,"El pago NO  asignado.");
                            }
                            
                        }catch (SQLException ex) {
                            
                            DialogError error = new DialogError (null,true,ex);
                            error.setVisible(true);
                        }
                        
                    }catch (ParseException ex) {

                           Logger.getLogger(Consultas.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }           
                }
                ActualizarDetalleTabla();
            }
        }
    }
    
    
    private void btn_sumatoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sumatoriaActionPerformed
        // TODO add your handling code here:
        btn_sumatoria();

    }//GEN-LAST:event_btn_sumatoriaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         if (!this.atras){             
             detalleTarjeta();
         }        
    }//GEN-LAST:event_jButton1ActionPerformed

    

            
    
    
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
                    
                    if (!this.atras)
                        monto = jTable1.getValueAt(rowArr[i], 1).toString();
                    else
                        monto = jTable1.getValueAt(rowArr[i], 3).toString();

                    
                    monto=utils.priceWithoutCommas(monto);

                    sum_monto = sum_monto + Double.parseDouble(monto);
                    
                } catch (ParseException ex) {
                    DialogError error = new DialogError (null,true,ex);
                    error.setVisible(true);
                }

            }           
        }
        monto = "Total: " +utils.priceWithDecimal(sum_monto);
        
        
        JDialogsSumatorio s = new JDialogsSumatorio (null,true,monto,saldo);
        s.setVisible(true);
                
    }    

    
    public void sumatoria()
    {
        Utilities utils = new Utilities();
        try {        

                PresupuestoDAO con = PresupuestoDAO.getInstance(); 
                int idpresupuesto = Integer.parseInt(presupuesto.getIdpresupuesto());

                this.rowData=con.getGastosPresupuestoxTipoPago(idpresupuesto);
                
              

                jTable1.setModel(new javax.swing.table.DefaultTableModel(
                        this.rowData,
                        this.columnNames2
                ){
                        @Override
                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                            return false;
                        }   
                });

                jTable1.getColumnModel().getColumn(0).setPreferredWidth(100);
                jTable1.getColumnModel().getColumn(1).setPreferredWidth(100);
                jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
                

            String  str_monto;   
            int registros = rowData.length; 
            double tmp_monto,monto=0;
            for (int i=0; i<registros; i++)
            {
                str_monto = (String) rowData [i][1];
                
                if (str_monto!=null){
                    try {
                        tmp_monto = Double.parseDouble(utils.priceWithoutCommas(str_monto));                                        
                        monto = monto + tmp_monto;                                                            
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                }                         
                
            }                                                
                
            lbl_monto.setText(utils.priceWithDecimal(monto));               
            
            /*
            if(con.hayDepositosPendientes()){
            
                    JOptionPane.showMessageDialog(null,
                            "Hay depositos pendientes de realizar que pueden afectar esta consulta. \n Para mas detalle haga click en [Ver Depositos Pendientes]");                                                    
            }
            */
            
          
        } catch (SQLException ex) {
                   DialogError error = new DialogError (null,true,ex);
                   error.setVisible(true);
        }     
    
    }   
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_atras;
    private javax.swing.JButton btn_pagado;
    private javax.swing.JButton btn_sumatoria;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbl_monto;
    // End of variables declaration//GEN-END:variables
}
