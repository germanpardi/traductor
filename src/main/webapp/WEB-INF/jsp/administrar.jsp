<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!-- metemos en pomp las dependencias_ maven dependencies spring boot web starter 2.1.3. . En total, añadir 3 dependencias. -->

<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <!-- PONEMOS taglib PARA .JSTL (COGIDO DE INTERNET) -->
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %> <!-- añadido para formato de decimales -->


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
	
	
	
	
	
		<p>
		<p>
		<h1 class="font-weight-light text-center text-lg-left mt-4 mb-0">TRADUCTOR administrar diccionario</h1>
		
		<hr class="mt-2 mb-5">
		
		<nav class="navbar navbar-inverse " role="navigation">
			<!-- navbar-text-white -->
			<div class="navbar-inner">
				<!-- <a class="brand" href="#">Title</a> -->
				<ul class="nav">
						<li class="active"> <a href="buscarpalabras"><h4> Palabras</h4></a></li>
						<li class="active"> <a href="buscarfrases"><h4> Frases</h4></a></li>
						<li class="active">
						<form class="form-inline active-cyan-3 active-cyan-4">
  							<i class="fas fa-search" aria-hidden="true"></i>
  							<input class="form-control form-control-sm ml-3 w-200" type="text" placeholder="Search" aria-label="Search" name="textobuscar" value="${textobuscar}" id="textobuscar">
						</form>
						</li>
						<li>   </li>
						<li class="active"><a href="cargamasiva" class="btn btn-info" role="button">  CARGA MASIVA  </a></li> 
				</ul>
			</div>
		</nav>
		
				
		<h4><p class="text-danger">${mensaje}</h4> <br>
		<hr class="mt-2 mb-5">

<!-- tabla de informacion -->		

<table class="table  table-striped " id="tabla" name="tabla" > <!-- BORDER="1" class="table table-striped  table-zebra">    -->

  	<thead class="thead-dark"> <!-- class="table table-sm"  "thead-dark" -->
  	<tr>
      <th scope="col" class="text-left"><h2>Espanol</h2></th>
      <th scope="col" class="text-left"><h2>Ingles</h2></th>
      <th scope="col" class="text-center"><h2>Tipo</h2></th>
      <th scope="col" class="text-left"><h2>Categoria</h2></th>
      <th scope="col" class="text-right"><h2>Num.Uso</h2></th>
      <th></th>
      <th></th>
      
    </tr>
    </thead>
	
	<tbody>
	<c:forEach var="diccionarioDTO" items="${diccionarioDTO}" > <!-- no distingue si viene por session o por request. --> 
		<div id="contenedor">
		</div>
		<tr>
	  		<td class="table-light" ><p class="text-left">${diccionarioDTO.espanol}</p></td>  <!-- son las PROPIEDADES del bean -->
	  		<td class="table-light" ><p class="text-left">${diccionarioDTO.ingles}</p></td>  <!-- son las PROPIEDADES del bean -->
	  		<td class="table-light" ><p class="text-center">${diccionarioDTO.tipo}</p></td>  <!-- son las PROPIEDADES del bean -->
	  		<td class="table-light" ><p class="text-left">${diccionarioDTO.categoria}</p></td>  <!-- son las PROPIEDADES del bean -->
	  		<td class="table-light" ><p class="text-right">${diccionarioDTO.num_uso}</p></td>  
	  		<td  class="table-light"><a href="editar?id=${diccionarioDTO.id}" class="btn btn-info" role="button">EDITAR</a>
	  		<td  class="table-light"><a href="borrar?id=${diccionarioDTO.id}" class="btn btn-info" role="button">BORRAR</a>
		</tr>
	</c:forEach>
	</tbody>
	
	
</table>


				</div>
			</div>
			<!-- Content -->
		</div>
		<!-- Card fin 1-->


	</div>
	<!-- /.container -->

<script src= "js/main.js"></script>
</body>
</html>