<%@page import="java.util.ArrayList"%>
<%@page import="logicaNegocio.TurismoAlpes"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String rutaS = "C:/Users/STEVENSON EL REY/Documents/Eclipses/eclipseDatos/jboss-4.0.5.GA/server/default/deploy";
	String rutaC = "/home/cvargasc/Desarrollo/software/jboss-4.2.2.GA/server/default/deploy";
	
	TurismoAlpes mundo = TurismoAlpes.darInstancia();
	mundo.inicializarRuta(rutaS);
	
	ArrayList<String> ciudades = mundo.consultarCiudades();
	
	ArrayList<String[]> itinerario = null;
	
	String origen =""; String fecha=""; String destino="";
	if(request.getParameter("origen") != null) {
		
		mundo.inicializarUsuario(request.getParameter("nombreUsuario"), request.getParameter("identificacionUsuario"));
		
		origen = request.getParameter("origen");
		fecha = request.getParameter("fecha");
		//hora = request.getParameter("hora");
		// String opcion = request.getParameter("opciones");
		destino = request.getParameter("destino");
		System.out.println("+++++++++++++++++++++++ Buscando ruta de "+origen+" a "+destino);
		itinerario = mundo.consultarVuelos(origen, destino, fecha);
		System.out.println("+++++++++++++++++++++++ Encontrados "+itinerario.size()+" vuelos");
	}
	
	String numReserva = null;
	if(request.getParameter("sillasReservar") != null) {
		numReserva = mundo.reservarVuelo(Integer.parseInt(request.getParameter("idVuelo")), Integer.parseInt(request.getParameter("sillasReservar")));
	}	
	
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 3.2//EN">

<HTML>
<HEAD>
	
	<META HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=utf-8">
	<TITLE>TDLA - Sistema de Información de Vuelos</TITLE>
	<META NAME="GENERATOR" CONTENT="LibreOffice 3.5  (Linux)">
	<META NAME="AUTHOR" CONTENT="Camilo Vargas">
	<META NAME="CREATED" CONTENT="20120519;12234300">
	<META NAME="CHANGEDBY" CONTENT="Camilo Vargas">
	<META NAME="CHANGED" CONTENT="20120519;13041600">
	
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
	
</HEAD>

<BODY TEXT="#000000">
<form action="index.jsp">
<TABLE CELLSPACING="0" COLS="8" BORDER="0">
	<COLGROUP WIDTH="30"></COLGROUP>
	<COLGROUP WIDTH="85"></COLGROUP>
	<COLGROUP WIDTH="21"></COLGROUP>
	<COLGROUP WIDTH="72"></COLGROUP>
	<COLGROUP SPAN="3" WIDTH="85"></COLGROUP>
	<COLGROUP WIDTH="169"></COLGROUP>
	<TR>
		<TD COLSPAN=2 ROWSPAN=5 HEIGHT="84" ALIGN="CENTER" VALIGN=MIDDLE>
			<img alt="Logo Cupidos" src="./imagenes/tdla.png" width="150">
		</TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="RIGHT"><a href="reservas.jsp">Reservas</a></TD>
	</TR>
	<TR>
		<TD ALIGN="LEFT"><BR></TD>
		<TD COLSPAN=4 ROWSPAN=2 ALIGN="LEFT" VALIGN=MIDDLE><B><FONT SIZE=4>Turismo de los Alpes</FONT></B></TD>
		<TD ALIGN="LEFT"><BR></TD>
	</TR>
	<TR>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
	</TR>
	<TR>
		<TD ALIGN="LEFT"><BR></TD>
		<TD COLSPAN=4 ALIGN="LEFT" VALIGN=MIDDLE><FONT SIZE=3>Sistema de Información de Vuelos</FONT></TD>
		<TD ALIGN="LEFT"><BR></TD>
	</TR>
	<TR>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
	</TR>
	<TR>
		<TD HEIGHT="17" ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
	</TR>
	<TR>
		<TD HEIGHT="18" ALIGN="LEFT"><BR></TD>
		<TD COLSPAN=3 ALIGN="RIGHT" VALIGN=MIDDLE>Aeropuerto Origen:</TD>
		<TD COLSPAN=4 ALIGN="CENTER" VALIGN=MIDDLE>
			<select style="width: 95%" name="origen">
						<% 	for(String s : ciudades) {%>
							<option value="<%= s %>"><%= s %></option>
						<%	} %>
			</select>
		</TD>
		</TR>
	<TR>
		<TD HEIGHT="10" ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
	</TR>
	<TR>
		<TD HEIGHT="18" ALIGN="LEFT"><BR></TD>
		<TD COLSPAN=3 ALIGN="RIGHT" VALIGN=MIDDLE>Fecha Salida:</TD>
		<TD COLSPAN=4 ALIGN="CENTER" VALIGN=MIDDLE >
		<input type="text" name="fecha" style="width: 95%">
			<!-- <select style="width: 65%" name="dia">
				<option value="lunes">Lunes</option>
				<option value="martes">Martes</option>
				<option value="miercoles">Miércoles</option>
				<option value="jueves">Jueves</option>
				<option value="viernes">Viernes</option>
				<option value="sabado">Sábado</option>
				<option value="domingo">Domingo</option>
			</select>
			<select style="width: 30%" name="hora">
			<% 	for(int i = 0;  i <= 23; i++) {%>
				<% 	for(int j = 0;  j < 6; j++) {
					String horaP = i < 10 ? "0"+i : ""+i;
					String minuto = j == 0 ? ":0"+j : ":"+(j*10);
				%>
					<option value="<%= horaP+minuto %>"><%= horaP+minuto %></option>
				<%	} %>
			<%	} %>
			</select> -->
		</TD>
		</TR>
	<TR>
		<TD HEIGHT="10" ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
	</TR>
	<TR>
		<TD HEIGHT="18" ALIGN="LEFT"><BR></TD>
		<TD COLSPAN=3 ALIGN="RIGHT" VALIGN=MIDDLE>Nombre Usuario:</TD>
		<TD COLSPAN=4 ALIGN="LEFT" VALIGN=MIDDLE >
			<input type="text" name="nombreUsuario"> 
		</TD>
	</TR>
	<TR>
		<TD HEIGHT="16" ALIGN="LEFT"><BR></TD>
		<TD COLSPAN=3 ALIGN="RIGHT" VALIGN=MIDDLE>Identificación:</TD>
		<TD COLSPAN=4 ALIGN="LEFT" VALIGN=MIDDLE >
			<input type="text" name="identificacionUsuario">
		</TD>
		</TR>
	<TR>
		<TD HEIGHT="16" ALIGN="LEFT"><BR></TD>
		<TD COLSPAN=3 ALIGN="RIGHT" VALIGN=MIDDLE>Número Reserva:</TD>
		<TD COLSPAN=4 ALIGN="LEFT" VALIGN=MIDDLE >
			<%= (numReserva != null ? numReserva : "Ninguna Reserva") %>
		</TD>
		</TR>
	<TR>
		<TD HEIGHT="10" ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
	</TR>
	<TR>
		<TD HEIGHT="18" ALIGN="LEFT"><BR></TD>
		<TD COLSPAN=3 ALIGN="RIGHT" VALIGN=MIDDLE>Aeropuerto Destino:</TD>
		<TD COLSPAN=4 ALIGN="CENTER" VALIGN=MIDDLE >
			<select style="width: 95%" name="destino">
						<% 	for(String s : ciudades) {%>
							<option value="<%= s %>"><%= s %></option>
						<%	} %>
			</select>
		</TD>
		</TR>
	<TR>
		<TD HEIGHT="17" ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="RIGHT"><input type="submit" value="Buscar"></TD>
	</TR>
	</table>
	</form>
	<BR />
	<%if (itinerario != null && itinerario.size()>0) {
		boolean alt = false;%>
	Itinerario desde <%= origen %> hasta <%= destino %> partiendo el <%= fecha %><BR />
	<BR />
	<table id="vuelos">
	<tr>
		<Th>Origen</Th>
		<Th>Destino</Th>
		<Th>Fecha</Th>
		<Th>Sillas Disponibles</Th>
		<Th>Sillas Totales</Th>
		<Th>Reservar</Th>
	</tr>
	<% 	for (String[] s : itinerario) {%>
	<TR <% if(alt) {%> class="alt" <%} alt = !alt; %>>
		<TD><%= s[1]%></TD>
		<TD><%= s[2]%></TD>
		<TD><%= s[3]%></TD>
		<TD><%= s[4]%></TD>
		<TD><%= s[5]%></TD>
		<TD><form action="index.jsp">
			<input type="hidden" name="idVuelo" value="<%= s[0]%>"/>
			<input type="text" name="sillasReservar">
			<input type="submit" value="Reservar"/>
			</form>
		</TD>
	</TR>
	<%	} %>
	</TABLE>
	<BR />

	<BR />
	<%} %>
<!-- ************************************************************************** -->
</BODY>
</HTML>
