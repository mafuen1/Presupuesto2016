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
public class CuotaObject {
    String idcuota;
    String idpresupuesto;
    String idrubro;
    String idfinanciado;
    String estado;
    String descripcion;
    String fecha;
    String monto;
    
    public static final String SIN_ASIGNAR = "SIN ASIGNAR";
    public static final  String ASIGNADO = "ASIGNADO";
    public static final  String PAGADO = "PAGADO";

    public String getIdcuota() {
        return idcuota;
    }

    public void setIdcuota(String idcuota) {
        this.idcuota = idcuota;
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

    public String getIdfinanciado() {
        return idfinanciado;
    }

    public void setIdfinanciado(String idfinanciado) {
        this.idfinanciado = idfinanciado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }
}
