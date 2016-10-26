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


<body id="page-top" onload="ativaMascarasIndex()">

    <%@ include file="topMenu.jsp" %>

    <header id="exclusivo">
        <div class="header-content">
            <div class="header-content-inner">
                <h1>Bem vindo!</h1>
                <hr>
                <p>Acompanhe o status de seus processos!</p>
                <a href="#processos" class="btn btn-primary btn-xl page-scroll">Processos!</a>
            </div>
        </div>
    </header>
	<section id="processos">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h2 class="section-heading">Processos</h2>
                    	<h1>${error }</h1>
                    <hr class="primary">
                </div>
            </div>
        </div>
        <div class="row">
        	 <div class="col-sm-1"></div>
        	 <div class="col-sm-10">
        		<h3 style="color:red">Processo em aberto</h3>
             	<table class="table table-hover">
             		<thead>
             			<tr>
             				<th>Número do Processo</th>
             				<th>Cliente</th>
             				<th>Causa</th>
             				<th>Descrição</th>
             				<th>Observação</th>
             				<th>Data de Abertura</th>
             				<th colspan="2"><center>Ações</center></th>
             			</tr>
             		</thead>             
             		<tbody>
             			<c:forEach var="p" items="${ProcessosAbertos }">
             			<tr>
             				<td>${p.numProcesso }</td>
             				<td>${p.cliente.razaoSocial}</td>
             				<td>${p.causa.causa }</td>
             				<td>${p.descricao }</td>
             				<td>${p.observacao }</td>
             				<td>${p.dtAbertura }</td>
             				<td>
             					<form method="get" action="Controller" name="formDespesa" id="formDespesa">
             					<input type="hidden" name="acao" value="listar">
             					<input type="hidden" name="numOAB" id="numOAB" value="${numOAB }">
             					<input type="hidden" name="num" id="num" value="${p.numProcesso }">
             						<input type="submit" class="btn btn-primary" value="Despesa">
             						
             					</form>
             				</td>
             				<td>
             					<form method="get" action="LancaHonorario">
             						<input type="hidden" name="acao" value="listar">
             						<input type="hidden" name="numOAB" id="numOAB" value="${numOAB }">
             					<input type="hidden" name="num" id="num" value="${p.numProcesso }">
             						<input type="submit" class="btn btn-sucess" value="Honorario">
             						
             					</form>
             				</td>
             			</tr>
             			</c:forEach>
             		</tbody>	
             	</table>       
             </div>
        </div>
        <div class="row">
        	 <div class="col-sm-1"></div>
        	 <div class="col-sm-10">
        		<h3 style="color:gray">Processo fechados</h3>
             	<table class="table table-hover">
             		<thead>
             			<tr>
             				<th>Número do Processo</th>
             				<th>Cliente</th>
             				<th>Causa</th>
             				<th>Descrição</th>
             				<th>Observação</th>
             				<th>Data de Abertura</th>
             			</tr>
             		</thead>             
             		<tbody>
             			<c:forEach var="p" items="${ProcessosFechados }">
             			<tr>
             				<td>${p.numProcesso }</td>
             				<td>${p.cliente.razaoSocial}</td>
             				<td>${p.causa.causa }</td>
             				<td>${p.descricao }</td>
             				<td>${p.observacao }</td>
             				<td>${p.dtAbertura }</td>
             			</tr>
             			</c:forEach>
             		</tbody>	
             	</table>       
             </div>
        </div>
       
    </section>
	

    <section id="contact">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 text-center">
                    <h2 class="section-heading">Precisa de ajuda?</h2>
                    <hr class="primary">
                    <p>Entre em contato conosco por telefone, temos uma central de atendimento exclusiva pra você.</p>
                    <p>Horário de funcionamento: Seg/Sex das 09:00 as 18:00.</p>
                </div>
                <div class="col-lg-4 col-lg-offset-2 text-center">
                    <i class="fa fa-phone fa-3x wow bounceIn"></i>
                    <p>(11)8001-5465</p>
                </div>
                <div class="col-lg-4 text-center">
                    <i class="fa fa-envelope-o fa-3x wow bounceIn" data-wow-delay=".1s"></i>
                    <p><a href="mailto:atendimento@advocaciaLCA.com">atendimento@advocaciaLCA.com</a></p>
                </div>
            </div>
        </div>
    </section>
    
    
<%@ include file="Scripts.jsp" %> 

</body>
</html>