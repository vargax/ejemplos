<%@page import="estructurasDatos.listas.IListaEncadenada"%>
<%@page import="mundo.AeroCupi"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	AeroCupi mundo = AeroCupi.getInstance();
	
	if(request.getParameter("nombreAeropuerto") != null) {
		mundo.agregarAeropuerto(request.getParameter("nombreAeropuerto"));
		System.out.println("Registrado el aeropuerto "+request.getParameter("nombreAeropuerto"));
	} else if(request.getParameter("aerolinea") != null) {
		String aerolinea = request.getParameter("aerolinea");
		String numero = request.getParameter("numero");
		String origen = request.getParameter("origen");
		String destino = request.getParameter("destino");
		String salida = request.getParameter("salida");
		String llegada = request.getParameter("llegada");
		String equipo = request.getParameter("equipo");
		String sillas = request.getParameter("sillas");
		boolean[] disponibilidad = new boolean[7];
		disponibilidad[0] = request.getParameter("lunes") != null;
		disponibilidad[1] = request.getParameter("martes") != null;
		disponibilidad[2] = request.getParameter("miercoles") != null;
		disponibilidad[3] = request.getParameter("jueves") != null;
		disponibilidad[4] = request.getParameter("viernes") != null;
		disponibilidad[5] = request.getParameter("sabado") != null;
		disponibilidad[6] = request.getParameter("domingo") != null;
		
		mundo.registrarVuelo(aerolinea, numero, origen, destino, salida, llegada, equipo, sillas, disponibilidad);
		
		System.out.println("Registrado el vuelo "+numero+" de "+aerolinea);
		mundo.refrescarFuentesSumideros();
	}
	IListaEncadenada<String> aeropuertos = mundo.darAeropuertos();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
	body {
		font-family: Arial, Helvetica, sans-serif;
	}
</style>
<title>AeroCupidos - Configurar</title>
</head>
<body>
	<a href="index.jsp">Home</a>
	<br />
	Registar nuevo Aeropuerto:
	<br />
	<form action="agregar.jsp">
		Nombre Aeropuerto: <input type="text" name="nombreAeropuerto"><input type="submit" value="Registrar">
	</form>
	<br />
	<br />
	Registrar nuevo Vuelo:
	<br>
	<form action="agregar.jsp">
		<table>
			<tr>
				<td>Aerolinea:</td><td><input type="text" name="aerolinea"></td>
			</tr>
			<tr>
				<td>Número:</td><td><input type="text" name="numero"></td>
			</tr>
			<tr>
				<td>Origen:</td>
				<td>
					<select style="width: 60%" name="origen">
						<% 	for(String s : aeropuertos) {%>
							<option value="<%= s %>"><%= s %></option>
						<%	} %>
					</select>
				</td>
			</tr>
			<tr>
				<td>Destino:</td>
				<td>
					<select style="width: 60%" name="destino">
						<% 	for(String s : aeropuertos) {%>
							<option value="<%= s %>"><%= s %></option>
						<%	} %>
					</select>
				</td>
			</tr>
			<tr>
				<td>Salida:</td><td><input type="text" name="salida"></td>
			</tr>
			<tr>
				<td>Llegada:</td><td><input type="text" name="llegada"></td>
			</tr>
			<tr>
				<td>Equipo:</td><td><input type="text" name="equipo"></td>
			</tr>
			<tr>
				<td>Sillas:</td><td><input type="text" name="sillas"></td>
			</tr>
			<tr>
				<td>Disponibilidad</td>
				<td>
					<input type="checkbox" name="lunes" value="x"/> L
					<input type="checkbox" name="martes" value="x"/> M
					<input type="checkbox" name="miercoles" value="x"/> I
					<input type="checkbox" name="jueves" value="x"/>J
					<input type="checkbox" name="viernes" value="x"/>V
					<input type="checkbox" name="sábado" value="x"/>S
					<input type="checkbox" name="domingo" value="x"/>D
				</td>
			</tr>
			<tr>
				<td></td><td align="right"><input type="submit" value="Registrar"></td>
			</tr>
		</table>
	</form>
</body>
</html>