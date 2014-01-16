package br.com.rlsystem.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


public class CategoriaDAO extends DAO{

	public void salvar(Categoria cat){
		EntityManager em = getEntityManager().createEntityManager();
		try{
			em.getTransaction().begin();
			em.persist(cat);
			em.getTransaction().commit();
		}catch (Exception e) {
			em.getTransaction().rollback();
		}
	}
	
	public Categoria getById(int idCat){
		EntityManager em = getEntityManager().createEntityManager();
		return em.find(Categoria.class, idCat);
	}
	
	public void update(Categoria cat){
		EntityManager em = getEntityManager().createEntityManager();
		
		try{
			em.getTransaction().begin();
			Categoria c = em.find(Categoria.class, cat.getId());
			c.setNome(cat.getNome());
			c.setOrdem(cat.getOrdem());
			em.getTransaction().commit();
		} catch(Exception e){
			em.getTransaction().rollback();
		}
	}
	
	public void delete(Categoria cat){
		EntityManager em = getEntityManager().createEntityManager();
		
		try{
			em.getTransaction().begin();
			Categoria c = em.find(Categoria.class, cat.getId());
			em.remove(c);
			em.getTransaction().commit();
		} catch(Exception e){
			em.getTransaction().rollback();
		}
	}

	public List<Categoria> getAll(){
		EntityManager em = getEntityManager().createEntityManager();
		
		try{
			Query q = em.createQuery("select object(c) from Categoria as c");
			return q.getResultList();
		} finally{
			em.close();
		}
	}
}
