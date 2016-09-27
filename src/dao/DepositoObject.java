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
public class DepositoObject {
    
    public final static String DEPOSITO_APLICADO ="DEPOSITO APLICADO";
    public final static String DEPOSITO_PENDIENTE ="PENDIENTE";

    public String getQuincena() {
        return quincena;
    }

    public void setQuincena(String quincena) {
        this.quincena = quincena;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    String iddeposito;
    String idpresupuesto;
    String quincena;
    String estado;

    public String getIdpresupuesto() {
        return idpresupuesto;
    }

    public void setIdpresupuesto(String idpresupuesto) {
        this.idpresupuesto = idpresupuesto;
    }

    public String getIddeposito() {
        return iddeposito;
    }

    public void setIddeposito(String iddeposito) {
        this.iddeposito = iddeposito;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipo_pago() {
        return tipo_pago;
    }

    public void setTipo_pago(String tipo_pago) {
        this.tipo_pago = tipo_pago;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getIdusuario() {
        return idusuario;
    }



    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }


    String fecha;
    String tipo_pago;
    double monto;

    public String getIdcomprobante() {
        return idcomprobante;
    }

    public void setIdcomprobante(String idcomprobante) {
        this.idcomprobante = idcomprobante;
    }
    String idusuario;
    String idcomprobante;
    
}
