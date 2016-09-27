/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.text.ParseException;
import presupuesto.DialogError;
import presupuesto.Utilities;

/**
 *
 * @author MaFuen1
 */
public class TiposPagoSingleton {
    private static TiposPagoSingleton instance = null;
    String tiposp[];
    String tipospDebito[];

    public String[] getTiposp() {
        return tiposp;
    }

    public String[] getTipospDebito() {
        return tipospDebito;
    }

    public void setTipospDebito(String[] tipospDebito) {
        this.tipospDebito = tipospDebito;
    }

    public void setTiposp(String[] tiposp) {
        this.tiposp = tiposp;
    }
  
    public static TiposPagoSingleton getInstance() throws SQLException 
    {
            if(instance == null) {
                    instance = new TiposPagoSingleton();
            }
            return instance;
    }    

    public static TiposPagoSingleton reload () throws SQLException 
    {          
                 instance = new TiposPagoSingleton();
                  return instance;
    }    
          
    
  
  private TiposPagoSingleton( ) throws SQLException
  {
      
      Utilities utils = new Utilities();
      try {
            PresupuestoDAO con = PresupuestoDAO.getInstance();
            
            this.tiposp = con.getTiposdePago();
            this.tipospDebito = con.getTiposdePagoDEBITO();
            
                                            
        } catch (SQLException | NumberFormatException ex) {
                DialogError error = new DialogError (null,true,ex);
                error.setVisible(true);
        }
      
  }      
}
