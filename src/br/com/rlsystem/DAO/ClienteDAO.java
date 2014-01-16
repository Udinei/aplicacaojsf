package br.com.rlsystem.DAO;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import br.com.rlsystem.VO.ClienteVO;



public class ClienteDAO extends DAO {
 
//	private static Cliente clienteEnt = new Cliente();
	private static Categoria cat = new Categoria();
	
	public boolean salvar(ClienteVO cliente){
		EntityManager em = getEntityManager().createEntityManager();
		boolean retorno = false;
		//Categoria cat = new Categoria();
		//cat.setId(1);
		Cliente clienteEnt = new Cliente();	
		clienteEnt.setNome(cliente.getNome());
		clienteEnt.setEmail(cliente.getEmail());
		clienteEnt.setIdade(cliente.getIdade());
		clienteEnt.setSobrenome(cliente.getSobrenome());
		
		clienteEnt.setCat(cliente.getCat());
		em.merge(clienteEnt);
		//
		
		try{
			em.getTransaction().begin();
			em.persist(clienteEnt);
            em.getTransaction().commit();
			retorno = true;
		}catch (Exception e) {
			e.getCause();
			em.getTransaction().rollback();
		}
		
		return retorno;
	}
	
	public ClienteVO getById(int idCliente){
		EntityManager em = getEntityManager().createEntityManager();
		return em.find(ClienteVO.class, idCliente);
	}
	
	public boolean update(ClienteVO cliente){
		EntityManager em = getEntityManager().createEntityManager();
		boolean retorno = true;
		
		try{
			em.getTransaction().begin();
				Cliente c = em.find(Cliente.class, cliente.getId());
				if(c != null){
					c.setNome(cliente.getNome());
					c.setSobrenome(cliente.getSobrenome());
					c.setEmail(cliente.getEmail());
					c.setIdade(cliente.getIdade());
					c.setCat(cliente.getCat());
			em.getTransaction().commit();
			}else{
				retorno = false;
			}
		} catch(Exception e){
			em.getTransaction().rollback();
		}
		
		return retorno;
	}
	
	public boolean delete(ClienteVO cliente){
		EntityManager em = getEntityManager().createEntityManager();
		boolean retorno = true;
		
		try{
			em.getTransaction().begin();
			Cliente c = em.find(Cliente.class, cliente.getId());
			if(c != null){
				em.remove(c);
				em.getTransaction().commit();
			}else{
				retorno = false;
			}
			
					
		} catch(Exception e){
			em.getTransaction().rollback();
		}
		
		return retorno;
	}
	
	public List<ClienteVO> exibir_by_name(String nome){
		EntityManager em = getEntityManager().createEntityManager();
		
		Criteria criteria = getSession().createCriteria(Cliente.class);
		//criteria.add(Restrictions.eq("nome", "Rafael"));
		//criteria.add(Restrictions.like("nome", "%R%"));
		//criteria.add(Restrictions.gt("ordem", 0));
		//gt() : maior que
		//ge() : maior ou igual que
		//lt() : menor que
		//le() : menor ou igual que
		//Criterion cr1 = Restrictions.like("nome", "%R%");
		//Criterion cr2 =  Restrictions.like("nome", "%T%");
		
		//LogicalExpression exp = Restrictions.or(cr1, cr2);
		
		//criteria.add(exp);
		
		criteria.add(Restrictions.like("nome", "%"+ nome +"%"));
		
		return criteria.list();
	}

	public List<ClienteVO> getAll(){
		EntityManager em = getEntityManager().createEntityManager();
		
		try{
			Query q = em.createQuery("select object(c) from Cliente as c");
			   
			List<Cliente> lista = new ArrayList<Cliente>();
			List<ClienteVO> listClienteVO = new ArrayList<ClienteVO>();
			
			lista = q.getResultList();
			
			    int i;
			    for(i=0; i < lista.size(); i++){
			    	ClienteVO vo = new ClienteVO();
			    	vo.setId(lista.get(i).getId());
			    	vo.setNome(lista.get(i).getNome());
			    	vo.setEmail(lista.get(i).getEmail());
			    	vo.setIdade(lista.get(i).getIdade());
			    	vo.setCat(lista.get(i).getCat());
			    	listClienteVO.add(vo);
			    }
				
					return listClienteVO;
				
			
		} finally{
			em.close();
		}
	}
}