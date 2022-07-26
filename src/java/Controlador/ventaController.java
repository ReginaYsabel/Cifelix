/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.PROD;
import Modelo.ven_re;
import Modelo.det_venta;
import Modelo.venta;
import Utils.ConDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author AMVM_
 */
public class ventaController extends HttpServlet {

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
                request.getRequestDispatcher("venta.jsp").forward(request, response);

            } catch (IOException | SQLException | ServletException e) {
                System.out.println("Error al mostrar elmentos" +e);
            } 
        } 
        
        else if(op.equals("agregar")){
             
            HttpSession sesion=request.getSession();
            ArrayList<det_venta> carr;
            
            if(sesion.getAttribute("carr")==null){
                carr=new ArrayList<det_venta>();
            }else{
                carr=(ArrayList<det_venta>)sesion.getAttribute("carr");
            }
            
            int fila = Integer.parseInt(request.getParameter("fila"));
            String cod=request.getParameter("cod"+fila);
            String nom=request.getParameter("nom"+fila);
            double pre=Double.parseDouble(request.getParameter("pre"+fila));
            int canDet=Integer.parseInt(request.getParameter("txtCan"+fila));
            
            
            int indice=-1;
            int can=0;
            
            for(int i=0;i<carr.size();i++){
                det_venta c2=carr.get(i);
                if(c2.getCod().equals(cod)){
                    indice=i;
                    can=c2.getCan();
                    break;
                }
            }
            if(indice==-1){
                det_venta c=new det_venta(cod, nom, pre, canDet);
                carr.add(c);
            }else{
                int can2=can+canDet;
                det_venta c3=new det_venta(cod,nom,pre,can2);
                carr.set(indice, c3);
            }
            
            sesion.setAttribute("carr", carr);
            log("Tamañoooooo: "+carr.size());
            request.getRequestDispatcher("ventaController?op=listar").forward(request, response);
            
            
            
        }else if(op.equals("menos")){
            int fila = Integer.parseInt(request.getParameter("fila"));
            HttpSession sesion=request.getSession();
            ArrayList<det_venta> carr=(ArrayList<det_venta>)sesion.getAttribute("carr");
            
            String cod=request.getParameter("cod"+fila);
            String nom=request.getParameter("nom"+fila);
            double pre=Double.parseDouble(request.getParameter("pre"+fila));

            
            for(int i=0;i<carr.size();i++){
                det_venta c2=carr.get(i);
                if(c2.getCod().equals(cod)){
                    if(c2.getCan()>0){
                        int can2=c2.getCan()-1;
                        det_venta c3=new det_venta(cod,nom,pre,can2);
                        carr.set(i, c3);
                    }
                    break;
                }
            }

            
            sesion.setAttribute("carr", carr);
             request.getRequestDispatcher("ventaController?op=listar").forward(request, response);           
    }
        else if(op.equals("mas")){
            int fila = Integer.parseInt(request.getParameter("fila"));
            HttpSession sesion=request.getSession();
            ArrayList<det_venta> carr=(ArrayList<det_venta>)sesion.getAttribute("carrito");
            
            String cod=request.getParameter("cod"+fila);
            String nom=request.getParameter("nom"+fila);
            double pre=Double.parseDouble(request.getParameter("pre"+fila));

            
            for(int i=0;i<carr.size();i++){
                det_venta c2=carr.get(i);
                if(c2.getCod().equals(cod)){
                    if(c2.getCan()>0){
                        int can2=c2.getCan()+1;
                        det_venta c3=new det_venta(cod,nom,pre,can2);
                        carr.set(i, c3);
                    }
                    break;
                }
            }
           
            sesion.setAttribute("carr", carr);
             request.getRequestDispatcher("ventaController?op=listar").forward(request, response);             
    }
        else if (op.equals("lisv")) {
            try {

                PreparedStatement sta = ConDB.getConnection().prepareStatement("SELECT v.id_venta, v.Nom_Ape, v.fecha, dv.cantidad,p.descripcion, dv.total FROM venta v inner join det_ventas dv on v.id_venta = dv.id_venta INNER JOIN productos p ON dv.id_Pro= p.id_Pro");
                ResultSet rs = sta.executeQuery();

                ArrayList<ven_re> lisv = new ArrayList<>();

                while (rs.next()) {
                    ven_re vRE = new ven_re(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getDouble(6));
                    lisv .add(vRE);
                }
                request.setAttribute("lisv ", lisv );
                request.getRequestDispatcher("VENT_echas.jsp").forward(request, response);
            } catch (Exception e) {
                System.out.println("Error al mostrar elmentos: "+e);
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
        
        String op = request.getParameter("op");
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
                request.getRequestDispatcher("venta.jsp").forward(request, response);

            } catch (IOException | SQLException | ServletException e) {
                System.out.println("Error al mostrar elmentos" +e);
            }
        }
        else if(op.equals("agregar")){
             
            HttpSession sesion=request.getSession();
            ArrayList<det_venta> carr;
            
            if(sesion.getAttribute("carr")==null){
                carr=new ArrayList<det_venta>();
            }else{
                carr=(ArrayList<det_venta>)sesion.getAttribute("carr");
            }
            
            int fila = Integer.parseInt(request.getParameter("fila"));
            String cod=request.getParameter("cod"+fila);
            String nom=request.getParameter("nom"+fila);
            double pre=Double.parseDouble(request.getParameter("pre"+fila));
            int canDet=Integer.parseInt(request.getParameter("txtCan"+fila));
            
            
            int indice=-1;
            int can=0;
            
            for(int i=0;i<carr.size();i++){
                det_venta c2=carr.get(i);
                if(c2.getCod().equals(cod)){
                    indice=i;
                    can=c2.getCan();
                    break;
                }
            }
            if(indice==-1){
                det_venta c=new det_venta(cod, nom, pre, canDet);
                carr.add(c);
            }else{
                int can2=can+canDet;
                det_venta c3=new det_venta(cod,nom,pre,can2);
                carr.set(indice, c3);
            }
            
            sesion.setAttribute("carr", carr);
            request.getRequestDispatcher("ventaController?op=listar").forward(request, response);
            
            
            
        }else if(op.equals("menos")){
            int fila = Integer.parseInt(request.getParameter("fila"));
            HttpSession sesion=request.getSession();
            ArrayList<det_venta> carr=(ArrayList<det_venta>)sesion.getAttribute("carr");
            
            String cod=request.getParameter("cod"+fila);
            String nom=request.getParameter("nom"+fila);
            double pre=Double.parseDouble(request.getParameter("pre"+fila));

            
            for(int i=0;i<carr.size();i++){
                det_venta c2=carr.get(i);
                if(c2.getCod().equals(cod)){
                    if(c2.getCan()>0){
                        int can2=c2.getCan()-1;
                        det_venta c3=new det_venta(cod,nom,pre,can2);
                        carr.set(i, c3);
                    }
                    break;
                }
            }

            
            sesion.setAttribute("carr", carr);
            request.getRequestDispatcher("ventaController?op=listar").forward(request, response);          
    }
        else if(op.equals("mas")){
            int fila = Integer.parseInt(request.getParameter("fila"));
            HttpSession sesion=request.getSession();
            ArrayList<det_venta> carr=(ArrayList<det_venta>)sesion.getAttribute("carr");
            
            String cod=request.getParameter("cod"+fila);
            String nom=request.getParameter("nom"+fila);
            double pre=Double.parseDouble(request.getParameter("pre"+fila));

            
            for(int i=0;i<carr.size();i++){
                det_venta c2=carr.get(i);
                if(c2.getCod().equals(cod)){
                    if(c2.getCan()>0){
                        int can2=c2.getCan()+1;
                        det_venta c3=new det_venta(cod,nom,pre,can2);
                        carr.set(i, c3);
                    }
                    break;
                }
            }
           
            sesion.setAttribute("carr", carr);
            request.getRequestDispatcher("ventaController?op=listar").forward(request, response);             
    }
        else if(op.equals("consultar")){
            
            String nombre=request.getParameter("buscar");
            try{
                PreparedStatement sta=ConDB.getConnection().prepareStatement("select id_Pro, descripcion, precio, cantidad from productos where descripcion like ?");
                sta.setString(1, "%"+nombre+"%");
                ResultSet rs = sta.executeQuery();

                ArrayList<PROD> lista = new ArrayList<>();

                while (rs.next()) {
                    PROD pro = new PROD(rs.getInt(1) , rs.getString(2), rs.getDouble(3),rs.getInt(4));
                    lista.add(pro);
                }
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("").forward(request, response);
            } catch (Exception e) {
                System.out.println("Error al mostrar elmentos: "+e);
            }
        }
        
        else if(op.equals("registrar")){
            
            int codigo=Integer.parseInt(request.getParameter("txCod"));
            String nombre = request.getParameter("txNom");
            double precio =Integer.parseInt(request.getParameter("txPre"));
            int cantidad = Integer.parseInt(request.getParameter("txCan"));
            double monto=Double.parseDouble(request.getParameter("txMon"));
         
            try {
                PreparedStatement sta = ConDB.getConnection().prepareStatement("insert into det_ventas ( id_Pro,nombre, precio,cantida, total) values(?, ?, ?, ?, ?)");
                
                sta.setInt(1, codigo);
                sta.setString(2, nombre);
                sta.setDouble(3, precio);
                sta.setInt(4, cantidad);
                sta.setDouble(5, monto);
                sta.executeUpdate();
                request.getRequestDispatcher("ventaController?op=listar").forward(request, response);

            } catch (IOException | SQLException | ServletException e) {
                System.out.println("Error al insertar elemento");
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
