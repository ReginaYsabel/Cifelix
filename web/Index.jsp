<%@page import="Modelo.Empleado"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="user.jsp"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> FERRETERIA LILY</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">    
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
        <link href="Sets/CSS/Estilos.css" rel="stylesheet" type="text/css"/>
        <script src="https://kit.fontawesome.com/9bdc09ed99.js" crossorigin="anonymous"></script>
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
                    
                     <div class="trec col-md-4 mb-3">
                        <div class="row bg-warning">
                            
                            <div class="col-5 m-0 justify-content-center p-2 rounded-top">
                                <h1 class="text-center text-white h1"></h1> 
                                <p class="let text-center text-white">PROVEEDORES</p>&nbsp;
                                <div class="">
                                    <div>                               
                                        <div class="text-center">
                                            <a href="proveedorController?op=listar" class="small-box-footer text-decoration-none text-dark">VER DETALLES </a>       
                                        </div></div></div></div>
                            <div class="col-3 m-0 justify-content-center p-2">
                                <img src="Sets/Imagenes/empleados.png" alt=""/>
                            </div></div>
                    </div>
                    <div class="trec col-md-4 mb-3">
                        <div class="row bg-success">
                            
                            <div class="col-5 m-0 justify-content-center p-2 rounded-top">
                                <h1 class="text-center text-white h1"></h1> 
                                <p class="let text-center text-white">PRODUCTOS PERECEDEROS</p>
                                <div class="">
                                    <div>                               
                                        <div class="text-center">
                                            <a href="LoteController?op=listar" class="small-box-footer text-decoration-none text-dark">VER DETALLES </a>       
                                        </div></div></div></div>
                            <div class="col-3 m-0 justify-content-center p-2">
                                <img src="Sets/Imagenes/Cajas.png" alt=""/>
                            </div></div>
                    </div>
                    
                    <div class="trec col-md-4 mb-3">
                        <div class="row bg-info">
                            
                            <div class="col-5 m-0 justify-content-center p-2 rounded-top">
                                <h1 class="text-center text-white h1"></h1> 
                                <p class="let text-center text-white">MARCAS PRODUCTOS </p>
                                <div class="">
                                    <div>                               
                                        <div class="text-center">
                                            <a href="LoteController?op=listar" class="small-box-footer text-decoration-none text-dark">VER DETALLES </a>       
                                        </div></div></div></div>
                            <div class="col-3 m-0 justify-content-center p-2">
                                
                                <img src="Sets/Imagenes/aplicacion.png" alt=""/>
                            </div></div>
                    </div>
                    
                    <div class="trec col-md-4 mb-3">
                        <div class="row bg-info">
                            
                            <div class="col-5 m-0 justify-content-center p-2 rounded-top">
                                <h1 class="text-center text-white h1"></h1> 
                                <p class="let text-center text-white">COTIZACION </p>
                                <div class="">
                                    <div>                               
                                        <div class="text-center">
                                            <a href="cotizacionController?op=listar" class="small-box-footer text-decoration-none text-dark">VER DETALLES </a>       
                                        </div></div></div></div>
                            <div class="col-3 m-0 justify-content-center p-2">
                                
                                <img src="Sets/Imagenes/7368673.png" alt=""/>
                            </div></div>
                    </div>
                    
                    <div class="trec col-md-4 mb-3">
                        <div class="row bg-danger">
                            
                            <div class="col-5 m-0 justify-content-center p-2 rounded-top">
                                <h1 class="text-center text-white h1"></h1> 
                                <p class="let text-center text-white">ALMACEN</p>
                                <div class="">
                                    <div>                               
                                        <div class="text-center">
                                            <a href="ProductoController?op=listar" class="small-box-footer text-decoration-none text-dark">VER DETALLES </a>       
                                        </div></div></div></div>
                            <div class="col-3 m-0 justify-content-center p-2">
                                <img src="Sets/Imagenes/4365341.png" alt=""/>
                            </div></div>
                    </div>
                    
                    <div class="trec col-md-4 mb-3">
                        <div class="row bg-primary">
                            
                            <div class="col-5 m-0 justify-content-center p-2 rounded-top">
                                <h1 class="text-center text-white h1"></h1> 
                                <p class="let text-center text-white">VENTAS</p>
                                <div class="">
                                    <div>                               
                                        <div class="text-center">
                                            <a href="ventaController?op=listar" class="small-box-footer text-decoration-none text-dark">VER DETALLES </a>       
                                        </div></div></div></div>
                            <div class="col-3 m-0 justify-content-center p-2">
                                <img src="Sets/Imagenes/dinero.png" alt=""/>
                            </div></div>
                    </div>


                <div class="row">
                    <div class="col-md-12 mb-3">
                        <div class="card">
                            
                                 
                                     
                            <div class="card-header">
                                <h4>GRAFICOS ESTADISTICOS</h4>
                            </div>
                            <div class="card-body">
                                <div class="container-fluid">
                                    <div class="row">
                                         &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                                        <div class="col-md-4 mb-3">
                                            <div class="card border-primary text-white ">
                                                <div class="card-body py-3 border-primary"><img src="Sets/Imagenes/grafi_1.png" class="img-fluid"/></div>
                                                <div class="card-footer d-flex border-primary">
                                                    <a href="grafi_circular.jsp" class="nav-link link-dark"> ver detalles grafica </a>
                                                </div>
                                            </div>
                                        </div>
                                        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
                                        <div class="col-md-4 mb-3">
                                            <div class="card border-primary text-white ">
                                                <div class=" card-body py-3 border-primary"><div class="center"><img src="Sets/Imagenes/grafi_barras.png" class="img-fluid" width="300"/></div></div>
                                                <div class="card-footer d-flex border-primary">
                                                    <a href="grafi_barras.jsp" class="nav-link link-dark"> ver detalles grafica </a>
                                                </div>
                                            </div>
                                        </div>
                                     
                                        </div>

                                    </div>             
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </body>
</html>
