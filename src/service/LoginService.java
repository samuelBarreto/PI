package service;

import dao.LoginDAO;
import model.Contato;

public class LoginService {
	
   LoginDAO dao = new LoginDAO();
		
	public Contato carregar(int Contato){
		return dao.carregar(Contato);
	}

}
