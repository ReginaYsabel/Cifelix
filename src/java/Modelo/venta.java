
package Modelo;


public class venta {
    private  int idDet;
    private  int codigo;
    private  String nombre;
    private  double precio;
    private  int cantidad;
    private double monto;

    public venta(int idDet, int codigo, String nombre, double precio, int cantidad, double monto) {
        this.idDet = idDet;
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.monto = monto;
    }

    public int getIdDet() {
        return idDet;
    }

    public void setIdDet(int idDet) {
        this.idDet = idDet;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
            
}
