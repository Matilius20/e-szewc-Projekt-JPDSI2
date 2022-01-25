package sze_DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import szewc_entities.Uzytkownik;

@Stateless
public class UserDAO {
	
	@PersistenceContext
	EntityManager em;
	
	public void create(Uzytkownik uzytkownik) {
		em.persist(uzytkownik);
	}

	public Uzytkownik  merge(Uzytkownik  uzytkownik) {
		return em.merge(uzytkownik);
	}

	public void remove(Uzytkownik  uzytkownik) {
		em.remove(em.merge(uzytkownik));
	}

	public Uzytkownik  find(Object id) {
		return em.find(Uzytkownik .class, id);
	}
	public List<Uzytkownik> getFullList() {
		List<Uzytkownik> list = null;

		Query query = em.createQuery("select p from Person p");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<Uzytkownik> getList(Map<String, Object> searchParams) {
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

	public Uzytkownik login(String login, String haslo) {
		Uzytkownik u = new Uzytkownik();
		try {
			u = (Uzytkownik) em.createQuery("from Uzytkownik where login = :login and haslo= :haslo")
					.setParameter("login", haslo).setParameter("haslo", haslo).getSingleResult();
		} catch (NoResultException e) {
			u = null;
		}
		return u;
	}

public List<String> getUserRolesFromDatabase(Uzytkownik user) {
		
		ArrayList<String> roles = new ArrayList<String>();
		
		if (user.getRola().equals("1")) {
			roles.add("Szewc");
		}
		if (user.getRola().equals("2")) {
			roles.add("Klient");
		}		
		return roles;
	}
	

}
