
<%@page import="javax.enterprise.inject.Model"%>
<%@page import="com.mysql.jdbc.Util"%>
<%@page import="org.jfree.data.category.DefaultCategoryDataset"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="org.Model"%>
<%@page import="org.Util"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.io.File"%>

<%@page import="org.jfree.chart.ChartFactory"  %>
<%@page import="org.jfree.chart.ChartUtilities"%>
<%@page import="org.jfree.chart.JFreeChart"%>
<%@page import="org.jfree.chart.plot.PlotOrientation"%>
<%@page import="org.jfree.data.category.DefaultIntervalCategoryDataset"%>

<%
    /*rescatamosnel modelo de conexion*/
    Model model=(Model)application.getAttribute("model");
    
    /*conseguimos lo datos de la bd*/
    HashMap mapa=model.consulaProductos();
    
    /*los convertimos en dataset*/
    DefaultCategoryDataset dataset= Util.convertirHashMapDataset(mapa,"total de alumno");
    
    /*Creamos el grafico*/
    JFreeChart chart= ChartFactory.createBarChart("Alumnos pormodulo", "tipos de modulo", "alumnos mujeres y hombres", dataset, PlotOrientation.VERTICAL,true, tooltips, urls);
    /*lo duardamos en un ubicacion en especifica por el objeto file*/
    
%>
