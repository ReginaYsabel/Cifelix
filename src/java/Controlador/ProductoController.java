package Controlador;

import Modelo.producto;
import Utils.ConDB;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductoController extends HttpServlet {

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

                PreparedStatement sta = ConDB.getConnection().prepareStatement("select p.id_Pro, p.descripcion, p.precio, p.cantidad, c.Categoria, pr.empresa, m.marca from productos as p inner join categoria as c on c.id_Categoria = p.id_Categoria inner join marca as m on m.id_Marca = p.id_Marca inner join proveedor as pr on pr.id_Proveedor = p.id_Proveedor");
                        
                ResultSet rs = sta.executeQuery();

                ArrayList<producto> lista = new ArrayList<>();

                while (rs.next()) {
                    producto prod = new producto(rs.getInt(1), rs.getString(2), rs.getDouble(3),rs.getInt(4),rs.getString(5),rs.getString(6),rs.getString(7));
                    lista.add(prod);
                }
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("productos.jsp").forward(request, response);
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
