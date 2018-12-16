<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic"%>

<!DOCTYPE html>
<html:html>
<head>
<meta charset="UTF-8">
<title><bean:message key="display.contact.title" /></title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.5.0/css/all.css">
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
				<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
					<li class="nav-item active"><html:link styleClass="nav-link" action="Main.do"><bean:message key="main.home" /> <span class="sr-only"><bean:message key="main.home" /></span></html:link></li>
					<li class="nav-item"><a class="nav-link"
						href="ContactCreation.do"><b><bean:message key="main.addcontact" /></b> </a></li>
					<li class="nav-item"><a class="nav-link"
						href="ContactDisplay.do"><b><bean:message key="main.displaycontact" /></b></a></li>
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
				<div class="col-md-6 offset m-5">
					<div class="card text-white bg-secondary p-5">						
						<div class="card-body mb-5">
							<table class="table table-striped table-dark">
								<logic:iterate name="CONTACT" id="contact">
									<tr>
										<td><bean:message key="add.contact.form.id" /></td>
										<td><bean:write name="contact" property="id" /></td>
									</tr>
									<tr>
										<td><bean:message key="add.contact.form.lastname" /></td>
										<td><bean:write name="contact" property="lastName" /></td>
									</tr>
									<tr>
										<td><bean:message key="add.contact.form.firstname" /></td>
										<td><bean:write name="contact" property="firstName" /></td>
									</tr>
									<tr>
										<td><bean:message key="add.contact.form.email" /></td>
										<td><bean:write name="contact" property="email" /></td>
									</tr>
								</logic:iterate>

							</table>
						</div>
						<div class="card-footer mb-5">
							<table class="table table-striped table-dark">
								<logic:present name="ADDRESS">
									<logic:iterate name="ADDRESS" id="address">
										<tr>
											<td><bean:message key="add.contact.form.street" /></td>
											<td><bean:write name="address" property="street" /></td>
										</tr>
										<tr>
											<td><bean:message key="add.contact.form.city" /></td>
											<td><bean:write name="address" property="city" /></td>
										</tr>
										<tr>
											<td><bean:message key="add.contact.form.zip" /></td>
											<td><bean:write name="address" property="zip" /></td>
										</tr>
										<tr>
											<td><bean:message key="add.contact.form.country" /></td>
											<td><bean:write name="address" property="country" /></td>
										</tr>
									</logic:iterate>
								</logic:present>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div id="footer"
			class="navbar navbar-dark bg-dark text-white text-center">
			Gestion Contact <b> &copy; Rivel</b> | <b> &copy; Mohamed</b> | <b>
				&copy; Aurelien</b> Miage 2018
		</div>
	</div>
</body>
</html:html>
