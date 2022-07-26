/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;


public class V_Mes {
  
   private String mes;
   private double sum_sub;
   private double sum_igv;
   private double sum_tot;

    public V_Mes(String mes, double sum_sub, double sum_igv, double sum_tot) {
        this.mes = mes;
        this.sum_sub = sum_sub;
        this.sum_igv = sum_igv;
        this.sum_tot = sum_tot;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public double getSum_sub() {
        return sum_sub;
    }

    public void setSum_sub(double sum_sub) {
        this.sum_sub = sum_sub;
    }

    public double getSum_igv() {
        return sum_igv;
    }

    public void setSum_igv(double sum_igv) {
        this.sum_igv = sum_igv;
    }

    public double getSum_tot() {
        return sum_tot;
    }

    public void setSum_tot(double sum_tot) {
        this.sum_tot = sum_tot;
    }
   
}
