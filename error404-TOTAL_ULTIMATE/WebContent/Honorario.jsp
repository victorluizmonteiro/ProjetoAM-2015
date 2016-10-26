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
			${msg }
		</div>
	</c:if>
	<c:if test="${not empty error }">
		<div class="alert alert-danger">
			${error }
		</div>
	</c:if>
	
	<section id="honorarios">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h2 class="section-heading">Honorários</h2>

                    <hr class="primary">
                </div>
            </div>
        </div>
	   
       </center>
        <div class="row">
        	 <div class="col-sm-1"></div>
        	 <div class="col-sm-10">
        		<h3 style="color:gray">Honorários do processo: ${numProcesso }<span id="proSelected"></span></h3>
             	<table class="table table-hover">
             		<thead>
             			<tr>
             				<th>Data</th>
             				<th>Tarefa</th>
             				<th>Horas trabalhadas</th>
             				<th>Observação</th>
             				<th>Total</th>
             				<th colspan="2">Ações</th>
             				
             			</tr>
             		</thead>             
             		<tbody>
             			<c:forEach items="${lancaHonorarios}" var="h">
             			<tr>
             				<td>${h.dataHonorario }</td>
             				<td>${h.tarefa.tarefa }</td>
             				<td>${h.qtdeHoras}</td>
             				<td>${h.observacao }</td>
             				<td>${h.total}</td>
             				<td>
             					<form method="post" name="formaltera" id="formDeleta" action="LancaHonorario">
             						  <input type="hidden" name="cdLancamento"  id="cdLancamento" value="${h.codLancamento }">
             						  <input type="hidden" name="num" id="num" value="${numProcesso }">
             						  <input type="hidden" name="numOAB" id="numOAB" value="${numOAB }"	>
             						  <input type="hidden" name="acao" value="deletar">
             						  
             						  <input type="submit" value="Deletar" class="btn btn-danger">
             						
             					</form>
             				</td>
             				<td>
             					<form method="post" name="formaltera" id="formaltera" action="LancaHonorario#LancaHonorario">
             						<input type="hidden" name="cdLancamento"  id="cdLancamento" value="${h.codLancamento }">
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
       		<a href="#LancaHonorario" class="btn btn-primary btn-xl page-scroll">Lançar honorários!</a>
       </center>
        <div class="row">
	       	<div class="col-sm-9"></div>
	       	<div class="col-sm-3">
	       		<a class="btn btn-warning" href="Index?numOAB=${numOAB }#processos" role="button">Voltar</a>
	       	</div>
       	</div>
       	
    </section>
    
    
	<section id="LancaHonorario" class="bg-dark">
		 <center><h2>Lançamento de Honorário</h2></center>
		 <hr>
		 <br>
		 
		 <% String validacao = "Cadastrar";%>
		 <c:if test="${not empty tipo }">
		  	<% validacao = "Atualizar"; %>
		 </c:if>
		 <form name="formHonorario" id="formHonorario" action="LancaHonorario" class="form-horizontal" method="post">
		 <input type="hidden" name="acao" id="acao" value="<%=validacao%>">
		 <input type="hidden" name="codigoTarefa" id="codigoTarefa" value="${codigoTarefa }">
		 <input type="hidden" name="num" id="num" value="${numProcesso }">
		 <input type="hidden" name="numOAB" id="numOAB" value="${numOAB }"	>
		  <input type="hidden" name="codLancamento"  id="codLancamento" value="${codLancamento }">
		 	<div class="row">
		 		<div class="col-sm-1"></div>
		 		<div class="col-sm-10 novoHonorario">
		 		
		 			<div class="container">
		 				<h3>Dados do honorário</h3>
		 				<br>
		 				<div class="form-group">
	       					<label for="slcHProcess" class="col-sm-5 control-label">Processo:</label>
	       					<div class="col-sm-3">
	       						<input  class="form-control" type="text" name="slcHProcess" id="slcHProcess" value="${numProcesso }"disabled>    					
	       					</div>
	       				</div>
	       				<br>
	       				<div class="form-group">
	       					<label for="slcTarefa" class="col-sm-5 control-label">Tarefa:</label>
	       					<div class="col-sm-3">
	       						<select name="slcTarefa" class="form-control tipoTarefa" ${regra } id="slcTarefa" required>
	       						<c:forEach var="t" items="${TipoTarefas}">     							
	       							<option value="${t.codigoTipoTarefa }">${t.tarefa }</option>
	       						</c:forEach>
	       						</select>
	       						
	       					</div>
	       				</div>
	       				<br>
	       				<div class="form-group">
	       					<label for="dtOcorreHonorario" class="col-sm-5 control-label">Data da ocorrência:</label>
	       					<div class="col-sm-3">
	       						<input type="text" value="${data }" onblur="validaData(this.id)" required class="form-control" id="dtOcorreHonorario" name="dtOcorreHonorario">
	       					</div>
	       				</div>
	       				<br>	
	       				<div class="form-group">
	       					<label for="txtHoras" class="col-sm-5 control-label">Horas trabalhadas:</label>
	       					<div class="col-sm-3">
	       						<input type="text" value="${qtdeHoras }" required class="form-control" id="txtHoras" name="txtHoras">
	       					</div>
	       				</div>
	       				<br>
	       				<div class="form-group">
	       					<label for="txtHObs" class="col-sm-5 control-label">Observação:</label>
	       					<div class="col-sm-5">
	       						<textarea class="form-control" rows="5" id="txtHObs" name="txtHObs">${obs }</textarea>
	       					</div>
	       				</div>
	       				<br style="clear:both">
	       				<br>
	       				<div class="form-group">
	       					<div class="col-sm-5"></div>
	       					<div class="col-sm-5">
	       						<input type="submit" class="btn btn-sucess" style="color:darkgray" value="<%=validacao%>">
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
		
		try { 
			var codigo = $('#codigoTarefa').val();
		$('option[value='+codigo+']').attr('selected', 'selected');
		}catch (err){
		
		}  
		
		
</script> 

</body>
</html>