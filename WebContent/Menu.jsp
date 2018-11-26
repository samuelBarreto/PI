<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <nav class="navbar navbar-inverse navbar-fixed-top" style="background:#000000">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <div id="navbar" class="navbar-collapse collapse" >
                <ul class="nav navbar-nav navbar-right" ">
                    <li><a href="controller.do?command=ListarContatosReiniciar" >Contatos</a>
                    </li>
                    <li><a href="controller.do?command=ListarFeriadosReiniciar">Feriados</a>
                    </li>
                    <li><a href="#">Administrador</a>
                    </li>
                    <li><a href="controller.do?command=SairLogin">Sair</a>                   
                    </li>
                </ul>
            </div>
        </div>
    </nav>