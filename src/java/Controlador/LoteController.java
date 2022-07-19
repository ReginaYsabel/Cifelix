/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.lote;
import Utils.ConDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rem
 */
public class LoteController extends HttpServlet {
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
        //response.setContentType("text/html;charset=UTF-8");

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
        //Opcion listar
        String op = request.getParameter("op");
        if (op.equals("listar")) {
            try {

                PreparedStatement sta =  cn.prepareStatement("select * from lote where fecha is not null");

                ResultSet rs = sta.executeQuery();

                ArrayList<lote> lista = new ArrayList<>();

                Date fechaActual = new Date(System.currentTimeMillis());
                int milisecondsByDay = 86400000;
                String estado;

                while (rs.next()) {
                    Date fecha = rs.getDate(4);
                    int dias = (int) ((fecha.getTime() - fechaActual.getTime()) / milisecondsByDay);
                    log("" + dias);
                    if (dias == 5 || dias == 4 || dias == 3 || dias == 2 || dias == 1) {
                        estado = "Proximo a vencer";
                        lote lot = new lote(rs.getInt(1), rs.getInt(2), rs.getInt(3), fecha, estado);
                        lista.add(lot);
                    } else if (dias == 0) {
                        estado = "Vencido";
                        lote lot = new lote(rs.getInt(1), rs.getInt(2), rs.getInt(3), fecha, estado);
                        lista.add(lot);
                    }
                }
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("precederos.jsp").forward(request, response);
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
        //Opcion insertar
        if (op.equals("insertar")) {
            int prod = Integer.parseInt(request.getParameter("select_prod"));
            int cantidad = Integer.parseInt(request.getParameter("txtCant"));
            String fecha = request.getParameter("txtVen");
            Date nuevaFecha;
            if ("".equals(fecha)) {
                nuevaFecha = null;
            } else {
                nuevaFecha = Date.valueOf(request.getParameter("txtVen"));
            }
            try {
                PreparedStatement sta =  cn.prepareStatement("insert into lote (id_Pro, fecha, cantidad) values(?,?,?)");
                sta.setInt(1, prod);
                sta.setDate(2, nuevaFecha);
                sta.setInt(3, cantidad);
                sta.executeUpdate();
                response.sendRedirect("ProductoController?op=listar");

            } catch (IOException | SQLException e) {
                System.out.println("Error al insertar elemento");
            }
        } //Opcion listar
        else if (op.equals("listar")) {
            try {

                PreparedStatement sta =  cn.prepareStatement("select * from lote where fecha is not null");

                ResultSet rs = sta.executeQuery();

                ArrayList<lote> lista = new ArrayList<>();

                Date fechaActual = new Date(System.currentTimeMillis());
                int milisecondsByDay = 86400000;
                
                String estado;

                while (rs.next()) {
                    Date fecha = rs.getDate(4);
                    int dias = (int) ((fecha.getTime() - fechaActual.getTime()) / milisecondsByDay);
                    log("" + dias);
                    if (dias == 5 || dias == 4 || dias == 3 || dias == 2 || dias == 1) {
                        estado = "Proximo a vencer";
                        lote lot = new lote(rs.getInt(1), rs.getInt(2), rs.getInt(3), fecha, estado);
                        lista.add(lot);
                    } else if (dias == 0) {
                        estado = "Vencido";
                        lote lot = new lote(rs.getInt(1), rs.getInt(2), rs.getInt(3), fecha, estado);
                        lista.add(lot);
                    }
                }
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("precederos.jsp").forward(request, response);
            } catch (IOException | SQLException | ServletException e) {
                System.out.println("Error al mostrar elmentos");
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
