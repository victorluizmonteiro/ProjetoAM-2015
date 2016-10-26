<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>Advocacia LCA</title>

   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <%@ include file="bootstrap.jsp" %>
   


</head>

<body id="page-top" onload="ativaMascarasLogin()">

	<nav id="mainNav" class="navbar navbar-default navbar-fixed-top">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand page-scroll" href="#page-top">Advocacia
				LCA</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>

	
	
	<header id="acesso">
	
	
	<form action="Index" method="get" name="formLogar" id="formLogar"
		class="form-horizontal">
		<div class="header-content">
		
			<div class="header-content-inner">
				<h1>Acesso</h1>
				<hr>
				<div style="opacity: 0.8; border-radius: 0px;"
					onclick="aparecer('log')" id="log" class="panel panel-default">
					<div class="panel-heading" style="color: red">
						<h3 class="panel-title">Informe seus dados</h3>
					</div>
					<div class="panel-body" style="color: black">
						<center>

							<div class="form-group">
								<div class="col-sm-2"></div>
								
								<label for="txtMail" class="col-sm-3 control-label"
									style="color: black">Numero OAB:</label>
								<div class="col-sm-4">
									<input type="text" required maxlength="128"
										class="form-control" name="numOAB"
										id="numOAB">
								</div>
								
								
							</div>
							
							<c:if test="${not empty error }">
								<div class="alert alert-danger">
										${error }
								</div>
							</c:if>
						</center>
					</div>
				</div>
				<input type="submit" value="Login" class="btn btn-primary btn-xl">
			</div>
		</div>
	</form>
	</header>



<%@ include file="Scripts.jsp" %> 

</body>
</html>