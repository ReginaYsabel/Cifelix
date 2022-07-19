<%@page import="Modelo.Empleado"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ include file="user.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
            <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">    
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
        <link href="Sets/CSS/Estilos.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <!-- Navegador y Sidebar -->
        <%@ include file="nav.jsp"%>
       
        <main class="mt-5 pt-3">
          <div class="container-fluid">
            <div class="row">
              <div class="col-md-12">
                <h4 align="center">SISTEMA DE CONTROL DE INVENTARIO</h4>
              </div>
            </div>
          </div>    
           &nbsp;   
           <div class="form-group">
             <button class="btn-sm bg-warning"> <a href="agreEmpleado.jsp" class="nav-link px-3 active text-light">Agregar nuevo trabajador</a></button>
           </div>
           &nbsp
                  
     
        <div class="row">
          <div class="col-md-12 mb-3">
            <div class="card">
              <div class="card-header">
                <span><i class="bi bi-table me-2"></i></span> TRABAJADORES
              </div>
              
              <div class="card-body">
                <div class="conteiner">
                   
                    <table class="table">
                    
                        <tr class="bg-warning">
                        <th>Id</th>  
                        <th>Nombre</th>
                        <th>DNI </th>
                        <th>Perfil</th>
                        <th>Correo</th>
                        <th>Password</th>
                        <th>Sueldo</th>
                      </tr>
                      <%
                        ArrayList<Empleado> lista=(ArrayList<Empleado>)request.getAttribute("lista");
                        for(int i=0; i<lista.size();i++)
                        {
                            Empleado em=lista.get(i);    
                       %>
                        <tr>
                            <td><%=em.getIdEmp()%></td>
                            <td><%=em.getNom_apellido()%></td>
                            <td><%=em.getDNI()%></td>
                            <td><%=em.getPerfil()%></td>
                            <td><%=em.getCorreo()%></td>
                            <td><%=em.getPass()%></td
                            <td><%=em.getSueldo()%></td>
                        </tr>
                        <%
                        }
                        %>
       
                  </table>
                   
                </div>
              </div>
               
            </div>
          </div>
        </div>         
        </main>
    </body>
</html>
