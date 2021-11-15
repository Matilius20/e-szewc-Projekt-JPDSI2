package naprawa;

import java.io.IOException;
import java.io.Serializable;

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


@Named
@ViewScoped
public class RepairEditBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_REPAIR_LIST = "repairList?faces-redirect=true";
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

		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "B³êdne u¿ycie systemu", null));
		}

	}

	public String saveData() {
		if (loaded == null) {
			return PAGE_STAY_AT_THE_SAME;
		}
		
		try {
			if (naprawa.getIdNaprawa() == 0) {
				
				repairDAO.create(naprawa);
			} else {
				
				repairDAO.merge(naprawa);
			}
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wyst¹pi³ b³¹d podczas zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}

		return PAGE_REPAIR_LIST;
	}
	}

