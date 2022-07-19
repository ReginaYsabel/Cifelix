
package Controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Utils.ConDB;
import Modelo.Empleado;
import java.sql.ResultSet;
import javax.servlet.http.HttpSession;

/**
 *
 * @author AMVM_
 */
public class logiController extends HttpServlet {

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
        
       String op=request.getParameter("op");
        if(op.equals("login"))
        {
            String logeo=request.getParameter("txtUsu");
            String pass=request.getParameter("txtPass");
            
            try 
            {
                PreparedStatement psta=ConDB.getConnection().prepareStatement("select * from trabajador where correo=? and password=?");
                psta.setString(1, logeo);
                psta.setString(2, pass);
                ResultSet rs=psta.executeQuery();
                
                if(rs.next())
                {
                    HttpSession sesionOK=request.getSession();
                    sesionOK.setAttribute("usuario", rs.getString(2).toString());
                    sesionOK.setAttribute("perfil", rs.getString(4).toString());
                    request.getRequestDispatcher("Index.jsp").forward(request, response);
                }
                else
                {
                    request.setAttribute("msg", "usuario o password incorrectos");
                    request.getRequestDispatcher("Login.jsp").forward(request, response);
                    
                }
            }
            catch (Exception e)
            {
                System.out.println("error" +e);
            }
        }
         else if(op.equals("cerrar"))
        {
            HttpSession sesionOK=request.getSession();
            sesionOK.invalidate();
            request.getRequestDispatcher("Login.jsp").forward(request, response);
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
        processRequest(request, response);
        
        
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
