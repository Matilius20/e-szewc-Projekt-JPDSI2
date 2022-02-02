package sze_DAO;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;



import szewc_entities.Naprawa;
import szewc_entities.Uzytkownik;

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
	
	public List<Uzytkownik> getFullList(Map<String, Object> searchParams) {
		List<Uzytkownik> list = null;


		String select = "select u ";
		String from = "from Uzytkownik u ";
		String where = "";
		String orderby = "order by u.nazwisko asc, u.imie";


		String nazwisko = (String) searchParams.get("nazwisko");
		if (nazwisko != null) {
			if (where.isEmpty()) {
				where = "where ";
			} else {
				where += "and ";
			}
			where += "u.nazwisko like :nazwisko ";
		}

		Query query = em.createQuery(select + from + where + orderby);


		if (nazwisko != null) {
			query.setParameter("nazwisko", nazwisko+"%");
		}

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}