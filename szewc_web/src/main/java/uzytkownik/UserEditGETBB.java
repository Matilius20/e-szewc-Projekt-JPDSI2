package uzytkownik;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import sze_DAO.UserDAO;
import szewc_entities.Uzytkownik;

@Named
@ViewScoped
public class UserEditGETBB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String PAGE_USER_LIST = "userList?faces-redirect=true";
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private Uzytkownik uzytkownik = new Uzytkownik();
	private Uzytkownik loaded = null;

	@Inject
	FacesContext context;

	@EJB
	UserDAO userDAO;

	public Uzytkownik getUzytkownik() {
		return uzytkownik;
	}

	public void onLoad() throws IOException {
		if (!context.isPostback()) {
			if (!context.isValidationFailed() && uzytkownik.getIdUzytkownik() != 0) {
				loaded = userDAO.find(uzytkownik.getIdUzytkownik());
			}
			if (loaded != null) {
				uzytkownik = loaded;
			} else {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błędne użycie systemu", null));
			}
		}

	}

	public String saveData() {
		if (loaded == null) {
			return PAGE_STAY_AT_THE_SAME;
		}

		try {
			if (uzytkownik.getIdUzytkownik() == 0) {
				userDAO.create(uzytkownik);
			} else {
				userDAO.merge(uzytkownik);
			}
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "wystąpił błąd podczas zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}

		return PAGE_USER_LIST;
	}
}
