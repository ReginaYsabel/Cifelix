<%@page import="Modelo.inventario"%>
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

                <!-- Titulo-->
                <div class="row">
                    <div class="col-md-12">
                        <h4>INVENTARIO</h4>
                    </div>
                </div> 

                <!-- Buscador  -->
                <div class="d-flex align-items-center justify-content-between">
                    <form class="buscador" method="post" action="ProductoController?op=consultar">
                        <div class="input-brand input-group mt-3">
                            <input type="text" class="form-control" placeholder="Buscar" aria-label="Buscar" aria-describedby="button-addon2" name="buscar" required>
                            <button class="btn btn-outline-secondary" type="submit">Buscar</button>
                        </div>
                    </form>
                    
                    <div>
                        <button class="btn btn-danger" type="button" id="button-addon2" onclick="printDiv('tabla')">Imprimir</button>
                    </div>
                </div>

                <!-- Tabla de categorias -->
                <div class="tabla row text-center" id="tabla">
                    <table class="table">
                        <thead class="table-light">
                            <tr>
                                <th scope="col">CODIGO</th>
                                <th scope="col">PRODUCTO</th>
                                <th scope="col">CANTIDAD INICIAL</th>
                                <th scope="col">CANTIDAD VENDIDA</th>
                                <th scope="col">STOCK TOTAL</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                ArrayList<inventario> lista = (ArrayList<inventario>) request.getAttribute("lista");
                                for (int i = 0; i < lista.size(); i++) {
                                    inventario inv = lista.get(i);

                            %>
                            
                            <tr>
                                <td><%=inv.getCodigo()%></td>
                                <td><%=inv.getProducto()%></td>
                                <td><%=inv.getInicial()%></td>
                                <td><%=inv.getVendida()%></td>
                                <td><%=inv.getTotal()%></td>
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
        function printDiv(nombreDiv) {
            var contenido= document.getElementById(nombreDiv).innerHTML;
            var contenidoOriginal= document.body.innerHTML;

            document.body.innerHTML = contenido;

            window.print();

            document.body.innerHTML = contenidoOriginal;
        }
        </script>
    </body>
</html>
