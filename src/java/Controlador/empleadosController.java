/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Empleado;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Utils.ConDB;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author AMVM_
 */
@WebServlet(name = "empleadosController", urlPatterns = {"/empleadosController"})
public class empleadosController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
          String op = request.getParameter("op"); 
        
        //Opcion listar
        if (op.equals("listar")) {
            try {

                PreparedStatement sta = ConDB.getConnection().prepareStatement("select * from trabajador");
                ResultSet rs = sta.executeQuery();

                ArrayList<Empleado> lista = new ArrayList<>();

                while (rs.next()) {
                    Empleado emp = new Empleado(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6), rs.getDouble(7));
                    lista.add(emp);
                }
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("Empleados.jsp").forward(request, response);
            } catch (Exception e) {
                System.out.println("Error al mostrar elmentos");
            }
        }
        
        //Opcion eliminar
        else if (op.equals("eliminar")) {
            try {
                int id = Integer.parseInt(request.getParameter("idE"));
                PreparedStatement sta = ConDB.getConnection().prepareStatement("delete from trabajador where id_trabajador=?");
                sta.setInt(1, id);
                sta.executeUpdate();
                request.getRequestDispatcher("empleadosController?op=listar").forward(request, response);
            } catch (IOException | SQLException | ServletException e) {
                 System.out.println("Error al eliminar elementos");
            }
        }
        
    }
     /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    
        String op = request.getParameter("op");

        //Opcion editar
        if (op.equals("editar")) {
            int id = Integer.parseInt(request.getParameter("idC"));
            String nom = request.getParameter("txtENom");
            int DNI = Integer.parseInt(request.getParameter("txtEDNI"));
            String per= request.getParameter("txtEPer");
            String cor=request.getParameter("txtECor");
            String pas=request.getParameter("txtEPas");
            double sue=Double.parseDouble(request.getParameter("txtSue"));
            try {
                PreparedStatement sta = ConDB.getConnection().prepareStatement("update trabajador set Nom_Ape=?, DNI=?, perfil=?, correo=? ,password=?, sueldo=? where id_trabajador=?");
                sta.setString(1, nom);
                sta.setInt(2, DNI);
                sta.setString(3, per);
                sta.setString(4, cor);
                sta.setString(5, pas);
                sta.setDouble(6, sue);
                sta.setInt(7, id);
                sta.executeUpdate();
                request.getRequestDispatcher("empleadosController?op=listar").forward(request, response);

            } catch (IOException | SQLException | ServletException e) {
                System.out.println("Error al actualizar elemento");
            }
        } 
        
        //Opcion listar
        else if (op.equals("listar")) {
            try {

                PreparedStatement sta = ConDB.getConnection().prepareStatement("select * from trabajador");
                ResultSet rs = sta.executeQuery();

                ArrayList<Empleado> lista = new ArrayList<>();

                while (rs.next()) {
                    Empleado emp = new Empleado(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6), rs.getDouble(7));
                    lista.add(emp);
                }
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("Empleados.jsp").forward(request, response);
            } catch (Exception e) {
                System.out.println("Error al mostrar elmentos");
            }
        }
        
         //Opcion insertar
        else if (op.equals("insertar")) {
            String nom = request.getParameter("txtNom");
            String DNI = request.getParameter("txtDNI");
            int per= Integer.parseInt(request.getParameter("txtPer"));
            String cor=request.getParameter("txtCor");
            String pas=request.getParameter("txtPas");
            double sue=Double.parseDouble(request.getParameter("txtSue"));
            try {
                PreparedStatement sta = ConDB.getConnection().prepareStatement("insert into trabajador (Nom_Ape,DNI,perfil,correo,password, sueldo) values(?,?,?,?,?,?)");
                sta.setString(1, nom);
                sta.setString(2, DNI);
                sta.setInt(3, per);
                sta.setString(4, cor);
                sta.setString(5, pas);
                sta.setDouble(6,sue);
                sta.executeUpdate();
                request.getRequestDispatcher("empleadosController?op=listar").forward(request, response);

            } catch (IOException | SQLException | ServletException e) {
                System.out.println("Error al insertar elemento");
            }
        }
    
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
