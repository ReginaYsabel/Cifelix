
package Modelo;

import java.sql.Date;

public class cotizacion 
{
 
  private int id_cot;
  private String nombre;
  private int DNI;	
  private int celular;
  private String direccion;
  private int idtrab;
  private Date fecha;

    public cotizacion(int id_cot, String nombre, int DNI, int celular, String direccion, int idtrab, Date fecha) {
        this.id_cot = id_cot;
        this.nombre = nombre;
        this.DNI = DNI;
        this.celular = celular;
        this.direccion = direccion;
        this.idtrab = idtrab;
        this.fecha = fecha;
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

    public int getIdtrab() {
        return idtrab;
    }

    public void setIdtrab(int idtrab) {
        this.idtrab = idtrab;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

   

}