
package Modelo;


public class ven_re {
    
    private int cod;
    private String nom;
    private String fecha;
    private int cant;
    private String desc;
    private double tot;

    public ven_re(int cod, String nom, String fecha, int cant, String desc, double tot) {
        this.cod = cod;
        this.nom = nom;
        this.fecha = fecha;
        this.cant = cant;
        this.desc = desc;
        this.tot = tot;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getTot() {
        return tot;
    }

    public void setTot(double tot) {
        this.tot = tot;
    }
    
    
            
}
