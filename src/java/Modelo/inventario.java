/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Rem
 */
public class inventario {   
    private int codigo;
    private String producto;
    private int inicial;
    private int vendida;
    private int total;

    public inventario(int codigo, String producto, int inicial, int vendida, int total) {
        this.codigo = codigo;
        this.producto = producto;
        this.inicial = inicial;
        this.vendida = vendida;
        this.total = total;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getInicial() {
        return inicial;
    }

    public void setInicial(int inicial) {
        this.inicial = inicial;
    }

    public int getVendida() {
        return vendida;
    }

    public void setVendida(int vendida) {
        this.vendida = vendida;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    
    
}
