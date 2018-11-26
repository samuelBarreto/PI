package command;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import model.Feriado;
import service.FeriadoService;

public class AdicionarFeriado implements Command {


	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		// busca o writer
		PrintWriter out = response.getWriter();

		String pId = request.getParameter("id");
		String nome = request.getParameter("nome");
		String pInicio = request.getParameter("inicio");
		String pFim = request.getParameter("fim");

		int id = -1;
		try {
			id = Integer.parseInt(pId);
		} catch (NumberFormatException e) {}
		
		Date inicio = null;
		Date fim = null;
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		try {
			inicio = df.parse(pInicio);
			fim =  df.parse(pFim);
		} catch (ParseException e) {
			
		
		}

		Feriado feriado = new Feriado();
		feriado.setId(id);
		feriado.setNome(nome);
		feriado.setInicio(inicio);
		feriado.setFim(fim);

		FeriadoService cont = new FeriadoService();

		RequestDispatcher view = null;
		HttpSession session = request.getSession();

		cont.criar(feriado);
		ArrayList<Feriado> lista = new ArrayList<>();
		lista.add(feriado);
		
		/*response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out1 = response.getWriter();
        out1.write(new Gson().toJson(lista));
		session.setAttribute("lista", lista);*/
		view = request.getRequestDispatcher("index.jsp");

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
