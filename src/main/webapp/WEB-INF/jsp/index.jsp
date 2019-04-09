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

<!-- nuevos para las figuritas del formulario -->
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" 
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">


<!-- PONEMOS PARA ESTILOS    fin-->


<body style="background-color: #0B79F2"> <!-- azul -->

	<div class="container" style="background-color: #9CD94b"> <!-- verde -->
	<!--  -->
			<!-- Card ini 1-->

		<div class="card card-image" style="background-color: #C3B996"> <!-- crema -->
			<!-- crema -->
			<!-- 		style="background-image: url(https://cdn.pixabay.com/photo/2017/03/25/17/55/color-2174045_960_720.png);">
			<!-- ./imagenes/FondoVerdeCuchillas.jpg"> -->
			<!-- Content -->

			<div>
				<div>
					<!-- Card fin 1-->

					<!--  -->

					<p>
					<p>
					<h1 class="font-weight-light text-center text-lg-left mt-4 mb-0">TRADUCTOR</h1>


					<!-- ZONA INPUT/OUTPUT -->
					<hr class="mt-2 mb-5"> 	<!-- raya separacion  -->

					<!-- formulario INPUT/OUTPUT - ini -->

					<form class="form-signin" id="inputoutput" name="inputoutput" method="post" action="/traducir">


						<div class="form-group">
							<label for="inputText" class="control-label">Texto a traducir:</label>
							<textarea class="form-control" rows="10" id="inputText"
								name="inputText" type="text" placeholder="texto" required>${inputText}</textarea>
						</div>
						<div class="form-group">
						
							 Traducciones frecuentes: <select
								class="form-control" name="traduccionesfrecuentes"
								id="traduccionesfrecuentes" style="visibility:hidden">
								
							</select> <br>
						<div>
						</div>
						</div>
						

						<div class="form-group">
							<label for="outputText" class="control-label">Traduccion:</label>
							<textarea class="form-control" rows="10" id="outText"
								name="outputText" type="text" placeholder="texto" >${outputText}</textarea>
						</div>
						
						<div class="form-group">
							<!-- Submit Button -->
							<button type="submit" class="btn btn-primary">TRADUCIR</button>
						</div>

						<h4>
							<p class="text-danger">${mensaje}
						</h4>
						<br>

					</form>
					<!-- formulario INPUT/OUTPUT - fin -->


					<!-- Card ini 1-->
				</div>
			</div>
			<!-- Content -->
		</div>  	<!-- crema -->
	
		<!-- Card fin 1-->
	<!-- APORTACIONES -->

	<div class="card card-image" style="background-color: #5C160C"> <!-- marron -->
				<div class="text-white text-left d-flex align-items-center rgba-black-strong py-5 px-4">
				<div>
					<h3 class="font-weight-light text-center text-lg-left mt-4 mb-0">APORTACIONES</h3>
					<hr class="mt-2 mb-5">
					<!-- raya separacion  -->

					<!-- formulario APORTACIONES - ini -->

					<form class="form-signin" id="aportaciones" name="aportaciones" method="post" action="/aportar">

						<div class="row text-center text-lg-left">



							<div class="form-group">
								<label for="aportarEsp" class="control-label">ESPAÑOL:</label>
								<textarea class="form-control" rows="10" id="aportarEsp"
									name="aportarEsp" type="text" placeholder="texto" required>${aportarEsp}</textarea>
							</div>
							
							<p>
							<br>
							<br>
							<div class="form-group">
								<label for="aportarIng" class="control-label">INGLES:</label>
								<textarea class="form-control" rows="10" id="aportarIng"
									name="aportarIng" type="text" placeholder="texto" required>${aportarIng}</textarea>
							</div>

							<div class="form-group">
								<!-- Submit Button -->
								<button type="submit" class="btn btn-primary">APORTAR</button>
							</div>

						</div>

						<h4>
							<p class="text-danger">${mensajeAportar}
						</h4>
						<br>
						
						
						
						
					</form>
					<!-- formulario APORTACIONES - fin -->
					</div></div></div>

	</div> <!-- verde -->
	<!-- /.container -->

			<a href="/administrar" class="btn btn-info" role="button">ADMINISTRAR</a>

</body> <!-- azul -->
</html>