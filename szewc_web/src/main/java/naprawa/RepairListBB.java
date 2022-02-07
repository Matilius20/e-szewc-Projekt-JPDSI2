package naprawa;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import sze_DAO.RepairDAO;
import szewc_entities.Naprawa;
import szewc_entities.Uzytkownik;

@Named
@RequestScoped
public class RepairListBB {
	private static final String PAGE_REPAIR_EDIT = "repairEdit?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;
	
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;

	@EJB
	RepairDAO repairDAO;
	
	private String nazwisko;
	
	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}
	
	public List<Naprawa> getList(){
		return repairDAO.getFullList();
	}
	
	public List<Naprawa> getFullList(){
		List<Naprawa> list = null;
		

		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		if (nazwisko != null && nazwisko.length() > 0){
			searchParams.put("nazwisko", nazwisko);
		}
		
		list = repairDAO.getList(searchParams);
		
		return list;
	}
	

	public String editRepair(Naprawa naprawa){

		flash.put("naprawa", naprawa);
		return PAGE_REPAIR_EDIT;
	}
	

	public String deleteRepair(Naprawa naprawa){
		repairDAO.remove(naprawa);
		return PAGE_STAY_AT_THE_SAME;
	}
}
