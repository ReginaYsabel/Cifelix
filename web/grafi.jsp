
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%
            Model model=(Model) application.getAttribute(model);
            if(model == null)
            {
                model= new Model();
                model.setDriver("com.mysql.jdbc.Driver");
                model.setURL("jdbc:mysql://localhost/ferreteria");
                model.setNombre("root");
                model.setClave("");
                
                model.conectar();
                application.setAttribute("model", model);
            }
        %>
    </head>
    <body>
        <jsp:include page="grafica.jsp"/>
    </body>
</html>
