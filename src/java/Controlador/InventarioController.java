/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.categoria;
import Modelo.inventario;
import Utils.ConDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rem
 */
public class InventarioController extends HttpServlet {
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
        //processRequest(request, response);
        
        //Parametro que elige la operacion a realizar
        String op = request.getParameter("op");
            
        //Opcion listar
        if (op.equals("listar")) {
            try {
                
                PreparedStatement sta = cn.prepareStatement("SELECT p.id_Pro, p.descripcion, sum(dv.cantidad) from productos as p inner join det_ventas as dv on p.id_Pro = dv.id_Pro;");
                ResultSet rs = sta.executeQuery();

                ArrayList<inventario> lista = new ArrayList<>();

                while (rs.next()) {
                    int id = rs.getInt(1);
                    PreparedStatement sta2 = ConDB.getConnection().prepareStatement("select sum(cantidad) from lote where id_Pro = ?");
                    sta2.setInt(1, id);
                    ResultSet rs2 = sta2.executeQuery();
                    while(rs2.next()){
                        int inicial = rs2.getInt(1);
                        int venta = rs.getInt(3);
                        inventario inv = new inventario(rs.getInt(1), rs.getString(2), inicial ,venta ,(inicial - venta));
                        lista.add(inv);
                    }
                    
                                                       
                }
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("inventario.jsp").forward(request, response);
            } catch (IOException | SQLException | ServletException e) {
                 System.out.println("Error al mostrar elmentos");
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
        //processRequest(request, response);
        String op = request.getParameter("op");
        if(op.equals("consultar")){

        String nombre=request.getParameter("buscar");
         try {
                
                PreparedStatement sta = cn.prepareStatement("SELECT p.id_Pro, p.descripcion, sum(dv.cantidad) from productos as p inner join det_ventas as dv on p.id_Pro = dv.id_Pro where p.descripcion like ? ");
                sta.setString(1, "%"+nombre+"%");
                ResultSet rs = sta.executeQuery();

                ArrayList<inventario> lista = new ArrayList<>();

                while (rs.next()) {
                    int id = rs.getInt(1);
                    PreparedStatement sta2 = ConDB.getConnection().prepareStatement("select sum(cantidad) from lote where id_Pro = ?");
                    sta2.setInt(1, id);
                    ResultSet rs2 = sta2.executeQuery();
                    while(rs2.next()){
                        int inicial = rs2.getInt(1);
                        int venta = rs.getInt(3);
                        inventario inv = new inventario(rs.getInt(1), rs.getString(2), inicial ,venta ,(inicial - venta));
                        lista.add(inv);
                    }
                    
                                                       
                }
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("inventario.jsp").forward(request, response);
            } 
            catch (Exception e) {
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
