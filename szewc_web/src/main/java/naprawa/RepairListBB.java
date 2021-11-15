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
	
	public List<Naprawa> getList(){
		return repairDAO.getFullList();
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
