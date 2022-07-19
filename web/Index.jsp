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
                        <h4>SISTEMA DE CONTROL DE INVENTARIO</h4>
                    </div>
                </div>
                &nbsp; 
                <div class="row">

                    <div class="trec col-md-3 mb-3">
                        <div class="card bg-primary text-white h-100">
                            <div class="card-body py-3">PROVEEDORES</div>
                            <div class="card-footer d-flex">
                                Ver detalles
                                <span class="ms-auto">
                                    <i class="bi bi-chevron-right"></i>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="trec col-md-3 mb-3">
                        <div class="card bg-warning text-dark h-100">
                            <div class="card-body py-3">VENTAS</div>
                            <div class="card-footer d-flex">
                                Ver detalles 
                                <span class="ms-auto">
                                    <i class="bi bi-chevron-right"></i>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="trec col-md-3 mb-3">
                        <div class="card bg-success text-white h-100">
                            <div class="card-body py-3">MARCAS DE PRODUCTOS</div>
                            <div class="card-footer d-flex">
                                Ver detalles

                            </div>
                        </div>
                    </div>
                    <div class="trec col-md-3 mb-3">
                        <div class="card bg-warning text-white h-100">
                            <div class="card-body py-3">PRODUCTOS INGRESADOS</div>
                            <div class="card-footer d-flex">
                                Ver detalles
                            </div>
                        </div>
                    </div>

                    <div class="trec col-md-3 mb-3">
                        <div class="card bg-danger text-white h-100">
                            <div class="card-body py-3">CAJA</div>
                            <div class="deta card-footer d-flex">
                                <a href="url"> Ver delatalles</a>
                            </div>
                        </div>
                    </div>

                    <div class="trec col-md-3 mb-3">
                        <div class="card bg-primary text-white h-100">
                            <div class="card-body py-3">APARTADOS</div>
                            <div class="card-footer d-flex">
                                Ver detalles
                            </div>
                        </div>
                    </div>
                    <div class="trec col-md-3 mb-3">
                        <div class="card bg-primary text-white h-100">
                            <div class="card-body py-3">COTIZACION</div>
                            <div class="card-footer d-flex">
                                Ver detalles
                            </div>
                        </div>
                    </div>

                    <div class="trec col-md-3 mb-3">
                        <div class="card bg-primary text-white h-100">
                            <div class="card-body py-3">ALMACEN </div>
                            <div class="card-footer d-flex">
                                <a href="ProductoController?op=listar" class="link-light" style="text-decoration: none">Ver detalles</a>
                            </div>
                        </div>
                    </div>
                </div>    



                <div class="row">
                    <div class="col-md-12 mb-3">
                        <div class="card">
                            <div class="card-header">
                                <span><i class="bi bi-table me-2"></i></span> 
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </body>
</html>
