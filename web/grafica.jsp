
<%@page import="Utils.ConDB"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="java.io.*"%>
<%@page import="java.sql.*"%>

<%@page import="org.jfree.chart.*"%>
<%@page import="org.jfree.chart.plot.*"%>
<%@page import="org.jfree.data.general.*"%>
<%@page import="org.jfree.data.category.DefaultCategoryDataset.* "%>

<%
   Connection cn = ConDB.getConnection();
   Statement cmd=cn.createStatement();
   String sql="SELECT M.marca,P.cantidad FROM productos P JOIN marca M ON P.id_Marca = M.id_Marca";
   ResultSet rs=cmd.executeQuery(sql);
%>
