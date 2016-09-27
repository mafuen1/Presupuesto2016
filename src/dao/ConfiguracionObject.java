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
public class ConfiguracionObject {
    String silvia_quincena1;
    String silvia_quincena2;
    String manuel_quincena1;
    String manuel_quincena2;
    String manuel_total;
    String silvia_total;

    public String getManuel_total() {
        return manuel_total;
    }

    public void setManuel_total(String manuel_total) {
        this.manuel_total = manuel_total;
    }

    public String getSilvia_total() {
        return silvia_total;
    }

    public void setSilvia_total(String silvia_total) {
        this.silvia_total = silvia_total;
    }

    public String getSilvia_quincena1() {
        return silvia_quincena1;
    }

    public void setSilvia_quincena1(String silvia_quincena1) {
        this.silvia_quincena1 = silvia_quincena1;
    }

    public String getSilvia_quincena2() {
        return silvia_quincena2;
    }

    public void setSilvia_quincena2(String silvia_quincena2) {
        this.silvia_quincena2 = silvia_quincena2;
    }

    public String getManuel_quincena1() {
        return manuel_quincena1;
    }

    public void setManuel_quincena1(String manuel_quincena1) {
        this.manuel_quincena1 = manuel_quincena1;
    }

    public String getManuel_quincena2() {
        return manuel_quincena2;
    }

    public void setManuel_quincena2(String manuel_quincena2) {
        this.manuel_quincena2 = manuel_quincena2;
    }
}
