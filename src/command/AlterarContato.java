package command;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Contato;
import service.ContatoService;

public class AlterarContato implements Command {

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
		HttpSession session = request.getSession();

		cont.atualizar(contato);
		@SuppressWarnings("unchecked")
		ArrayList<Contato> lista = (ArrayList<Contato>) session
				.getAttribute("lista");
		int pos = busca(contato, lista);
		lista.remove(pos);
		lista.add(pos, contato);
		session.setAttribute("lista", lista);
		request.setAttribute("pais", contato);
		view = request.getRequestDispatcher("ListarContatos.jsp");

		view.forward(request, response);

	}

	public int busca(Contato contato, ArrayList<Contato> lista) {
		Contato to;
		for (int i = 0; i < lista.size(); i++) {
			to = lista.get(i);
			if (to.getId() == contato.getId()) {
				return i;
			}
		}
		return -1;
	}

}
