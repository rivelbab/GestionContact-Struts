<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ taglib prefix="nested" uri="http://struts.apache.org/tags-nested"%>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic"%>

<!DOCTYPE html>
<html:html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/custom.css">
<link rel="favicon, icon" href="images/icon.png">

<title><bean:message key="main.page.title" /></title>
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
				<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
					<li class="nav-item active">
						<html:link styleClass="nav-link" action="Main.do"><bean:message key="main.home" /> <span class="sr-only"><bean:message key="main.home" /></span></html:link>
					</li>
					<li class="nav-item"><a class="nav-link" href="ContactCreation.do"><b><bean:message key="main.addcontact" /></b> </a></li>
					<li class="nav-item"><a class="nav-link" href="ContactDisplay.do"><b><bean:message key="main.displaycontact" /></b></a></li>						
				</ul>
				<html:form styleClass="form-inline" action="/SearchAll">					
					<html:text property="word" styleClass="form-control mr-sm-2" />
					<html:submit styleClass="btn btn-outline-success my-2 my-sm-0">
							<bean:message key="main.search" />
					</html:submit>
				</html:form>				
				<ul class="navbar-nav justify-content-end ml-auto">
					<li class="nav-item"><html:link styleClass="nav-link btn btn-sm btn-danger" action="Login.do"><b><bean:message key="main.logout" /></b> </html:link></li>
				</ul>				
			</div>
		</nav>
		<div class="content">
			<div class="row justify-content-center">
				<div class="col-md-6 offset-md-3">					
					<h1 class="p-5"><bean:message key="main.app.name" /> </h1>					
					<div class="img-box md-auto">
						<img src="images/contact1.png">
					</div>					
				</div>
			</div>
		</div>
		
		<div id="footer" class="navbar navbar-dark bg-dark text-white text-center">
			<bean:message key="main.app.name" />  <b> &copy; Rivel</b> | <b> &copy; Mohamed</b> | <b> &copy; Aurelien</b>  Miage 2018
		</div>
	</div>
</body>
</html:html>