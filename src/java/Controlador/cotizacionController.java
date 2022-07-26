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
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
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

        String op = request.getParameter("op");
        
        HttpSession sesion = request.getSession();
        //Opcion listar
        if (op.equals("listar")) {
            try {

                PreparedStatement sta = cn.prepareStatement("select id_Pro, descripcion, precio, cantidad from productos");

                ResultSet rs = sta.executeQuery();

                ArrayList<PROD> lista = new ArrayList<>();

                while (rs.next()) {
                    PROD pro = new PROD(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4));
                    lista.add(pro);
                }
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("Cotizacion.jsp").forward(request, response);

            } catch (IOException | SQLException | ServletException e) {
                System.out.println("Error al mostrar elmentos" + e);
            }
        } else if (op.equals("agregar")) {

            
            ArrayList<Cotizar> carrito;

            if (sesion.getAttribute("carrito") == null) {
                carrito = new ArrayList<Cotizar>();
            } else {
                carrito = (ArrayList<Cotizar>) sesion.getAttribute("carrito");
            }

            int fila = Integer.parseInt(request.getParameter("fila"));
            int cod = Integer.parseInt(request.getParameter("cod" + fila));
            String nom = request.getParameter("nom" + fila);
            double pre = Double.parseDouble(request.getParameter("pre" + fila));
            int canDet = Integer.parseInt(request.getParameter("txtCan" + fila));

            int indice = -1;
            int can = 0;

            for (int i = 0; i < carrito.size(); i++) {
                Cotizar c2 = carrito.get(i);
                if (c2.getCod()==cod) {
                    indice = i;
                    can = c2.getCan();
                    break;
                }
            }
            if (indice == -1) {
                Cotizar c = new Cotizar(cod, nom, pre, canDet);
                carrito.add(c);
            } else {
                int can2 = can + canDet;
                Cotizar c3 = new Cotizar(cod, nom, pre, can2);
                carrito.set(indice, c3);
            }

            sesion.setAttribute("carrito", carrito);
            log("Tamañoooooo: " + carrito.size());
            request.getRequestDispatcher("cotizacionController?op=listar").forward(request, response);

        } else if (op.equals("menos")) {
            int fila = Integer.parseInt(request.getParameter("fila"));
            
            ArrayList<Cotizar> carrito = (ArrayList<Cotizar>) sesion.getAttribute("carrito");

            int cod = Integer.parseInt(request.getParameter("cod" + fila));
            String nom = request.getParameter("nom" + fila);
            double pre = Double.parseDouble(request.getParameter("pre" + fila));

            for (int i = 0; i < carrito.size(); i++) {
                Cotizar c2 = carrito.get(i);
                if (c2.getCod() == cod) {
                    if (c2.getCan() > 0) {
                        int can2 = c2.getCan() - 1;
                        Cotizar c3 = new Cotizar(cod, nom, pre, can2);
                        carrito.set(i, c3);
                    }
                    break;
                }
            }

            sesion.setAttribute("carrito", carrito);
            request.getRequestDispatcher("cotizacionController?op=listar").forward(request, response);
        } else if (op.equals("mas")) {
            int fila = Integer.parseInt(request.getParameter("fila"));
            
            ArrayList<Cotizar> carrito = (ArrayList<Cotizar>) sesion.getAttribute("carrito");

            int cod = Integer.parseInt(request.getParameter("cod" + fila));
            String nom = request.getParameter("nom" + fila);
            double pre = Double.parseDouble(request.getParameter("pre" + fila));

            for (int i = 0; i < carrito.size(); i++) {
                Cotizar c2 = carrito.get(i);
                if (c2.getCod() == cod) {
                    if (c2.getCan() > 0) {
                        int can2 = c2.getCan() + 1;
                        Cotizar c3 = new Cotizar(cod, nom, pre, can2);
                        carrito.set(i, c3);
                    }
                    break;
                }
            }

            sesion.setAttribute("carrito", carrito);
            request.getRequestDispatcher("cotizacionController?op=listar").forward(request, response);
        } else if (op.equals("calcular")) {

            HttpSession session = request.getSession();

            double subtotal = 0, igv = 0, total = 0, i = 0, t = 0;
            ArrayList<Cotizar> list = (ArrayList<Cotizar>) session.getAttribute("carrito");
            if (list != null) {
                for (int j = 0; j < list.size(); j++) {
                    Cotizar d = list.get(j);

                    subtotal = subtotal + (d.getPre() * d.getCan());
                }
                igv = subtotal * 0.18;
                i = Math.round(igv * 100.0) / 100.0;
                total = subtotal + igv;
                t = Math.round(total * 100.0) / 100.0;

                request.setAttribute("subtotal", subtotal);
                request.setAttribute("igv", igv);
                request.setAttribute("i", i);
                request.setAttribute("total", total);
                request.setAttribute("t", t);

                request.getRequestDispatcher("cotizacionController?op=listar").forward(request, response);
            }
        } 
        
        else if(op.equals("insert")){
            
            ArrayList<Cotizar> carrito = (ArrayList<Cotizar>) sesion.getAttribute("carrito");
            int id=0;
             try {
                PreparedStatement sta = cn.prepareStatement("select max(id_cot) from cotizacion");
                ResultSet rs = sta.executeQuery();
                while(rs.next()){
                         id = rs.getInt(1);                 
                     }
                 for (int i = 0; i < carrito.size(); i++) {
                     Cotizar c2 = carrito.get(i);
                     PreparedStatement sta2 = ConDB.getConnection().prepareStatement("insert into det_cotizacion values(id_cot, cantidad, total, id_Pro) values(?,?,?,?)");
                     double total = c2.getCan()*c2.getPre();
                     int cantidad = c2.getCan();
                     int codigo = c2.getCod();
                         sta2.setInt(1,id);
                         sta2.setInt(2, cantidad);
                         sta2.setDouble(3, total);
                         sta2.setInt(4, codigo);
                     
                     sta2.executeUpdate();
                     
                 }
               
            request.getRequestDispatcher("cotizacionController?op=listar").forward(request, response);
            } catch (SQLException e) {
                System.out.println("Error al insertar elemento" +e);
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
        HttpSession sesion = request.getSession();

        if (op.equals("listar")) {
            try {

                PreparedStatement sta = ConDB.getConnection().prepareStatement("select id_Pro, descripcion, precio, cantidad from productos");

                ResultSet rs = sta.executeQuery();

                ArrayList<PROD> lista = new ArrayList<>();

                while (rs.next()) {
                    PROD pro = new PROD(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4));
                    lista.add(pro);
                }
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("Cotizacion.jsp").forward(request, response);

            } catch (IOException | SQLException | ServletException e) {
                System.out.println("Error al mostrar elmentos" + e);
            }
        } else if (op.equals("agregar")) {

            
            ArrayList<Cotizar> carrito;

            if (sesion.getAttribute("carrito") == null) {
                carrito = new ArrayList<Cotizar>();
            } else {
                carrito = (ArrayList<Cotizar>) sesion.getAttribute("carrito");
            }

            int fila = Integer.parseInt(request.getParameter("fila"));
            int cod = Integer.parseInt(request.getParameter("cod" + fila));
            String nom = request.getParameter("nom" + fila);
            double pre = Double.parseDouble(request.getParameter("pre" + fila));
            int canDet = Integer.parseInt(request.getParameter("txtCan" + fila));

            int indice = -1;
            int can = 0;

            for (int i = 0; i < carrito.size(); i++) {
                Cotizar c2 = carrito.get(i);
                if (c2.getCod()==cod) {
                    indice = i;
                    can = c2.getCan();
                    break;
                }
            }
            if (indice == -1) {
                Cotizar c = new Cotizar(cod, nom, pre, canDet);
                carrito.add(c);
            } else {
                int can2 = can + canDet;
                Cotizar c3 = new Cotizar(cod, nom, pre, can2);
                carrito.set(indice, c3);
            }
            sesion.setAttribute("carrito", carrito);
            request.getRequestDispatcher("cotizacionController?op=listar").forward(request, response);
        } else if (op.equals("menos")) {
            int fila = Integer.parseInt(request.getParameter("fila"));
            
            ArrayList<Cotizar> carrito = (ArrayList<Cotizar>) sesion.getAttribute("carrito");

            int cod = Integer.parseInt(request.getParameter("cod" + fila));
            String nom = request.getParameter("nom" + fila);
            double pre = Double.parseDouble(request.getParameter("pre" + fila));

            for (int i = 0; i < carrito.size(); i++) {
                Cotizar c2 = carrito.get(i);
                if (c2.getCod()==cod) {
                    if (c2.getCan() > 0) {
                        int can2 = c2.getCan() - 1;
                        Cotizar c3 = new Cotizar(cod, nom, pre, can2);
                        carrito.set(i, c3);
                    }
                    break;
                }
            }
            sesion.setAttribute("carrito", carrito);
            request.getRequestDispatcher("cotizacionController?op=listar").forward(request, response);
        } else if (op.equals("mas")) {
            int fila = Integer.parseInt(request.getParameter("fila"));
            
            ArrayList<Cotizar> carrito = (ArrayList<Cotizar>) sesion.getAttribute("carrito");

            int cod = Integer.parseInt(request.getParameter("cod" + fila));
            String nom = request.getParameter("nom" + fila);
            double pre = Double.parseDouble(request.getParameter("pre" + fila));

            for (int i = 0; i < carrito.size(); i++) {
                Cotizar c2 = carrito.get(i);
                if (c2.getCod()==cod) {
                    if (c2.getCan() > 0) {
                        int can2 = c2.getCan() + 1;
                        Cotizar c3 = new Cotizar(cod, nom, pre, can2);
                        carrito.set(i, c3);
                    }
                    break;
                }
            }

            sesion.setAttribute("carrito", carrito);
            request.getRequestDispatcher("cotizacionController?op=listar").forward(request, response);
        } else if (op.equals("consultar")) {

            String nombre = request.getParameter("buscar");
            try {
                PreparedStatement sta = cn.prepareStatement("select id_Pro, descripcion, precio, cantidad from productos where descripcion like ?");
                sta.setString(1, "%" + nombre + "%");
                ResultSet rs = sta.executeQuery();

                ArrayList<PROD> lista = new ArrayList<>();

                while (rs.next()) {
                    PROD pro = new PROD(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getInt(4));
                    lista.add(pro);
                }
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("cotizacionController?op=listar").forward(request, response);
            } catch (Exception e) {
                System.out.println("Error al mostrar elmentos: " + e);
            }
        } //Opcion insertar
        else if (op.equals("insertar")) {

            String nom = request.getParameter("txtNom");
            int DNI = Integer.parseInt(request.getParameter("txtDNI"));
            int cel = Integer.parseInt(request.getParameter("txtCel"));
            String dir = request.getParameter("txtDir");
            int idtrab = Integer.parseInt(request.getParameter("txtItra"));
            String fec = request.getParameter("txtFe");

            try {
                PreparedStatement sta = cn.prepareStatement("insert into cotizacion ( Nom_Ape, DNI,celular, direccion, id_trabajador, fecha) values(?, ?, ?, ?, ?, ?)");

                sta.setString(1, nom);
                sta.setInt(2, DNI);
                sta.setInt(3, cel);
                sta.setString(4, dir);
                sta.setInt(5, idtrab);
                sta.setString(6, fec);
                sta.executeUpdate();
                request.getRequestDispatcher("cotizacionController?op=listar").forward(request, response);

            } catch (IOException | SQLException | ServletException e) {
                System.out.println("Error al insertar elemento" +e);
            }
        }
        else if (op.equals("guardar")) {
           
            
            String nom = request.getParameter("txtNombre");
            int DNI = Integer.parseInt(request.getParameter("txtDni"));
            int cel = Integer.parseInt(request.getParameter("txtTelef"));
            String dir = request.getParameter("txtDir");
            int idtrab = Integer.parseInt(request.getParameter("txtTrab"));
            //String fec = request.getParameter("txtFe");
            Date nuevaFecha = Date.valueOf(request.getParameter("txtFecha"));
            
            double subtotal = Double.parseDouble(request.getParameter("txtSub"));
            double igv = Double.parseDouble(request.getParameter("txtIgv"));
            double total = Double.parseDouble(request.getParameter("txtTotal"));

            try {
                PreparedStatement sta = cn.prepareStatement("insert into cotizacion ( Nom_Ape, DNI,celular, direccion, id_trabajador, fecha) values(?, ?, ?, ?, ?, ?)");

                sta.setString(1, nom);
                sta.setInt(2, DNI);
                sta.setInt(3, cel);
                sta.setString(4, dir);
                sta.setInt(5, idtrab);
                sta.setDate(6, nuevaFecha);
                sta.setDouble(7, subtotal);
                sta.setDouble(8, igv);
                sta.setDouble(9, total);
                sta.executeUpdate();
                
               
                request.getRequestDispatcher("cotizacionController?op=listar").forward(request, response);

            } catch (IOException | SQLException | ServletException e) {
                System.out.println("Error al insertar elementos" +e);
            }
        }
        else if(op.equals("insert")){
            
            ArrayList<Cotizar> carrito = (ArrayList<Cotizar>) sesion.getAttribute("carrito");
            int id=0;
             try {
                PreparedStatement sta = cn.prepareStatement("select max(id_cot) from cotizacion");
                ResultSet rs = sta.executeQuery();
                while(rs.next()){
                         id = rs.getInt(1);                 
                     }
                 for (int i = 0; i < carrito.size(); i++) {
                     Cotizar c2 = carrito.get(i);
                     PreparedStatement sta2 = ConDB.getConnection().prepareStatement("insert into det_cotizacion values(id_cot, cantidad, total, id_Pro) values(?,?,?,?)");
                     String total = String.valueOf(c2.getCan()*c2.getPre());
                     DecimalFormat decimalFormat=new DecimalFormat("#.#");
                     Double nuevoTotal= Double.valueOf(decimalFormat.format(total));
                     int cantidad = c2.getCan();
                     int codigo = c2.getCod();
                         sta2.setInt(1,id);
                         sta2.setInt(2, cantidad);
                         sta2.setDouble(3, nuevoTotal);
                         sta2.setInt(4, codigo);
                     
                     sta2.executeUpdate();
                     
                 }
               
            request.getRequestDispatcher("cotizacionController?op=listar").forward(request, response);
            } catch (SQLException e) {
                System.out.println("Error al insertar elemento" +e);
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
