<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

  <tbody>
                  <c:forEach var="DiasUteis" items="${lista }">
                  <tr>
                        <td><fmt:formatDate value="${DiasUteis.dataFinal}" pattern="dd/MM/yyyy " /></td>
                        
                      
                    </tr>
                  </c:forEach> 				
                </tbody>
		<tr>
		 <th>${dataFinal.ResultadoDias }</th>
		
		 </tr>
</body>
</html>