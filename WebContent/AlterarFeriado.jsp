<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>

        <!DOCTYPE html>
        <html lang="pt-br">

        <head>
            <meta charset="utf-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <title>Alterar Feriado</title>
            <link rel="icon" type="imagem/png" href="img/calendar.png" />
            <link href="css/bootstrap.min.css" rel="stylesheet">
            <link href="css/style.css" rel="stylesheet">
        </head>

        <body>
        
        <%
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	%>
                <!-- Barra superior com os menus de navegação -->
				<c:import url="Menu.jsp"/>
                <!-- Container Principal -->
                <div id="main" class="container">
                    <h3 class="page-header">Alterar Feriado ${feriado.id }</h3>
                    <!-- Formulario para alteração de clientes -->
                    <form action="controller.do" method="post">
                        <!-- area de campos do form -->
                        <input type="hidden" name="id" value="${feriado.id }" />
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label for="nome">Nome</label>
                                <input type="text" class="form-control" name="nome" id="nome" required maxlength="100" placeholder="Digite o país aqui" value="${feriado.nome }">
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-6">
                                <label for="inicio">Data Inicio</label>
                                <input type="text" class="form-control" name="inicio" id="inicio" required maxlength="60" placeholder="" value="${feriado.inicio}">
                            </div>
                            <div class="form-group col-md-6">
                                <label for="fim">Data Final</label>
                                <input type="text" class="form-control" name="fim" id="fim" required maxlength="60" placeholder="" value="${feriado.fim}">
                            </div>
                        </div>
                        <hr />
                        <div id="actions" class="row">
                            <div class="col-md-12">
                                <button type="submit" class="btn btn-primary" name="command" value="AlterarFeriado">Salvar</button>
                                <a href="ListarFeriados.jsp" class="btn btn-default">Cancelar</a>
                            </div>
                        </div>
                    </form>
                </div>
                <script src="js/jquery.js"></script>
             	<script src="js/jquery-ui.js"></script>
                <script src="js/jquery.min.js"></script>
                <script src="js/bootstrap.min.js"></script>
                <script src="js/moment.min.js"></script>
				<script src="js/agendaEventos.js"></script>
				<script src="js/fullcalendar.min.js"></script>
                
                
        </body>

        </html>