
package Modelo;

import java.sql.Date;

public class cotizacion 
{
 
  private int id_cot;
   private String nombre;
  private int DNI;	
  private int celular;
  private String direccion, fecha;
  private double subtotal, igv, total;

    public cotizacion(int id_cot, String nombre, int DNI, int celular, String direccion, String fecha, double subtotal, double igv, double total) {
        this.id_cot = id_cot;
        this.nombre = nombre;
        this.DNI = DNI;
        this.celular = celular;
        this.direccion = direccion;
        this.fecha = fecha;
        this.subtotal = subtotal;
        this.igv = igv;
        this.total = total;
    }

    public int getId_cot() {
        return id_cot;
    }

    public void setId_cot(int id_cot) {
        this.id_cot = id_cot;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getIgv() {
        return igv;
    }

    public void setIgv(double igv) {
        this.igv = igv;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
  

   

    
  
}
