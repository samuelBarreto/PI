package command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Contato;
import model.Usuario;
import service.ContatoService;
import service.UsuarioService;


public class FazerLogin implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");

		Contato contato = new Contato();
		contato.setEmail(email);
		contato.setSenha(senha);
		ContatoService service = new ContatoService();
		
		if(service.validar(contato) ){
			HttpSession session = request.getSession();
			session.setAttribute("logado", contato);	
			System.out.println("Logou "+contato);
		} else {
			System.out.println("NÃ£o Logou "+contato);
			throw new ServletException("Usuário/Senha inválidos");
		}
		response.sendRedirect("index.jsp");	

			
	}	
}
