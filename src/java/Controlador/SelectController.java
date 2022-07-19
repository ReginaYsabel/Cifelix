/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.categoria;
import Modelo.marca;
import Modelo.perecedero;
import Modelo.proveedor;
import Utils.ConDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rem
 */
public class SelectController extends HttpServlet {
    Connection cn = ConDB.getConnection();
    

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
       // processRequest(request, response);
       
       if (false) {
            try {

                PreparedStatement sta = cn.prepareStatement("select * from marca");
                ResultSet rs = sta.executeQuery();

                ArrayList<marca> listaM = new ArrayList<>();

                while (rs.next()) {
                    marca marc = new marca(rs.getInt(1), rs.getString(2));
                    listaM.add(marc);
                }
                request.setAttribute("listaM", listaM);
                              
            } catch (Exception e) {
                log("Error al mostrar elmentos"+e);
            }
        }

         
        if (false) {
            try {               
                PreparedStatement sta = cn.prepareStatement("select * from categoria");
                ResultSet rs = sta.executeQuery();

                ArrayList<categoria> listaC = new ArrayList<>();

                while (rs.next()) {
                    int id = rs.getInt(1);
                    PreparedStatement sta2 = ConDB.getConnection().prepareStatement("select sum(cantidad) from productos where id_Categoria = ?");
                    sta2.setInt(1, id);
                    ResultSet rs2 = sta2.executeQuery();
                    while(rs2.next()){
                        categoria cat = new categoria(id, rs.getString(2), rs2.getInt(1));
                        listaC.add(cat);
                    }                  
                }
                request.setAttribute("listaC", listaC);
                log("aaaaaaaaaaaaa");
                               
            } catch (Exception e) {
                 log("Error al mostrar elmentos"+e);
            }
        }
        
        if (false) {
            try {

                PreparedStatement sta = cn.prepareStatement("select * from proveedor");
                ResultSet rs = sta.executeQuery();

                ArrayList<proveedor> listaP = new ArrayList<>();

                while (rs.next()) {
                    proveedor prov = new proveedor(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6));
                    listaP.add(prov);
                }
                request.setAttribute("listaP", listaP);
                
                response.sendRedirect("ProductoController?op=listar");
            } catch (Exception e) {
                log("Error al mostrar elmentos: "+ e);
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
