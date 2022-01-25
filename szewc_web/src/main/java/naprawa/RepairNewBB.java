package naprawa;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import sze_DAO.RepairDAO;
import szewc_entities.Naprawa;
import szewc_entities.RodzajNaprawy;
import szewc_entities.RodzajPlatnosci;
import szewc_entities.Uzytkownik;


@Named
@ViewScoped
public class RepairNewBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_MAIN_BACK = "main?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private Naprawa naprawa = new Naprawa();
	private Naprawa loaded = null;

	@EJB
	RepairDAO repairDAO;

	@Inject
	FacesContext context;

	@Inject
	Flash flash;

	public Naprawa getNaprawa() {
		return naprawa;
	}

	public void onLoad() throws IOException {

		loaded = (Naprawa) flash.get("naprawa");

		if (loaded != null) {
			naprawa = loaded;

		} 

	}

	public String saveRepair() {
		
		
		try {
			if (naprawa.getIdNaprawa() == 0) {
				
				repairDAO.create(naprawa);
			} else {
				
				repairDAO.merge(naprawa);
			}
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wystąpił błąd podczas zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}

		return PAGE_MAIN_BACK;
	}
	
	@PostConstruct
	public void init() {
		naprawa.setRodzajNaprawy(new RodzajNaprawy());
		naprawa.setRodzajPlatnosci(new RodzajPlatnosci());
		naprawa.setData(new Date());
	}
	
	}