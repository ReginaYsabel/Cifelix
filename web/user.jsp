<%@page  session="true" %>
<%
    String usuario = "";
    String perfil = "";

    HttpSession sesionOK = request.getSession();
    if (sesionOK.getAttribute("usuario") == null) {
%>        
<jsp:forward page="Login.jsp">
    <jsp:param name="msg2" value="Iniciar sesion obligatoriamente"/>
</jsp:forward>
<%  } else {
        usuario = (String) sesionOK.getAttribute("usuario");
        perfil = (String) sesionOK.getAttribute("perfil");
    }
%>