
<%@page import="Modelo.ven_re"%>
<%@page import="java.util.ArrayList"%>
<%@ include file="user.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

                <!-- Titulo-->
                <div class="row">
                    <div class="col-md-12">
                        <h4>Registro de ventas</h4>
                    </div>
                </div> 

                <!-- Buscador -->
                   

                <!-- Tabla de categorias -->
                <div class="tabla row text-center">
                    <table class="table">
                        <thead class="table-light">
                            <tr>
                                <th scope="col">IdVenta</th>
                                <th scope="col">Nomnre Clien</th>
                                <th scope="col">fecha</th>
                                <th scope="col">cantidad</th>
                                <th scope="col">Producto</th>
                              
                                <th scope="col">Pre_total</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                               ArrayList<ven_re> lisv  = (ArrayList<ven_re>) request.getAttribute("lisv ");
                                for (int i = 0; i < lisv .size(); i++) {
                                    ven_re vRE = lisv .get(i);

                            %>
                            
                            <tr>
                                <td><%=vRE.getCod()%></td>
                                <td><%=vRE.getNom()%></td>
                                <td><%=vRE.getFecha()%></td>
                                <td><%=vRE.getCant()%></td>
                                <td><%=vRE.getDesc()%></td>
                                <td><%=vRE.getTot()%></td>
                                
                            </tr>
                            <%
                                }
                            %>
                            
                        </tbody>
                    </table>    
                            
                </div>
            </div>
        </main>
    </body>
</html>
