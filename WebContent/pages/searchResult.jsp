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
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css">
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
					<li class="nav-item active">
						<html:link styleClass="nav-link" action="Main.do">Accueil <span class="sr-only">Accueil</span></html:link>
					</li>
					<li class="nav-item"><a class="nav-link" href="ContactCreation.do"><b>Ajouter un Contact</b> </a></li>
					<li class="nav-item"><a class="nav-link" href="ContactDisplay.do"><b>Afficher les Contacts</b></a></li>						
				</ul>
				<html:form styleClass="form-inline" action="/SearchAll">					
					<html:text property="word" styleClass="form-control mr-sm-2" />
					<html:submit styleClass="btn btn-outline-success my-2 my-sm-0">
							Rechercher
					</html:submit>
				</html:form>
				<ul class="navbar-nav justify-content-end ml-auto">
					<li class="nav-item"><html:link styleClass="nav-link btn btn-sm btn-danger" action="Login.do"><b>Deconnexion</b> </html:link></li>
				</ul>				
			</div>
		</nav>
		<div class="content">
			<div class="row justify-content-center">
				<div class="col-md-9 offset m-5">
					<div class="card border-secondary m-5">
						<div class="card-header text-center"><h1> Resultat de recherche</h1></div>
						<div class="card-body mt-5">
							<div class="table-responsive">
								<table class="table table-striped">
									<thead>
										<tr>
											<th><bean:message key="add.contact.form.id" /></th>
											<th><bean:message key="add.contact.form.lastname" /></th>
											<th><bean:message key="add.contact.form.firstname" /></th>
											<th><bean:message key="add.contact.form.email" /></th>
											<th><bean:message key="contact.all.delete" /></th>
											<th><bean:message key="contact.all.update" /></th>
											<th>Afficher</th>
										</tr>
									</thead>
									<tbody>
										<logic:iterate name="CONTACT" id="contact">
											<tr>
												<td><bean:write name="contact" property="id" /></td>
												<td><bean:write name="contact" property="firstName" /></td>
												<td><bean:write name="contact" property="lastName" /></td>
												<td><bean:write name="contact" property="email" /></td>
												<td><html:link action="ContactDelete.do?action=delete"
														paramId="id" paramName="contact" paramProperty="id"
														styleClass="btn btn-sm btn-danger">
														<bean:message key="main.deletecontact.link" />
													</html:link>
												</td>
												<td><html:link action="ContactUpdate.do?action=update" 
														paramId="id" paramName="contact" paramProperty="id"
														styleClass="btn btn-sm btn-info">
														<bean:message key="contact.all.update" />
													</html:link>
												</td>
												<td><html:link action="/DisplayOneContact.do" 
														paramId="id" paramName="contact" paramProperty="id"
														styleClass="btn btn-sm btn-info">
														<bean:message key="contact.display.link" />
													</html:link>
												</td>
											</tr>
										</logic:iterate>
									</tbody>
								</table>
							</div>
						</div>
					</div>																			
				</div>
			</div>
		</div>
		
		<div id="footer" class="navbar navbar-dark bg-dark text-white text-center">
			Gestion Contact  <b> &copy; Rivel</b> | <b> &copy; Mohamed</b> | <b> &copy; Aurelien</b>  Miage 2018
		</div>
	</div>
</body>
</html:html>
