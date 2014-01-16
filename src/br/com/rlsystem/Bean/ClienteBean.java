package br.com.rlsystem.Bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

import br.com.rlsystem.DAO.Categoria;
import br.com.rlsystem.DAO.CategoriaDAO;
import br.com.rlsystem.DAO.ClienteDAO;
import br.com.rlsystem.VO.ClienteVO;

@ManagedBean(name="cliBean")
@SessionScoped
public class ClienteBean {
	
	
	private ClienteVO clienteVO = new ClienteVO();
	private DataModel<ClienteVO> clientes;
	
	public String apagar(){
		String retorno = "erro";
		
		try{
			
			ClienteDAO dao = new ClienteDAO();
			if(dao.delete(clienteVO)){
				retorno = "listar";
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return retorno;
		
	}
	
	public void novoReg(){
		this.idCatSelectedCbox = null;
	   clienteVO = new ClienteVO();	
	}
	
	
	public void selecionarReg(){
		this.clienteVO = clientes.getRowData();
		idCatSelectedCbox = String.valueOf(clienteVO.getCat().getId());
		
	}
		
	
	public String update(){
		String retorno = "erro";
		
		try{
			
			ClienteDAO dao = new ClienteDAO();
			CategoriaDAO daoCat = new CategoriaDAO();
	        clienteVO.setCat(daoCat.getById(Integer.valueOf(idCatSelectedCbox)));
			if(dao.update(clienteVO)){
				retorno = "listar";
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return retorno;
		
	}
	
	public DataModel<ClienteVO> getClientes() {
		ClienteDAO dao = new ClienteDAO();
		
		List<ClienteVO> lista = dao.getAll();
		clientes = new ListDataModel<ClienteVO>(lista);
		return clientes;
	}


	public void setClientes(DataModel<ClienteVO> clientes) {
		this.clientes = clientes;
	}


	public ClienteVO getClienteVO() {
		return clienteVO;
	}


	public void setClienteVO(ClienteVO clienteVO) {
		this.clienteVO = clienteVO;
	}


	public String addUser(){
		String retorno = "erro";
		
		try{
			
			ClienteDAO dao = new ClienteDAO();
	        CategoriaDAO daoCat = new CategoriaDAO();
	   		clienteVO.setCat(daoCat.getById(Integer.valueOf(idCatSelectedCbox)));
			if(dao.salvar(clienteVO)){
				retorno = "listar";
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return retorno;
	}
	
	private String idCatSelectedCbox;

	public String getIdCatSelectedCbox() {
	    return idCatSelectedCbox;
	}

	public void setIdCatSelectedCbox(String idCatSelectedCbox) {
		this.idCatSelectedCbox = idCatSelectedCbox;
	}
	
	public List<SelectItem> getCboxCategorias() {
	    List<SelectItem> retVal = new ArrayList<SelectItem>();
	    CategoriaDAO daoCat = new CategoriaDAO();
	    List<Categoria> categiasList = daoCat.getAll() ;
	    
	    for (Categoria categoria : categiasList) {
	    	 retVal.add(new SelectItem(categoria.getId() ,categoria.getNome() ));
		}
	        return retVal;
	}
	
	
}
