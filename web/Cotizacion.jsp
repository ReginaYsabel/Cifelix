<%-- 
    Document   : Cotizacion
    Created on : 05/07/2022, 06:09:32 PM
    Author     : AMVM_
--%>
<%@page import="Modelo.Cotizar"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.PROD"%>
<%@page session="true"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="user.jsp"%>
<!DOCTYPE html>
<html lang="en">
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
            <form action="cotizacionController?op=guardar" id="formulario" method="post">
                <div class="container">
                    <div class="row-fluid">
                        <div class="col-md-12">
                            <h2><span class="glyphicon glyphicon-edit"></span> Generar Cotización</h2>
                            <hr>
                            
                                <div class="form-group row">
                                    <label for="atencion" class="col-md-1 control-label">Nombre:</label>
                                    <div class="col-md-3">
                                        <input type="text" class="form-control" id="atencion" placeholder="Nombre" name="txtNombre" required>
                                    </div>
                                    <label for="tel1" class="col-md-1 control-label">Teléfono:</label>
                                    <div class="col-md-2">
                                        <input type="text" class="form-control" id="tel1" placeholder="Teléfono" name="txtTelef" required>
                                    </div>
                                    <label for="fecha" class="col-md-1 control-label">Fecha:</label>
                                    <div class="col-md-2">
                                        <input type="date" class="form-control" id="fecha"  name="txtFecha" required>
                                    </div>
                                </div> &nbsp;

                                <div class="form-group row">
                                    <label for="empresa" class="col-md-1 control-label">DNI-RUC:</label>
                                    <div class="col-md-3">
                                        <input type="text" class="form-control" id="empresa" name="txtDni" placeholder="DNI">
                                    </div>
                                    <label for="tel2" class="col-md-1 control-label">Direccion:</label>
                                    <div class="col-md-2">
                                        <input type="text" class="form-control" id="tel2" name="txtDir" placeholder="Direccion">
                                    </div>
                                    <label for="email" class="col-md-1 control-label">Trabajador:</label>
                                    <div class="col-md-2">
                                        <input type="email" class="form-control" id="email" name="txtTrab" placeholder="Codigo">
                                    </div>
                                </div>&nbsp;&nbsp;


                                <div class="col-md-12">
                                    <div class="pull-right">
                                        <!-- Button trigger modal -->
                                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#staticBackdrop"><a href="cotizacionController?op=listar"></a> Productos</button>
                                        <!-- Modal -->
                                        <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                                            <div class="modal-dialog modal-dialog-scrollable modal-lg">
                                                <div class="modal-content">
                                                    <div class="modal-header">
                                                        <h5 class="modal-title" id="staticBackdropLabel">Lista de productos</h5>
                                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                    </div>
                                                    <div class="modal-body">
                                                        <!-- buscardor-->
                                                        <form class="d-flex ms-4 my-1 my-lg-0">
                                                            <div class="input-group">
                                                                <input class="form-control" type="search" placeholder="Buscar" aria-label="Search"/>
                                                                <button class="btn btn-primary" type="submit"><i class="fas fa-search"></i></button>
                                                            </div>
                                                        </form>
                                                        &nbsp;&nbsp;

                                                        <table class="table table-striped table-bordered wd-100 text-center">
                                                            <thead class="bg-primary" >
                                                            <th>ID</th>
                                                            <th>Nombre</th>
                                                            <th>Precio</th>
                                                            <th>Cantidad</th>
                                                            <th>Accion</th>
                                                            </thead>  

                                                            <tbody>
                                                                <%  
                                                                    ArrayList<PROD> lista = (ArrayList<PROD>) request.getAttribute("lista");
                                                                    for (int i = 0; i < lista.size(); i++) {
                                                                        PROD prod = lista.get(i);
                                                                %>
                                                                <tr>
                                                            <form action="cotizacionController?fila=<%=i%>" method="post">

                                                                <td><%=prod.getCodigo()%></td>
                                                                <td><%=prod.getNombre()%></td>
                                                                <td><%=prod.getPrecio()%></td>
                                                                <td align="center" style="width:180px"> <input type="number" name="txtCan<%=i%>" min="1" max="100"></td>
                                                                <td><input type="submit" name="btn" value="Agregar" class="btn btn-success" align="center"></td>
                                                                <input type="hidden" name="op" value="agregar">
                                                                <input type="hidden" name="cod<%=i%>" value="<%=prod.getCodigo()%>">
                                                                <input type="hidden" name="nom<%=i%>" value="<%=prod.getNombre()%>">
                                                                <input type="hidden" name="pre<%=i%>" value="<%=prod.getPrecio()%>">

                                                            </form>

                                                            </tr>

                                                            <%
                                                                }
                                                            %>
                                                            </tbody>
                                                        </table>

                                                    </div>    

                                                </div>
                                            </div>
                        </div>
                    </div>	
                </div>

                <div class="tabla row text-center">
                    <hr size="5px" color="black">     
                    <table class="table">
                        <tr>
                            <th>Codigo</th>
                            <th>Nombre</th>
                            <th>Precio</th>
                            <th>Cantidad</th>
                            <th>Monto</th>
                        </tr>
                        <%
                            double subtotal = 0, igv = 0, total = 0, i = 0, t = 0;
                            ArrayList<Cotizar> list = (ArrayList<Cotizar>) session.getAttribute("carrito");
                            if (list != null) {
                                for (int j = 0; j < list.size(); j++) {
                                    Cotizar d = list.get(j);
                        %>
                        <tr>
                        
                            <td><%=d.getCod()%></td>
                            <td><%=d.getNom()%></td>
                            <td><%=d.getPre()%></td>
                            <td><a href="cotizacionController?op=menos&fila=<%=j%>&cod<%=j%>=<%=d.getCod()%>&nom<%=j%>=<%=d.getNom()%>&pre<%=j%>=<%=d.getPre()%>" class="btn btn-danger">-</a> <%=d.getCan()%> <a href="cotizacionController?op=mas&fila=<%=j%>&cod<%=j%>=<%=d.getCod()%>&nom<%=j%>=<%=d.getNom()%>&pre<%=j%>=<%=d.getPre()%>" class="btn btn-primary">+</a> </td>
                            <td><%=d.getPre() * d.getCan()%></td>
                        

                        </tr>
                        <%
                                 subtotal = subtotal + (d.getPre() * d.getCan());
                                }
                                igv = subtotal * 0.18;
                                i = Math.round(igv * 100.0) / 100.0;
                                total = subtotal + igv;
                                t = Math.round(total * 100.0) / 100.0;
                            }
                        %>               
                    </table>
                </div> 

                <div class="row">
                    
                        <div class="mx-2">                           
                            <div class="form-group">
                                <label class="fw-bold">Subtotal</label>
                                <input type="text" value="<%=subtotal%>" name="txtSubD" disabled>
                                <label class="fw-bold">Igv</label>
                                <input type="text" value="<%=i%>" name="txtIgvD" disabled>
                                <label class="fw-bold">Total</label>
                                <input type="text" value="<%=t%>" name="txtTotalD" disabled>
                            </div>
                            <div>
                                
                                <a onclick="document.getElementById('formulario').submit();" class="btn btn-danger fw-bold mt-5">Guardar Cotización</a>   
                                
                            </div>
                                               
                        </div>
                    
                </div>
                            
            </form>
        </main>
    </body>
</html>
                                        </div>                    
                                        <button type="button" class="btn btn-danger" onclick="print()">Imprimir</button>   
                                    </div>	     
                                </div>
                            	
