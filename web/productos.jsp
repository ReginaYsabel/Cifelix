<%@page import="Modelo.proveedor"%>
<%@page import="Modelo.marca"%>
<%@page import="Modelo.categoria"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.producto"%>
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
                        <h4>PRODUCTOS</h4>
                    </div>
                </div> 

                <!-- Buscador y Ventana Modal - Nuevo producto-->
                <div class="d-flex align-items-center justify-content-between">
                    <div>
                        <!-- Button trigger modal -->
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#Modal">Agregar Producto</button>

                        <!-- Modal -->                   
                        <div class="modal fade" id="Modal" tabindex="-1" aria-labelledby="ModalLabel" aria-hidden="true">
                            <div class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <form method="post" action="ProductoController?op=insertar">

                                        <!-- Titulo del modal -->
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="ModalLabel">Agregar Producto</h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>

                                        <!-- Cuerpo del modal -->
                                        <div class="modal-body">                                   
                                            <div class="row">                              
                                                <div class="col-md-6 form-group">
                                                    <label for="producto" class="col-form-label">Producto</label>
                                                    <input type="text" class="form-control" id="producto" name="txtProd" required>
                                                </div>
                                                <div class="col-md-6 form-group">
                                                    <label for="precio" class="col-form-label">Precio</label>
                                                    <input type="text" class="form-control" id="precio" name="txtPrecio" required>
                                                </div>

                                                <div class="col-md-6 form-group">
                                                    <label for="marca" class="col-form-label">Marca</label>
                                                    <!-- <input type="text" class="form-control" id="marca" name="txtMarca">  -->
                                                    <select class="form-select" aria-label="Default select example" name="select_marca" required>
                                                        <option selected disabled>Elegir marca</option>
                                                        <%                                                            ArrayList<marca> listaM = (ArrayList<marca>) request.getAttribute("listaM");
                                                            for (int i = 0; i < listaM.size(); i++) {
                                                                marca marca = listaM.get(i);
                                                        %>
                                                        <option value="<%=marca.getCodigo()%>"><%=marca.getNombre()%></option>
                                                        <%
                                                            }
                                                        %>
                                                    </select>


                                                </div>
                                                <div class="col-md-6 form-group">

                                                    <label for="categoria" class="col-form-label">Categoria</label>
                                                    <!-- <input type="text" class="form-control" id="observacion" name="txtObsv">  -->
                                                    <select class="form-select" aria-label="Default select example" name="select_cat" required>
                                                        <option selected disabled>Elegir categoria</option>
                                                        <%
                                                            ArrayList<categoria> listaC = (ArrayList<categoria>) request.getAttribute("listaC");
                                                            for (int i = 0; i < listaC.size(); i++) {
                                                                categoria cat = listaC.get(i);
                                                        %>
                                                        <option value="<%=cat.getCodigo()%>"><%=cat.getNombre()%></option>
                                                        <%
                                                            }
                                                        %>
                                                    </select>

                                                </div>
                                                <div class="col-md-6 form-group">
                                                    <label for="proveedor" class="col-form-label">Proveedor</label>
                                                    <!-- <input type="text" class="form-control" id="proveedor" name="txtProv"> -->
                                                    <select class="form-select" aria-label="Default select example" name="select_prov" required>
                                                        <option selected disabled>Elegir proveedor</option>
                                                        <%
                                                            ArrayList<proveedor> listaP = (ArrayList<proveedor>) request.getAttribute("listaP");
                                                            for (int i = 0; i < listaP.size(); i++) {
                                                                proveedor prov = listaP.get(i);
                                                        %>
                                                        <option value="<%=prov.getId()%>"><%=prov.getEmpresa()%></option>
                                                        <%
                                                            }
                                                        %>
                                                    </select>

                                                </div>
                                            </div>

                                            <!-- Agregar Producto -->
                                            <div class="form-group mt-3">
                                                <button type="submit" class="btn btn-primary">Agregar</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>

                        <!-- Button trigger modal - Nuevo Lote-->
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#ModalN">Agregar Nuevo Lote</button>

                        <!-- Modal -->                   
                        <div class="modal fade" id="ModalN" tabindex="-1" aria-labelledby="ModalLabel" aria-hidden="true">
                            <div class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <form method="post" action="LoteController?op=insertar">

                                        <!-- Titulo del modal -->
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="ModalLabel">Nuevo Lote</h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>

                                        <!-- Cuerpo del modal -->
                                        <div class="modal-body">                                   
                                            <div class="row">                              
                                                <div class="col-md-6 form-group">
                                                    <label for="producto" class="col-form-label">Producto</label>
                                                    <!--<input type="text" class="form-control" id="producto" name="txtProd"> -->
                                                    <select class="form-select" aria-label="Default select example" name="select_prod" required>
                                                        <option selected disabled>Elegir producto</option>
                                                        <%
                                                            ArrayList<producto> lista = (ArrayList<producto>) request.getAttribute("lista");
                                                            for (int i = 0; i < lista.size(); i++) {
                                                                producto prod = lista.get(i);
                                                        %>
                                                        <option value="<%=prod.getCodigo()%>"><%=prod.getDescripcion()%></option>
                                                        <%
                                                            }
                                                        %>
                                                    </select>
                                                </div>
                                                <div class="col-md-6 form-group">
                                                    <label for="cantidad" class="col-form-label">Cantidad</label>
                                                    <input type="text" class="form-control" id="cantdad" name="txtCant" required>
                                                </div>
                                                <div class="col-md-6 form-group">
                                                    <label for="vencimiento" class="col-form-label">Fecha de Vencimiento</label>
                                                    <input type="date" class="form-control" id="vencimiento" name="txtVen">
                                                </div>

                                            </div>

                                            <!-- Agregar Nuevo Lote -->
                                            <div class="form-group mt-3">
                                                <button type="submit" class="btn btn-primary">Agregar</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Buscador -->
                    
                    <form class="buscador" method="post" action="ProductoController?op=consultar">
                        <div class="input-brand input-group mt-3">
                            <input type="text" class="form-control" placeholder="Buscar" aria-label="Buscar" aria-describedby="button-addon2" name="buscar" required>
                            <button class="btn btn-outline-secondary" type="submit" id="button-addon2">Buscar</button>
                        </div>
                    </form>

                </div>

                <!-- Tabla de productos -->
                <div class="tabla row text-center">
                    <table class="table">
                        <thead class="table-light">
                            <tr>
                                <th scope="col">CODIGO</th>
                                <th scope="col">PRODUCTO</th>
                                <th scope="col">PRECIO</th>
                                <th scope="col">CANTIDAD</th>
                                <th scope="col">CATEGORIA</th>
                                <th scope="col">ACCIÓN</th>
                            </tr>
                        </thead>
                        <tbody>                            
                            <%
                                ArrayList<producto> lista2 = (ArrayList<producto>) request.getAttribute("lista");
                                for (int i = 0; i < lista2.size(); i++) {
                                    producto prod = lista2.get(i);
                            %>
                            <tr>
                                <td><%=prod.getCodigo()%></td>
                                <td><%=prod.getDescripcion()%></td>
                                <td><%=prod.getPrecio()%></td>
                                <td><%=prod.getCantidad()%></td>
                                <td><%=prod.getCategoria()%></td>
                                <td>
                                    <!-- Toolbar -->
                                    <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                                        <div class="btn-group mr-2" role="group" aria-label="Basic example">

                                            <!-- Button trigger modal - Editar-->
                                            <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#modalEditar<%=i%>">Editar</button>
                                            <div class="modal fade" id="modalEditar<%=i%>" tabindex="-1" aria-labelledby="ModalLabel" aria-hidden="true">
                                                <div class="modal-dialog modal-lg">
                                                    <div class="modal-content">
                                                        <form method="post" action="ProductoController?op=editar&idC=<%=prod.getCodigo()%>">

                                                            <!-- Titulo del modal -->
                                                            <div class="modal-header">
                                                                <h5 class="modal-title" id="ModalLabel">Editar Marca</h5>
                                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                            </div>

                                                            <!-- Cuerpo del modal -->
                                                            <div class="modal-body">                                   
                                                                <div class="row">                              
                                                                    <div class="col-md-6 form-group">
                                                                        <label for="producto" class="col-form-label">Producto</label>
                                                                        <input type="text" class="form-control" id="productoEdit" name="txtProdEdit" value="<%=prod.getDescripcion()%>" required>
                                                                    </div>
                                                                    <div class="col-md-6 form-group">
                                                                        <label for="precio" class="col-form-label">Precio</label>
                                                                        <input type="text" class="form-control" id="precioEdit" name="txtPrecioEdit" value="<%=prod.getPrecio()%>" required>
                                                                    </div>

                                                                    <div class="col-md-6 form-group">
                                                                        <label for="marca" class="col-form-label">Marca</label>
                                                                        <!-- <input type="text" class="form-control" id="marca" name="txtMarca">  -->
                                                                        <select class="form-select" aria-label="Default select example" name="select_marcaEdit" required>
                                                                            <option selected disabled>Elegir marca</option>
                                                                            <%
                                                                                ArrayList<marca> listaME = (ArrayList<marca>) request.getAttribute("listaM");
                                                                                for (int j = 0; j < listaME.size(); j++) {
                                                                                    marca marca = listaME.get(j);
                                                                                    
                                                                            %>
                                                                            <option value="<%=marca.getCodigo()%>"><%=marca.getNombre()%></option>
                                                                            <%
                                                                                }
                                                                            %>
                                                                        </select>


                                                                    </div>
                                                                    <div class="col-md-6 form-group">

                                                                        <label for="categoria" class="col-form-label">Categoria</label>
                                                                        <!-- <input type="text" class="form-control" id="observacion" name="txtObsv">  -->
                                                                        <select class="form-select" aria-label="Default select example" name="select_catEdit" required>
                                                                            <option selected disabled>Elegir categoria</option>
                                                                            <%
                                                                                ArrayList<categoria> listaCE = (ArrayList<categoria>) request.getAttribute("listaC");
                                                                                for (int j = 0; j < listaC.size(); j++) {
                                                                                    categoria cat = listaCE.get(j);
                                                                            %>
                                                                            <option value="<%=cat.getCodigo()%>"><%=cat.getNombre()%></option>
                                                                            <%
                                                                                }
                                                                            %>
                                                                        </select>

                                                                    </div>
                                                                    <div class="col-md-6 form-group">
                                                                        <label for="proveedor" class="col-form-label">Proveedor</label>
                                                                        <!-- <input type="text" class="form-control" id="proveedor" name="txtProv"> -->
                                                                        <select class="form-select" aria-label="Default select example" name="select_provEdit" required>
                                                                            <option selected disabled>Elegir proveedor</option>
                                                                            <%
                                                                                ArrayList<proveedor> listaPE = (ArrayList<proveedor>) request.getAttribute("listaP");
                                                                                for (int j = 0; j < listaPE.size(); j++) {
                                                                                    proveedor prov = listaPE.get(j);
                                                                            %>
                                                                            <option value="<%=prov.getId()%>"><%=prov.getEmpresa()%></option>
                                                                            <%
                                                                                }
                                                                            %>
                                                                        </select>

                                                                    </div>
                                                                </div>

                                                                <!-- Agregar Producto -->
                                                                <div class="form-group mt-3">
                                                                    <button type="submit" class="btn btn-primary">Actualizar</button>
                                                                </div>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>

                                            <!-- Button trigger modal - Eliminar-->                    

                                            <a onclick="return confirm('¿Esta seguro de eliminar este registro?')" href="ProductoController?op=eliminar&idE=<%=prod.getCodigo()%>" class="btn btn-danger link-light" style="text-decoration: none">Eliminar</a>
                                        </div>                           
                                    </div>
                                </td>
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
