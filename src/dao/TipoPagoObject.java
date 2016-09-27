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
public class TipoPagoObject {
    String tipo_pago;
    String idtipopago;
    String fecha_corte;

    public String getTipo_pago() {
        return tipo_pago;
    }

    public void setTipo_pago(String tipo_pago) {
        this.tipo_pago = tipo_pago;
    }

    public String getIdtipopago() {
        return idtipopago;
    }

    public void setIdtipopago(String idtipopago) {
        this.idtipopago = idtipopago;
    }

    public String getFecha_corte() {
        return fecha_corte;
    }

    public void setFecha_corte(String fecha_corte) {
        this.fecha_corte = fecha_corte;
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

    public String getTipo() {
        return tipo.trim();
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    String fecha_pago_minimo;
    String fecha_pago_contado;
    String tipo;
            
}
