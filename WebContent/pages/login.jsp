<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
<%@ taglib prefix="nested" uri="http://struts.apache.org/tags-nested"%>
<!DOCTYPE html>
<html:html>
<head>
<meta charset="UTF-8">
<title><bean:message key="login" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/custom.css">
<link rel="favicon, icon" href="images/icon.png">
</head>
<body class="">
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
		</nav>	
		<div class="content p-5">
			<div class="row justify-content-center p-5">
				<div class="col-md-6 offset p-5">	
					<div class="card text-white bg-secondary mt-5">
						<div class="card-header text-center">
							<h1><bean:message key="login" /></h1>
						</div>
						<div class="card-body">							
							<html:form action="/LoginAction">
								<html:errors />
							    <div class="form-group">
							        <label><bean:message key="form.login" /></label>
							        <html:text property="login" styleClass="form-control"/>
							    </div>
							    <div class="form-group">
							        <label><bean:message key="form.password" /></label>
							        <html:text property="password" styleClass="form-control"/>
							    </div>							    
							    <html:submit styleClass="btn btn-success btn-lg" ><bean:message key="form.login.submit" /></html:submit>
							</html:form>
						</div>
					</div>													
				</div>
			</div>
		</div>
		
		<div id="footer" class="navbar navbar-dark bg-dark text-white text-center">
			  <bean:message key="main.app.name" /> <b> &copy; Rivel</b> | <b> &copy; Mohamed</b> | <b> &copy; Aurelien</b>  Miage 2018
		</div>
	</div>
</body>
</html:html>
