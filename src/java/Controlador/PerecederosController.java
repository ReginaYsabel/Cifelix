package Controlador;
import Modelo.perecedero;
import Utils.ConDB;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class PerecederosController extends HttpServlet {
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
        processRequest(request, response);
        //Parametro que elige la operacion a realizar
        String op = request.getParameter("op");
         
        if (op.equals("listar")) {
            try {                
                PreparedStatement sta =  cn.prepareStatement("select * from pro_vencidos");
                ResultSet rs = sta.executeQuery();               
                
                ArrayList<perecedero> lista = new ArrayList<>();
                while (rs.next()) {
                    
                    perecedero per = new perecedero(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4),rs.getString(5),rs.getString(6));
                    lista.add(per);
                }
                
                PreparedStatement sta2 =  cn.prepareStatement("select descripcion from productos");
                        
                ResultSet rs2 = sta2.executeQuery();

                ArrayList<String> lista2 = new ArrayList<>();

                while (rs2.next()) {                    
                    lista2.add(rs2.getString(1));
                }
                request.setAttribute("lista2", lista2);
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
        processRequest(request, response);
        
        //Parametro que elige la operacion a realizar
        String op = request.getParameter("op");
        
        //Opcion insertar
        if (op.equals("insertar")) {
            String nombre = request.getParameter("select_product");
            int cantidad = Integer.parseInt(request.getParameter("txtCant"));
            String estado = request.getParameter("txtEst");
            String observacion = request.getParameter("txtObsv");
            
            try {              
                PreparedStatement staC =  cn.prepareStatement("select id_Pro ,id_Categoria from productos where Descripcion=?");
                staC.setString(1,nombre);                         
                ResultSet rs = staC.executeQuery();
                while (rs.next()) {
                    int codigoProd = rs.getInt(1);
                    int codigoCategoria = rs.getInt(2);
                    PreparedStatement sta =  cn.prepareStatement("insert into pro_vencidos(id_Pro,Cantidad,id_Categoria,Estado,Observacion) values(?,?,?,?,?)");
                    sta.setInt(1, codigoProd);
                    sta.setInt(2, cantidad);
                    sta.setInt(3, codigoCategoria);
                    sta.setString(4, estado);
                    sta.setString(5, observacion);
                    sta.executeUpdate();                   
                }     
                request.getRequestDispatcher("PerecederosController?op=listar").forward(request, response);
            } catch (IOException | SQLException | ServletException e) {
                System.out.println("Error al insertar elemento");
            }
        }
        
        //Opcion listar
        else if (op.equals("listar")) {
            try {
                
                PreparedStatement sta =  cn.prepareStatement("select * from pro_vencidos");
                ResultSet rs = sta.executeQuery();               
                
                ArrayList<perecedero> lista = new ArrayList<>();
                while (rs.next()) {
                    
                    perecedero per = new perecedero(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4),rs.getString(5),rs.getString(6));
                    lista.add(per);
                }
                
                PreparedStatement sta2 =  cn.prepareStatement("select descripcion from productos");
                        
                ResultSet rs2 = sta2.executeQuery();

                ArrayList<String> lista2 = new ArrayList<>();

                while (rs2.next()) {                    
                    lista2.add(rs2.getString(1));
                }
                request.setAttribute("lista2", lista2);
                    
            
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
