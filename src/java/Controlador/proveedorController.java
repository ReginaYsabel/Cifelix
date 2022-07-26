/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.proveedor;
import Utils.ConDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author AMVM_
 */
public class proveedorController extends HttpServlet {

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
        
        //Parametro que elige la operacion a realizar
        String op = request.getParameter("op"); 
        
        //Opcion listar
        if (op.equals("listar")) {
            try {

                PreparedStatement sta = ConDB.getConnection().prepareStatement("select * from proveedor");
                ResultSet rs = sta.executeQuery();

                ArrayList<proveedor> lista = new ArrayList<>();

                while (rs.next()) {
                    proveedor prov = new proveedor(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6));
                    lista.add(prov);
                }
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("proveedores.jsp").forward(request, response);
            } catch (Exception e) {
                System.out.println("Error al mostrar elmentos");
            }
        }
        
        //Opcion eliminar
        else if (op.equals("eliminar")) {
            try {
                int id = Integer.parseInt(request.getParameter("idE"));
                PreparedStatement sta = ConDB.getConnection().prepareStatement("delete from proveedor where id_Proveedor=?");
                sta.setInt(1, id);
                sta.executeUpdate();
                request.getRequestDispatcher("proveedorController?op=listar").forward(request, response);
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
    
        //Parametro que elige la operacion a realizar
        String op = request.getParameter("op");

        //Opcion editar
        if (op.equals("editar")) {
            int id = Integer.parseInt(request.getParameter("idC"));
            String nom = request.getParameter("txtENom");
            String emp = request.getParameter("txtEEmp");
            int cel= Integer.parseInt(request.getParameter("txtECel"));
            String cor=request.getParameter("txtECor");
            String dir=request.getParameter("txtEDir");
            try {
                PreparedStatement sta = ConDB.getConnection().prepareStatement("update proveedor set Nom_Ape=?, empresa=?, celular=?, correo=? ,direccion=? where id_Proveedor=?");
                sta.setString(1, nom);
                sta.setString(2, emp);
                sta.setInt(3, cel);
                sta.setString(4, cor);
                sta.setString(5, dir);
                sta.setInt(6, id);
                sta.executeUpdate();
                request.getRequestDispatcher("proveedorController?op=listar").forward(request, response);

            } catch (IOException | SQLException | ServletException e) {
                System.out.println("Error al actualizar elemento");
            }
        } 
        else if (op.equals("listar")) {
            try {

                PreparedStatement sta = ConDB.getConnection().prepareStatement("select * from proveedor");
                ResultSet rs = sta.executeQuery();

                ArrayList<proveedor> lista = new ArrayList<>();

                while (rs.next()) {
                    proveedor prov = new proveedor(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6));
                    lista.add(prov);
                }
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("proveedores.jsp").forward(request, response);
            } catch (Exception e) {
                System.out.println("Error al mostrar elmentos");
            }
        }
        
         //Opcion insertar
        else if (op.equals("insertar")) {
            String nom = request.getParameter("txtNom");
            String emp = request.getParameter("txtEmp");
            int cel= Integer.parseInt(request.getParameter("txtCel"));
            String cor=request.getParameter("txtCor");
            String dir=request.getParameter("txtDir");
            try {
                PreparedStatement sta = ConDB.getConnection().prepareStatement("insert into proveedor (Nom_Ape,empresa,celular,correo,direccion) values(?,?,?,?,?)");
                sta.setString(1, nom);
                sta.setString(2, emp);
                sta.setInt(3, cel);
                sta.setString(4, cor);
                sta.setString(5, dir);
                sta.executeUpdate();
                request.getRequestDispatcher("proveedorController?op=listar").forward(request, response);

            } catch (IOException | SQLException | ServletException e) {
                System.out.println("Error al insertar elemento");
            }
        }
        
        else if(op.equals("consultar")){
            
            String nombre=request.getParameter("buscar");
            try{
                PreparedStatement sta=ConDB.getConnection().prepareStatement("select * from proveedor where Nom_Ape like ?");
                sta.setString(1, "%"+nombre+"%");
                ResultSet rs = sta.executeQuery();

                ArrayList<proveedor> lista = new ArrayList<>();

                while (rs.next()) {
                    proveedor prov = new proveedor(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6));
                    lista.add(prov);
                }
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("proveedores.jsp").forward(request, response);
            } catch (Exception e) {
                System.out.println("Error al mostrar elmentos: "+e);
            }
        }
        
    
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
