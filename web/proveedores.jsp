<%@page import="java.util.ArrayList"%>
<%@page import="Modelo.proveedor"%>
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
                
                <!-- Titulo -->
                <div class="row">
                    <div class="col-md-12">
                        <h4>PROVEEDORES</h4>
                    </div>
                </div> 

                <!-- Buscador y Ventana modal -->
                <div class="d-flex align-items-center justify-content-between">
                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#Modal">Agregar Proveedor </button>

                    <!-- Modal -->
                    <div class="modal fade" id="Modal" tabindex="-1" aria-labelledby="ModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content">
                                <form method="post" action="proveedorController?op=insertar">
                                    
                                    <!-- Titulo del modal --> 
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="ModalLabel">Agregar proveedor</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    
                                    <!-- Cuerpo del modal --> 
                                    <div class="modal-body">    
                                        <div class="row">
                                            <div class="col-md-6 form-group">
                                                <label for="nombre" class="col-form-label">Nombres-Apellidos</label>
                                                <input type="text" class="form-control" id="nombre" name="txtNom">
                                            </div>
                                            <div class="col-md-6 form-group">
                                                <label for="empresa" class="col-form-label">Empresa</label>
                                                <input type=text" class="form-control" id="empresa" name="txtEmp">
                                            </div>
                                            <div class="col-md-6 form-group">
                                                <label for="celular" class="col-form-label">Celular</label>
                                                <input type="text" class="form-control" id="celular" name="txtCel">
                                            </div>
                                            <div class="col-md-6 form-group">
                                                <label for="correo" class="col-form-label">Correo</label>
                                                <input type="text" class="form-control" id="correo" name="txtCor">
                                            </div>
                                            <div class="col-md-6 form-group">
                                                <label for="direccion" class="col-form-label">Dirección</label>
                                                <input type="text" class="form-control" id="direccion" name="txtDir">
                                            </div>
                                        </div>
                                        
                                        <!-- Agregar Categoria -->
                                        <div class="form-group mt-3">
                                            <button type="submit" class="btn btn-primary">Agregar</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>

                    <!-- Buscador -->
                    <div class="input-brand input-group mt-3">
                        <input type="text" class="form-control" placeholder="Buscar" aria-label="Buscar" aria-describedby="button-addon2">
                        <button class="btn btn-outline-secondary" type="button" id="button-addon2">Buscar</button>
                    </div>
                </div>

                <!-- Tabla de marcas -->
                <div class="tabla text-center">
                    <table class="table">
                        <thead class="table-light">
                            <tr>
                                <th scope="col">CODIGO</th>
                                <th scope="col">Nombres</th>
                                <th scope="col">Empresa</th>
                                <th scope="col">Celular</th>
                                <th scope="col">Correo</th>
                                <th scope="col">Dirección</th>
                                <th scope="col">Acción</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                ArrayList<proveedor> lista = (ArrayList<proveedor>) request.getAttribute("lista");
                                for (int i = 0; i < lista.size(); i++) {
                                    proveedor prov = lista.get(i);

                            %>
                            <tr>
                                <td><%=prov.getId()%></td>
                                <td><%=prov.getNom()%></td>
                                <td><%=prov.getEmpresa()%></td>
                                <td><%=prov.getCelular()%></td>
                                <td><%=prov.getCorreo()%></td>
                                <td><%=prov.getDireccion()%></td>
                                <td>
                                    <!-- Toolbar -->
                                    <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                                        <div class="btn-group mr-2" role="group" aria-label="Basic example">
                                            
                                            <!-- Button trigger modal - Editar-->
                                            <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#modalEditar<%=i%>">Editar</button>
                                            <div class="modal fade" id="modalEditar<%=i%>" tabindex="-1" aria-labelledby="ModalLabel" aria-hidden="true">
                                                <div class="modal-dialog modal-lg">
                                                    <div class="modal-content">
                                                        <form method="post" action="proveedorController?op=editar&idC=<%=prov.getId()%>">
                                                            
                                                            <!-- Titulo del modal -->
                                                            <div class="modal-header">
                                                                <h5 class="modal-title" id="ModalLabel">Editar Proveedor</h5>
                                                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                            </div>
                                                            
                                                            <!-- Cuerpo del modal-->
                                                            <div class="modal-body"> 
                                                                <div class="row">
                                                                    
                                                                    <div class="col-md-6 form-group">
                                                                        <label for="nombre" class="col-form-label">Nombres-Apellidos</label>
                                                                        <input type="text" class="form-control" id="nombre" name="txtENom" value="<%=prov.getNom()%>" >
                                                                    </div>
                                                                    <div class="col-md-6 form-group">
                                                                        <label for="empresa" class="col-form-label">Empresa</label>
                                                                        <input type=text" class="form-control" id="empresa" name="txtEEmp" value="<%=prov.getEmpresa()%>" >
                                                                    </div>
                                                                    <div class="col-md-6 form-group">
                                                                        <label for="celular" class="col-form-label">Celular</label>
                                                                        <input type="text" class="form-control" id="celular" name="txtECel" value="<%=prov.getCelular()%>">
                                                                    </div>
                                                                    <div class="col-md-6 form-group">
                                                                        <label for="correo" class="col-form-label">Correo</label>
                                                                        <input type="text" class="form-control" id="correo" name="txtECor" value="<%=prov.getCorreo()%>">
                                                                    </div>
                                                                    <div class="col-md-6 form-group">
                                                                        <label for="direccion" class="col-form-label">Dirección</label>
                                                                        <input type="text" class="form-control" id="direccion" name="txtEDir" value="<%=prov.getDireccion()%>">
                                                                    </div>
                                                                 </div>

                                                                <!-- Editar Categoria -->
                                                                <div class="form-group mt-3">
                                                                    <button type="submit" class="btn btn-primary">Actualizar</button>
                                                                </div>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                                                
                                            <!-- Button trigger modal - Eliminar-->                    
          
                                            <a onclick="return confirm('¿Esta seguro de eliminar este registro?')" href="proveedorController?op=eliminar&idE=<%=prov.getId()%>" class="btn btn-danger link-light" style="text-decoration: none">Eliminar</a>
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


