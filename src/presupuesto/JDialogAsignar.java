/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presupuesto;

import dao.GastoObject;
import dao.PresupuestoDAO;
import dao.PresupuestoObject;
import dao.TiposPagoSingleton;
import java.sql.SQLException;
import java.text.ParseException;
import javax.swing.JOptionPane;

/**
 *
 * @author MaFuen1
 */
public class JDialogAsignar extends javax.swing.JDialog {
    String[] columnNames = {"ID","PAGAR A","RUBRO",
                        "SALDO"         
                        };
    Object rowData[][];   
    PresupuestoObject presupuesto;
    GastoObject gastoobject;
    
    /**
     * Creates new form JDialogAsignar
     */
    public JDialogAsignar(java.awt.Frame parent, boolean modal,PresupuestoObject pre,GastoObject gasto) {
        super(parent, modal);
        initComponents();
        this.presupuesto=pre;
        this.gastoobject=gasto;
        
        cargarRubros();
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

        jPanel2 = new javax.swing.JPanel();
        lbl_quincena = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btn_asignar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbl_quincena.setFont(new java.awt.Font("Arial Unicode MS", 1, 18)); // NOI18N
        lbl_quincena.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_quincena.setText("Rubros presupuesto actual");
        lbl_quincena.setRequestFocusEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lbl_quincena, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(lbl_quincena)
                .addContainerGap())
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
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Seleccione el rubro al cual desea agregar el gasto:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 16, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btn_asignar.setText("Asignar");
        btn_asignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_asignarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(207, 207, 207)
                .addComponent(btn_asignar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_asignar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_asignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_asignarActionPerformed
        // TODO add your handling code here:

        int row = jTable1.getSelectedRow();
        int col = jTable1.getSelectedColumn();  
        
       try {
            Utilities utils = new Utilities ();
            String idrubro =   jTable1.getValueAt(row, 0).toString();
 
            PresupuestoDAO presupuestodao = null;
            
            presupuestodao = PresupuestoDAO.getInstance();
            GastoObject gasto = presupuestodao.getGasto(gastoobject.getIdgasto());

            gasto.setIdrubro(idrubro);           
            String tipo_pago;

            

                    if(JOptionPane.showConfirmDialog(null, "¿Desea cambiar el tipo de pago ("+ gasto.getTipo_pago()+") del gasto?",
                                                            "Warning",
                                                            JOptionPane.OK_CANCEL_OPTION,
                                                            JOptionPane.QUESTION_MESSAGE)
                                                            ==JOptionPane.OK_OPTION){                  
            
            
                            TiposPagoSingleton tipospago_singleton = TiposPagoSingleton.getInstance();
                            String tiposp [] = tipospago_singleton.getTiposp();

                            Object[] possibilities = tiposp;//{"EFECTIVO", "DEBITO BCR", "DEBITO BNCR", "CREDOMATIC VISA", "CREDOMATIC AMERICAN EXPRESS", "POPULAR VISA", "DAVIVIENDA VISA","CREDIX","KUIKI"};
                            tipo_pago = (String)JOptionPane.showInputDialog(
                                                null,
                                                "Seleccione el tipo de pago:\n",
                                                "Tipo de Pago",
                                                JOptionPane.PLAIN_MESSAGE,
                                                null,
                                                possibilities,
                                                gasto.getTipo_pago());
            
                            
            
                            gasto.setTipo_pago(tipo_pago);
                    }
                    
                    presupuestodao.sp_asignar_gasto_a_rubro(gasto.getIdgasto(), gasto.getTipo_pago(), gasto.getIdrubro());
                        
            
             JOptionPane.showMessageDialog(null,
                    "Gasto asignado.");
            
            this.setVisible(false);
            
            
            
        } catch (Exception ex) {
           DialogError error = new DialogError (null,true,ex);
           error.setVisible(true);
        }       
        
        
    }//GEN-LAST:event_btn_asignarActionPerformed

    public void cargarRubros(){
Utilities utils = new Utilities();
        try {
                // TODO add your handling code here:
            PresupuestoDAO con = PresupuestoDAO.getInstance();
                        

            this.rowData=con.getRubrosPresupuesto2( this.presupuesto.getIdpresupuesto() );

            actualizarTabla(this.rowData);
            
        
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
    
   }
    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_asignar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lbl_quincena;
    // End of variables declaration//GEN-END:variables
}
