package sze_DAO;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;



import szewc_entities.Naprawa;

@Stateless
public class RepairDAO {
	
	@PersistenceContext
	EntityManager em;
	
	public void create(Naprawa naprawa) {
		em.persist(naprawa);
	}

	public Naprawa merge(Naprawa  naprawa) {
		return em.merge(naprawa);
	}

	public void remove(Naprawa  naprawa) {
		em.remove(em.merge(naprawa));
	}

	public Naprawa  find(Object id) {
		return em.find(Naprawa .class, id);
	}
	public List<Naprawa> getFullList() {
		List<Naprawa> list = null;

		Query query = em.createQuery("SELECT n FROM Naprawa n");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}