package command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Feriado;
import service.FeriadoService;

public class ListarFeriadosPuxar implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		FeriadoService feriadoService = new FeriadoService();
		List<Feriado> products = feriadoService.listarFeriados();
		
	    String json = new Gson().toJson(products);

	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(json);		
	}
}
