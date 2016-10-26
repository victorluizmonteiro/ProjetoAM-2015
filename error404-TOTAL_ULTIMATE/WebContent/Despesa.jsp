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
<body>
	 <c:if test="${not empty msg }">
		<div class=" alert alert-success">
			${msg}
			
		</div>
	</c:if>
		
	<c:if test="${not empty erro }">
		<div class="alert alert-danger">
			${error }
		</div>
	</c:if>
	
	 <section id="despesas">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h2 class="section-heading">Despesas</h2>
                    <hr class="primary">
                </div>
            </div>
        </div>
	   
        <div class="row">
        	 <div class="col-sm-1"></div>
        	 <div class="col-sm-10">
        		<h3 style="color:gray">Despesas do processo: ${numProcesso } <span id="despSelected"></span></h3>
             	
             	<table class="table table-hover">
             		<thead>
             			<tr>
	             			<th>Data</th>
	             			<th>Despesa</th>
	             			<th>Valor</th>
	             			<th>Observação</th>
	             			<th colspan="2">Ações</th>
	             		</tr>
             		</thead>
             		<tbody>
             			<c:forEach items ="${despesas }" var="d">
             			<tr>
             				<td>${d.dtDespesa }</td>
             				<td>${d.tipoDespesa.despesa }</td>
             				<td>${d.valor }</td>
             				<td>${d.observacao }</td>
             				<td>
             					
             					
					
             					
             					<form action="Controller" method="post" id="formDel" name="formDel" >
	             					<input type="hidden" name="codLancamento"  value="${d.codigoLancamento }">
	             					<input type="hidden" name="numOAB" id="numOAB" value="${numOAB }">
	             					<input type="hidden" name="num" id="num" value="${numProcesso }">
	             					
	             					<input type="hidden" name="acao" value="deletar">
	             					<input type="submit" value="Deletar" class="btn btn-danger">
             					</form>
             				
             				</td>
             				<td>
             					<form action="Controller#LancaDespesa" method="post" name="formaltera" id="formaltera" >
             						<input type="hidden" name="codLancamento"  id="codLancamento" value="${d.codigoLancamento }">
             						  <input type="hidden" name="num" id="num" value="${numProcesso }">
             						  <input type="hidden" name="numOAB" id="numOAB" value="${numOAB }"	>
             						<input type="hidden" name="acao" id="acao" value="alterar">
             							  <input type="submit" class="btn btn-danger" value="Alterar">
             					
             					</form>
             				</td>
             				
             			</tr>
             			</c:forEach>
             		</tbody>             
             			
             	</table>       
             </div>
        </div>
         <center>
       		<a href="#Controller" class="btn btn-primary btn-xl page-scroll">Lançar Despesas!</a>
       </center>
       <div class="row">
       	<div class="col-sm-9"></div>
       	<div class="col-sm-3">
       		<a class="btn btn-warning" href="Index?numOAB=${numOAB }#processos" role="button">Voltar</a>
       	</div>
       </div>
    </section>
	<section id="LancaDespesa" class="bg-blue">
		 <center><h2>Lançamento de Despesas</h2></center>
		 <hr>
		 <br>
		 <% String validacao = "Cadastrar";%>
		 <c:if test="${not empty tipo }">
		  	<% validacao = "Atualizar"; %>
		 </c:if>
		 <form name="formDespesas" id="formDespesas" action="Controller" class="form-horizontal" method="post">
		 							<input type="hidden" name="acao" value="<%=validacao%>">
		 							<input type="hidden" name="codigoDespesa" id="codigoDespesa" value="${codigoDespesa }">
		 							<input type="hidden" name="cdLancamento" id="cdLancamento" value="${codLancamento }">
	             					<input type="hidden" name="numOAB" id="numOAB" value="${numOAB }">
	             					<input type="hidden" name="num" id="num" value="${numProcesso }">
	             					
		 	
		 	<div class="row">
		 	
		 	 
		 		<div class="col-sm-1"></div>
		 		<div class="col-sm-10 novaDespesa">
		 		
		 			<div class="container">
		 				<h3>Dados da despesa</h3>
		 				
		 				<br>
		 				<div class="form-group">
	       					<label for="slcDProcess" class="col-sm-5 control-label">Processo:</label>
	       					<div class="col-sm-3">
	       						<input class="form-control" type="text" name="slcDProcess" id="slcDProcess" value="${numProcesso }" disabled>
	       					</div>
	       				</div>
	       				<br>
	       				
	       				<div class="form-group">
	       					<label for="slcDespesa	" class="col-sm-5 control-label">Despesa:</label>
	       					
	       					<div class="col-sm-3">
	       						<select name="slcDespesa" class="form-control" ${regra }  id="slcDespesa" required>
	       							<c:forEach items="${TipoDespesas }" var="d" >
	       							<option value="${d.codigoDespesa }">${d.despesa }</option>
	       							
	       							</c:forEach>
	       						</select>
	       					</div>
	       				</div>
	       				<br>
	       				<div class="form-group">
	       					<label for="dtOcorreDespesa" class="col-sm-5 control-label">Data da ocorrência:</label>
	       					<div class="col-sm-3">
	       						<input value="${data }" type="text" onblur="validaData(this.id)" required class="form-control " id="dtOcorreDespesa" name="dtOcorreDespesa">
	       					</div>
	       				</div>
	       				<br>	
	       				<div class="form-group">
	       					<label for="txtValor" class="col-sm-5 control-label">Valor:</label>
	       					<div class="col-sm-3">
	       						<input  value="${valorDespesa }"type="text" required class="form-control" id="txtValor" name="txtValor">
	       					</div>
	       				</div>
	       				<br>
	       				<div class="form-group">
	       					<label for="txtHObs" class="col-sm-5 control-label">Observação:</label>
	       					<div class="col-sm-5">
	       						<textarea value="${obs }" class="form-control" rows="5" id="txtHObs" name="txtHObs">${obs }</textarea>
	       					</div>
	       				</div>
	       				<br style="clear:both">
	       				<br>
	       				<div class="form-group">
	       					<div class="col-sm-5"></div>
	       					<div class="col-sm-5">
	       						<input type="submit" class="btn btn-sucess" style="color:green" value="<%=validacao%>">
	       					</div>
	       					<div class="form-group">
	       					<a class="btn btn-default" href="Index?numOAB=${numOAB }#processos" role="button">Voltar</a>
	       					</div>
	       				</div>
		 			</div>
		 		</div>
		 	</div>
		 </form>
   </section>
<%@ include file="Scripts.jsp" %> 
<script type="text/javascript">
	ativaMascarasIndex();
	$("#txtValor").maskMoney();
</script>
</body>
</html>