package Controlador;

import java.io.IOException;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Utils.ConDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Modelo.categoria;
import java.sql.Connection;

public class CategoriaController extends HttpServlet {
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
        //Parametro que elige la operacion a realizar
        String op = request.getParameter("op");
         
        //Opcion listar
        if (op.equals("listar")) {
            try {
                
                PreparedStatement sta = cn.prepareStatement("select * from categoria");
                ResultSet rs = sta.executeQuery();

                ArrayList<categoria> lista = new ArrayList<>();

                while (rs.next()) {
                    int id = rs.getInt(1);
                    PreparedStatement sta2 = cn.prepareStatement("select sum(cantidad) from productos where id_Categoria = ?");
                    sta2.setInt(1, id);
                    ResultSet rs2 = sta2.executeQuery();
                    while(rs2.next()){
                        categoria cat = new categoria(id, rs.getString(2), rs2.getInt(1));
                        lista.add(cat);
                    }               
                }
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("categoria.jsp").forward(request, response);
            } catch (IOException | SQLException | ServletException e) {
                 System.out.println("Error al mostrar elmentos");
            }
        }  
        //Opcion eliminar
        else if (op.equals("eliminar")) {
            try {
                int id = Integer.parseInt(request.getParameter("idE"));
                log(""+id);
                PreparedStatement sta = cn.prepareStatement("delete from categoria where id_Categoria=?");
                sta.setInt(1, id);
                sta.executeUpdate();
                request.getRequestDispatcher("CategoriaController?op=listar").forward(request, response);
            } catch (IOException | SQLException | ServletException e) {
                 System.out.println("Error al eliminar elemento");
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
        //Parametro que elige la operacion a realizar
        String op = request.getParameter("op");   
        
        //Opcion editar
        if(op.equals("editar")){
            int id = Integer.parseInt(request.getParameter("idC"));
            String categoria = request.getParameter("txtCatEdit");

            try {
                PreparedStatement sta = cn.prepareStatement("update categoria set categoria=? where id_Categoria=?");
                sta.setString(1, categoria);
                sta.setInt(2, id);
                sta.executeUpdate();
                request.getRequestDispatcher("CategoriaController?op=listar").forward(request, response);

            } catch (IOException | SQLException | ServletException e) {
                System.out.println("Error al actualizar elemento");
            }
        }
        
        //Opcion listar
        else if (op.equals("listar")) {
            try {
                
                PreparedStatement sta = cn.prepareStatement("select * from categoria");
                ResultSet rs = sta.executeQuery();

                ArrayList<categoria> lista = new ArrayList<>();

                while (rs.next()) {
                    int id = rs.getInt(1);
                    PreparedStatement sta2 = cn.prepareStatement("select sum(cantidad) from productos where id_Categoria = ?");
                    sta2.setInt(1, id);
                    ResultSet rs2 = sta2.executeQuery();
                    while(rs2.next()){
                        categoria cat = new categoria(id, rs.getString(2), rs2.getInt(1));
                        lista.add(cat);
                    }
                    
                    
                }
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("categoria.jsp").forward(request, response);
            } catch (IOException | SQLException | ServletException e) {
                 log("Error al mostrar elmentos: "+e);
            }
        } 
        
        //Opcion insertar
        else if (op.equals("insertar")) {
            String categoria = request.getParameter("txtCat");
            try {
                PreparedStatement sta = cn.prepareStatement("insert into categoria (categoria) values(?)");
                sta.setString(1, categoria); 
                sta.executeUpdate();
                request.getRequestDispatcher("CategoriaController?op=listar").forward(request, response);

            } catch (IOException | SQLException | ServletException e) {
                System.out.println("Error al insertar elemento");
            }
        } 
        
        else if(op.equals("consultar")){
            
            String nombre=request.getParameter("busca");
            try{
                PreparedStatement sta=cn.prepareStatement("select * from categoria where Categoria like ?");
                sta.setString(1, "%"+nombre+"%");
                ResultSet rs = sta.executeQuery();

                ArrayList<categoria> lista = new ArrayList<>();
                
                while (rs.next()) {
                    int id = rs.getInt(1);
                    PreparedStatement sta2 = cn.prepareStatement("select sum(cantidad) from productos where id_Categoria = ?");
                    sta2.setInt(1, id);
                    ResultSet rs2 = sta2.executeQuery();
                    while(rs2.next()){
                        categoria cat = new categoria(id, rs.getString(2), rs2.getInt(1));
                        lista.add(cat);
                    }               
                }
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("categoria.jsp").forward(request, response);
            } catch (IOException | SQLException | ServletException e) {
                 System.out.println("Error al mostrar elmentos"+e);
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
