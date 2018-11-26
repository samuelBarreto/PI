package service;

import java.io.IOException;
import java.util.ArrayList;

import dao.ContatoDAO;
import model.Contato;

public class ContatoService {
	ContatoDAO dao = new ContatoDAO();
	
	public ContatoService() {
		ContatoDAO dao;
	}
	
	public int criar(Contato contato) throws IOException {
		return dao.criar(contato);
	}
	
	public int TirarPermissao(Contato contato) throws IOException {
		return dao.criar(contato);
	}
	
	public void atualizar(Contato contato) throws IOException{
		dao.atualizar(contato);
	}
	
	public void excluir(int id) throws IOException{
		dao.excluir(id);
	}
	
	public Contato carregar(int id) throws IOException{
		return dao.carregar(id);
	}
    
	public ArrayList<Contato> listarContatos() throws IOException{
		return dao.listarContatos();
	}
	public ArrayList<Contato> listarContatos(String chave) throws IOException{
		return dao.listarContatos(chave);
	}
	
	public boolean validar(Contato contato) {
		if(dao.validar(contato)) {
			return true;
		}else {
			return false;
		}
	}

}
