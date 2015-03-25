<%@page import="logicaNegocio.TurismoAlpes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
if(request.getParameter("nombreUsuario") != null && request.getParameter("identificacionUsuario") != null) {
	
	String rutaS = "C:/Users/STEVENSON EL REY/Documents/Eclipses/eclipseDatos/jboss-4.0.5.GA/server/default/deploy";
	String rutaC = "/home/cvargasc/Desarrollo/software/jboss-4.2.2.GA/server/default/deploy";	
	
	TurismoAlpes mundo = TurismoAlpes.darInstancia();
	mundo.inicializarRuta(rutaS);
	
	mundo.inicializarUsuario(request.getParameter("nombreUsuario"), request.getParameter("identificacionUsuario"));
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TDLA - Registro</title>
</head>
<body>
<form action="registrar.jsp">
<table>
	<tr>
		<td>
		Nombre:
		</td>
		<td>
			<input type="text" name="nombreUsuario">
		</td>
	</tr>
	<tr>
		<td>
		Identificación:
		</td>
		<td>
			<input type="text" name="identificacionUsuario">
		</td>
	</tr>
	<tr>
		<td>
			<input type="submit" value="Registrar">
		</td>
	</tr>
</table>
</form>
</body>
</html>