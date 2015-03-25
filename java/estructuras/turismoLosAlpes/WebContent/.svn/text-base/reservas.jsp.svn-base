<%@page import="java.util.ArrayList"%>
<%@page import="logicaNegocio.TurismoAlpes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String rutaS = "C:/Users/STEVENSON EL REY/Documents/Eclipses/eclipseDatos/jboss-4.0.5.GA/server/default/deploy";
String rutaC = "/home/cvargasc/Desarrollo/software/jboss-4.2.2.GA/server/default/deploy";
TurismoAlpes mundo = TurismoAlpes.darInstancia();
mundo.inicializarRuta(rutaS);

ArrayList<String[]> reservas = mundo.consultarReservas();
String resultado = null;
if (request.getParameter("accion") != null && request.getParameter("accion").equals("cancelar")) {
	mundo.cancelarReserva(request.getParameter("idReserva"));
	resultado = "Se ha cancelado la reserva "+request.getParameter("idReserva");
}
if (request.getParameter("accion") != null && request.getParameter("accion").equals("pagar")) {
	mundo.comprarVuelo(request.getParameter("idReserva"), "93052308842");
	resultado = "Se ha pagado la reserva "+ request.getParameter("idReserva");
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TDLA - Reservas</title>
<style type="text/css">
	body {
		font-family: Arial, Helvetica, sans-serif;
	}
	#vuelos {
		width:90%;
		border-collapse:collapse;
	}
	#vuelos td, #vuelos th	{
	font-size:1em;
	border:1px solid #494949;
	padding:3px 7px 2px 7px;
	}
	#vuelos th {
	font-size:1.1em;
	text-align:left;
	padding-top:5px;
	padding-bottom:4px;
	background-color:#797373;
	color:#ffffff;
	}
	#vuelos tr.alt td {
	color:#000000;
	background-color:#ECECEC;
	}
</style>
</head>
<body>
	<%if (reservas != null && reservas.size()>0) {
		boolean alt = false;%>
	<BR />
	<table id="vuelos">
	<tr>
		<Th>Reserva Número</Th>
		<Th>origen</Th>
		<Th>Destino</Th>
		<Th>Fecha</Th>
		<Th>Posiciones</Th>
		<Th>Acciones</Th>
	</tr>
	<% 	for (String[] s : reservas) {%>
	<TR <% if(alt) {%> class="alt" <%} alt = !alt; %>>
		<TD><%= s[0]%></TD>
		<TD><%= s[1]%></TD>
		<TD><%= s[2]%></TD>
		<TD><%= s[3]%></TD>
		<TD><%= s[4]%></TD>
		<TD><form action="reservas.jsp">
			<input type="hidden" name="accion" value="pagar"/>
			<input type="hidden" name="idReserva" value="<%= s[0]%>"/>
			<input type="submit" value="Pagar"/>
			</form>
			<form action="reservas.jsp">
			<input type="hidden" name="accion" value="cancelar"/>
			<input type="hidden" name="idReserva" value="<%= s[0]%>"/>
			<input type="submit" value="Cancelar"/>
			</form>
		</TD>
	</TR>
	<%	} %>
	</TABLE>
	<BR />

	<BR />
	<%} else {%>
		No hay reservas registradas para el usuario
	<% } %>
</body>
</html>