package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import model.Feriado;

public class FeriadoDAO {
	public int criar(Feriado feriado) throws IOException {
		String sqlInsert = "INSERT INTO feriado(nome, inicio,fim) VALUES (?, ?,? )";
		// pega a conexão em um try normal para que ela não seja fechada
		try {
			Connection conn = ConnectionFactory.obterConexao();
			// usando o try with resources do Java 7, que fecha o que abriu
			try (PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
				stm.setString(1, feriado.getNome());
				//stm.setString(2, feriado.getCor());
				stm.setDate(2, new java.sql.Date(feriado.getInicio().getTime()));
				stm.setDate(3, new java.sql.Date(feriado.getFim().getTime()));
				stm.execute();
				String sqlQuery = "SELECT LAST_INSERT_ID()";
				try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery); 
						ResultSet rs = stm2.executeQuery();) {
					if (rs.next()) {
						feriado.setId(rs.getInt(1));
					}
				} catch (SQLException e) {
					e.printStackTrace();
					throw new IOException();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new IOException();
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new IOException();
		}
		return feriado.getId();
	}

	// Vai fazer o update do contato
	public void atualizar(Feriado feriado) throws IOException {
		String sqlUpdate = "UPDATE feriado SET nome=?, inicio=?, fim=? WHERE id=?";
		// pega a conexão em um try normal para que ela não seja fechada
		try {
			Connection conn = ConnectionFactory.obterConexao();
			// usando o try with resources do Java 7, que fecha o que abriu
			try (PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
				stm.setString(1, feriado.getNome());
				stm.setDate(2, new java.sql.Date(feriado.getInicio().getTime()));
				stm.setDate(3, new java.sql.Date(feriado.getFim().getTime()));
				
				stm.setInt(4, feriado.getId());
				stm.execute();
			} catch (Exception e) {
				e.printStackTrace();
				throw new IOException();
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new IOException();
		}
	}

	// Aqui vai excluir o contato pegando somente o ID
	public void excluir(int id) throws IOException {
		String sqlDelete = "DELETE FROM feriado WHERE id = ?";
		// pega a conexão em um try normal para que ela não seja fechada
		try {
			Connection conn = ConnectionFactory.obterConexao();
			// usando o try with resources do Java 7, que fecha o que abriu
			try (PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
				stm.setInt(1, id);
				stm.execute();
			} catch (Exception e) {
				e.printStackTrace();
				throw new IOException();
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new IOException();
		}
	}

	public Feriado carregar(int id) throws IOException {
		Feriado feriado = new Feriado();
		feriado.setId(id);
		String sqlSelect = "SELECT nome, inicio,fim FROM feriado WHERE feriado.id = ?";
		// pega a conexão em um try normal para que ela não seja fechada
		try {
			Connection conn = ConnectionFactory.obterConexao();
			// usando o try with resources do Java 7, que fecha o que abriu
			try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
				stm.setInt(1, feriado.getId());
				try (ResultSet rs = stm.executeQuery();) {
					if (rs.next()) {
						//feriado.setId(rs.getInt("id"));
						feriado.setNome(rs.getString("nome"));
						feriado.setInicio(rs.getDate("inicio"));
						feriado.setFim(rs.getDate("fim"));
					
						
						// popula a data de nascimento do contato, fazendo a conversao
						//Date data = Date.from(null);
						//data.setTime(rs.getDate("dataNascimento").getTime());
						//contato.setDataNascimento(data);
						// Calendar data = Calendar.getInstance();
						// data.setTime(rs.getDate("dataNascimento"));

					} else {
						feriado.setId(-1);
						feriado.setNome(null);
						feriado.setInicio(null);
						feriado.setFim(null);
					}
				} catch (SQLException e) {
					e.printStackTrace();
					throw new IOException();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new IOException();
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new IOException();
		}
		return feriado;
	}

	public ArrayList<Feriado> listarFeriados() throws IOException {
		Feriado feriado;
		ArrayList<Feriado> lista = new ArrayList<>();
		// pega a conexão em um try normal para que ela não seja fechada
		try {
			Connection conn = ConnectionFactory.obterConexao();
			String sqlSelect = "SELECT id, nome,inicio,fim FROM feriado";
			// usando o try with resources do Java 7, que fecha o que abriu
			try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
				try (ResultSet rs = stm.executeQuery();) {
					while (rs.next()) {
						feriado = new Feriado();
						feriado.setId(rs.getInt("id"));
						feriado.setNome(rs.getString("nome"));
						feriado.setInicio(rs.getDate("inicio"));
						feriado.setFim(rs.getDate("fim"));

						// popula a data de nascimento do contato, fazendo a conversao
						// Date data = Date.from(null);
						// data.setTime(rs.getDate("dataNascimento").getTime());
						// contato.setDataNascimento(data);
						// Date date = rs.getDate("dataNascimento");
						// SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
						// contato.setDataNascimento(formato.format(date));

						// Aqui vai adicionar os contatos na lista
						lista.add(feriado);
					}
					
				} catch (SQLException e) {
					e.printStackTrace();
					throw new IOException();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new IOException();
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new IOException();
		}
		return lista;
	}

	public ArrayList<Feriado> listarFeriados(String chave) throws IOException {
		Feriado feriado;
		ArrayList<Feriado> lista = new ArrayList<>();
		String sqlSelect = "SELECT id, nome, inicio, fim FROM feriado where upper(nome) like ?";
		// pega a conexão em um try normal para que ela não seja fechada
		try {
			Connection conn = ConnectionFactory.obterConexao();
			// usando o try with resources do Java 7, que fecha o que abriu
			try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
				stm.setString(1, "%" + chave.toUpperCase() + "%");
				try (ResultSet rs = stm.executeQuery();) {
					while (rs.next()) {
						feriado = new Feriado();
						feriado.setId(rs.getInt("id"));
						feriado.setNome(rs.getString("nome"));
						feriado.setInicio(rs.getDate("inicio"));
						feriado.setFim(rs.getDate("fim"));

						// popula a data de nascimento do contato, fazendo a conversao
						// Date data = Date.from(null);
						// data.setTime(rs.getDate("dataNascimento").getTime());
						// contato.setDataNascimento(data);
						lista.add(feriado);
					}
				} catch (SQLException e) {
					e.printStackTrace();
					throw new IOException();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new IOException();
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			throw new IOException();
		}
		return lista;
	}
}