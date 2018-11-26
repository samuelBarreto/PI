package service;

import java.io.IOException;
import java.util.ArrayList;

import dao.FeriadoDAO;
import model.Feriado;



public class FeriadoService {
	FeriadoDAO dao = new FeriadoDAO();
	
	public int criar(Feriado feriado) throws IOException {
		return dao.criar(feriado);
	}
	
	public void atualizar(Feriado contato) throws IOException{
		dao.atualizar(contato);
	}
	
	public void excluir(int id) throws IOException{
		dao.excluir(id);
	}
	
	public Feriado carregar(int id) throws IOException{
		return dao.carregar(id);
	}
    
	public ArrayList<Feriado> listarFeriados() throws IOException{
		return dao.listarFeriados();
	}
	public ArrayList<Feriado> listarFeriados(String chave) throws IOException{
		return dao.listarFeriados(chave);
	}
}
