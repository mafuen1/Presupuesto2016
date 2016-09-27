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
public class FinanciadoPagosObject {
    String idfinanciado;
    String idpresupuesto;
    String idrubro;
    String monto;
    String fecha;

    public String getIdfinanciado() {
        return idfinanciado;
    }

    public void setIdfinanciado(String idfinanciado) {
        this.idfinanciado = idfinanciado;
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

    public String getNcuota() {
        return ncuota;
    }

    public void setNcuota(String ncuota) {
        this.ncuota = ncuota;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha_pago_minimo() {
        return fecha_pago_minimo;
    }

    public void setFecha_pago_minimo(String fecha_pago_minimo) {
        this.fecha_pago_minimo = fecha_pago_minimo;
    }

    public String getFecha_pago_contado() {
        return fecha_pago_contado;
    }

    public void setFecha_pago_contado(String fecha_pago_contado) {
        this.fecha_pago_contado = fecha_pago_contado;
    }
    String ncuota;
    String estado;
    String fecha_pago_minimo;
    String fecha_pago_contado;
}
