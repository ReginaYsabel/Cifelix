<%-- 
    Document   : grafi_barras
    Created on : 24/07/2022, 04:00:10 PM
    Author     : AMVM_
--%>

<%@page import="org.jfree.data.category.DefaultCategoryDataset"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="grafica.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
         <%
          try{ 
              DefaultCategoryDataset data=new DefaultCategoryDataset();
               while(rs.next()){
                   data.setValue( rs.getInt(2),rs.getString(1),rs.getString(1)+" "+rs.getInt(2));
               }
              JFreeChart grafico=ChartFactory.createBarChart("Grafico de Barras", "Marca", "Cantidad",data, PlotOrientation.VERTICAL,true,true,true);
              
              response.setContentType("image/png");
              OutputStream sa=response.getOutputStream();
              ChartUtilities.writeChartAsPNG(sa, grafico, 1000, 700 );
              
              }catch(Exception ex){
                  out.print(ex);
          }
        %>
    </body>
</html>
