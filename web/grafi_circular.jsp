<%-- 
    Document   : grafi
    Created on : 24/07/2022, 01:18:38 PM
    Author     : AMVM_
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="grafica.jsp" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        
        <%
          try{ 
              DefaultPieDataset data=new DefaultPieDataset();
               while(rs.next()){
                   data.setValue(rs.getString(1),rs.getInt(2));
               }
              JFreeChart grafico=ChartFactory.createPieChart("Cantidad-Marca", data, true,true,true);
              
              response.setContentType("image/png");
              OutputStream sa=response.getOutputStream();
              ChartUtilities.writeChartAsPNG(sa, grafico, 600, 600 );
              
              }catch(Exception ex){
                  out.print(ex);
          }
        %>
        <footer><button class="bg-danger"><a href="Index.jsp" class="nav-link link-light">Regresar</a></button></footer>
    </body>
</html>
