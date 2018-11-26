package dao;                                                                                                                          
                                                                                                                                       
import java.sql.Connection;                                                                                                            
import java.sql.PreparedStatement;                                                                                                    
import java.sql.ResultSet;                                                                                                            
import java.sql.SQLException;                                                                                                          
                                                                                                                                       
import model.Login;                                                                                                                  
import model.Contato;

public class LoginDAO {                                                                                                                                                                                                                                                     
public Contato carregar(int id) {                                                                                                  
 Contato contato = new Contato();                                                                                              
 contato.setId(id);                                                                                                            
 String sqlSelect = "SELECT email FROM contato WHERE  email = ? senha= ?";                                              
                                                             
 try (Connection conn = ConnectionFactory.obterConexao();                                                                      
   PreparedStatement stm = conn.prepareStatement(sqlSelect);) {                                                          
  stm.setInt(1, contato.getId());                                                                                            
  try (ResultSet rs = stm.executeQuery();) {                                                                                
   if (rs.next()) {                                                                                                      
	   stm.setString(1, contato.getEmail());                                                                                      
	   stm.setString(2, contato.getSenha());                                                                                      

   } else {                                                                                                              
    contato.setId(-1);                                                                                                
    contato.setEmail(null);                                                                                            
    contato.setSenha(null);                                                                                            

   }                                                                                                                      
  } catch (SQLException e) {                                                                                                
   e.printStackTrace();                                                                                                  
  }                                                                                                                          
 } catch (SQLException e1) {                                                                                                    
  System.out.print(e1.getStackTrace());                                                                                      
 }                                                                                                                              
 return contato;                                                                                                                
}                                                                                                                                  
                                                                                                                                       
} 
