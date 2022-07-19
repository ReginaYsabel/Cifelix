
package Modelo;

public class cotizacion 
{
  private int id_detC;
  private int id_cot;
  private int id_Pro;	
  private int cantidad;
  private double precio,total,IGV,Pre_total;
  private String nombre;

    public cotizacion(int id_detC, int id_cot, int id_Pro, int cantidad, double precio, double total, double IGV, double Pre_total, String nombre) {
        this.id_detC = id_detC;
        this.id_cot = id_cot;
        this.id_Pro = id_Pro;
        this.cantidad = cantidad;
        this.precio = precio;
        this.total = total;
        this.IGV = IGV;
        this.Pre_total = Pre_total;
        this.nombre = nombre;
    }

    public int getId_detC() {
        return id_detC;
    }

    public void setId_detC(int id_detC) {
        this.id_detC = id_detC;
    }

    public int getId_cot() {
        return id_cot;
    }

    public void setId_cot(int id_cot) {
        this.id_cot = id_cot;
    }

    public int getId_Pro() {
        return id_Pro;
    }

    public void setId_Pro(int id_Pro) {
        this.id_Pro = id_Pro;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getIGV() {
        return IGV;
    }

    public void setIGV(double IGV) {
        this.IGV = IGV;
    }

    public double getPre_total() {
        return Pre_total;
    }

    public void setPre_total(double Pre_total) {
        this.Pre_total = Pre_total;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
  
}
