<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
<%@ taglib prefix="nested" uri="http://struts.apache.org/tags-nested"%>
<!DOCTYPE html>
<html:html>
<head>
<meta charset="UTF-8">
<title><bean:message key="add.contact" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/custom.css">
<link rel="favicon, icon" href="images/icon.png">
</head>
<body>
	<div class="container-fluid">
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<a class="navbar-brand" href="#">
				<img src="images/logo.png">
			</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">				
				<ul class="navbar-nav justify-content-end ml-auto">
					<li class="nav-item"><html:link styleClass="nav-link btn btn-sm btn-success" action="Login.do">Connexion</html:link></li>
				</ul>				
			</div>
		</nav>
		<div class="content m-5">
			<div class="row justify-content-center m-5">
				<div class="col-md-6 offset m-5">	
					<h1 class="alert alert-danger alert-block m-5">Oppps, Une erreur s'est produite !!! </h1>												
				</div>
			</div>
		</div>
		
		<div id="footer" class="navbar navbar-dark bg-dark text-white text-center">
			Gestion Contact  <b> &copy; Rivel</b> | <b> &copy; Mohamed</b> | <b> &copy; Aurelien</b>  Miage 2018
		</div>
	</div>
</body>
</html:html>