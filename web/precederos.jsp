<%@page import="Modelo.lote"%>
<%@page import="Modelo.producto"%>
<%@page import="Modelo.perecedero"%>
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
                    <!-- Titulo -->
                    <div class="col-md-12">
                        <h4>PRODUCTOS PERECEDEROS</h4>
                    </div>
                </div> 

                <!-- Buscador -->
                <div>
                    <div class="input-group mt-3">                     
                        <input type="text" class="form-control" placeholder="Buscar Producto" aria-label="Buscar Producto" aria-describedby="button-addon2">
                        <button class="btn btn-outline-secondary" type="button" id="button-addon2">Buscar</button>
                    </div>
                </div>

                <!-- Tabla de productos -->
                <div class="tabla row text-center">
                    <table class="table">
                        <thead class="table-light">
                            <tr>
                                <th scope="col">CODIGO</th>
                                <th scope="col">PRODUCTO</th>                              
                                <th scope="col">CANTIDAD</th>
                                <th scope="col">VENCIMIENTO</th>
                                <th scope="col">ESTADO</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%                                ArrayList<lote> lista = (ArrayList<lote>) request.getAttribute("lista");
                                for (int i = 0; i < lista.size(); i++) {
                                    lote lot = lista.get(i);
                            %>
                            <tr>
                                <td><%=lot.getCodigo()%></td>
                                <td><%=lot.getProducto()%></td>
                                <td><%=lot.getCantidad()%></td>
                                <td><%=lot.getVencimiento()%></td>
                                <td><p class="estado"><%=lot.getEstado()%></p></td>
                            </tr>
                            <%
                                }
                            %>
                        </tbody>
                    </table>
                </div>
            </div>
        </main>
        <script>
            function color() {
                const p = document.getElementsByClassName("estado");
                for (let i = 0; i < p.length; i++) {
                    const estado = p[i].textContent;                
                    if (estado === "Vencido") {
                        p[i].style.backgroundColor = "#ff7588";                       
                    } else {
                        p[i].style.backgroundColor = "#16d39a";                        
                    }
                }
            }           
            color();
        </script>
    </body>
</html>

