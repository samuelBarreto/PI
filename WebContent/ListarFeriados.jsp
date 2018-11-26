<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Buscar Feriados Especificos</title>
	<link rel="icon" type="imagem/png" href="img/calendar.png" />
  	<link href="css/bootstrap.min.css" rel="stylesheet">
  	<link href="css/style.css" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round" rel="stylesheet" >
    <link href="css/font-icons.css" rel="stylesheet">
	<link href="css/font-awesome.min.css" rel="stylesheet"> 
	<link href="css/lista.css" rel="stylesheet">
	<link href="css/jquery-ui.css" rel="stylesheet">
	


</head>
<body>
   
	<%
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	%>
    <!--  EXCLUIR MODAL -->
   <!-- Modal -->
	<div class="modal fade" id="delete-modal" tabindex="-1" role="dialog"
		aria-labelledby="modalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Fechar">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="modalLabel">Excluir Feriado</h4>
				</div>
				<div class="modal-body">Deseja realmente excluir este feriado?
				</div>
				<div class="modal-footer">
					<form action="controller.do" method="post">
						<input type="hidden" name="id" id="id_excluir" />
						<button type="submit" class="btn btn-danger" name="command"
							value="ExcluirFeriado" >Sim</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">N&atilde;o</button>
					</form>
				</div>
			</div>
		</div>
	</div>
<!-- Barra superior com os menus de navegação -->
	<c:import url="Menu.jsp" />
	<br>
    <div id="main" class=" container ">
        <div class=" table-wrapper " >
            <div class="table-title">
            <form action="controller.do" method="post">  
                <div id="top" class="row ">
                    <div class="col-md-2">
						<h2><b>Feriados</b></h2>
					</div>			                							
					<div class="col-md-7">
                            <div class="input-group h2">
                                <input name="data[search]" class="form-control" id="search" type="text" placeholder="Pesquisar Feriados (deixe vazio para trazer todos)">
                                <span class="input-group-btn">
                <button class="btn btn-primary" type="submit" name="command" value="ListarFeriadosBuscar">
                    <span class="glyphicon glyphicon-search"></span>
                    </button>
                    </span>
                    </div>
                    </div>
                    <div class="col-md-3">
						<a href="#addEmployeeModal"  class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Adicionar novo Feriado</span></a>					
					</div>		
                    </form>
                </div>
            </div>
            <c:if test="${not empty lista}">
                <div  id="list" class="row">
            <div class=" table-responsive col-sm-12 ">
            <table class=" table table-striped table-hover table-bordered "  cellspacing="0" cellpadding="0">
                <thead>
                    <tr>
                        <th >ID</th>
                        <th>Nome</th>
                        <th>DataInicio</th>
                        <th>DataFinal</th>
                        <th class="actions">Ações</th>
                    </tr>
                </thead>
                <tbody>
                  <c:forEach var="feriado" items="${lista }">
                  <tr>
                        <th>${feriado.id }</th>
                        <td>${feriado.nome } </td>
						<td><fmt:formatDate value="${feriado.inicio}" pattern="dd/MM/yyyy " /></td>
                        <td><fmt:formatDate value="${feriado.fim}" pattern="dd/MM/yyyy " /></td>
                        <td class="actions">
                            <a href="controller.do?command=EditarFeriado&id=${feriado.id }" class="edit" data-toggle="modal" ><i class="material-icons" data-toggle="tooltip" title="Editar" >&#xE254;</i></a>
                            <a href="#" id="btn${feriado.id }%>" class="delete" data-toggle="modal" data-target="#delete-modal" data-feriado="${feriado.id }" ><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>                      
                        </td>
                    </tr>
                  </c:forEach> 				
                </tbody>
            </table>
            </div>
			<div class="clearfix">          
                <ul class="pagination">
                    <li class="page-item disabled"><a href="#">Voltar</a></li>
                    <li class="page-item active""><a href="#" class="page-link">1</a></li>
                    <li class="page-item"><a href="#" class="page-link">2</a></li>
                    <li class="page-item "><a href="#" class="page-link">3</a></li>
                    <li class="page-item"><a href="#" class="page-link">4</a></li>
                    <li class="page-item"><a href="#" class="page-link">5</a></li>
                    <li class="page-item"><a href="#" class="page-link">Próximo</a></li>
                </ul>
            </div>
        </div>
        </c:if>
        </div>
  
	<!-- Adicionar Modal HTML -->
	<div id="addEmployeeModal" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="controller.do" method="post">
					<div class="modal-header">						
						<h4 class="modal-title">Adicionar Feriado</h4>
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					</div>
					<div class="modal-body">					
						<div class="form-group">
							<label for="nome">Nome</label>
							<input type="text" class="form-control" name="nome" id="nome" required maxlength="100" placeholder="Digite o Nome aqui">
						</div>
						<div class="form-group">
							<label for="inicio">Data de Inicio</label>
							<input type="text"  class="form-control" name="inicio" id="inicio" required maxlength="60" placeholder=""></input>
						</div>
						<div class="form-group">
							<label for="fim">Data Final</label>
							<input type="text" class="form-control" name="fim" id="fim" required maxlength="60" placeholder=""></input>
						</div>						
					</div>
					 <div id="actions">
					<div class="modal-footer">			
						<input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
						<input type="submit" class="btn btn-success" name="command" value="AdicionarFeriado">
					 </div> 
					 </div>  
					</form>    
					</div>
			</div>
		</div>	
		<script src="js/jquery.min.js"></script>
    	<script src="js/bootstrap.min.js"></script>
    
    <script type="text/javascript">
    $(document).ready(function(){
	// Activate tooltip
	$('[data-toggle="tooltip"]').tooltip();
	//EXCLUIR CONTATO
                $("#delete-modal").on('show.bs.modal', function(event) {
			var button = $(event.relatedTarget); //botao que disparou a modal
			var recipient = button.data('feriado');
			$("#id_excluir").val(recipient);
		     });         
	
    });
</script>   
</body>
</html>  