<%@page import="estructurasDatos.IListaEncadenada"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page import="org.apache.commons.fileupload.FileItemFactory"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="uniandes.cupi2.cupiClima.mundo.CupiClima"%>
<%@page import="uniandes.cupi2.cupiClima.mundo.ICupiClima"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String usuario = request.getParameter("usuario");
String contrasena = request.getParameter("contrasena");
if(usuario != null && !usuario.equals("admin") && contrasena != null && !contrasena.equals("admin")) { 
	response.sendRedirect("index.jsp");
}
	
	ICupiClima mundo = CupiClima.getInstance();
	IListaEncadenada<String[]> anuncios = mundo.darAnuncios();
	System.out.println("JSP: Se han recuperado "+anuncios.darNumeroObjetos()+" anuncios para desplegar");
	IListaEncadenada<String> ciudades = mundo.darCiudades();

	if (ServletFileUpload.isMultipartContent(request)) {
		System.out.println("JSP: Detectado el registro de un anuncio...");
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> items = upload.parseRequest(request);
		
		String woeid = "";
		String texto = "";
		String link = "";
		String rutaImagen = "";
		for (FileItem f : items) {
			if (f.isFormField()) {
				if (f.getFieldName().equals("textoAnuncio")) texto = f.getString();
				else if (f.getFieldName().equals("ciudad"))	woeid = f.getString();
				else if (f.getFieldName().equals("linkAnuncio")) link = f.getString();
			} else {
				rutaImagen = f.getName();
//				rutaImagen = getServletContext().getRealPath(f.getName());
//				System.out.println(rutaImagen);
				File uploadedFile = new File(getServletContext().getRealPath(rutaImagen));
				f.write(uploadedFile);
			}
		}
		mundo.registrarAnuncio(woeid, texto, link, rutaImagen);
		response.sendRedirect("administrador.jsp");
} 
	
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Administración CupiClima</title>
	<style type="text/css">
		body {
			font-family: sans-serif;
		}
		#titulo {
			float: left;
		}
		#logout {
			text-align: right;
		}
		#cuerpo {
			text-align: center;
		}
		#agregar {
			text-align: center;
		}
		#agregar.td {
			border: 0px;
		}
		form {
			text-align: center;
		}
		table {
			text-align: left;
			width: 90%;
			border: 2px solid gray;
		}
		td {
			border: 1px solid gray;
		}
		input {
			width: 90%
		}
		select {
			width: 90%	
		}
	</style>
</head>
<body>
	<div id="encabezado">
		<div id="titulo">
			Administración CupiClima
		</div>
		<div id="logout">
			<a href="index.jsp">logout</a>
		</div>
	</div>
	<div id="cuerpo">
	<br>
		<form method="post" enctype="multipart/form-data" action="">
			<table id="agregar">
				<tr>
				<td>
					<input type="text" name="textoAnuncio" value="Texto Anuncio">
				</td>
				<td>
					<input type="text" name="linkAnuncio" value="Link Anuncio">
				</td>
				<td>
					<select name="ciudad">
						<% 	for(String s : ciudades) {
								String[] tmp = s.split(" "); 
						%>
							<option value="<%= tmp[tmp.length-1]%>"><%= s %></option>
						<%	} %>
					</select>
				</td>
				<td>
					<input type="file" name="imagenAnuncio">
				</td>
				<td>
					<input type="submit" value="Agregar">
				</td>
				</tr>
			</table>
		</form>
		<br>
		<% 	if(anuncios.darNumeroObjetos() > 0) {%>
		<table>
		<tr>
			<th>Texto Anuncio</th>
			<th>Ciudad</th>
			<th>Despliege</th>
		</tr>
		<%		for(String[] s : anuncios) {%>
			<tr>
				<td><%= s[0] %></td>
				<td><%= s[1] %></td>
				<td><%= s[2] %></td>
			</tr>
		<%		} %>			
		</table>
		<%	} %>
	</div>
</body>
</html>