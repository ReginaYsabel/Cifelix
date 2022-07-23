<%@page import="Modelo.PROD"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.Cotizar"%>
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
            <div class="container">
                <div class="row-fluid">
                    <div class="col-md-12">
                        <h2><span class="glyphicon glyphicon-edit"></span> Generar Cotización</h2>
                        <hr>
                        <form class="form-horizontal" role="form" id="datos_cotizacion">
                            <div class="form-group row">
                                <label for="atencion" class="col-md-1 control-label">Nombre:</label>
                                <div class="col-md-3">
                                    <input type="text" class="form-control" id="atencion"  name="Nom" placeholder="Atención" required>
                                </div>
                                <label for="tel1" class="col-md-1 control-label">Teléfono:</label>
                                <div class="col-md-2">
                                    <input type="text" class="form-control" id="tel1" name="Tel" placeholder="Teléfono" required>
                                </div>
                                <label for="fecha" class="col-md-1 control-label">fecha:</label>
                                <div class="col-md-2">
                                    <input type="date" class="form-control" id="decha" name="txtFe"  required>
                                </div>
                            </div> &nbsp;

                            <div class="form-group row">
                                <label for="empresa" class="col-md-1 control-label">DNI-RUC:</label>
                                <div class="col-md-3">
                                    <input type="text" class="form-control" id="empresa" name="txtDNI" placeholder="INGRESE DATOS">
                                </div>
                                <label for="tel2" class="col-md-1 control-label">Direccion:</label>
                                <div class="col-md-2">
                                    <input type="text" class="form-control" id="tel2" name="txtDir" placeholder="Direccion">
                                </div>
                                <label for="email" class="col-md-1 control-label">Email:</label>
                                <div class="col-md-2">
                                    <input type="email" class="form-control" id="email" name="txtEmail" placeholder="Email">
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
                                                    <form action="cotizacionController">
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
                                                                    <td><%=prod.getCodigo()%></td>
                                                                    <td><%=prod.getNombre()%></td>
                                                                    <td><%=prod.getPrecio()%></td>
                                                                    <td align="center" style="width:180px"> <input type="number" name="txtCan" min="1" max="100"></td>
                                                                    <td><input type="submit" name="btn" value="Agregar" class="btn btn-success" align="center"></td>
                                                                    <input type="hidden" name="op" value="agregar">
                                                                    <input type="hidden" name="cod" value="<%=prod.getCodigo()%>">
                                                                    <input type="hidden" name="nom" value="<%=prod.getNombre()%>">
                                                                    <input type="hidden" name="pre" value="<%=prod.getPrecio()%>">
                                                                </tr>

                                                                <%
                                                                    }
                                                                %>
                                                            </tbody>
                                                        </table>
                                                    </form>    
                                                </div>    
          
                                            </div>
                                        </div>
                                    </div>                    
                                    <button type="button" class="btn btn-danger" onclick="print()">Imprimir</button>   
                                </div>	     
                            </div>
                        </form>	
                    </div>
                </div>	
            </div>
        </main>
        
        <main class="mt-5 pt-2">
            <form action="cotizacionController"> 
                <div class="container-fluid mt-2">              
                    <div class="row">    
                        <div class="col-sm-8">
                          <hr size="5px" color="black">     
                            <table class="table table-hover">
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
                                    if (lista != null) {
                                        for (Cotizar d : list) {
                                %>
                                <tr>
                                    <td name="txtCodigo"><%=d.getCod()%></td>
                                    <td name="txtNombre"><%=d.getNom()%></td>
                                    <td name="txtPrecio"><%=d.getPre()%></td>
                                    <td name="txtCantidad"><a href="cotizacionController?op=menos&cod=<%=d.getCod()%>&nom=<%=d.getNom()%>&pre=<%=d.getPre()%>" class="btn btn-danger">-</a> <%=d.getCan()%> <a href="cotizacionController?op=mas&cod=<%=d.getCod()%>&nom=<%=d.getNom()%>&pre=<%=d.getPre()%>" class="btn btn-primary">+</a> </td>
                                    <td name="txtMonto"><%=d.getPre() * d.getCan()%></td>
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
                        <div class="col-sm-4">
                            
                                <div class="card">
                                    <div class="card-header bg-primary">
                                        <p class="h5 text-light text-center">Generar Calculos</p>
                                    </div>
                                    <div class="card-body">
                                        <label class="fw-bold">Subtotal</label>
                                        <input type="text" value="<%=subtotal%>" name="txtst" class="form-control" readonly>
                                        <label class="fw-bold">Igv</label>
                                        <input type="text" value="S/<%=i%>"  name="txtIGV" class="form-control " disabled>
                                        <label class="fw-bold">Total</label>
                                        <input type="text" value="<%=t%>" name="txtT"  class="form-control " readonly>
                                    </div>
                                    <div class="card-footer">
                                         <button type="submit" href="cotizacionController?op=insertarDT" class="btn btn-danger w-100 fw-bold mt-2">Guardar Cotización</button>   
                                    </div>
                                    <input type="hidden" name="ope" value="Insertars    " >                    
                                </div>                   
                        </div>
                    </div>
                </div>
            </form>        
        </main>      
    </body>
</html>
