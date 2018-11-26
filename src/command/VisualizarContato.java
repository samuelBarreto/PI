package command;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Contato;
import service.ContatoService;

public class VisualizarContato implements Command {

	@Override
	public void executar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//busca o writer
		PrintWriter out = response.getWriter();
								
		String pId = request.getParameter("id");
		String nome = request.getParameter("nome");
		String cidade = request.getParameter("cidade");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
				
		
		int id = -1;				
		try {
			id = Integer.parseInt(pId);
		} catch (NumberFormatException e) {
		}
		
	    Contato contato = new Contato();
		contato.setId(id);
		contato.setNome(nome);
		contato.setCidade(cidade);
		contato.setEmail(email);
		contato.setSenha(senha);
						
		ContatoService cont = new ContatoService();
		
		RequestDispatcher view = null;

		contato = cont.carregar(contato.getId());
		request.setAttribute("pais", contato);
		view = request.getRequestDispatcher("VisualizarContato.jsp");

		view.forward(request, response);

	}

}
