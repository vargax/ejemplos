<%@page import="estructurasDatos.listas.IListaEncadenada"%>
<%@page import="mundo.AeroCupi"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	AeroCupi mundo = AeroCupi.getInstance();
	IListaEncadenada<String> noFuentes = mundo.darNoFuentes();
	IListaEncadenada<String> noSumideros = mundo.darNoSumideros();
	
	String origen =""; String dia=""; String hora=""; String destino="";
	
	IListaEncadenada<String[]> itinerario = null;
	String[] duraciones = null;
	if(request.getParameter("origen") != null) {
		origen = request.getParameter("origen");
		dia = request.getParameter("dia");
		hora = request.getParameter("hora");
		String opcion = request.getParameter("opciones");
		destino = request.getParameter("destino");
		System.out.println("+++++++++++++++++++++++ Buscando ruta mínima de "+origen+" a "+destino);
		itinerario = mundo.darRutaMinima(origen, destino, dia, hora);
		duraciones = mundo.darDuraciones();
	}
	
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 3.2//EN">

<HTML>
<HEAD>
	
	<META HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=utf-8">
	<TITLE>AeroCupidos - Sistema de Información de Vuelos</TITLE>
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
			<img alt="Logo Cupidos" src="./logoCUPI2Grande.jpg" width="150">
		</TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="RIGHT"><a href="agregar.jsp">setup</a></TD>
	</TR>
	<TR>
		<TD ALIGN="LEFT"><BR></TD>
		<TD COLSPAN=4 ROWSPAN=2 ALIGN="LEFT" VALIGN=MIDDLE><B><FONT SIZE=4>AeroCupidos</FONT></B></TD>
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
						<% 	for(String s : noSumideros) {%>
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
			<select style="width: 65%" name="dia">
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
		<TD COLSPAN=3 ALIGN="RIGHT" VALIGN=MIDDLE>Opciones:</TD>
		<TD COLSPAN=4 ALIGN="LEFT" VALIGN=MIDDLE >
			<input type="radio" name="opciones" value="rf4">Ruta Directa (RF4)
		</TD>
		</TR>
	<TR>
		<TD HEIGHT="16" ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD COLSPAN=4 ALIGN="LEFT" VALIGN=MIDDLE >
			<input type="radio" name="opciones" value="rf5">Recorrer ciudades (RF5)
		</TD>
		</TR>
	<TR>
		<TD HEIGHT="16" ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD ALIGN="LEFT"><BR></TD>
		<TD COLSPAN=4 ALIGN="LEFT" VALIGN=MIDDLE >
			<input type="radio" name="opciones" value="rf6">Ciclo Simple (RF6)
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
						<% 	for(String s : noFuentes) {%>
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
	<%if (itinerario != null && itinerario.darNumeroObjetos()>0) {
		boolean alt = false;%>
	Itinerario desde <%= origen %> hasta <%= destino %> partiendo el <%= dia %> a las <%= hora %><BR />
	<BR />
	<table id="vuelos">
	<tr>
		<Th>Vuelo</Th>
		<Th>Aerolínea</Th>
		<Th>Origen</Th>
		<Th>Destino</Th>
	</tr>
	<% 	for (String[] s : itinerario) {%>
	<TR <% if(alt) {%> class="alt" <%} alt = !alt; %>>
		<TD><%= s[0]%></TD>
		<TD><%= s[1]%></TD>
		<TD><%= s[2]%></TD>
		<TD><%= s[3]%></TD>
	</TR>
	<%	} %>
	</TABLE>
	<BR />
	Duración estimada: <%=  duraciones[2] %> minutos (En vuelo: <%= duraciones[0] %> En espera: <%= duraciones[1] %>)
	<BR />
	<%} %>
<!-- ************************************************************************** -->
</BODY>

</HTML>