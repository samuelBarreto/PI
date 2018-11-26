<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><!DOCTYPE html>
<html lang="pt-br">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>E-Calendario</title>
    
	<!--  SÓ USA ESSE BOOTSTRAP NA  TELA DE LOGIN!!> -->
    <link href="css/bootstrap4.1.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
     <link href="css/Login.css" rel="stylesheet">
     <link rel="icon" type="imagem/png" href="img/calendar.png" />
</head>

<body>
  <div class="container">
    <div class="row">
      <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
        <div class="card card-signin my-5">
          <div class="card-body">
            <h2 class="card-title text-center" id="admin" >Administrador</h2>
            <img id="profile-img" class="profile-img-card" src="img/avatar.png" />
            <br>              
            <form class="form-signin" action="controller.do" method="post" id="Login">
              <div class="form-label-group">
                <input type="email" id="inputEmail" class="form-control" name = "email" id="email" placeholder="Email address" required autofocus>
                <label for="inputEmail">Usuário</label>
              </div>

              <div class="form-label-group">
                <input type="password" id="inputPassword" class="form-control" name="senha"  id="senha" placeholder="Password" required>
                <label for="inputPassword">Senha</label>
              </div>

             <div class="forgot">
       		 <a href="RecuperarSenha.jsp">Esqueceu senha?</a>
			</div>
			 <br>
              <button class="btn btn-lg btn-primary btn-block text-uppercase" id="btnCor" type="submit" name="command" value="FazerLogin">Login</button>
              <hr class="my-4">
            </form>
          </div>
        </div>
      </div>
      </div>
      </div>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootstrap.bundle.min.js"></script>
</body>

</html>