/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import presupuesto.DialogError;
import presupuesto.Utilities;

/**
 *
 * @author MaFuen1
 */
public class QuincenaActual {
               
  private static QuincenaActual instance = null;  
  
  Object datos[][];

    public String getMontoDisponible() {
        return montoDisponible;
    }

    public double getMontoPresupuestado() {
        return montoPresupuestado;
    }

    public double getMontoSaldo() {
        return montoSaldo;
    }
  PresupuestoObject presupuestoobject;
  String montoDisponible;
  double montoPresupuestado;
  double montoSaldo;
  int quincena;

    public int getQuincena() {
        return quincena;
    }

    public void setQuincena(int quincena) {
        this.quincena = quincena;
    }

    public Utilities getUtils() {
        return utils;
    }

    public void setUtils(Utilities utils) {
        this.utils = utils;
    }
  Utilities utils = new Utilities();

    public Object[][] getDatos() {
        return datos;
    }

    public PresupuestoObject getPresupuestoobject() {
        return presupuestoobject;
    }
  
      
    public static QuincenaActual getInstance() throws SQLException 
    {
            if(instance == null) {
                    instance = new QuincenaActual();
            }
            return instance;
    }    
    
      
    public void  clean () throws SQLException 
    {
         
                    instance = new QuincenaActual();
    }    
          
      
  
  private QuincenaActual( ) throws SQLException
  {
            
      try {
            PresupuestoDAO con = PresupuestoDAO.getInstance();
            
            String quincena_str = con.getPresupuestoActivo();
            
            cargarQuincena(quincena_str);
                        
                                    
        } catch (SQLException | NumberFormatException ex) {
                DialogError error = new DialogError (null,true,ex);
                error.setVisible(true);
        }
      
  }
  
  
  public void cargarQuincena(String quincena_str) throws SQLException{
    PresupuestoDAO con = PresupuestoDAO.getInstance();
    this.presupuestoobject = con.getPresupuesto(quincena_str);    
         
    datos =con.getRubrosPresupuesto(presupuestoobject.getIdpresupuesto());    

    String str[]=quincena_str.split("-");
    this.setQuincena (Integer.parseInt(str[0]));
    
    if (str[0].equals("1"))
         montoDisponible = con.getMontoPresupuestoDisponibleQuincena1();
     else            
         montoDisponible = con.getMontoPresupuestoDisponibleQuincena2();            


    String str_monto,str_saldo;
    double tmp_monto=0,tmp_saldo=0;           
    montoPresupuestado=0;
    montoSaldo=0;      

    int registros = datos.length;            
    for (int i=0; i<registros; i++)
    {
        str_monto = (String) datos [i][3];
        str_saldo = (String) datos [i][4];

        try {
            tmp_monto = Double.parseDouble(utils.priceWithoutCommas(str_monto));
            tmp_saldo = Double.parseDouble(utils.priceWithoutCommas(str_saldo));

            montoPresupuestado = montoPresupuestado + tmp_monto;
            montoSaldo = montoSaldo + tmp_saldo;                    


        } catch (ParseException ex) {
            ex.printStackTrace();
        }                             
    }
  }

    public void setPresupuestoobject(PresupuestoObject presupuestoobject) {
        this.presupuestoobject = presupuestoobject;
    }

    public void setDatos(Object[][] datos) {
        this.datos = datos;
    }
    
    
    public void cambiarQuincena(String NuevaQuincena){
        Utilities utils = new Utilities();
        try {
            PresupuestoDAO con = PresupuestoDAO.getInstance();
            
            con.cerrarPresupuesto(this.presupuestoobject.getIdpresupuesto());
            
            cargarQuincena(NuevaQuincena);
            
            con.abrirPresupuesto(this.presupuestoobject.getIdpresupuesto());
        
        } catch (SQLException | NumberFormatException ex) {
                DialogError error = new DialogError (null,true,ex);
                error.setVisible(true);
        }            
    
    
    
    }
    
}
