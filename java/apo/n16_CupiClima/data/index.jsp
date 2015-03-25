<%@page import="uniandes.cupi2.cupiClima.mundo.CupiClima"%>
<%@page import="uniandes.cupi2.cupiClima.mundo.ICupiClima"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	ICupiClima mundo = CupiClima.getInstance();
	String criterioBusqueda = request.getParameter("nombreCiudad");
	String[][] resultados = new String[1][1];
	if (criterioBusqueda != null && criterioBusqueda.length() >= 3) {
		resultados = mundo.buscarCiudad(criterioBusqueda);
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	body {
		font-family: sans-serif;
	}
	#divLogin {
		text-align: right;
	}
	#divBusqueda {
		text-align: center;
		position: absolute;
		left: 50%;
		top: 50%;
		width: 650px;
		height: 200px;
		margin: -100px 0 0 -320px;
	}
	#cuadroBusqueda {
		width: 550px;
	}
</style>
<script type="text/javascript">
function cleanUsuario() {
	document.getElementById("campoUsuario").setAttribute("value","");
	
}
function cleanContrasena() {
	document.getElementById("campoContrasena").setAttribute("value","");
	document.getElementById("campoContrasena").setAttribute("type","password");
}
</script>
<title>CupiClima</title>
</head>
<body>
	<div id="divLogin">
		<form name="login" action="administrador.jsp" method="post">
			<input id="campoUsuario" type="text" name="usuario" value="usuario" onfocus="cleanUsuario()">
			<input id="campoContrasena" type="text" name="contrasena" value="contraseña" onfocus="cleanContrasena()">
			<a href="javascript:document.login.submit()">login</a>
		</form>
	</div>
	<div id="divBusqueda">
		<img alt="Logo Cupidos" src="./imagenes/logoCUPI2Grande.jpg" width="200">
		<form name="buscar" action="" method="get">
			<input id="cuadroBusqueda" type="text" name="nombreCiudad">
			<input type="submit" value="Buscar">
		</form>
		<% 	if (criterioBusqueda != null) { %>
		<table style="text-align: left;">
		<%		for (String[] s : resultados) {%>
			<% 		if (s[0] != null) {%>
			<tr>
			<td>
				<a href="usuario.jsp?woeid=<%= s[0] %>"><%= s[2] %></a>
			</td>
			</tr>
			<% 		} %>
		<%		} %>
		</table>
		<%	} %>
	</div>
</body>
</html>