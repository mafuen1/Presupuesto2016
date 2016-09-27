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
public class PendientesObject {
    String identificador;
    String nombre;
    String plan;
    String tipo_pago;
    String fecha;
    String saldo;
    String fecha_pago_minimo;
    String fecha_pago_contado;

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

    public String getQuincena() {
        return quincena;
    }

    public void setQuincena(String quincena) {
        this.quincena = quincena;
    }
    String quincena;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    String estado;

    public String getSaldo() {
        return saldo;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
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

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getCuotas_pagadas() {
        return cuotas_pagadas;
    }

    public void setCuotas_pagadas(String cuotas_pagadas) {
        this.cuotas_pagadas = cuotas_pagadas;
    }

    public String getMonto_cuota() {
        return monto_cuota;
    }

    public void setMonto_cuota(String monto_cuota) {
        this.monto_cuota = monto_cuota;
    }

    public String getMonto_total() {
        return monto_total;
    }

    public void setMonto_total(String monto_total) {
        this.monto_total = monto_total;
    }

    public String getMonto_pagado() {
        return monto_pagado;
    }

    public void setMonto_pagado(String monto_pagado) {
        this.monto_pagado = monto_pagado;
    }
    String cuotas_pagadas;
    String monto_cuota;
    String monto_total; 
    String monto_pagado;
           
}
