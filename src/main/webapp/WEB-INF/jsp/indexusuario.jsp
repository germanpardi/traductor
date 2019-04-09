<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!-- metemos en pomp las dependencias_ maven dependencies spring boot web starter 2.1.3. . En total, añadir 3 dependencias. -->

<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- PONEMOS taglib PARA .JSTL (COGIDO DE INTERNET) -->

<html>

<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

<!-- Bootstrap CSS -->
<!-- PONEMOS PARA ESTILOS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">

<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>


<!-- Bootstrap CSS -->
<!-- PONEMOS PARA ESTILOS    fin-->


<body style="background-color: #0B79F2">

	<div class="container" style="background-color: #9CD94b">
	<!--  -->
			<!-- Card ini 1-->
			
	<div class="card card-image" style="background-color: #C3B996">
	<!-- 		style="background-image: url(https://cdn.pixabay.com/photo/2017/03/25/17/55/color-2174045_960_720.png);">
			<!-- ./imagenes/FondoVerdeCuchillas.jpg"> -->
			<!-- Content -->

			<div
				class="text-black text-left d-flex align-items-center rgba-black-strong py-5 px-4">
				<div>
		<!-- Card fin 1-->
	
	<!--  -->
	
	
	
	
<!-- TITULO -->	
		<p>
		<p>
		<h1 class="font-weight-light text-center text-lg-left mt-4 mb-0">TRADUCTOR zona administradores</h1>
		
<!-- ZONA ENTRE RAYAS - SUBBOTONES  -->
		<hr class="mt-2 mb-5">
		<a href="/index" class="badge badge-primary">Home Traductor</a>
		 
		<h4><p class="text-danger">${mensaje}</h4> <br>
		<hr class="mt-2 mb-5">
 
 <!-- ZONA LIBRE  -->
 
<!-- INDEX.JSP añadimos fecha y hora, ya que el resto es todo igual. Asi hacemos dinamica esta informacion  -->
					<%@page import="java.util.Date"%>
					<%
						Date fechaActual = new Date();
					%>
					<%!int contadorvisitas = 0;%>
					<!-- el ! hace que lo ponga en el constructor -->

				<form class="form-signin" id="traductorusuario" name="traductorusuario"
						method="post" action="opcionesadministrar">
						<!-- action es el enviar y va a etiqueta del controlodaor -->

						<!-- INDEX.JSP -->
						<h2>
							Madrid <br>
							<%=fechaActual.toLocaleString()%>
						</h2>
						<br>
						<!-- Ha visitado esta pagina: <%= ++contadorvisitas%> veces <br> <br> -->


						<p>
							Usuario: <input type="text" name="id_usuario" id="id_usuario"
								class="form-control col-xs-2" placeholder="usuario"
								value='${id_usuario}' /> 
						<h4><p class="text-danger">${mensaje_usuario}</p></h4>
						<br><br><br>
						<p>
							Password: <input type="password" name="password" id="password"
								class="form-control" placeholder="password" value='${password}' />
						<h4><p class="text-danger">${mensaje_password}</p></h4>
						<br> <br><br><br>
							<br> <input class="btn btn-lg btn-primary" type="submit"
								name="entrar" value="ENTRAR">
							<!-- VA A ACTION DESIGNADA -->
							<input class="btn btn-lg btn-secondary" type="reset" name="reset"
								value="RESET"> <br>
							
					</form>
					


<!-- ANUNCIOS EN CUADRADOS  -->
	 
	
<!-- Card ini 1-->
				</div>
			</div>
			<!-- Content -->
		</div>
		<!-- Card fin 1-->


	</div>
	<!-- /.container -->


</body>
</html>