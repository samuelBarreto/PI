<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<html lang="pt-br">

<head>
<link rel="icon" type="imagem/png" href="img/calendar.png" />
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/fullcalendar.min.css" rel="stylesheet" />
<link href="css/style.css" rel="stylesheet">
<link href="css/calendario.css" rel="stylesheet" />
<link href="css/agenda.css" rel="stylesheet" />
 	<title>E-Calendar</title>

</head>
<body class="body" style="background-color: #f2f2f2;">
<%
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	%>
	<c:import url="Menu.jsp" />

<br></br>
	<div id='div-agenda-eventos'></div>
	
	<!-- //AQUI VAI CADASTRAR OS EVENTOS -->
<div class="modal fade" id="cadastrarModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" data-backdrop="static">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title text-center">Cadastrar Evento</h4>
					</div>
					<div class="modal-body">
						<form class="form-horizontal" action="controller.do" method="post">
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">Titulo</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" name="nome" id="nome" placeholder="Titulo do Evento">
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">Data Inicial</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" name="inicio" id="inicio" onKeyPress="DataHora(event, this)">
								</div>
							</div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">Data Final</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" name="fim" id="fim" onKeyPress="DataHora(event, this)">
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<button type="submit" class="btn btn-success" name="command" value="AdicionarFeriado">Cadastrar</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>	
		
		<div class="modal fade" id="visualizar" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" data-backdrop="static">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<h4 class="modal-title text-center">Dados do Evento</h4>
					</div>
					<div class="modal-body">
						<div class="visualizar">
							<dl class="dl-horizontal">
								<dt>ID do Evento</dt>
								<dd id="id"></dd>
								<dt>Titulo do Evento</dt>
								<dd id="title"></dd>
								<dt>Inicio do Evento</dt>
								<dd id="start"></dd>
								<dt>Fim do Evento</dt>
								<dd id="end"></dd>
							</dl>
							<button class="btn btn-canc-vis btn-warning">Editar</button>
						</div>
						<div class="form">
							<form class="form-horizontal" method="POST" action="proc_edit_evento.php">
								<div class="form-group">
									<label for="inputEmail3" class="col-sm-2 control-label">Titulo</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" name="title" id="title" placeholder="Titulo do Evento">
									</div>
								</div>
								<div class="form-group">
									<label for="inputEmail3" class="col-sm-2 control-label">Data Inicial</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" name="start" id="star" onKeyPress="DataHora(event, this)">
									</div>
								</div>
								<div class="form-group">
									<label for="inputEmail3" class="col-sm-2 control-label">Data Final</label>
									<div class="col-sm-10">
										<input type="text" class="form-control" name="end" id="end" onKeyPress="DataHora(event, this)">
									</div>
								</div>
								<input type="hidden" class="form-control" name="id" id="id">
								<div class="form-group">
									<div class="col-sm-offset-2 col-sm-10">
										<button type="button" class="btn btn-canc-edit btn-primary">Cancelar</button>
										<button type="submit" class="btn btn-warning">Salvar AlteraÃ§Ãµes</button>
									</div>
								</div>
							</form>
							
						
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--//USAR ESSE JQUERY SOMENTE NO CALENDARIO -->
	<script src="js/jquery-1.12.4.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/moment.min.js"></script>
	<script src="js/agendaEventos.js"></script>
	<script src="js/fullcalendar.min.js"></script>
	<script src="js/lang-all.js"></script>
	<script>
			$('.btn-canc-vis').on("click", function() {
				$('.form').slideToggle();
				$('.visualizar').slideToggle();
			});
			$('.btn-canc-edit').on("click", function() {
				$('.visualizar').slideToggle();
				$('.form').slideToggle();
			});
		</script>
	
</body>
</html>