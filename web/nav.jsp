<script src="https://kit.fontawesome.com/9bdc09ed99.js" crossorigin="anonymous"></script>
<link href="Sets/CSS/Estilos.css" rel="stylesheet" type="text/css"/>
 <script src="https://kit.fontawesome.com/9bdc09ed99.js" crossorigin="anonymous"></script>
<nav class="cabe navbar navbar-expand-lg navbar-dark bg-primary fixed-top ">
    <div class="container-fluid">
        <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#sidebar" aria-controls="offcanvasExample" >
            <span class="navbar-toggler-icon" data-bs-target="#sidebar"></span>
        </button>
        <a class="navbar-brand me-auto ms-lg-0 ms-3 text-uppercase fw-bold"  href="#" >FERRETERIA LILY</a>

        <div class="collapse navbar-collapse">

            <form class="d-flex ms-auto my-3 my-lg-0">
                
            </form>
            
            <ul class="navbar-nav">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle ms-2" href="#" role="button" data-bs-toggle="dropdown" >
                        <i class="fa-solid fa-bell"></i> &nbsp;
                    </a>
                    <ul class="dropdown-menu dropdown-menu-end">
                        <%if(sesionOK.getAttribute("msgPerecedero")!=null){
                            String msgP=sesionOK.getAttribute("msgPerecedero").toString(); 
                        %>
                        <li><a class="dropdown-item" href="LoteController?op=listar"><%=msgP%></a></li>
                        <%
                            }
                            else{
                                %>
                                <li><a class="dropdown-item">No tiene nuevas notificaciones</a></li>
                                <%
                            }
                        %>
                    </ul>
                </li>
            </ul>
            <ul class="navbar-nav">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle ms-2" href="#" role="button" data-bs-toggle="dropdown" >
                        <i class="icon fas fa-user"></i> &nbsp;<%=usuario%>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-end">
                        <li><a class="dropdown-item" href="logiController?op=cerrar">Cerrar cesión</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="offcanvas offcanvas-start sidebar-nav bg-primary" tabindex="-1">
    <div class="offcanvas-body p-0">
        <nav class="navbar-dark">
            <ul class="navbar-nav">
                <li>
                    &nbsp;
                </li>
                <li>
                    <a href="Index.jsp" class="nav-link px-3 active">
                        <span class="me-2"><i class="bi bi-speedometer2"></i></span>
                        <span class="me-2"><i class="fa-solid fa-house-chimney "></i> &nbsp; INICIO </span>
                    </a>
                </li>
            </ul>
        </nav>   

        <div class="accordion accordion-flush" id="accordionFlushExample">
            <div class="accordion-item ">
                <h2 class="accordion-header" id="flush-headingOne">
                    <button class="menu accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseOne" aria-expanded="false" aria-controls="flush-collapseOne">
                        <i class="icon fa-solid fa-warehouse"></i> &nbsp; ALMACEN
                    </button>
                </h2>
                <div id="flush-collapseOne" class="accordion-collapse collapse" aria-labelledby="flush-headingOne" data-bs-parent="#accordionFlushExample">
                    <div class="accordion-body">
                        <ul>
                            <li class="lista"> <a href="ProductoController?op=listar" class="link-dark" style="text-decoration: none">Productos</a></li>
                            <li class="lista"> <a href="CategoriaController?op=listar" class="link-dark" style="text-decoration: none">Categorias</a></li>
                            <li class="lista"> <a href="MarcasController?op=listar" class="link-dark" style="text-decoration: none">Marcas</a></li>

                        </ul>
                    </div>
                </div>
            </div>


            <div class="accordion-item">
                <h2 class="accordion-header" id="flush-headingTwo">
                    <button class="menu accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseTwo" aria-expanded="false" aria-controls="flush-collapseTwo">
                        <i class="icon fa-solid fa-money-check-dollar"></i> &nbsp; COTIZACION
                    </button>
                </h2>
                <div id="flush-collapseTwo" class="accordion-collapse collapse" aria-labelledby="flush-headingTwo" data-bs-parent="#accordionFlushExample">
                    <div class="accordion-body">
                        <ul>
                            <li class="lista"> <a class="nav-link link-dark" href="cotizacionController?op=listar">Generar cotizaion</a></li>
                            <li class="lista">Ver cotizaciones</li>
                        </ul>

                    </div>
                </div>
            </div>

            <div class="accordion-item">
                <h2 class="accordion-header" id="flush-headingThree">
                    <button class="menu accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseThree" aria-expanded="false" aria-controls="flush-collapseThree">
                        <span> <i class="icon fa-solid fa-tag"></i>&nbsp; VENTAS </span>
                    </button>
                </h2>
                <div id="flush-collapseThree" class="accordion-collapse collapse" aria-labelledby="flush-headingThree" data-bs-parent="#accordionFlushExample">
                    <div class="accordion-body">
                        <ul>
                            <li class="lista"><a class="nav-link link-dark" href="ventaController?op=listar">Realizar Ventas</a> </li>
                          
                            <li class="lista">ventas Realizadas</li>
                        </ul>

                    </div>
                </div>
            </div>



            <div class="accordion-item">
                <h2 class="accordion-header" id="flush-headingfive">
                    <button class="menu accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapsefive" aria-expanded="false" aria-controls="flush-collapsefive">
                        <i class="icon fa-solid fa-file-invoice-dollar"></i> &nbsp; REPORTES
                    </button>
                </h2>
                <div id="flush-collapsefive" class="accordion-collapse collapse" aria-labelledby="flush-headingfive" data-bs-parent="#accordionFlushExample">
                    <div class="accordion-body">
                        <ul>
                            <li class="lista"><a class="nav-link text-dark" href="InventarioController?op=listar">Reporte de Productos</a></li>
                            <li class="lista"> <a href="LoteController?op=listar" class="link-dark" style="text-decoration: none">Productos perecederos</a></li>

                        </ul>

                    </div>
                </div>
            </div>


            <div class="accordion-item">
                <h2 class="accordion-header" id="flush-headingsix">
                    <button class="menu accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapsesix" aria-expanded="false" aria-controls="flush-collapsesix">
                        <i class="icon fa-solid fa-arrows-down-to-people"></i> &nbsp; CONTACTOS
                    </button>
                </h2>
                <div id="flush-collapsesix" class="accordion-collapse collapse" aria-labelledby="flush-headingsix" data-bs-parent="#accordionFlushExample">
                    <div class="accordion-body">
                        <ul>
                            <li class="lista"><a class="nav-link text-dark" href="proveedorController?op=listar">Lista Proveedores</a></li>
                            <li class="lista"><a class="nav-link text-dark" href="empleadosController?op=listar"><i class="icon fa-solid fa-user-pen"></i> &nbsp; Trabajadores</a></li>
                        </ul>
                    </div>
                </div>
            </div>  



        </div>

    </div>
</div>
