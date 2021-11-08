package uzytkownik;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.http.HttpSession;

import sze_DAO.UserDAO;
import szewc_entities.Uzytkownik;



@Named
@RequestScoped
public class UserListBB {
	private static final String PAGE_USER_EDIT = "userEdit?faces-redirect=true";
	private static final String PAGE_USER_NEW = "userNew?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String nazwisko;
		
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	UserDAO userDAO;
		
	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public List<Uzytkownik> getFullList(){
		return userDAO.getFullList();
	}

	public List<Uzytkownik> getList(){
		List<Uzytkownik> list = null;
		

		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		if (nazwisko != null && nazwisko.length() > 0){
			searchParams.put("nazwisko", nazwisko);
		}
		
		list = userDAO.getList(searchParams);
		
		return list;
	}

	public String newUzytkownik(){
		Uzytkownik uzytkownik = new Uzytkownik();
		

		flash.put("uzytkownik", uzytkownik);
		
		return PAGE_USER_NEW;
	}

	public String editUzytkownik(Uzytkownik uzytkownik){
	
		flash.put("uzytkownik", uzytkownik);
		
		return PAGE_USER_EDIT;
	}

	public String removeUzytkownik(Uzytkownik uzytkownik){
		userDAO.remove(uzytkownik);
		return PAGE_STAY_AT_THE_SAME;
	}
}
