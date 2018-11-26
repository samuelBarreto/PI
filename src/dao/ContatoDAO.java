package dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Contato;

public class ContatoDAO {
	public int criar(Contato contato) throws IOException {
		String sqlInsert = "INSERT INTO contato(nome, cidade, email, senha) VALUES (?, ?, ?,?)";
		// pega a conexão em um try normal para que ela não seja fechada
		try {
			Connection conn = ConnectionFactory.obterConexao();
			// usando o try with resources do Java 7, que fecha o que abriu
			try (PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
				stm.setString(1, contato.getNome());
				stm.setString(2, contato.getCidade());
				stm.setString(3, contato.getEmail());
				stm.setString(4, contato.getSenha());
				stm.execute();
				String sqlQuery = "SELECT LAST_INSERT_ID()";
				try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery); 
						ResultSet rs = stm2.executeQuery();) {
					if (rs.next()) {
						contato.setId(rs.getInt(1));
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
		return contato.getId();
	}

	// Vai fazer o update do contato
	public void atualizar(Contato contato) throws IOException {
		String sqlUpdate = "UPDATE contato SET nome=?, cidade=?, email=? WHERE id=?";
		// pega a conexão em um try normal para que ela não seja fechada
		try {
			Connection conn = ConnectionFactory.obterConexao();
			// usando o try with resources do Java 7, que fecha o que abriu
			try (PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
				stm.setString(1, contato.getNome());
				stm.setString(2, contato.getCidade());
				stm.setString(3, contato.getEmail());
				stm.setInt(4, contato.getId());
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
		String sqlDelete = "DELETE FROM contato WHERE id = ?";
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

	public Contato carregar(int id) throws IOException {
		Contato contato = new Contato();
		contato.setId(id);
		String sqlSelect = "SELECT nome, cidade, email FROM contato WHERE contato.id = ?";
		// pega a conexão em um try normal para que ela não seja fechada
		try {
			Connection conn = ConnectionFactory.obterConexao();
			// usando o try with resources do Java 7, que fecha o que abriu
			try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
				stm.setInt(1, contato.getId());
				try (ResultSet rs = stm.executeQuery();) {
					if (rs.next()) {
						//contato.setId(rs.getInt("id"));
						contato.setNome(rs.getString("nome"));
						contato.setCidade(rs.getString("cidade"));
						contato.setEmail(rs.getString("email"));
						//contato.setSenha(rs.getString("senha"));
						
						// popula a data de nascimento do contato, fazendo a conversao
						//Date data = Date.from(null);
						//data.setTime(rs.getDate("dataNascimento").getTime());
						//contato.setDataNascimento(data);
						// Calendar data = Calendar.getInstance();
						// data.setTime(rs.getDate("dataNascimento"));

					} else {
						contato.setId(-1);
						contato.setNome(null);
						contato.setCidade(null);
						contato.setEmail(null);
						contato.setSenha(null);
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
		return contato;
	}

	public ArrayList<Contato> listarContatos() throws IOException {
		Contato contato;
		ArrayList<Contato> lista = new ArrayList<>();
		// pega a conexão em um try normal para que ela não seja fechada
		try {
			Connection conn = ConnectionFactory.obterConexao();
			String sqlSelect = "SELECT id, nome,cidade, email FROM contato";
			// usando o try with resources do Java 7, que fecha o que abriu
			try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
				try (ResultSet rs = stm.executeQuery();) {
					while (rs.next()) {
						contato = new Contato();
						contato.setId(rs.getInt("id"));
						contato.setNome(rs.getString("nome"));
						contato.setCidade(rs.getString("cidade"));
						contato.setEmail(rs.getString("email"));

						// popula a data de nascimento do contato, fazendo a conversao
						// Date data = Date.from(null);
						// data.setTime(rs.getDate("dataNascimento").getTime());
						// contato.setDataNascimento(data);
						// Date date = rs.getDate("dataNascimento");
						// SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
						// contato.setDataNascimento(formato.format(date));

						// Aqui vai adicionar os contatos na lista
						lista.add(contato);
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

	public ArrayList<Contato> listarContatos(String chave) throws IOException {
		Contato contato;
		ArrayList<Contato> lista = new ArrayList<>();
		String sqlSelect = "SELECT id, nome, cidade, email FROM contato where upper(nome) like ?";
		// pega a conexão em um try normal para que ela não seja fechada
		try {
			Connection conn = ConnectionFactory.obterConexao();
			// usando o try with resources do Java 7, que fecha o que abriu
			try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
				stm.setString(1, "%" + chave.toUpperCase() + "%");
				try (ResultSet rs = stm.executeQuery();) {
					while (rs.next()) {
						contato = new Contato();
						contato.setId(rs.getInt("id"));
						contato.setNome(rs.getString("nome"));
						contato.setCidade(rs.getString("cidade"));
						contato.setEmail(rs.getString("email"));

						// popula a data de nascimento do contato, fazendo a conversao
						// Date data = Date.from(null);
						// data.setTime(rs.getDate("dataNascimento").getTime());
						// contato.setDataNascimento(data);
						lista.add(contato);
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
	
	public boolean validar(Contato contato) {
		String sqlSelect = "SELECT email, senha FROM contato "
				+ "WHERE email = ? and senha = ?";
		// pega a conexão em um try normal para que ela não seja fechada
		try {
			Connection conn = ConnectionFactory.obterConexao();
			// usando o try with resources do Java 7, que fecha o que abriu
			try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
				stm.setString(1, contato.getEmail());
				stm.setString(2, contato.getSenha());
				try (ResultSet rs = stm.executeQuery();) {
					if (rs.next()) {
						return true;
					} else {
						return false;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (SQLException e1) {
				System.out.print(e1.getStackTrace());
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return false;
	}
	
	
}