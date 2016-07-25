/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author MaFuen1
 */
public class PresupuestoObject {
  String idpresupuesto;
  String quincena;

    public String getIdpresupuesto() {
        return idpresupuesto;
    }

    public void setIdpresupuesto(String idpresupuesto) {
        this.idpresupuesto = idpresupuesto;
    }

    public String getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(String presupuesto) {
        this.presupuesto = presupuesto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
  String presupuesto;
  String estado;

    public String getMonto_disponible() {
        return monto_disponible;
    }

    public void setMonto_disponible(String monto_disponible) {
        this.monto_disponible = monto_disponible;
    }
  String monto_disponible;

    public String getQuincena() {
        
        if (this.presupuesto!=null){
        String arr[]=this.presupuesto.split("-");
        
        return arr[0];
        }
        else
            return "";
    }
  
  
  
  
}
