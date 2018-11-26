package model;

import java.io.Serializable;

	public class Login implements Serializable {
	    private static final long serialVersionUID = 1L;
	    
	    private int id;
	    private String email;
	    private String senha;

	    public String getEmail() {
	   	 return email;
	   	}

	   	public void setEmail(String email) {
	   	 this.email = email;
	   	}
	    
	    public String getSenha() {
	    	 return senha;
	    	}

	    	public void setSenha(String senha) {
	    	 this.senha = senha;
	    	}
	    		public void Email() {
	}

	public int getId() {
	 return id;
	}

	public void setId(int id) {
	 this.id = id;
	}

	public String getEmail(String email) {
	 return email;
	}

	public void setEmail() {
	 this.email = email;
	}

	public String getSenha(String senha) {
	 return senha;
	}

	public void set() {
	 this.senha = senha;
	}

	@Override
	public String toString() {
	 return "Contato [id=" + "id" + "\n" + 
			   "Login: " + email + "\n" +
			   "Senha: " + senha + "\n";
	}

	@Override
	public boolean equals(Object obj) {
	 if (this == obj)
	  return true;
	 if (obj == null)
	  return false;
	 if (getClass() != obj.getClass())
	  return false;
	 if (email == null) {
			if (email == null)
				return false;
		} else if (email == null)
			return false;
	return false;
	 }

}
