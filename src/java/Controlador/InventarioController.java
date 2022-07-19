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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InventarioController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InventarioController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
                
                PreparedStatement sta = ConDB.getConnection().prepareStatement("SELECT p.id_Pro, p.descripcion, p.cantidad, sum(dv.cantidad), (p.cantidad - sum(dv.cantidad)) from productos as p inner join det_ventas as dv on p.id_Pro = dv.id_Pro;");
                ResultSet rs = sta.executeQuery();

                ArrayList<inventario> lista = new ArrayList<>();

                while (rs.next()) {
                    inventario inv = new inventario(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4),rs.getInt(5));
                    lista.add(inv);
                                                       
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
