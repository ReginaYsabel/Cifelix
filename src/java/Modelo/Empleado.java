
package Modelo;


public class Empleado 
{
 private int idEmp;
 private String nom_apellido;
 private int DNI;
 private String perfil, correo, pass;
 private double sueldo;

    public Empleado(int idEmp, String nom_apellido, int DNI, String perfil, String correo, String pass, double sueldo) {
        this.idEmp = idEmp;
        this.nom_apellido = nom_apellido;
        this.DNI = DNI;
        this.perfil = perfil;
        this.correo = correo;
        this.pass = pass;
        this.sueldo = sueldo;
    }

    public int getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(int idEmp) {
        this.idEmp = idEmp;
    }

    public String getNom_apellido() {
        return nom_apellido;
    }

    public void setNom_apellido(String nom_apellido) {
        this.nom_apellido = nom_apellido;
    }

    public int getDNI() {
        return DNI;
    }

    public void setDNI(int DNI) {
        this.DNI = DNI;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

}