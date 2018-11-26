package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Feriado;
import service.FeriadoService;

public class ExcluirFeriado implements Command {

	@Override
	public void executar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pId = request.getParameter("id");
		
		int id = -1;
		try {
			id = Integer.parseInt(pId);
		} catch (NumberFormatException e) {
			
		}

		Feriado feriado = new Feriado();
		feriado.setId(id);
		FeriadoService feri = new FeriadoService();
		
		RequestDispatcher view = null;
		HttpSession session = request.getSession();

		feri.excluir(feriado.getId());
		@SuppressWarnings("unchecked")
		ArrayList<Feriado> lista = (ArrayList<Feriado>) session
				.getAttribute("lista");
		lista.remove(busca(feriado, lista));
		session.setAttribute("lista", lista);
		view = request.getRequestDispatcher("ListarFeriados.jsp");
		view.forward(request, response);

	}

	public int busca(Feriado feriado, ArrayList<Feriado> lista) {
		Feriado to;
		for (int i = 0; i < lista.size(); i++) {
			to = lista.get(i);
			if (to.getId() == feriado.getId()) {
				return i;
			}
		}
		return -1;
	}

}
