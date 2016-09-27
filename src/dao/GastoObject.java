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
public class GastoObject {
    
    String lugar;
    String monto;
    String fecha;
    String idpresupuesto;
    String idrubro;
    String tipo_pago;
    String idgasto;

    public String getIdgasto() {
        return idgasto;
    }

    public void setIdgasto(String idgasto) {
        this.idgasto = idgasto;
    }

    public String getTipo_pago() {
        return tipo_pago;
    }

    public void setTipo_pago(String tipo_pago) {
        this.tipo_pago = tipo_pago;
    }
    

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getIdpresupuesto() {
        return idpresupuesto;
    }

    public void setIdpresupuesto(String idpresupuesto) {
        this.idpresupuesto = idpresupuesto;
    }

    public String getIdrubro() {
        return idrubro;
    }

    public void setIdrubro(String idrubro) {
        this.idrubro = idrubro;
    }
           
    
}
