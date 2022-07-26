package Controlador;
import Modelo.categoria;
import Modelo.marca;
import Modelo.producto;
import Modelo.proveedor;
import Utils.ConDB;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
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

public class ProductoController extends HttpServlet {
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
        String op = request.getParameter("op");
        
        //Opcion listar
        if (op.equals("listar")) {
            try {

                PreparedStatement sta = cn.prepareStatement("select p.id_Pro, p.descripcion, p.precio,c.Categoria, pr.empresa, m.marca from productos as p inner join categoria as c on c.id_Categoria = p.id_Categoria inner join marca as m on m.id_Marca = p.id_Marca inner join proveedor as pr on pr.id_Proveedor = p.id_Proveedor");
                        
                ResultSet rs = sta.executeQuery();

                ArrayList<producto> lista = new ArrayList<>();
                
                while (rs.next()) {
                    int id = rs.getInt(1);
                    PreparedStatement sta2 = cn.prepareStatement("select sum(cantidad) from lote where id_Pro = ?");
                    sta2.setInt(1, id);
                    ResultSet rs2 = sta2.executeQuery();
                    while(rs2.next()){
                        
                       
                        producto prod = new producto(id,rs.getString(2), rs.getDouble(3),rs2.getInt(1),rs.getString(4),rs.getString(5),rs.getString(6));
                        lista.add(prod);
                        int cantidad = rs2.getInt(1);
                        PreparedStatement sta3 = cn.prepareStatement("update productos set cantidad = ? where id_Pro=? ");
                        sta3.setInt(1,cantidad );
                        sta3.setInt(2, id);
                        sta3.executeUpdate();
                    }                                      
                }

                request.setAttribute("lista", lista);
                
                
            } catch (Exception e) {
                log("Error al mostrar elmentos: "+e);
            }
            
            if (true) {
            try {

                PreparedStatement sta = cn.prepareStatement("select * from marca");
                ResultSet rs = sta.executeQuery();

                ArrayList<marca> listaM = new ArrayList<>();

                while (rs.next()) {
                    marca marc = new marca(rs.getInt(1), rs.getString(2));
                    listaM.add(marc);
                }
                request.setAttribute("listaM", listaM);
                              
            } catch (Exception e) {
                log("Error al mostrar elmentos"+e);
            }
        }

         
        if (true) {
            try {               
                PreparedStatement sta = cn.prepareStatement("select * from categoria");
                ResultSet rs = sta.executeQuery();

                ArrayList<categoria> listaC = new ArrayList<>();

                while (rs.next()) {
                    int id = rs.getInt(1);
                    PreparedStatement sta2 = cn.prepareStatement("select sum(cantidad) from productos where id_Categoria = ?");
                    sta2.setInt(1, id);
                    ResultSet rs2 = sta2.executeQuery();
                    while(rs2.next()){
                        categoria cat = new categoria(id, rs.getString(2), rs2.getInt(1));
                        listaC.add(cat);
                    }                  
                }
                request.setAttribute("listaC", listaC);
                
                               
            } catch (Exception e) {
                 log("Error al mostrar elmentos"+e);
            }
        }
        
        if (true) {
            try {

                PreparedStatement sta = cn.prepareStatement("select * from proveedor");
                ResultSet rs = sta.executeQuery();

                ArrayList<proveedor> listaP = new ArrayList<>();

                while (rs.next()) {
                    proveedor prov = new proveedor(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6));
                    listaP.add(prov);
                }
                request.setAttribute("listaP", listaP);
                
                request.getRequestDispatcher("productos.jsp").forward(request, response);
            } catch (Exception e) {
                log("Error al mostrar elmentos: "+ e);
            }
        }
        }
        //Opcion eliminar
        else if (op.equals("eliminar")) {
            try {
                int id = Integer.parseInt(request.getParameter("idE"));
                PreparedStatement sta = cn.prepareStatement("delete from productos where id_Pro=?");
                sta.setInt(1, id);
                sta.executeUpdate();
                request.getRequestDispatcher("ProductoController?op=listar").forward(request, response);
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
        // processRequest(request, response);
        String op = request.getParameter("op");

        //Opcion insertar
        if (op.equals("insertar")) {
            String nombre = request.getParameter("txtProd");
            int categoria = Integer.parseInt(request.getParameter("select_cat"));
            double precio = Double.parseDouble(request.getParameter("txtPrecio"));
            int marca = Integer.parseInt(request.getParameter("select_marca"));
            int proveedor = Integer.parseInt(request.getParameter("select_prov"));

            try {

                PreparedStatement sta = cn.prepareStatement("insert into productos (descripcion, precio, id_Marca, id_Categoria, id_Proveedor) values(?,?,?,?,?)");
                sta.setString(1, nombre);
                sta.setDouble(2, precio);
                sta.setInt(3, marca);
                sta.setInt(4, categoria);
                sta.setInt(5, proveedor);

                sta.executeUpdate();

                response.sendRedirect("ProductoController?op=listar");

            } catch (IOException e) {
                log("Error al insertar elemento: " + e);
            } catch (SQLException s) {
                if (s instanceof MySQLIntegrityConstraintViolationException) {
                    if (s.getSQLState().equals("23000")) {
                        if (s.getMessage().contains("Duplicate")) {
                            System.out.println("Elmento duplicadooooo");
                            request.setAttribute("msg", "El nombre ya ha sido tomado. Por favor, intente con otro");
                            request.getRequestDispatcher("ProductoController?op=listar").forward(request, response);

                        }
                    }
                }
            }
        }
        
        //Opcion listar
        else if (op.equals("listar")) {
            try {

                PreparedStatement sta = cn.prepareStatement("select p.id_Pro, p.descripcion, p.precio,c.Categoria, pr.empresa, m.marca from productos as p inner join categoria as c on c.id_Categoria = p.id_Categoria inner join marca as m on m.id_Marca = p.id_Marca inner join proveedor as pr on pr.id_Proveedor = p.id_Proveedor");
                        
                ResultSet rs = sta.executeQuery();

                ArrayList<producto> lista = new ArrayList<>();
                
                while (rs.next()) {
                    int id = rs.getInt(1);
                    PreparedStatement sta2 = cn.prepareStatement("select sum(cantidad) from lote where id_Pro = ?");
                    sta2.setInt(1, id);
                    ResultSet rs2 = sta2.executeQuery();
                    
                    while(rs2.next()){
                        int cantidad = rs2.getInt(1);
                        PreparedStatement sta3 = cn.prepareStatement("update productos set cantidad = ? where id_Pro=? ");
                        sta3.setInt(1,cantidad );
                        sta3.setInt(2, id);
                        sta3.executeUpdate();

                        producto prod = new producto(id,rs.getString(2), rs.getDouble(3),cantidad,rs.getString(4),rs.getString(5),rs.getString(6));
                        lista.add(prod);
                    }                                      
                }

                request.setAttribute("lista", lista);
                
                
            } catch (Exception e) {
                log("Error al mostrar elmentos: "+e);
            }
            
            if (true) {
            try {

                PreparedStatement sta = cn.prepareStatement("select * from marca");
                ResultSet rs = sta.executeQuery();

                ArrayList<marca> listaM = new ArrayList<>();

                while (rs.next()) {
                    marca marc = new marca(rs.getInt(1), rs.getString(2));
                    listaM.add(marc);
                }
                request.setAttribute("listaM", listaM);
                              
            } catch (Exception e) {
                log("Error al mostrar elmentos"+e);
            }
        }

         
        if (true) {
            try {               
                PreparedStatement sta = cn.prepareStatement("select * from categoria");
                ResultSet rs = sta.executeQuery();

                ArrayList<categoria> listaC = new ArrayList<>();

                while (rs.next()) {
                    int id = rs.getInt(1);
                    PreparedStatement sta2 = cn.prepareStatement("select sum(cantidad) from productos where id_Categoria = ?");
                    sta2.setInt(1, id);
                    ResultSet rs2 = sta2.executeQuery();
                    while(rs2.next()){
                        categoria cat = new categoria(id, rs.getString(2), rs2.getInt(1));
                        listaC.add(cat);
                    }                  
                }
                request.setAttribute("listaC", listaC);
                
                               
            } catch (Exception e) {
                 log("Error al mostrar elmentos"+e);
            }
        }
        
        if (true) {
            try {

                PreparedStatement sta = cn.prepareStatement("select * from proveedor");
                ResultSet rs = sta.executeQuery();

                ArrayList<proveedor> listaP = new ArrayList<>();

                while (rs.next()) {
                    proveedor prov = new proveedor(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6));
                    listaP.add(prov);
                }
                request.setAttribute("listaP", listaP);
                
                request.getRequestDispatcher("productos.jsp").forward(request, response);
            } catch (Exception e) {
                log("Error al mostrar elmentos: "+ e);
            }
        }
        }  
        //Opcion editar
        if(op.equals("editar")){
            int id = Integer.parseInt(request.getParameter("idC"));
            String nombre = request.getParameter("txtProdEdit");
            int categoria = Integer.parseInt(request.getParameter("select_catEdit"));
            double precio = Double.parseDouble(request.getParameter("txtPrecioEdit"));
            int marca = Integer.parseInt(request.getParameter("select_marcaEdit"));
            int proveedor = Integer.parseInt(request.getParameter("select_provEdit"));

            try {
                PreparedStatement sta = cn.prepareStatement("update productos set descripcion=?, precio=?, id_Marca=?, id_Categoria=?, id_Proveedor=? where id_Pro=?");
                sta.setString(1, nombre);
                sta.setDouble(2, precio);
                sta.setInt(3, marca);
                sta.setInt(4, categoria);
                sta.setInt(5, proveedor);
                sta.setInt(6, id);
                sta.executeUpdate();
                request.getRequestDispatcher("ProductoController?op=listar").forward(request, response);

            } catch (IOException | SQLException | ServletException e) {
                System.out.println("Error al actualizar elemento");
            }
        }
        
        else if(op.equals("consultar")){
            
            String nombre=request.getParameter("buscar");
            try{
                PreparedStatement sta=cn.prepareStatement("select * from productos where descripcion like ?");
                sta.setString(1, "%"+nombre+"%");
                ResultSet rs = sta.executeQuery();

                ArrayList<producto> lista = new ArrayList<>();
                
                while (rs.next()) {
                    int id = rs.getInt(1);
                    PreparedStatement sta2 = cn.prepareStatement("select sum(cantidad) from lote where id_Pro = ?");
                    sta2.setInt(1, id);
                    ResultSet rs2 = sta2.executeQuery();
                    while(rs2.next()){

                        producto prod = new producto(id,rs.getString(2), rs.getDouble(3),rs2.getInt(1),rs.getString(4),rs.getString(5),rs.getString(6));
                        lista.add(prod);
                    }                                      
                }

                request.setAttribute("lista", lista);

            }catch(SQLException e){
                log(""+e);
            }
            if (true) {
            try {

                PreparedStatement sta = cn.prepareStatement("select * from marca");
                ResultSet rs = sta.executeQuery();

                ArrayList<marca> listaM = new ArrayList<>();

                while (rs.next()) {
                    marca marc = new marca(rs.getInt(1), rs.getString(2));
                    listaM.add(marc);
                }
                request.setAttribute("listaM", listaM);
                              
            } catch (Exception e) {
                log("Error al mostrar elmentos"+e);
            }
        }

         
        if (true) {
            try {               
                PreparedStatement sta = cn.prepareStatement("select * from categoria");
                ResultSet rs = sta.executeQuery();

                ArrayList<categoria> listaC = new ArrayList<>();

                while (rs.next()) {
                    int id = rs.getInt(1);
                    PreparedStatement sta2 = cn.prepareStatement("select sum(cantidad) from productos where id_Categoria = ?");
                    sta2.setInt(1, id);
                    ResultSet rs2 = sta2.executeQuery();
                    while(rs2.next()){
                        categoria cat = new categoria(id, rs.getString(2), rs2.getInt(1));
                        listaC.add(cat);
                    }                  
                }
                request.setAttribute("listaC", listaC);
                log("servlet produvtos");
                               
            } catch (Exception e) {
                 log("Error al mostrar elmentos"+e);
            }
        }
        
        if (true) {
            try {

                PreparedStatement sta = cn.prepareStatement("select * from proveedor");
                ResultSet rs = sta.executeQuery();

                ArrayList<proveedor> listaP = new ArrayList<>();

                while (rs.next()) {
                    proveedor prov = new proveedor(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),rs.getString(6));
                    listaP.add(prov);
                }
                request.setAttribute("listaP", listaP);
                
                request.getRequestDispatcher("productos.jsp").forward(request, response);
            } catch (IOException | SQLException | ServletException e) {
                log("Error al mostrar elmentos: "+ e);
            }
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
