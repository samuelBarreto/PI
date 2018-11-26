package command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Usuario;
import service.UsuarioService;

public class SairLogin implements Command {

	public void executar (HttpServletRequest request, HttpServletResponse response)  
            throws ServletException, IOException {  
			response.setContentType("text/html");  
			PrintWriter out=response.getWriter();  

			request.getRequestDispatcher("Login.jsp").include(request, response);  

			HttpSession session=request.getSession();  
			session.invalidate();  

			response.sendRedirect("Login.jsp");  
	}  

}
