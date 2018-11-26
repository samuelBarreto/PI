package command;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ConnectionFactory;
import model.Contato;
import service.ContatoService;

public class RecuperarSenha implements Command {


	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			Connection conn = ConnectionFactory.obterConexao();		
			
				String email = request.getParameter("email");
				String senha = request.getParameter("senha");
			
				PreparedStatement ps = conn.prepareStatement("Update contato SET senha= ? where email=?");
				ps.setString(2, email);
				ps.setString(1, senha);
				
				int i = ps.executeUpdate();
				if(i>0) {
					response.sendRedirect("SenhaAlterada.jsp");				
				}
				else{
					response.sendRedirect("error.jsp");
				}
		}
		catch(Exception e){
			e.printStackTrace();
			
		}
	}
}	