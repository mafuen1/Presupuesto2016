/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presupuesto;

import java.util.Date;
import dao.ConfiguracionObject;
import dao.CuotaObject;
import dao.GastoObject;
import dao.PendientesObject;
import dao.PresupuestoDAO;
import dao.PresupuestoObject;
import dao.QuincenaActual;
import dao.RubrosObject;
import dao.TipoPagoObject;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import org.joda.time.DateTime;


/**
 *
 * @author MaFuen1
 */
public class Presupuesto extends javax.swing.JFrame {

    /**
     * Creates new form Presupuesto
     */
    boolean inicio=true;
    public static final String CERRADO = "CERRADO"; 
    public static final String ABIERTO = "ABIERTO"; 
    int year;
    int month;
    int day;
    String monto_previo;
    String path;
    String imgpath;
    int cont=0;        
    PresupuestoObject presupuesto;
           
    String[] columnNames = {"ID","PAGAR A","RUBRO",
                        "MONTO","SALDO" //,"VER GASTOS"          
                        };
    String rowData[][];    
    
    CellEditorListener ChangeNotification = new CellEditorListener() {
        public void editingCanceled(ChangeEvent e) {
            System.out.println("The user canceled editing.");
        }

        public void editingStopped(ChangeEvent e) {
            System.out.println("The user stopped editing successfully."+ ++cont);
            modificar();
        }
    };
    
    ImageIcon adelanteicon; 
    ImageIcon atrasicon; 
    ImageIcon insertaricon;
    ImageIcon borraricon;
    ImageIcon pagadoicon;
    ImageIcon modificaricon;
    ImageIcon sumatoriaicon;
    
    public Presupuesto() {
      
        initComponents();
        this.path = System.getProperty("user.dir")+"\\"; 
        this.imgpath = this.path;
        this.path = "jdbc:ucanaccess://"+path+"dbpre.accdb";
        
        System.out.println(this.imgpath);
        
        adelanteicon =  new ImageIcon(getClass().getResource("/img/adelante2.jpg"));              
        atrasicon =  new ImageIcon(getClass().getResource("/img/atras2.jpg")); //new ImageIcon(this.imgpath+"img\\atras2.jpg");
        insertaricon =  new ImageIcon(getClass().getResource("/img/insertar.JPG"));
        borraricon = new ImageIcon(getClass().getResource("/img/borrar.jpg"));
        pagadoicon = new ImageIcon(getClass().getResource("/img/pagado.jpg"));
        modificaricon = new ImageIcon(getClass().getResource("/img/modificar.JPG"));
        sumatoriaicon =new ImageIcon(getClass().getResource("/img/sumatoria.jpg"));
        
        
        presupuesto = new PresupuestoObject();
        
        
        btn_sumatoria.setIcon(sumatoriaicon);
        btn_adelante.setIcon(adelanteicon);
        btn_atras.setIcon(atrasicon);
        btn_agregar_rubro1.setIcon(insertaricon);
        
        btn_eliminar_rubro.setIcon(borraricon);
        btn_pagado.setIcon(pagadoicon);
        btn_modificar.setIcon(modificaricon);
        
        btn_pagado.setEnabled(false);
        btn_modificar.setEnabled(false);
        btn_eliminar_rubro.setEnabled(false);
        
        
        cargarValoresIniciales();        
        setLocationRelativeTo(null);
        setVisible(true);
        cargarPresupuesto();
        
     
        cmb_quincena.setVisible(false);
        cmb_mes.setVisible(false);
        cmb_ano.setVisible(false);
        lbl_quincena.setText("Quincena "+cmb_quincena.getSelectedItem()+" "+cmb_mes.getSelectedItem()+" "+cmb_ano.getSelectedItem());
        
        if (lbl_quincena.getText().equals("Quincena 1 OCTUBRE 2015"))
            btn_atras.setEnabled(false);
        else
            btn_atras.setEnabled(true);   
        
       // recordatorios(true);
        
        
 try {            

                PresupuestoDAO presupuestodao = null;
                presupuestodao = PresupuestoDAO.getInstance();
                
                
                if(presupuestodao.HAYGastosSinAsignar(   )){
                
                   if(JOptionPane.showConfirmDialog(null, "Hay gastos sin asignar ¿Desea asignarlos?",
                                                            "Warning",
                                                            JOptionPane.OK_CANCEL_OPTION,
                                                            JOptionPane.QUESTION_MESSAGE)
                                                            ==JOptionPane.OK_OPTION){  
                       
                                   JDialogGastosSinAsignar gastos = new JDialogGastosSinAsignar (new JFrame(),true,this.presupuesto);
                                gastos.setVisible(true);
                                cargarPresupuesto();             
                       
                     }
                   
                   
                    
                    
                }
              
        } catch (SQLException ex) {
        }           
        
        
        
        
        
    }
    
public String path()
{
    URL url1 = getClass().getResource("");
    String ur=url1.toString();
    ur=ur.substring(9);
    String truepath[]=ur.split("Presupuesto.jar!");
    truepath[0]=truepath[0].replaceAll("%20"," ");
    return truepath[0];
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem_modificar = new javax.swing.JMenuItem();
        jMenuItem_eliminar = new javax.swing.JMenuItem();
        jMenuItem_pagar = new javax.swing.JMenuItem();
        jMenuItem_vergastos = new javax.swing.JMenuItem();
        jMenuItem_insertargasto = new javax.swing.JMenuItem();
        jMenuItem_vercuotas = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btn_adelante = new javax.swing.JButton();
        lbl_quincena = new javax.swing.JLabel();
        btn_atras = new javax.swing.JButton();
        cmb_mes = new javax.swing.JComboBox();
        cmb_ano = new javax.swing.JComboBox();
        cmb_quincena = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lbl_saldo = new javax.swing.JLabel();
        lbl_presupuesto = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbl_gastado = new javax.swing.JLabel();
        lbl_monto_disponible2 = new javax.swing.JLabel();
        lbl_monto_disponible = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btn_consultas = new javax.swing.JButton();
        btn_pendientes = new javax.swing.JButton();
        btn_configuracion_general = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btn_gastos = new javax.swing.JButton();
        btn_detalle_cuotas = new javax.swing.JButton();
        btn_limpiar = new javax.swing.JButton();
        btn_bancos = new javax.swing.JButton();
        btn_cargarQuincena = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        btn_sumatoria = new javax.swing.JButton();
        btn_pagado = new javax.swing.JButton();
        btn_eliminar_rubro = new javax.swing.JButton();
        btn_modificar = new javax.swing.JButton();
        btn_agregar_rubro1 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        btn_buscar = new javax.swing.JButton();
        txt_buscar = new javax.swing.JTextField();
        radio_detalle = new javax.swing.JRadioButton();
        radio_monto = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        idpresupuesto_txt = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jMenuItem_modificar.setText("Modificar");
        jMenuItem_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_modificarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem_modificar);

        jMenuItem_eliminar.setText("Eliminar");
        jMenuItem_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_eliminarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem_eliminar);

        jMenuItem_pagar.setText("Pagar");
        jMenuItem_pagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_pagarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem_pagar);

        jMenuItem_vergastos.setText("Ver gastos");
        jMenuItem_vergastos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_vergastosActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem_vergastos);

        jMenuItem_insertargasto.setText("Insertar Gasto");
        jMenuItem_insertargasto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_insertargastoActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem_insertargasto);

        jMenuItem_vercuotas.setText("Ver Detalle Cuotas");
        jMenuItem_vercuotas.setToolTipText("");
        jMenuItem_vercuotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_vercuotasActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem_vercuotas);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PRESUPUESTO");

        jScrollPane1.setBackground(new java.awt.Color(255, 204, 255));

        table.setAutoCreateRowSorter(true);
        table.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "RUBRO", "MONTO", "SALDO", "VER GASTOS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        table.setEditingColumn(1);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jPopupMenu1, org.jdesktop.beansbinding.ELProperty.create("${name}"), table, org.jdesktop.beansbinding.BeanProperty.create("componentPopupMenu"));
        bindingGroup.addBinding(binding);

        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tableMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        jPanel2.setBackground(new java.awt.Color(255, 204, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_adelante.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        btn_adelante.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btn_adelante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_adelanteActionPerformed(evt);
            }
        });

        lbl_quincena.setFont(new java.awt.Font("Arial Unicode MS", 1, 18)); // NOI18N
        lbl_quincena.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_quincena.setText("jLabel1");
        lbl_quincena.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_atras.setFont(new java.awt.Font("Arial Unicode MS", 1, 14)); // NOI18N
        btn_atras.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btn_atras.setBorderPainted(false);
        btn_atras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_atrasActionPerformed(evt);
            }
        });

        cmb_mes.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmb_mes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SETIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE" }));

        cmb_ano.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmb_ano.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025" }));

        cmb_quincena.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmb_quincena.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmb_mes, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmb_ano, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmb_quincena, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(105, 105, 105)
                .addComponent(btn_atras, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbl_quincena, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_adelante, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmb_mes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmb_ano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cmb_quincena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(15, 15, 15)
                            .addComponent(lbl_quincena))
                        .addComponent(btn_adelante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_atras, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel3.setFocusable(false);
        jPanel3.setMinimumSize(new java.awt.Dimension(100, 100));

        jLabel10.setFont(new java.awt.Font("Arial Unicode MS", 1, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText(" DISPONIBLE:");
        jLabel10.setRequestFocusEnabled(false);

        jLabel8.setFont(new java.awt.Font("Arial Unicode MS", 1, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("PRESUPUESTADO:");

        lbl_saldo.setBackground(new java.awt.Color(255, 204, 255));
        lbl_saldo.setFont(new java.awt.Font("Arial Unicode MS", 1, 18)); // NOI18N
        lbl_saldo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_saldo.setText("0.00");

        lbl_presupuesto.setBackground(new java.awt.Color(255, 204, 255));
        lbl_presupuesto.setFont(new java.awt.Font("Arial Unicode MS", 1, 18)); // NOI18N
        lbl_presupuesto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_presupuesto.setText("0.00");

        jLabel9.setFont(new java.awt.Font("Arial Unicode MS", 1, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("GASTADO:");

        lbl_gastado.setBackground(new java.awt.Color(0, 0, 0));
        lbl_gastado.setFont(new java.awt.Font("Arial Unicode MS", 1, 18)); // NOI18N
        lbl_gastado.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_gastado.setText("0.00");
        lbl_gastado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_gastadoMouseClicked(evt);
            }
        });

        lbl_monto_disponible2.setBackground(new java.awt.Color(255, 204, 255));
        lbl_monto_disponible2.setFont(new java.awt.Font("Arial Unicode MS", 1, 18)); // NOI18N
        lbl_monto_disponible2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_monto_disponible2.setText("MONTO DISPONIBLE:");

        lbl_monto_disponible.setBackground(new java.awt.Color(255, 204, 255));
        lbl_monto_disponible.setFont(new java.awt.Font("Arial Unicode MS", 1, 18)); // NOI18N
        lbl_monto_disponible.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbl_monto_disponible.setText("0.00");
        lbl_monto_disponible.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_monto_disponibleMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbl_saldo, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbl_monto_disponible2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbl_monto_disponible, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_presupuesto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl_gastado, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_monto_disponible)
                    .addComponent(lbl_monto_disponible2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_presupuesto)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_gastado)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(lbl_saldo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_consultas.setText("Tarjetas");
        btn_consultas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_consultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_consultasActionPerformed(evt);
            }
        });

        btn_pendientes.setText("Pendientes");
        btn_pendientes.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_pendientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pendientesActionPerformed(evt);
            }
        });

        btn_configuracion_general.setText("Configuracion");
        btn_configuracion_general.setToolTipText("");
        btn_configuracion_general.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_configuracion_general.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_configuracion_generalActionPerformed(evt);
            }
        });

        jButton1.setText("Recordatorios");
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btn_gastos.setText("Sin Asignar");
        btn_gastos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_gastosActionPerformed(evt);
            }
        });

        btn_detalle_cuotas.setText("Detalle Cuotas");
        btn_detalle_cuotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_detalle_cuotasActionPerformed(evt);
            }
        });

        btn_limpiar.setText("Limpiar Quincena");
        btn_limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_limpiarActionPerformed(evt);
            }
        });

        btn_bancos.setText("Salarios");
        btn_bancos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_bancosActionPerformed(evt);
            }
        });

        btn_cargarQuincena.setText("Cargar Quincena");
        btn_cargarQuincena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cargarQuincenaActionPerformed(evt);
            }
        });

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_consultas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_pendientes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_gastos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_detalle_cuotas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_limpiar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_cargarQuincena)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_bancos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_configuracion_general)
                .addGap(90, 90, 90))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_pendientes)
                    .addComponent(btn_consultas)
                    .addComponent(btn_gastos)
                    .addComponent(jButton1)
                    .addComponent(btn_detalle_cuotas)
                    .addComponent(btn_limpiar)
                    .addComponent(btn_cargarQuincena)
                    .addComponent(btn_bancos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_configuracion_general)))
            .addComponent(jSeparator2)
            .addComponent(jSeparator3)
        );

        btn_sumatoria.setBackground(new java.awt.Color(255, 255, 255));
        btn_sumatoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sumatoriaActionPerformed(evt);
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

        btn_eliminar_rubro.setBackground(new java.awt.Color(255, 255, 255));
        btn_eliminar_rubro.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn_eliminar_rubro.setBorderPainted(false);
        btn_eliminar_rubro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminar_rubroActionPerformed(evt);
            }
        });

        btn_modificar.setBackground(new java.awt.Color(255, 255, 255));
        btn_modificar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn_modificar.setBorderPainted(false);
        btn_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_modificarActionPerformed(evt);
            }
        });

        btn_agregar_rubro1.setBackground(new java.awt.Color(255, 255, 255));
        btn_agregar_rubro1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btn_agregar_rubro1.setBorderPainted(false);
        btn_agregar_rubro1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregar_rubro1ActionPerformed(evt);
            }
        });

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btn_buscar.setLabel("Buscar");
        btn_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarActionPerformed(evt);
            }
        });

        txt_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_buscarActionPerformed(evt);
            }
        });

        radio_detalle.setSelected(true);
        radio_detalle.setText("Detalle");
        radio_detalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio_detalleActionPerformed(evt);
            }
        });

        radio_monto.setText("Monto");
        radio_monto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio_montoActionPerformed(evt);
            }
        });

        jLabel1.setText("Seleccione el rubro por el cual desea buscar:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(txt_buscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_buscar))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(radio_detalle)
                                .addGap(18, 18, 18)
                                .addComponent(radio_monto))
                            .addComponent(jLabel1))
                        .addGap(0, 139, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radio_detalle)
                    .addComponent(radio_monto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_buscar)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(txt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btn_agregar_rubro1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_eliminar_rubro, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_pagado, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(btn_sumatoria, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btn_eliminar_rubro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_modificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_agregar_rubro1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_pagado, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_sumatoria, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Listado de rubros asignados al presupuesto");

        jLabel3.setText("Presupuesto: ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(101, 101, 101)
                .addComponent(idpresupuesto_txt, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(313, 313, 313))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(idpresupuesto_txt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

public void clearLabels(){
    lbl_monto_disponible.setText("0.00");
    lbl_presupuesto.setText("0.00");
    lbl_saldo.setText("0.00");
}
    
public void cargarPresupuesto()
{        
    boolean cargado=true;
    String rubros_inactivados=null,monto_disponible="0";
    Utilities utils = new Utilities();
    clearLabels();
    try 
    {
        // TODO add your handling code here:
        PresupuestoDAO con = PresupuestoDAO.getInstance(); 

        QuincenaActual quincenaActual = QuincenaActual.getInstance();
        this.presupuesto = quincenaActual.getPresupuestoobject();
        
        idpresupuesto_txt.setText(this.presupuesto.getIdpresupuesto());   
        int quincena = Integer.parseInt(cmb_quincena.getSelectedItem().toString());
        
        Object[][] datos;
        if (inicio==false){// La primera vez que corre la aplicacion, ya los valores estan precargados.
            //datos =con.getRubrosPresupuesto(this.presupuesto.getIdpresupuesto());    
            //quincenaActual.setDatos(datos);
            quincenaActual.cargarQuincena(this.presupuesto.getPresupuesto());
        }
        
        datos = quincenaActual.getDatos();
        actualizarTabla(datos);               
        
        double montoPresupuestado = quincenaActual.getMontoPresupuestado() ;

        lbl_monto_disponible.setForeground(Color.black);
        lbl_monto_disponible2.setForeground(Color.black);

        if (quincena==1 ){
            if (montoPresupuestado > Double.parseDouble(utils.priceWithoutCommas(quincenaActual.getMontoDisponible())) )
            {
                lbl_monto_disponible.setForeground(Color.red);
                lbl_monto_disponible2.setForeground(Color.red);
            }
            else
            {
                        lbl_monto_disponible.setForeground(Color.black);
                        lbl_monto_disponible2.setForeground(Color.black);
            }
        }
        else
        if (quincena==2) 
        {
            if (montoPresupuestado > Double.parseDouble(utils.priceWithoutCommas(quincenaActual.getMontoDisponible())))
            {
                lbl_monto_disponible.setForeground(Color.red);
                lbl_monto_disponible2.setForeground(Color.red);
            }      
            else
            {
                lbl_monto_disponible.setForeground(Color.black);
                lbl_monto_disponible2.setForeground(Color.black);
            }   
        }

        lbl_monto_disponible.setText(this.presupuesto.getMonto_disponible());
        lbl_presupuesto.setText(utils.priceWithDecimal ( quincenaActual.getMontoPresupuestado()  ));                                
        lbl_saldo.setText(utils.priceWithDecimal (quincenaActual.getMontoSaldo()));
        lbl_gastado.setText("- "+utils.priceWithDecimal (quincenaActual.getMontoPresupuestado()-quincenaActual.getMontoSaldo()));

                      
                                  
    } catch (Exception ex) {
        DialogError error = new DialogError (null,true,ex);         
        error.setVisible(true);            
    }   
    
    /* Se deshabilitan los botones de pagado y eliminar si no 
    hay nada seleccionado.
    */
    int row = table.getSelectedRow();
    int col = table.getSelectedColumn();  

    if  (row!=-1 && col!=-1)
    {
        btn_pagado.setEnabled(true);
        btn_modificar.setEnabled(true);
        btn_eliminar_rubro.setEnabled(true);                       
    } 
    else
    {
        btn_pagado.setEnabled(false);
        btn_modificar.setEnabled(false);
        btn_eliminar_rubro.setEnabled(false);        
    }
    inicio=false;
}


public void cargarOtraQuincena()
{        
    String rubros_inactivados=null,monto_disponible="0";
    Utilities utils = new Utilities();
    clearLabels();
    try 
    {
        PresupuestoDAO con = PresupuestoDAO.getInstance(); 
                                
        con.insertaTarjetasPendientes_de_Deposito(this.presupuesto.getIdpresupuesto(), this.presupuesto.getPresupuesto(), con.getIdusuario_logueado());
        
        
        int quincena = Integer.parseInt(cmb_quincena.getSelectedItem().toString());
        int ano = Integer.parseInt(cmb_ano.getSelectedItem().toString());
        int mes = cmb_mes.getSelectedIndex()+1;
        String quincena_str = String.valueOf(quincena)+"-"+String.valueOf(mes)+"-"+String.valueOf(ano);
        
        
        PresupuestoObject presupuestoobject = con.getPresupuesto(quincena_str);
        
        if (quincena==1)
            monto_disponible = con.getMontoPresupuestoDisponibleQuincena1();               
        else
            monto_disponible = con.getMontoPresupuestoDisponibleQuincena2();
         
                
        
        if (presupuestoobject.getIdpresupuesto().equals("0")){

            /*
            con.insertarPresupuesto(quincena_str, utils.priceWithoutCommas(monto_disponible));   
            con.insertarPresupuestoSalarios(quincena);
            presupuestoobject = con.getPresupuesto(quincena_str);                                    
     */
            if(JOptionPane.showConfirmDialog(null, "¿Desea insertar los RUBROS "
                    + "RECURRENTES y PENDIENTES?", 
                                   "Warning",
                                   JOptionPane.OK_CANCEL_OPTION,
                                   JOptionPane.QUESTION_MESSAGE)
                                    ==JOptionPane.OK_OPTION)
            {
                //rubros_inactivados=insertarCargosRecurrentes(con,quincena,mes,ano,monto_disponible);         
                
                cargar();
                
           }                
        }
                
        
        QuincenaActual quincenaActual = QuincenaActual.getInstance();
        quincenaActual.cambiarQuincena(quincena_str);                         
        this.presupuesto = quincenaActual.getPresupuestoobject();
            
        con.modificarIDPRESUPUESTO_de_GASTOXidGASTO(presupuestoobject.getIdpresupuesto());
        
        Object[][] datos =  quincenaActual.getDatos();
        actualizarTabla(datos);               
        
        double montoPresupuestado = quincenaActual.getMontoPresupuestado() ;

        lbl_monto_disponible.setForeground(Color.black);
        lbl_monto_disponible2.setForeground(Color.black);

        if (quincena==1 && montoPresupuestado > Double.parseDouble(utils.priceWithoutCommas(quincenaActual.getMontoDisponible())) )
        {
            lbl_monto_disponible.setForeground(Color.red);
            lbl_monto_disponible2.setForeground(Color.red);
        }
        else
        if (quincena==2 && montoPresupuestado > Double.parseDouble(utils.priceWithoutCommas(quincenaActual.getMontoDisponible())))
        {
            lbl_monto_disponible.setForeground(Color.red);
            lbl_monto_disponible2.setForeground(Color.red);
        }      

        lbl_monto_disponible.setText(this.presupuesto.getMonto_disponible());
        lbl_presupuesto.setText(utils.priceWithDecimal ( quincenaActual.getMontoPresupuestado()  ));                                
        lbl_saldo.setText(utils.priceWithDecimal (quincenaActual.getMontoSaldo()));
        lbl_gastado.setText("- "+utils.priceWithDecimal (quincenaActual.getMontoPresupuestado()-quincenaActual.getMontoSaldo()));

                              
            
    } catch (SQLException | NumberFormatException | HeadlessException | ParseException ex) {
        DialogError error = new DialogError (null,true,ex);         
        error.setVisible(true);            
    }   
    
    /* Se deshabilitan los botones de pagado y eliminar si no 
    hay nada seleccionado.
    */
    int row = table.getSelectedRow();
    int col = table.getSelectedColumn();  

    if  (row!=-1 && col!=-1)
    {
        btn_pagado.setEnabled(true);
        btn_modificar.setEnabled(true);
        btn_eliminar_rubro.setEnabled(true);                       
    } 
    else
    {
        btn_pagado.setEnabled(false);
        btn_modificar.setEnabled(false);
        btn_eliminar_rubro.setEnabled(false);        
    }
    
}



public void cargarOtraQuincena2()
{        
    String rubros_inactivados=null,monto_disponible="0";
    Utilities utils = new Utilities();
    clearLabels();
    try 
    {
        PresupuestoDAO con = PresupuestoDAO.getInstance(); 

        int quincena = Integer.parseInt(cmb_quincena.getSelectedItem().toString());
        int ano = Integer.parseInt(cmb_ano.getSelectedItem().toString());
        int mes = cmb_mes.getSelectedIndex()+1;
        String quincena_str = String.valueOf(quincena)+"-"+String.valueOf(mes)+"-"+String.valueOf(ano);
        
                            

        PresupuestoObject presupuestoobject = con.getPresupuesto(quincena_str);
        
        if (quincena==1)
            monto_disponible = con.getMontoPresupuestoDisponibleQuincena1();               
        else
            monto_disponible = con.getMontoPresupuestoDisponibleQuincena2();
         
        
        /*
        SI PARA LA QUINCENA SELECCIONADA NO HAY PRESUPUESTO CREADO SE CREA.
        */
        
        if (presupuestoobject.getIdpresupuesto().equals("0")){

            
            con.insertarPresupuesto(quincena_str, utils.priceWithoutCommas(monto_disponible));   
            con.insertarPresupuestoSalarios(quincena);
            presupuestoobject = con.getPresupuesto(quincena_str);
            
                                       
                rubros_inactivados=insertarCargosRecurrentes(con,quincena,mes,ano,monto_disponible);                                                                                          
                
                JOptionPane.showMessageDialog(null,
                            "La Quincena cargada.");
                
                          
        }
        else
              JOptionPane.showMessageDialog(null,
                            "La Quincena ya esta cargada.");
                        
        
        QuincenaActual quincenaActual = QuincenaActual.getInstance();
        quincenaActual.cambiarQuincena(quincena_str);                         
        this.presupuesto = quincenaActual.getPresupuestoobject();
                  
        
        Object[][] datos =  quincenaActual.getDatos();
        actualizarTabla(datos);               
        
        double montoPresupuestado = quincenaActual.getMontoPresupuestado() ;

        lbl_monto_disponible.setForeground(Color.black);
        lbl_monto_disponible2.setForeground(Color.black);

        if (quincena==1 && montoPresupuestado > Double.parseDouble(utils.priceWithoutCommas(quincenaActual.getMontoDisponible())) )
        {
            lbl_monto_disponible.setForeground(Color.red);
            lbl_monto_disponible2.setForeground(Color.red);
        }

        if (quincena==2 && montoPresupuestado > Double.parseDouble(utils.priceWithoutCommas(quincenaActual.getMontoDisponible())))
        {
            lbl_monto_disponible.setForeground(Color.red);
            lbl_monto_disponible2.setForeground(Color.red);
        }      

        lbl_monto_disponible.setText(this.presupuesto.getMonto_disponible());
        lbl_presupuesto.setText(utils.priceWithDecimal ( quincenaActual.getMontoPresupuestado()  ));                                
        lbl_saldo.setText(utils.priceWithDecimal (quincenaActual.getMontoSaldo()));
        lbl_gastado.setText("- "+utils.priceWithDecimal (quincenaActual.getMontoPresupuestado()-quincenaActual.getMontoSaldo()));

                              
            
    } catch (SQLException | NumberFormatException | HeadlessException | ParseException ex) {
        DialogError error = new DialogError (null,true,ex);         
        error.setVisible(true);            
    }   
    
    /* Se deshabilitan los botones de pagado y eliminar si no 
    hay nada seleccionado.
    */
    int row = table.getSelectedRow();
    int col = table.getSelectedColumn();  

    if  (row!=-1 && col!=-1)
    {
        btn_pagado.setEnabled(true);
        btn_modificar.setEnabled(true);
        btn_eliminar_rubro.setEnabled(true);                       
    } 
    else
    {
        btn_pagado.setEnabled(false);
        btn_modificar.setEnabled(false);
        btn_eliminar_rubro.setEnabled(false);        
    }
    
}

    public String  insertarCargosRecurrentes(PresupuestoDAO con, int quincena, int mes, int ano,String monto_disponible) throws SQLException, ParseException{
        String rubros_inactivados="";
        int idpresupuesto;       
        String quincena_str=String.valueOf(quincena)+"-"+String.valueOf(mes)+"-"+String.valueOf(ano); 
        
        Utilities utils = new Utilities();
        
       // con.insertarPresupuesto(quincena_str, utils.priceWithoutCommas(monto_disponible));                    
        idpresupuesto = con.getIDPresupuesto(String.valueOf(quincena)+"-"+String.valueOf(mes)+"-"+String.valueOf(ano));                   

        List<RubrosObject> list = con.getRubrosRecurrentesPresupuesto(quincena);

        for (int i = 0; i < list.size(); i++) {

            RubrosObject obj = list.get(i);	

            obj.setIdpresupuesto(String.valueOf(idpresupuesto));

            con.insertarRubro(obj);
        }    

        rubros_inactivados = insertarRubrosPendientes(String.valueOf(idpresupuesto),quincena_str);
        return rubros_inactivados;
    }


public void actualizarTabla(Object[][] datos){
   DefaultTableModel model;
   model = new javax.swing.table.DefaultTableModel(
           datos,
           this.columnNames
   )
   {
       final Class[] tiposColumnas = new Class[]{
                   java.lang.String.class,
                   java.lang.String.class,
                   java.lang.String.class,
                   java.lang.String.class,
                   java.lang.String.class
                   //javax.swing.JButton.class 
               };
       boolean[] canEdit = new boolean [] {
        false,   false, true, true, false//, false
       };
       Class[] tipos = tiposColumnas;

       @Override
       public boolean isCellEditable(int rowIndex, int columnIndex) {
           return canEdit [columnIndex];
       }
       @Override
       public Class getColumnClass(int columnIndex) {
           // Este método es invocado por el CellRenderer para saber que dibujar en la celda,
           // observen que estamos retornando la clase que definimos de antemano.                      
           return tipos[columnIndex];
       } 

   };



   
   table.setModel(model);


   table.getColumnModel().getColumn(0).setMaxWidth(60);
   table.getColumnModel().getColumn(1).setPreferredWidth(100);
   table.getColumnModel().getColumn(2).setPreferredWidth(300);
   table.getColumnModel().getColumn(3).setPreferredWidth(100);
      table.getColumnModel().getColumn(4).setPreferredWidth(100);
   
   DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
   rightRenderer.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);   
   table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
   table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
}        
    
    
    public void modificar(){  
        Utilities utils = new Utilities();
        try {
            PresupuestoDAO con = PresupuestoDAO.getInstance();                
            TableModel model = table.getModel();

            int row = table.getSelectedRow();
            int col = table.getSelectedColumn();               

            if  (row!=-1 && col!=-1)
            { 
                String idrubro = table.getValueAt(row, 0).toString();
                String nombre = table.getValueAt(row, 2).toString();
                String monto = table.getValueAt(row, 3).toString();
                monto = utils.priceWithoutCommas(monto);
                String saldotxt = table.getValueAt(row, 4).toString();   
                saldotxt = utils.priceWithoutCommas(saldotxt);

                RubrosObject obj = con.getRubro(idrubro);
                double dmonto = Double.parseDouble(monto);
                double dsaldo = Double.parseDouble(saldotxt);
                double dmonto_obj = Double.parseDouble(obj.getMonto());
                double dsaldo_obj = Double.parseDouble(obj.getSaldo());

                if (dmonto!=dmonto_obj || dsaldo!=dsaldo_obj )
                {
                    double saldo = Double.parseDouble(saldotxt);                
                    double monto1 = Double.parseDouble(obj.getMonto());
                    double monto2 = Double.parseDouble(monto);
                    double variable = monto2 - monto1;
                    saldo = saldo + variable;

                    con.modificarMontosRubro(idrubro, monto, String.valueOf(saldo));
                    System.out.println("Modificando ....");
                    cargarPresupuesto();
                }

                if (!nombre.equals(obj.getNombre()))
                {
                    con.modificarNombreRubro(idrubro, nombre);
                    System.out.println("Modificando Nombre....");
                    cargarPresupuesto();
                }
            }

        } catch (Exception ex) {
            DialogError error = new DialogError (null,true,ex);
            error.setVisible(true);
        }            
    }
    
    public void insertarGastos(){
        RubrosObject object = new RubrosObject();

        TableModel model = table.getModel();

        int row = table.getSelectedRow();
        int col = table.getSelectedColumn();

        if (row!=-1 && col!=-1)
        {
            String idrubro = table.getValueAt(row, 0).toString();
            String nombre = table.getValueAt(row, 1).toString();
            String monto = table.getValueAt(row, 2).toString();
            String estado = ABIERTO;


            object.setIdentificador(idrubro);
            object.setNombre(nombre);
            object.setMonto(monto);
            object.setIdpresupuesto(String.valueOf(presupuesto.getIdpresupuesto()));

            Gastos gastos = new Gastos (new JFrame(),true,1,object);
            gastos.setVisible(true);
            cargarPresupuesto();
        }
        else
        {
            JOptionPane.showMessageDialog(null,
                "Seleccione el rubro al cual desea incluirle gastos.");
        }    
    }
    
    private void btn_eliminar_rubroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminar_rubroActionPerformed
        // TODO add your handling code here:
        btn_eliminar();
    }//GEN-LAST:event_btn_eliminar_rubroActionPerformed
    
    public void btn_eliminar(){
            try {
                Utilities utils = new Utilities ();
                PresupuestoDAO con = PresupuestoDAO.getInstance();                                
                
                int rowArr[] = table.getSelectedRows();
                
                if (rowArr.length ==0)        
                {
                JOptionPane.showMessageDialog(null,
                            "Debe seleccionar un rubro de la tabla.");
                }          
                
                for (int i=0; i<rowArr.length ; i++){
                if  (rowArr[i]!=-1)
                {                                
                    String idrubro = table.getValueAt(rowArr[i], 0).toString();
                    String nombre = table.getValueAt(rowArr[i], 2).toString();
                    String monto = utils.priceWithoutCommas(table.getValueAt(rowArr[i], 3).toString());
                    String saldo = utils.priceWithoutCommas(table.getValueAt(rowArr[i], 4).toString());

                    if(JOptionPane.showConfirmDialog(null, "¿Desea eliminar el rubro '"+nombre+"'?",
                                                            "Warning",
                                                            JOptionPane.OK_CANCEL_OPTION,
                                                            JOptionPane.QUESTION_MESSAGE)
                                                            ==JOptionPane.OK_OPTION){                                                
                        if (!monto.equals(saldo)){
                            JOptionPane.showMessageDialog(null,
                                "El rubro "+nombre+" ya tiene gastos asociados. Click en VER GASTOS.");
                        }
                        else
                        {       
                            /*
                            24/06/2016
                            SE AGREGA STORE PROCEDURE ELIMINAR RUBRO.
                            */
                            
                            con.sp_eliminar_rubro(idrubro);
                                
                           JOptionPane.showMessageDialog(null,
                            "Rubro '"+nombre+"'  eliminado." );     
                            
                        }                        
                        
                    }
                }
                }                                                    
            } catch (Exception ex) {
                DialogError error = new DialogError (null,true,ex);
                error.setVisible(true);
            }     
            cargarPresupuesto();
    }
    
    
    public void cerrarPresupuesto()
    {
        // TODO add your handling code here:
        if (presupuesto.getIdpresupuesto()==null){
                    JOptionPane.showMessageDialog(null,
                        "No hay ningun presupuesto cargado que se pueda cerrar.");
                }
        else
        {
            JOptionPane.showMessageDialog(null,
                "Si cierra el presupuesto no podrá realizarle más modificaciones.");

            if(JOptionPane.showConfirmDialog(null, "¿Desea cerrar el presupuesto?",
                "Warning",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE)
            ==JOptionPane.OK_OPTION){

            try {
                PresupuestoDAO con = PresupuestoDAO.getInstance ();
                con.cerrarPresupuesto(Integer.parseInt(presupuesto.getIdpresupuesto()));
                presupuesto.setEstado(con.getEstadoPresupuesto(Integer.parseInt(presupuesto.getIdpresupuesto())));
            }
            catch(Exception ex){
                       DialogError error = new DialogError (null,true,ex);
                       error.setVisible(true);

            }
            }
        }    
    }
    
    
    
    private void btn_adelanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_adelanteActionPerformed

       
        
        adelantar_cmb();                 
        lbl_quincena.setText("Quincena "+cmb_quincena.getSelectedItem()+" "+cmb_mes.getSelectedItem()+" "+cmb_ano.getSelectedItem());
        
        cargarOtraQuincena();               
                
        if (lbl_quincena.getText().equals("Quincena 1 OCTUBRE 2015"))
            btn_atras.setEnabled(false);
        else
            btn_atras.setEnabled(true);        
    }//GEN-LAST:event_btn_adelanteActionPerformed

    public void adelantar_cmb (){
        //quincena 1
        if (cmb_quincena.getSelectedIndex()==0){
            cmb_quincena.setSelectedIndex(cmb_quincena.getSelectedIndex()+1);
         
        }
        else{
            //quincena2
            if (cmb_quincena.getSelectedIndex()==1){
                cmb_quincena.setSelectedIndex(0);
                
                if (cmb_mes.getSelectedIndex()==11){
                    cmb_mes.setSelectedIndex(0); 
                    cmb_ano.setSelectedIndex(cmb_ano.getSelectedIndex()+1);
                }
                else
                    cmb_mes.setSelectedIndex(cmb_mes.getSelectedIndex()+1);                                
            }
                
        }    
    }
    
    public void atrasar ()
    {
        //quincena 1
        if (cmb_quincena.getSelectedIndex()==0){
            cmb_quincena.setSelectedIndex(1);
            
            if (cmb_mes.getSelectedIndex()==0){
                cmb_mes.setSelectedIndex(11); 
                cmb_ano.setSelectedIndex(cmb_ano.getSelectedIndex()-1);
            }
            else
                cmb_mes.setSelectedIndex(cmb_mes.getSelectedIndex()-1);         
        }
        else{
            //quincena2
            if (cmb_quincena.getSelectedIndex()==1){
                cmb_quincena.setSelectedIndex(0);                                            
            }                
        }    
    }    
    
    
    private void btn_agregar_rubro1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregar_rubro1ActionPerformed
        // TODO add your handling code here:
        String quincena = cmb_quincena.getSelectedItem().toString();
        String mes = Integer.toString(cmb_mes.getSelectedIndex() +1);
        String ano = cmb_ano.getSelectedItem().toString();

        Rubros rubros = new Rubros(this,true,Integer.parseInt(presupuesto.getIdpresupuesto()), Integer.parseInt(quincena), Integer.parseInt(mes),Integer.parseInt(ano));

        rubros.setVisible(true);
        
        if(!rubros.cancelar)
            cargarPresupuesto();
        
        rubros.dispose();
    }//GEN-LAST:event_btn_agregar_rubro1ActionPerformed

    public void verGastos(){
       try { 
       RubrosObject object = new RubrosObject();
       Utilities utils = new Utilities();
       
        TableModel model = table.getModel();

        int row = table.getSelectedRow();
        int col = table.getSelectedColumn();

        if (row!=-1 && col!=-1)
        {
            String idrubro = table.getValueAt(row, 0).toString();
            String nombre = table.getValueAt(row, 2).toString();
            
            String monto;
           
            monto = utils.priceWithoutCommas(table.getValueAt(row, 3).toString());
           
            String saldo = utils.priceWithoutCommas(table.getValueAt(row, 4).toString());
            String estado = ABIERTO;


            object.setIdentificador(idrubro);
            object.setNombre(nombre);
            object.setMonto(monto);
            object.setSaldo(saldo);
            object.setIdpresupuesto(String.valueOf(presupuesto.getIdpresupuesto()));


            DetalleGastos gastos = new DetalleGastos (new JFrame(),true,object,this.path);
            gastos.setVisible(true);
            cargarPresupuesto();
        }
        
        } catch (ParseException ex){
                DialogError error = new DialogError (null,true,ex);
                error.setVisible(true);
        }

    }
    
    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
        TableModel model = table.getModel();

        int row = table.getSelectedRow();
        int col = table.getSelectedColumn();
        
        System.out.println("ID Rubro seleccionado:"+table.getValueAt(row, 0).toString());
        

        
        System.out.println("Selected row:"+table.getSelectedRow()); 
        System.out.println("Selected column:"+table.getSelectedColumn()); 
        
        btn_pagado.setEnabled(true);
        btn_eliminar_rubro.setEnabled(true);
        btn_modificar.setEnabled(true);
        
        
        
        
    }//GEN-LAST:event_tableMouseClicked

    private void btn_pendientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pendientesActionPerformed
        // TODO add your handling code here:
        if (presupuesto.getIdpresupuesto()==null){
            JOptionPane.showMessageDialog(null,
                "Debe cargar alguna quincena.");
        }
        else
        {
            Financiado pendientes = new Financiado (new JFrame(),true,presupuesto);
            pendientes.setVisible(true);
            cargarPresupuesto();
        }
        
    }//GEN-LAST:event_btn_pendientesActionPerformed

    private void btn_consultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_consultasActionPerformed
        // TODO add your handling code here:
            Consultas consultas = new Consultas (new JFrame(),true,presupuesto);
            consultas.setVisible(true);
          
    }//GEN-LAST:event_btn_consultasActionPerformed

    private void btn_atrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_atrasActionPerformed
        // TODO add your handling code here:
          

        atrasar();
        lbl_quincena.setText("Quincena "+cmb_quincena.getSelectedItem()+" "+cmb_mes.getSelectedItem()+" "+cmb_ano.getSelectedItem());
        cargarOtraQuincena();

        if (lbl_quincena.getText().equals("Quincena 1 OCTUBRE 2015"))
            btn_atras.setEnabled(false);
        else
            btn_atras.setEnabled(true);        
    }//GEN-LAST:event_btn_atrasActionPerformed

    private void btn_configuracion_generalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_configuracion_generalActionPerformed
        // TODO add your handling code here:
        ConfiguracionGeneral config = new ConfiguracionGeneral(this,true,this.path,presupuesto);

        config.setVisible(true);
        cargarPresupuesto();
    }//GEN-LAST:event_btn_configuracion_generalActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

           String texto = recordatorios(false);
        
            JDialogRecordatorios r = new JDialogRecordatorios(null,true,texto);

            r.setVisible(true);
            cargarPresupuesto();
                
       /*
       agregar en rubros_recurrentes el campo tipo_pago.
       
       */
    }//GEN-LAST:event_jButton1ActionPerformed

    public String recordatorios(boolean enviar){
 /*
            1. traer los rubros con saldo mayor a 0
                getRubrosPresupuestoSinPagar
            2. ver el tipo de pago de los rubros financiados
                getFechaPagoMinimo
            3. traer las fechas de pago minimo
            4. comparar con la fecha actual
            5. disparar alerta en caso de ser necesario
        */
        boolean sendemail = false;
        Utilities utils = new Utilities();
        int contador=0;
        String body_str="";
        
        int dia_actual = utils.getDay();
        
        try {
            PresupuestoDAO con = PresupuestoDAO.getInstance();


            Object sinpagar[][] = con.getRubrosPresupuestoSinPagar(this.presupuesto.getIdpresupuesto());

            
            String idrubro,rubro,saldo;
            int index=0,dia_limite_pago_minimo;
            while (index<sinpagar.length){
                
                idrubro = sinpagar[index][0].toString();
                rubro = sinpagar[index][1].toString();
                saldo = sinpagar[index][3].toString();
                
                String diaCorte = con.getDiaFechaCorte(idrubro);
                
                
                if (diaCorte!=null){
                    if (!diaCorte.equals("")){
                        String diasPagoMinimo = con.getDiasPagoMinimo(idrubro);
                        String diasPagoContado = con.getDiasPagoContado(idrubro);
                        String fechaCorte = diaCorte + "/" + utils.getMonth() +"/"+ utils.getYear();            
                        String fechaActual = utils.getDate();

                        SimpleDateFormat dateformat3 = new SimpleDateFormat("dd/MM/yyyy");            
                        Date date_fechaCorte = dateformat3.parse(fechaCorte);

                        Date date_fechaContado;
                        Date date_fechaMinimo;  


                        Date date_fechaActual = dateformat3.parse(fechaActual);

                        DateTime dt = new DateTime(date_fechaCorte);
                        dt = dt.plusDays(Integer.parseInt(diasPagoContado));
                        date_fechaContado = dt.toDate();

                        DateTime dt2 = new DateTime(date_fechaCorte);
                        dt2 = dt2.plusDays(Integer.parseInt(diasPagoMinimo));
                        date_fechaMinimo = dt2.toDate();  


                        if(date_fechaActual.before(date_fechaContado) ||date_fechaActual.equals(date_fechaContado)){
                            contador++;
                            body_str = body_str + contador+") " + rubro+". \n     Monto: "+saldo+" colones. "
                                        + "\n     Fecha límite Pago Contado: "+dateformat3.format(date_fechaContado) +"\n\n";
                        }
                        else
                        if(date_fechaActual.after(date_fechaContado)){
                            contador++;
                            body_str = body_str + contador+") " + rubro+". \n     Monto Vencido: "+saldo+" colones. "
                                    + "\n     Fecha límite Pago Contado: "+dateformat3.format(date_fechaContado)+"\n\n";
                        }
                    }
                }
                /*
                if (dia_limite!=null)
                {
                    dia_limite_pago_minimo = Integer.parseInt(dia_limite);
                    if (dia_limite_pago_minimo < dia_actual)
                    {
                        contador++;
                        sendemail=true;
                        
                        body_str = body_str+ contador+") " + rubro+".  Monto: "+saldo+" colones. Debió ser pagado el: "+dia_limite_pago_minimo+" "+utils.getMes()+"\n" ;                    
                    }
                    else
                    if (dia_actual<dia_limite_pago_minimo)
                    {//Send an email
                        contador++;
                        
                        if (dia_actual+4==dia_limite_pago_minimo || 
                            dia_actual+3==dia_limite_pago_minimo || 
                            dia_actual+2==dia_limite_pago_minimo || 
                            dia_actual+1==dia_limite_pago_minimo)
                            sendemail=true;
                        
                        body_str = body_str + contador+") " + rubro+". Monto: "+saldo+" colones. Fecha límite de pago: "+dia_limite_pago_minimo+" "+utils.getMes()+"\n" ;
                    }
                }*/
                index++;
            }
        
            if (contador!=0){
                body_str =  "----------------------------------------------------------\n"
                               +"Rubros que estan pendientes de pago:\n" 
                               +"----------------------------------------------------------\n\n"
                            +body_str
                            +"\n ";            
            }
            else
            {
                body_str =  "----------------------------------------------------------\n"
                               +"NO HAY RUBROS PENDIENTES.\n" 
                               +"----------------------------------------------------------\n\n"
                            +body_str
                            +"\n ";              
            }
                
            if (sendemail && enviar){
                
                SendMailTLS email = new SendMailTLS("fuentesster@gmail.com","fuentesster@gmail.com","Recordatorio de pago",body_str);                
                email.start();                
            }
            else
                System.out.println("Email not sent. Done");
            

        } catch (Exception ex) {
           DialogError error = new DialogError (null,true,ex);
           error.setVisible(true);
        }     
        return body_str;
    }
    

    private void btn_pagadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pagadoActionPerformed
        // TODO add your handling code here:

        btn_pagar();
    }//GEN-LAST:event_btn_pagadoActionPerformed

    public void btn_pagar(){
        TableModel model = table.getModel();
        Utilities utils = new Utilities();
        
        int rowArr[] = table.getSelectedRows();
        
        if (rowArr.length ==0)        
        {
        JOptionPane.showMessageDialog(null,
                    "Debe seleccionar un rubro de la tabla.");
        }         
        
        
        for (int i=0; i<rowArr.length ; i++){
        
        if  (rowArr[i]!=-1)
        {                                
            String idrubro = table.getValueAt(rowArr[i], 0).toString();            
            String nombre = table.getValueAt(rowArr[i], 2).toString();
            
            DetalleGastos detalle = new DetalleGastos();

            PresupuestoDAO presupuestodao = null;
            try {
                presupuestodao = PresupuestoDAO.getInstance();

                RubrosObject obj = presupuestodao.getRubro (idrubro);

                detalle.asignarcomoPagado(obj);
                
            }catch (SQLException ex) {
                                  
                   DialogError error = new DialogError (null,true,ex);
                   error.setVisible(true);
            }
            
        }           
        }
        cargarPresupuesto();
    }
    
    private void btn_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_modificarActionPerformed
        // TODO add your handling code here:
           btn_modificar();       
    }//GEN-LAST:event_btn_modificarActionPerformed

    public void btn_modificar(){
        TableModel model = table.getModel();

        int row = table.getSelectedRow();
        int col = table.getSelectedColumn();  

        if  (row!=-1 && col!=-1)
        {                                
            String idrubro = table.getValueAt(row, 0).toString();
            
            Rubros rubros = new Rubros(this,true,idrubro);
            rubros.setVisible(true);            
            cargarPresupuesto();                        
        }
        else
        {
            JOptionPane.showMessageDialog(null,
            "Debe seleccionar un rubro de la tabla.");
        }     
    }
    
    private void jMenuItem_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_modificarActionPerformed
        // TODO add your handling code here:
        btn_modificar();
    }//GEN-LAST:event_jMenuItem_modificarActionPerformed

    private void tableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseReleased
        // TODO add your handling code here:
    if (evt.getButton()== MouseEvent.BUTTON3)
    {
        JTable source = (JTable)evt.getSource();
        int row = source.rowAtPoint( evt.getPoint() );
        int column = source.columnAtPoint( evt.getPoint() );

        if (!source.isRowSelected(row)) {
            source.changeSelection(row, column, false, false);
        }
        jPopupMenu1.show(evt.getComponent(), evt.getX(), evt.getY());
    }
        
    }//GEN-LAST:event_tableMouseReleased

    private void jMenuItem_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_eliminarActionPerformed
        // TODO add your handling code here:
        btn_eliminar();
    }//GEN-LAST:event_jMenuItem_eliminarActionPerformed

    private void jMenuItem_pagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_pagarActionPerformed
        // TODO add your handling code here:
        btn_pagar();
    }//GEN-LAST:event_jMenuItem_pagarActionPerformed

    private void jMenuItem_vergastosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_vergastosActionPerformed
        // TODO add your handling code here:
        verGastos();
    }//GEN-LAST:event_jMenuItem_vergastosActionPerformed

    private void jMenuItem_insertargastoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_insertargastoActionPerformed
        // TODO add your handling code here:
        TableModel model = table.getModel();

        int row = table.getSelectedRow();
        int col = table.getSelectedColumn();  

        if  (row!=-1 && col!=-1)
        {                                
            String idrubro = table.getValueAt(row, 0).toString();
            String nombre = table.getValueAt(row, 2).toString();
            RubrosObject rubrosobject = new RubrosObject(); 
            rubrosobject.setIdentificador(idrubro);
            rubrosobject.setIdpresupuesto(this.presupuesto.getIdpresupuesto());
            rubrosobject.setNombre(nombre);
            
            Gastos gastos = new Gastos (new JFrame(),true,1,rubrosobject);
            gastos.setVisible(true);            
        }
            cargarPresupuesto();
    }//GEN-LAST:event_jMenuItem_insertargastoActionPerformed

    private void jMenuItem_vercuotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_vercuotasActionPerformed
        verDetalleCuotas();
        
    }//GEN-LAST:event_jMenuItem_vercuotasActionPerformed

    public void verDetalleCuotas(){
        // TODO add your handling code here:
        Utilities utils = new Utilities();  
        PresupuestoDAO con = null;
        TableModel model = table.getModel();
                
        int row = table.getSelectedRow();
        int col = table.getSelectedColumn();  

        if  (row!=-1 && col!=-1)
        {           
            try {
                String idrubro = table.getValueAt(row, 0).toString();                                
                PendientesObject obj = new PendientesObject();

                con = PresupuestoDAO.getInstance();                
                obj = con.getPendientesXidrubro(idrubro);
                
                String idfinanciado = obj.getIdentificador();
                if (idfinanciado!=null)
                {
                    DetallePagosFinanciados pagos = new DetallePagosFinanciados (new JFrame(),true,obj, this.presupuesto,this.path);
                    pagos.setVisible(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(null,
                    "No es un plan de credito.");
                }
                                       
            } catch (SQLException ex) {
                DialogError error = new DialogError (null,true,ex);         
                error.setVisible(true);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null,
            "Debe seleccionar un rubro de la tabla.");
        }     
    }
    
 
    
    private void btn_gastosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_gastosActionPerformed
        // TODO add your handling code here:

            JDialogGastosSinAsignar gastos = new JDialogGastosSinAsignar (new JFrame(),true,this.presupuesto);
            gastos.setVisible(true);
            cargarPresupuesto();              
    }//GEN-LAST:event_btn_gastosActionPerformed

    private void btn_detalle_cuotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_detalle_cuotasActionPerformed
        // TODO add your handling code here:
        verDetalleCuotas();
    }//GEN-LAST:event_btn_detalle_cuotasActionPerformed

    private void btn_sumatoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sumatoriaActionPerformed
        // TODO add your handling code here:
        btn_sumatoria();
        
    }//GEN-LAST:event_btn_sumatoriaActionPerformed

    private void btn_limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_limpiarActionPerformed
        // TODO add your handling code here:
        
        try 
          {
              
              PresupuestoDAO con = PresupuestoDAO.getInstance();                                              

              
              JOptionPane.showMessageDialog(null,
                                "Los gastos ya asignados volveran a su estado sin asignar. No se perderan.");

                    if(JOptionPane.showConfirmDialog(null, "¿Desea limpiar la quincena?",
                                                            "Warning",
                                                            JOptionPane.OK_CANCEL_OPTION,
                                                            JOptionPane.QUESTION_MESSAGE)
                                                            ==JOptionPane.OK_OPTION){                                                


                        

                        
                       // boolean gastos = con.hayGastosenPresupuesto(this.presupuesto.getIdpresupuesto());



                        
                        
                                         
                            //con.LIMPIAR_QUINCENA(this.presupuesto.getIdpresupuesto());
                            // con.getQuincenaPrevia();
                            
                            con.sp_limpiar(this.presupuesto.getIdpresupuesto());
                           
                           

                              Object[][] datos=null;
                              actualizarTabla(datos);                

                               JOptionPane.showMessageDialog(null,
                                  "Quincena limpiada.");
                               lbl_presupuesto.setText("0.00");
                               lbl_gastado.setText("0.00");
                               lbl_monto_disponible.setText("0.00");
                               lbl_saldo.setText("0.00");
                               
                        
                    }
              
          } catch (Exception ex) {
              DialogError error = new DialogError (null,true,ex);         
              error.setVisible(true);            
          }  
        
        
        

    }//GEN-LAST:event_btn_limpiarActionPerformed

    private void btn_bancosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_bancosActionPerformed
        // TODO add your handling code here:
        JDialogCuentaBancarias s = new JDialogCuentaBancarias (null,true,this.presupuesto);
        s.setVisible(true);        
        
    }//GEN-LAST:event_btn_bancosActionPerformed

    private void btn_cargarQuincenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cargarQuincenaActionPerformed
        // TODO add your handling code here:               
        cargar();
    }//GEN-LAST:event_btn_cargarQuincenaActionPerformed

    
    
public void cargar()
{        
    String rubros_inactivados=null,monto_disponible="0";
    Utilities utils = new Utilities();
    clearLabels();
    try 
    {
        PresupuestoDAO con = PresupuestoDAO.getInstance(); 

        int quincena = Integer.parseInt(cmb_quincena.getSelectedItem().toString());
        int ano = Integer.parseInt(cmb_ano.getSelectedItem().toString());
        int mes = cmb_mes.getSelectedIndex()+1;
        String quincena_str = String.valueOf(quincena)+"-"+String.valueOf(mes)+"-"+String.valueOf(ano);        
                            

        PresupuestoObject presupuestoobject = con.getPresupuesto(quincena_str);
        
        if (quincena==1)
            monto_disponible = con.getMontoPresupuestoDisponibleQuincena1();               
        else
            monto_disponible = con.getMontoPresupuestoDisponibleQuincena2();
         
        
        String idpresupuesto = con.sp_insertar_presupuesto(quincena_str);
        if (con.isLimpio(idpresupuesto))
        {
            con.sp_cargar(idpresupuesto);
        }
        else
        {
                JOptionPane.showMessageDialog(null,
                "El presupuesto no esta limpio.");        
        }
            
       
                        
        
        QuincenaActual quincenaActual = QuincenaActual.getInstance();
        quincenaActual.cambiarQuincena(quincena_str);                         
        this.presupuesto = quincenaActual.getPresupuestoobject();
                  
        
        Object[][] datos =  quincenaActual.getDatos();
        actualizarTabla(datos);               
        
        double montoPresupuestado = quincenaActual.getMontoPresupuestado() ;

        lbl_monto_disponible.setForeground(Color.black);
        lbl_monto_disponible2.setForeground(Color.black);

        if (quincena==1 && montoPresupuestado > Double.parseDouble(utils.priceWithoutCommas(quincenaActual.getMontoDisponible())) )
        {
            lbl_monto_disponible.setForeground(Color.red);
            lbl_monto_disponible2.setForeground(Color.red);
        }

        if (quincena==2 && montoPresupuestado > Double.parseDouble(utils.priceWithoutCommas(quincenaActual.getMontoDisponible())))
        {
            lbl_monto_disponible.setForeground(Color.red);
            lbl_monto_disponible2.setForeground(Color.red);
        }      

        lbl_monto_disponible.setText(this.presupuesto.getMonto_disponible());
        lbl_presupuesto.setText(utils.priceWithDecimal ( quincenaActual.getMontoPresupuestado()  ));                                
        lbl_saldo.setText(utils.priceWithDecimal (quincenaActual.getMontoSaldo()));
        lbl_gastado.setText("- "+utils.priceWithDecimal (quincenaActual.getMontoPresupuestado()-quincenaActual.getMontoSaldo()));

                              
            
    } catch (SQLException | NumberFormatException | HeadlessException | ParseException ex) {
        DialogError error = new DialogError (null,true,ex);         
        error.setVisible(true);            
    }   
    
    /* Se deshabilitan los botones de pagado y eliminar si no 
    hay nada seleccionado.
    */
    int row = table.getSelectedRow();
    int col = table.getSelectedColumn();  

    if  (row!=-1 && col!=-1)
    {
        btn_pagado.setEnabled(true);
        btn_modificar.setEnabled(true);
        btn_eliminar_rubro.setEnabled(true);                       
    } 
    else
    {
        btn_pagado.setEnabled(false);
        btn_modificar.setEnabled(false);
        btn_eliminar_rubro.setEnabled(false);        
    }
    
}    
    
    
    private void lbl_gastadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_gastadoMouseClicked
        // TODO add your handling code here:
        JDialogListadoGastos listadoGastos = new JDialogListadoGastos (this,true,this.path, this.presupuesto);
        listadoGastos.setVisible(true);
        cargarPresupuesto();  
    }//GEN-LAST:event_lbl_gastadoMouseClicked

    private void lbl_monto_disponibleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_monto_disponibleMouseClicked
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            JDialogSalariosxQuincena s = new JDialogSalariosxQuincena (null,true,this.presupuesto);
            s.setVisible(true);
            QuincenaActual quincenaActual = QuincenaActual.getInstance() ;
            quincenaActual.clean();
            
            cargarPresupuesto();
        } catch (SQLException ex) {
            Logger.getLogger(Presupuesto.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }//GEN-LAST:event_lbl_monto_disponibleMouseClicked

    private void radio_detalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio_detalleActionPerformed
        // TODO add your handling code here:
       radio_monto.setSelected(false);
       radio_detalle.setSelected(true);
    }//GEN-LAST:event_radio_detalleActionPerformed

    private void radio_montoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio_montoActionPerformed
        // TODO add your handling code here:
      radio_monto.setSelected(true);
       radio_detalle.setSelected(false);        
    }//GEN-LAST:event_radio_montoActionPerformed

    private void btn_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarActionPerformed
        // TODO add your handling code here:
        buscar();
            
            
    }//GEN-LAST:event_btn_buscarActionPerformed

    private void txt_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_buscarActionPerformed
        // TODO add your handling code here:
        buscar();
    }//GEN-LAST:event_txt_buscarActionPerformed

    public void buscar(){
        boolean detalle=false;
            if (radio_detalle.isSelected())
                detalle=true;
            else
                detalle=false;        
        
            String str = txt_buscar.getText().toUpperCase().trim();
            
            if (!str.equals("")){
                ResultadoBusqueda resultado = new ResultadoBusqueda (detalle, str, this.path,this.presupuesto);
                resultado.setVisible(true);   
            }
    }
    
    public void btn_sumatoria(){
        TableModel model = table.getModel();
        Utilities utils = new Utilities();
        
        int rowArr[] = table.getSelectedRows();
        String monto="", saldo="";
        double sum_saldo=0, sum_monto=0, temp=0;
   
        
        for (int i=0; i<rowArr.length ; i++){
        
            if  (rowArr[i]!=-1)
            {                                
                try {
                    monto = table.getValueAt(rowArr[i], 3).toString();
                    saldo = table.getValueAt(rowArr[i], 4).toString();

                    saldo=utils.priceWithoutCommas(saldo);
                    monto=utils.priceWithoutCommas(monto);

                    sum_monto = sum_monto + Double.parseDouble(monto);
                    sum_saldo = sum_saldo + Double.parseDouble(saldo); 



                } catch (ParseException ex) {
                    DialogError error = new DialogError (null,true,ex);
                    error.setVisible(true);
                }

            }           
        }
        monto = "Monto total disponible: " +utils.priceWithDecimal(sum_monto);
        saldo = "Monto saldo disponible: " +utils.priceWithDecimal(sum_saldo);
        
        JDialogsSumatorio s = new JDialogsSumatorio (null,true,monto,saldo);
        s.setVisible(true);
                
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Presupuesto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Presupuesto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Presupuesto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Presupuesto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Presupuesto().setVisible(true);
            }
        });
      
    }

    public void setActualDate(){
        
            Date date = null; // your date
            Calendar cal = Calendar.getInstance();
            
            year = cal.get(Calendar.YEAR);
            month = cal.get(Calendar.MONTH)+1;
            day = cal.get(Calendar.DAY_OF_MONTH);
                   
            cmb_ano.setSelectedItem(String.valueOf(year));
            
            
            if (month == 1)
                cmb_mes.setSelectedItem("ENERO");
            else
                if (month == 2)
                    cmb_mes.setSelectedItem("FEBRERO");
                else
                    if (month == 3)
                        cmb_mes.setSelectedItem("MARZO");
                    else
                        if (month == 4)
                            cmb_mes.setSelectedItem("ABRIL");
                        else
                            if (month == 5)
                                cmb_mes.setSelectedItem("MAYO");
                            else
                                if (month == 6)
                                    cmb_mes.setSelectedItem("JUNIO");
                                else
                                    if (month == 7)
                                        cmb_mes.setSelectedItem("JULIO");
                                    else
                                        if (month == 8)
                                            cmb_mes.setSelectedItem("AGOSTO");
                                        else
                                            if (month == 9)
                                                cmb_mes.setSelectedItem("SETIEMBRE");
                                            else
                                                if (month == 10)
                                                    cmb_mes.setSelectedItem("OCTUBRE");
                                                else
                                                    if (month == 11)
                                                        cmb_mes.setSelectedItem("NOVIEMBRE");
                                                    else
                                                        if (month == 12)
                                                            cmb_mes.setSelectedItem("DICIEMBRE");
            
    }
            
    public void cargarValoresIniciales(){
        try {

            PresupuestoDAO con = PresupuestoDAO.getInstance();
            
            String str = con.getPresupuestoActivo();
            String arr[] = str.split("-");
        
            int quincena =Integer.parseInt(arr[0]);
            int mes = Integer.parseInt(arr[1]);
            int ano = Integer.parseInt(arr[2]);
   
            cmb_quincena.setSelectedIndex(quincena-1);
            cmb_mes.setSelectedIndex(mes-1);
            cmb_ano.setSelectedItem(arr[2]);                    
                    
        } catch (SQLException | NumberFormatException ex) {
                DialogError error = new DialogError (null,true,ex);
                error.setVisible(true);
        }
        

        table.setDefaultRenderer(JButton.class, new TableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable jtable, Object objeto, boolean estaSeleccionado, boolean tieneElFoco, int fila, int columna) {
            /**
             * Observen que todo lo que hacemos en éste método es retornar el objeto que se va a dibujar en la 
             * celda. Esto significa que se dibujará en la celda el objeto que devuelva el TableModel. También 
             * significa que este renderer nos permitiría dibujar cualquier objeto gráfico en la grilla, pues 
             * retorna el objeto tal y como lo recibe.
             */
            return (Component) objeto;
        }
    });

    table.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            int fila = table.rowAtPoint(e.getPoint());
            int columna = table.columnAtPoint(e.getPoint());

            /**
             * Preguntamos si hicimos clic sobre la celda que contiene el botón, si tuviéramos más de un botón 
             * por fila tendríamos que además preguntar por el contenido del botón o el nombre de la columna
             */
            if (table.getModel().getColumnClass(columna).equals(JButton.class)) {
                    /**
                     * Aquí pueden poner lo que quieran, para efectos de este ejemplo, voy a mostrar
                     * en un cuadro de dialogo todos los campos de la fila que no sean un botón.
                     */
                    verGastos();

            }
        }
    });                

    table.getDefaultEditor(String.class).addCellEditorListener(ChangeNotification);

    }

    
    
    public String insertarRubrosPendientes(String idpre,String quincena_str) throws ParseException{
        String pendientes_inactivados=null;
        String descripcion="";
        int contador=0;
        String monto_total="", monto_pagado="",plan="";
        double monto_t, monto_p,montocuota;
        Utilities utils = new Utilities();
        CuotaObject next_cuota;
        
        PresupuestoDAO con = null;
        try {
            con = PresupuestoDAO.getInstance();
            String arr[]= quincena_str.split("-");
            
            String rubros_array [][]= con.getGastosPendientesdePago(arr[0]);
            
            if (rubros_array!=null){
                int j = rubros_array.length;
                int i =0;
                while (i<j)
                {            
                    RubrosObject rubrosfinanciado = new RubrosObject();
                    plan = rubros_array[i][2].toString();
                    monto_total = utils.priceWithoutCommas(rubros_array[i][4].toString());
                    monto_t = Double.parseDouble(monto_total);
                    monto_pagado = utils.priceWithoutCommas(rubros_array[i][6].toString());
                    monto_p = Double.parseDouble(monto_pagado);
                    rubrosfinanciado.setIdpresupuesto(idpre);
                    int cuotas_pagadas = Integer.parseInt(rubros_array[i][3].toString());

                    String idfinanciado=rubros_array[i][0];
                    next_cuota = con.getNextCuota(idfinanciado);  
                    
                    if (next_cuota.getIdcuota()!=null)
                    {                                                               
                        rubrosfinanciado.setIdentificador(idfinanciado);                    
                        if (!plan.equals("SIG. QUINCENA") )
                            descripcion=rubros_array[i][1] + " Cuota:"+ next_cuota.getDescripcion() ;
                        else
                            descripcion=rubros_array[i][1];

                        rubrosfinanciado.setNombre(descripcion);                                          
                        rubrosfinanciado.setMonto(utils.priceWithoutCommas(rubros_array[i][5]));

                        int idrubro=0;
                        idrubro=con.getIDRUBRO_SEQ();
                        rubrosfinanciado.setIdentificador(String.valueOf(idrubro));
                                               
                        rubrosfinanciado.setTipo_pago(rubros_array[i][7]);

                        if (monto_p < monto_t)
                        {        
                            con.insertarRubro2(rubrosfinanciado);
                            con.insertarRubroFinanciado(idrubro,Integer.parseInt(idfinanciado));
                            montocuota =  Double.parseDouble(rubrosfinanciado.getMonto());

                            /*
                            //se modifica el pago en la tabla FINANCIADO_PAGOS.                        
                            */                        
                            CuotaObject cuota = new CuotaObject();                        
                            cuota.setIdfinanciado(idfinanciado);
                            cuota.setIdpresupuesto(idpre);
                            cuota.setIdrubro(String.valueOf(idrubro));
                            cuota.setMonto( String.valueOf(montocuota));
                            cuota.setFecha(utils.getDate());
                            cuota.setDescripcion(descripcion);
                            cuota.setEstado(CuotaObject.ASIGNADO);
                            cuota.setIdcuota(next_cuota.getIdcuota() );                        
                            con.modificarEstadoCuota(cuota);

                        }
                    }
                    i++;                  
                }
            }
        } catch (SQLException | NumberFormatException ex) 
        {
            DialogError error = new DialogError (null,true,ex);
            error.setVisible(true);
        }   
        return pendientes_inactivados;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_adelante;
    private javax.swing.JButton btn_agregar_rubro1;
    private javax.swing.JButton btn_atras;
    private javax.swing.JButton btn_bancos;
    private javax.swing.JButton btn_buscar;
    private javax.swing.JButton btn_cargarQuincena;
    private javax.swing.JButton btn_configuracion_general;
    private javax.swing.JButton btn_consultas;
    private javax.swing.JButton btn_detalle_cuotas;
    private javax.swing.JButton btn_eliminar_rubro;
    private javax.swing.JButton btn_gastos;
    private javax.swing.JButton btn_limpiar;
    private javax.swing.JButton btn_modificar;
    private javax.swing.JButton btn_pagado;
    private javax.swing.JButton btn_pendientes;
    private javax.swing.JButton btn_sumatoria;
    private javax.swing.JComboBox cmb_ano;
    private javax.swing.JComboBox cmb_mes;
    private javax.swing.JComboBox cmb_quincena;
    private javax.swing.JLabel idpresupuesto_txt;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem_eliminar;
    private javax.swing.JMenuItem jMenuItem_insertargasto;
    private javax.swing.JMenuItem jMenuItem_modificar;
    private javax.swing.JMenuItem jMenuItem_pagar;
    private javax.swing.JMenuItem jMenuItem_vercuotas;
    private javax.swing.JMenuItem jMenuItem_vergastos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lbl_gastado;
    private javax.swing.JLabel lbl_monto_disponible;
    private javax.swing.JLabel lbl_monto_disponible2;
    private javax.swing.JLabel lbl_presupuesto;
    private javax.swing.JLabel lbl_quincena;
    private javax.swing.JLabel lbl_saldo;
    private javax.swing.JRadioButton radio_detalle;
    private javax.swing.JRadioButton radio_monto;
    private javax.swing.JTable table;
    private javax.swing.JTextField txt_buscar;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}

