/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.PROD;
import Modelo.Cotizar;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author AMVM_
 */
public class cotizacionController extends HttpServlet {

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

                PreparedStatement sta = ConDB.getConnection().prepareStatement("select id_Pro, descripcion, precio, cantidad from productos");
                        
                ResultSet rs = sta.executeQuery();

                ArrayList<PROD> lista = new ArrayList<>();

                while (rs.next()) 
                {
                    PROD pro=new PROD(rs.getInt(1) , rs.getString(2), rs.getDouble(3),rs.getInt(4));
                    lista.add(pro);
                }
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("Cotizacion.jsp").forward(request, response);

            } catch (IOException | SQLException | ServletException e) {
                System.out.println("Error al mostrar elmentos");
            }
        } 
        
        else if(op.equals("agregar")){
             
            HttpSession sesion=request.getSession();
            ArrayList<Cotizar> carrito;
            
            if(sesion.getAttribute("carrito")==null){
                carrito=new ArrayList<Cotizar>();
            }else{
                carrito=(ArrayList<Cotizar>)sesion.getAttribute("carrito");
            }
            
            String cod=request.getParameter("cod");
            String nom=request.getParameter("nom");
            double pre=Double.parseDouble(request.getParameter("pre"));
            int canDet=Integer.parseInt(request.getParameter("txtCan"));
            
            int indice=-1;
            int can=0;
            
            for(int i=0;i<carrito.size();i++){
                Cotizar c2=carrito.get(i);
                if(c2.getCod().equals(cod)){
                    indice=i;
                    can=c2.getCan();
                    break;
                }
            }
            if(indice==-1){
                Cotizar c=new Cotizar(cod, nom, pre, canDet);
                carrito.add(c);
            }else{
                int can2=can+canDet;
                Cotizar c3=new Cotizar(cod,nom,pre,can2);
                carrito.set(indice, c3);
            }
            
            sesion.setAttribute("carrito", carrito);
            response.sendRedirect("cotizacionController?op=listar");
            
        }else if(op.equals("menos")){
            
            HttpSession sesion=request.getSession();
            ArrayList<Cotizar> carrito=(ArrayList<Cotizar>)sesion.getAttribute("carrito");
            
            String cod=request.getParameter("cod");
            String nom=request.getParameter("nom");
            double pre=Double.parseDouble(request.getParameter("pre"));

            
            for(int i=0;i<carrito.size();i++){
                Cotizar c2=carrito.get(i);
                if(c2.getCod().equals(cod)){
                    if(c2.getCan()>0){
                        int can2=c2.getCan()-1;
                        Cotizar c3=new Cotizar(cod,nom,pre,can2);
                        carrito.set(i, c3);
                    }
                    break;
                }
            }

            
            sesion.setAttribute("carrito", carrito);
            response.sendRedirect("cotizacionController?op=listar");           
    }
        else if(op.equals("mas")){
            
            HttpSession sesion=request.getSession();
            ArrayList<Cotizar> carrito=(ArrayList<Cotizar>)sesion.getAttribute("carrito");
            
            String cod=request.getParameter("cod");
            String nom=request.getParameter("nom");
            double pre=Double.parseDouble(request.getParameter("pre"));

            
            for(int i=0;i<carrito.size();i++){
                Cotizar c2=carrito.get(i);
                if(c2.getCod().equals(cod)){
                    if(c2.getCan()>0){
                        int can2=c2.getCan()+1;
                        Cotizar c3=new Cotizar(cod,nom,pre,can2);
                        carrito.set(i, c3);
                    }
                    break;
                }
            }
           
            sesion.setAttribute("carrito", carrito);
            response.sendRedirect("cotizacionController?op=listar");             
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
