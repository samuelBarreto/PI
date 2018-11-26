package command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Contato;
import service.ContatoService;

public class EditarContato implements Command {

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

		//Calendar cal = Calendar.getInstance();
		//cal setTime(dataNascimento);

	    Contato contato = new Contato();
		contato.setId(id);
		contato.setNome(nome);
		contato.setCidade(cidade);
		contato.setEmail(email);
		contato.setSenha(senha);
		
		ContatoService cont = new ContatoService();
		RequestDispatcher view = request.getRequestDispatcher("AlterarContato.jsp");
		
		 contato = cont.carregar(contato.getId());
		 request.setAttribute("contato", contato);
		 view = request.getRequestDispatcher("AlterarContato.jsp");
		 view.forward(request, response);
				
		/*ContatoService cont = new ContatoService();
		
		RequestDispatcher view = null;
		HttpSession session = request.getSession();
		
		contato = cont.carregar(contato.getId());
		request.setAttribute("pais", contato);
		view = request.getRequestDispatcher("AlterarContato.jsp");		
		
		view.forward(request, response);*/

	}

	public int busca(Contato contato, ArrayList<Contato> lista) {
		Contato to;
		for(int i = 0; i < lista.size(); i++){
			to = lista.get(i);
			if(to.getId() == contato.getId()){
				return i;
			}
		}
		return -1;
	}

}
