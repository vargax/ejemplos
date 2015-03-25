<%@page import="uniandes.cupi2.cupiClima.mundo.CupiClima"%>
<%@page import="uniandes.cupi2.cupiClima.mundo.ICupiClima"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 	
	ICupiClima mundo = CupiClima.getInstance();
	String woeid = request.getParameter("woeid");
	String[] condiciones = mundo.darCondiciones(woeid);
	String[] temperaturas = condiciones[5].split(" ");
	String[][] fotos = new String[1][1];
	try {
		fotos = mundo.darFotosWoeid(woeid);
	} catch(Exception e) {
		e.printStackTrace();
		fotos = mundo.darFotos(condiciones[0]);
	}
	IListaEncadenada<String[]> calidas = mundo.darCiudadesTemperatura();
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 3.2//EN">


<%@page import="estructurasDatos.IListaEncadenada"%><HTML>
<HEAD>
	
	<META HTTP-EQUIV="CONTENT-TYPE" CONTENT="text/html; charset=utf-8">
	<TITLE>Pronostico para <%= condiciones[0] %> (<%= woeid %>)</TITLE>
	<META NAME="GENERATOR" CONTENT="LibreOffice 3.4  (Unix)">
	<META NAME="AUTHOR" CONTENT="Camilo Vargas">
	<META NAME="CREATED" CONTENT="20120416;11045300">
	<META NAME="CHANGED" CONTENT="0;0">
	
	<STYLE>
		<!-- 
		BODY,DIV,TABLE,THEAD,TBODY,TFOOT,TR,TH,TD,P { font-family:"Arial"; font-size:small }
		 -->
	</STYLE>
	
</HEAD>

<BODY TEXT="#000000">
<form name="buscar" action="index.jsp" method="get">
<TABLE FRAME=VOID CELLSPACING=0 COLS=14 RULES=NONE BORDER=0>
	<COLGROUP><COL WIDTH=86><COL WIDTH=86><COL WIDTH=10><COL WIDTH=86><COL WIDTH=86><COL WIDTH=12><COL WIDTH=86><COL WIDTH=86><COL WIDTH=11><COL WIDTH=86><COL WIDTH=86><COL WIDTH=11><COL WIDTH=86><COL WIDTH=86></COLGROUP>	
	<TBODY>
		<TR>
			<TD WIDTH=86 HEIGHT=17 ALIGN=LEFT>Condición:</TD>
			<TD WIDTH=86 ALIGN=CENTER VALIGN=MIDDLE BGCOLOR="#99CCFF"><%= condiciones[4] %></TD>
			<TD WIDTH=10 ALIGN=LEFT><BR></TD>
			<TD COLSPAN=2 ROWSPAN=6 WIDTH=171 ALIGN=CENTER VALIGN=MIDDLE BGCOLOR="#C0C0C0">
			<% if (calidas.darNumeroObjetos()>0) { %>
			Ciudades cálidas:<BR>
				<table>
				<% for (String[] s : calidas) {%>
					<tr>
						<td><%= s[0] %></td><td><%= s[1] %></td>
					</tr>
				<% } %>
				</table>
			<%} %>	
			</TD>
			<TD WIDTH=12 ALIGN=LEFT><BR></TD>
			<TD COLSPAN=7 WIDTH=451 ALIGN=CENTER VALIGN=MIDDLE BGCOLOR="#99CCFF">
				<input style="width: 90%" id="cuadroBusqueda" type="text" name="nombreCiudad">
			</TD>
			<TD WIDTH=86 ALIGN=RIGHT>
				<input type="submit" value="Buscar">
			</TD>
		</TR>
		<TR>
			<TD HEIGHT=10 ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
		</TR>
		<TR>
			<TD HEIGHT=17 ALIGN=LEFT>Temperatura:</TD>
			<TD ALIGN=CENTER VALIGN=MIDDLE BGCOLOR="#99CCFF"><%= temperaturas[0] %></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD COLSPAN=8 ROWSPAN=7 ALIGN=CENTER VALIGN=MIDDLE BGCOLOR="#C0C0C0">
				<a href="<%= condiciones[8]%>"><img alt="<%= condiciones[6] %>" src="<%= condiciones[7] %>"></a>
			</TD>
			</TR>
		<TR>
			<TD HEIGHT=10 ALIGN=LEFT>Pronóstico para <%= temperaturas[3] %></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			</TR>
		<TR>
			<TD HEIGHT=17 ALIGN=LEFT>Mínima:</TD>
			<TD ALIGN=CENTER VALIGN=MIDDLE><%= temperaturas[1] %></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			</TR>
		<TR>
			<TD HEIGHT=16 ALIGN=LEFT>Máxima</TD>
			<TD ALIGN=CENTER VALIGN=MIDDLE><%= temperaturas[2] %></TD>
			<TD ALIGN=JUSTIFY><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			</TR>
		<TR>
			<TD HEIGHT=10 ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			</TR>
		<TR>
			<TD COLSPAN=2 HEIGHT=17 ALIGN=LEFT>Fecha Recuperación:</TD>
			<TD COLSPAN=3 ALIGN=CENTER VALIGN=MIDDLE BGCOLOR="#99CCFF"><%= condiciones[2] %></TD>
			<TD ALIGN=LEFT><BR></TD>
			</TR>
		<TR>
			<TD COLSPAN=2 HEIGHT=17 ALIGN=LEFT>Fecha Pronostico:</TD>
			<TD COLSPAN=3 ALIGN=CENTER VALIGN=MIDDLE BGCOLOR="#99CCFF"><%= condiciones[3] %></TD>
			<TD ALIGN=LEFT><BR></TD>
			</TR>
		<TR>
			<TD HEIGHT=17 ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
		</TR>
		<TR>
			<TD COLSPAN=2 ROWSPAN=5 HEIGHT=85 ALIGN=CENTER VALIGN=MIDDLE BGCOLOR="#C0C0C0">
				<img alt="<%= fotos[0][0] %>" src="<%= fotos[0][1] %>" width="200"/>
			</TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD COLSPAN=2 ROWSPAN=5 ALIGN=CENTER VALIGN=MIDDLE BGCOLOR="#C0C0C0">
				<img alt="<%= fotos[1][0] %>" src="<%= fotos[1][1] %>" width="200"/>
			</TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD COLSPAN=2 ROWSPAN=5 ALIGN=CENTER VALIGN=MIDDLE BGCOLOR="#C0C0C0">
				<img alt="<%= fotos[2][0] %>" src="<%= fotos[2][1] %>" width="200"/>
			</TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD COLSPAN=2 ROWSPAN=5 ALIGN=CENTER VALIGN=MIDDLE BGCOLOR="#C0C0C0">
				<img alt="<%= fotos[3][0] %>" src="<%= fotos[3][1] %>" width="200"/>
			</TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD COLSPAN=2 ROWSPAN=5 ALIGN=CENTER VALIGN=MIDDLE BGCOLOR="#C0C0C0">
				<img alt="<%= fotos[4][0] %>" src="<%= fotos[4][1] %>" width="200"/>
			</TD>
			</TR>
		<TR>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			</TR>
		<TR>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			</TR>
		<TR>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			</TR>
		<TR>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			</TR>
		<TR>
			<TD HEIGHT=12 ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
		</TR>
		<TR>
			<TD COLSPAN=2 ROWSPAN=5 HEIGHT=85 ALIGN=CENTER VALIGN=MIDDLE BGCOLOR="#C0C0C0">
				<img alt="<%= fotos[5][0] %>" src="<%= fotos[5][1] %>" width="200"/>
			</TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD COLSPAN=2 ROWSPAN=5 ALIGN=CENTER VALIGN=MIDDLE BGCOLOR="#C0C0C0">
				<img alt="<%= fotos[6][0] %>" src="<%= fotos[6][1] %>" width="200"/>
			</TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD COLSPAN=2 ROWSPAN=5 ALIGN=CENTER VALIGN=MIDDLE BGCOLOR="#C0C0C0">
				<img alt="<%= fotos[7][0] %>" src="<%= fotos[7][1] %>" width="200"/>
			</TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD COLSPAN=2 ROWSPAN=5 ALIGN=CENTER VALIGN=MIDDLE BGCOLOR="#C0C0C0">
				<img alt="<%= fotos[8][0] %>" src="<%= fotos[8][1] %>" width="200"/>
			</TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD COLSPAN=2 ROWSPAN=5 ALIGN=CENTER VALIGN=MIDDLE BGCOLOR="#C0C0C0">
				<img alt="<%= fotos[9][0] %>" src="<%= fotos[9][1] %>" width="200"/>
			</TD>
			</TR>
		<TR>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			</TR>
		<TR>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			</TR>
		<TR>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			</TR>
		<TR>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			</TR>
		<TR>
			<TD HEIGHT=11 ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD ALIGN=LEFT><BR></TD>
		</TR>
		<TR>
			<TD COLSPAN=2 ROWSPAN=5 HEIGHT=85 ALIGN=CENTER VALIGN=MIDDLE BGCOLOR="#C0C0C0">
				<img alt="<%= fotos[10][0] %>" src="<%= fotos[10][1] %>" width="200"/>
			</TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD COLSPAN=2 ROWSPAN=5 ALIGN=CENTER VALIGN=MIDDLE BGCOLOR="#C0C0C0">
				<img alt="<%= fotos[11][0] %>" src="<%= fotos[11][1] %>" width="200"/>
			</TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD COLSPAN=2 ROWSPAN=5 ALIGN=CENTER VALIGN=MIDDLE BGCOLOR="#C0C0C0">
				<img alt="<%= fotos[12][0] %>" src="<%= fotos[12][1] %>" width="200"/>
			</TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD COLSPAN=2 ROWSPAN=5 ALIGN=CENTER VALIGN=MIDDLE BGCOLOR="#C0C0C0">
				<img alt="<%= fotos[13][0] %>" src="<%= fotos[13][1] %>" width="200"/>
			</TD>
			<TD ALIGN=LEFT><BR></TD>
			<TD COLSPAN=2 ROWSPAN=5 ALIGN=CENTER VALIGN=MIDDLE BGCOLOR="#C0C0C0">
				<img alt="<%= fotos[14][0] %>" src="<%= fotos[14][1] %>" width="200"/>
			</TD>
			</TR>
	</TBODY>
</TABLE>
</form>
<!-- ************************************************************************** -->
</BODY>

</HTML>