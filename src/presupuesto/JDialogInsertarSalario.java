/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presupuesto;

import dao.PresupuestoDAO;
import dao.PresupuestoObject;
import dao.RubrosObject;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author MaFuen1
 */
public class JDialogInsertarSalario extends javax.swing.JDialog {
PresupuestoObject presupuesto;
String usuarios[];
String idusuario;
String salario;
String motivo;
boolean modificar;

    /**
     * Creates new form JDialogInsertarSalario
     */
    public JDialogInsertarSalario(java.awt.Frame parent, boolean modal,PresupuestoObject pre) {
        super(parent, modal);
        initComponents();
        
         this.presupuesto=pre;
        
         setLocationRelativeTo(null);   
         
        try {

            PresupuestoDAO con = PresupuestoDAO.getInstance();
            
            usuarios = con.getUsuarios3();
            cmb_usuarios.setModel(new javax.swing.DefaultComboBoxModel(usuarios));
            
        } catch (SQLException | NumberFormatException ex) {
                DialogError error = new DialogError (null,true,ex);
                error.setVisible(true);
        }                
        modificar=false;
    }
    
    public JDialogInsertarSalario(java.awt.Frame parent, boolean modal,PresupuestoObject pre,String idusuario,String salario, String motivo) {
        super(parent, modal);
        initComponents();
        
         this.presupuesto=pre;
        
         setLocationRelativeTo(null);   
         
        try {

            PresupuestoDAO con = PresupuestoDAO.getInstance();            
            usuarios = con.getUsuarios3();
            cmb_usuarios.setModel(new javax.swing.DefaultComboBoxModel(usuarios));
            
            btn.setText("Modificar");
            lbl.setText("Modificar");
            
            cmb_usuarios.setSelectedItem(idusuario);
            cmb_usuarios.setEnabled(false);
            
            txt_motivo.setText(motivo);
            txt_monto.setText(salario);
                                                
        } catch (SQLException | NumberFormatException ex) {
                DialogError error = new DialogError (null,true,ex);
                error.setVisible(true);
        }                 
        modificar=true;
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
        lbl = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_motivo = new javax.swing.JTextField();
        txt_monto = new javax.swing.JTextField();
        btn = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        cmb_usuarios = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lbl.setFont(new java.awt.Font("Arial Unicode MS", 1, 18)); // NOI18N
        lbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl.setText("Insertar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl)
                .addContainerGap())
        );

        jLabel1.setText("Motivo");

        jLabel2.setText("Monto");

        btn.setLabel("Insertar");
        btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActionPerformed(evt);
            }
        });

        btn_cancelar.setLabel("Cancelar");
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });

        jLabel3.setText("Usuario");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(42, 42, 42)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btn)
                        .addGap(18, 18, 18)
                        .addComponent(btn_cancelar))
                    .addComponent(txt_motivo)
                    .addComponent(txt_monto, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addComponent(cmb_usuarios, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmb_usuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_motivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_monto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn)
                    .addComponent(btn_cancelar))
                .addGap(37, 37, 37))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActionPerformed
        // TODO add your handling code here:
       if(!modificar)
         insertar();
       else
           modificar();
        
    }//GEN-LAST:event_btnActionPerformed

    public void modificar(){
    
     if ( txt_motivo.getText().trim().equals("")||txt_monto.getText().trim().equals(""))
        {
            JOptionPane.showMessageDialog(null,
                "Debe especificar todos los valores solicitados.");
        }
        else
        {
            PresupuestoDAO presupuestodao = null;
            try {
                presupuestodao = PresupuestoDAO.getInstance();                                
                
                presupuestodao.modificarSalario(this.presupuesto.getIdpresupuesto(), cmb_usuarios.getSelectedItem().toString(),  Double.parseDouble(txt_monto.getText()), txt_motivo.getText());
                presupuestodao.modificarMontoDisponible(this.presupuesto.getIdpresupuesto());
                
                 JOptionPane.showMessageDialog(null,
                "Monto disponible para la quincena actual modificado.");
                
                
                this.setVisible(false);
                
            } catch (Exception ex) {
                 DialogError error = new DialogError (null,true,ex);
                 error.setVisible(true);
            }          
        }  
    
    }
    
    
    public void insertar(){
     if ( txt_motivo.getText().trim().equals("")||txt_monto.getText().trim().equals(""))
        {
            JOptionPane.showMessageDialog(null,
                "Debe especificar todos los valores solicitados.");
        }
        else
        {
            PresupuestoDAO presupuestodao = null;
            try {
                presupuestodao = PresupuestoDAO.getInstance();                                
                
                presupuestodao.insertarSalario(this.presupuesto.getIdpresupuesto(), cmb_usuarios.getSelectedItem().toString(),  Double.parseDouble(txt_monto.getText()), txt_motivo.getText());
                presupuestodao.modificarMontoDisponible(this.presupuesto.getIdpresupuesto());
                
                 JOptionPane.showMessageDialog(null,
                "Monto disponible para la quincena actual modificado.");
                
                
                this.setVisible(false);
                
            } catch (Exception ex) {
                 DialogError error = new DialogError (null,true,ex);
                 error.setVisible(true);
            }          
        }    
    
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JComboBox cmb_usuarios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbl;
    private javax.swing.JTextField txt_monto;
    private javax.swing.JTextField txt_motivo;
    // End of variables declaration//GEN-END:variables
}
