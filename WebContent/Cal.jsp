<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<c:import url="Menu.jsp" />


  <form class="form-signin" action="controller.do" method="post" id="DiasUteis">
              <div class="form-label-group">
                <input type="text" id="dataInicial" class="form-control" name = "dataInicial" id="dataInicial" placeholder="dataInicial" required autofocus>
                <label for="inputEmail">Usuário</label>
              </div>

              <div class="form-label-group">
                <input type="text" class="form-control" name="diasUteis"  id="diasUteis" placeholder="diasUteis" required>
                <label for="input">Senha</label>
              </div>

            
			 <br>
              <button class="btn btn-lg btn-primary btn-block text-uppercase" id="btnCor" type="submit" name="command" value="HomeController">Calculo</button>
              <hr class="my-4">
            </form>
            
            
            

</body>
</html>