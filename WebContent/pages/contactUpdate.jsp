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
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/custom.css">
<link rel="favicon, icon" href="images/icon.png">
</head>
<body>
	<div class="container-fluid">
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<a class="navbar-brand" href="#"> <img src="images/logo.png">
			</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
					<li class="nav-item active"><html:link styleClass="nav-link"
							action="Main.do">Accueil <span class="sr-only">Accueil</span>
						</html:link></li>
					<li class="nav-item"><a class="nav-link"
						href="ContactCreation.do"><b>Ajouter un Contact</b> </a></li>
					<li class="nav-item"><a class="nav-link"
						href="ContactDisplay.do"><b>Afficher les Contacts</b></a></li>
				</ul>
				<html:form styleClass="form-inline" action="/SearchAll">
					<html:text property="word" styleClass="form-control mr-sm-2" />
					<html:submit styleClass="btn btn-outline-success my-2 my-sm-0">
							Rechercher
					</html:submit>
				</html:form>
				<ul class="navbar-nav justify-content-end ml-auto">
					<li class="nav-item"><html:link
							styleClass="nav-link btn btn-sm btn-danger" action="Login.do">
							<b>Deconnexion</b>
						</html:link></li>
				</ul>
			</div>
		</nav>
		<div class="content">
			<div class="row justify-content-center">
				<div class="col-md-10 offset m-2">
					<div class="card border-secondary">
						<div class="card-header text-center">
							<h1>Création de contact</h1>
						</div>
						<div class="card-body m-2">
							<html:form action="/UpdateContact">
								<div class="card card-default">
									<div class="card-header text-center">
										<html:errors />
									</div>
									<div class="card-body">
										<div class="row">
											<div class="col-sm-6">
												<div class="card card-default">
													<div class="card-header text-center">
														<h2>Contact</h2>
													</div>
													<div class="card-body mb-2">
														<div class="form-group">
															<label>ID du contact</label>
															<html:text property="id" styleClass="form-control" readonly="true"/>
														</div>
														<div class="form-group">
															<label>Prénom</label>
															<html:text property="firstName" styleClass="form-control" />
														</div>
														<div class="form-group">
															<label>Nom</label>
															<html:text property="lastName" styleClass="form-control" />
														</div>
														<div class="form-group">
															<label>Email</label>
															<html:text property="email" styleClass="form-control" />
														</div>
													</div>
												</div>
											</div>
											<div class="col-sm-6">
												<div class="card card-default">
													<div class="card-header text-center">
														<h2>Address</h2>
													</div>
													<div class="card-body mb-2">
														<div class="form-group">
															<label>Ruel</label>
															<html:text property="street" styleClass="form-control" />
														</div>
														<div class="form-group">
															<label>Ville</label>
															<html:text property="city" styleClass="form-control" />
														</div>
														<div class="form-group">
															<label>Code postal</label>
															<html:text property="zip" styleClass="form-control" />
														</div>
														<div class="form-group">
															<label>Pays</label>
															<html:text property="country" styleClass="form-control" />
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="card-footer text-center">
										<html:submit styleClass="mt-5 btn btn-primary btn-lg">Enregistrer le contact</html:submit>
									</div>
								</div>
							</html:form>
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
