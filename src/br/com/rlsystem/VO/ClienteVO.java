package br.com.rlsystem.VO;

import br.com.rlsystem.DAO.Categoria;

public class ClienteVO {
	private int id;
	private String nome;
	private String email;
	private Integer idade;
	private String sobrenome;
	private Categoria cat;
	private String idCategoria;
	
	
	public String getIdCategoria() {
		return idCategoria;
	}
	
	public void setIdCategoria(String idCategoria) {
		this.idCategoria = idCategoria;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	public Categoria getCat() {
			return cat;
	}
	
	public void setCat(Categoria cat) {
		this.cat = cat;
	}

	

}
