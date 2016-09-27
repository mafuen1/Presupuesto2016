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
public class UsuarioObject {
    String idUsuario;
    String nombre;
    String clave;
    String pago;
    double quincena1;
    double quincena2;
    double mensual;

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
    String perfil;

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }

    public double getQuincena1() {
        return quincena1;
    }

    public void setQuincena1(double quincena1) {
        this.quincena1 = quincena1;
    }

    public double getQuincena2() {
        return quincena2;
    }

    public void setQuincena2(double quincena2) {
        this.quincena2 = quincena2;
    }

    public double getMensual() {
        return mensual;
    }

    public void setMensual(double mensual) {
        this.mensual = mensual;
    }
            
    
}
