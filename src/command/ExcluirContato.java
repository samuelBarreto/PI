package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Contato;
import service.ContatoService;

public class ExcluirContato implements Command {

	@Override
	public void executar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pId = request.getParameter("id");
		
		int id = -1;
		try {
			id = Integer.parseInt(pId);
		} catch (NumberFormatException e) {}


		Contato contato = new Contato();
		contato.setId(id);
		ContatoService cont = new ContatoService();
		
		RequestDispatcher view = null;
		HttpSession session = request.getSession();

		cont.excluir(contato.getId());
		@SuppressWarnings("unchecked")
		ArrayList<Contato> lista = (ArrayList<Contato>) session
				.getAttribute("lista");
		lista.remove(busca(contato, lista));
		session.setAttribute("lista", lista);
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
