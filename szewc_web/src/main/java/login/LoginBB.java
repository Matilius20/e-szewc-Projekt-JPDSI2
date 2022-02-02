
package login;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.simplesecurity.RemoteClient;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



import sze_DAO.UserDAO;
import szewc_entities.Uzytkownik;


 

 
@Named
@RequestScoped
public class LoginBB {
	private static final String PAGE_MAIN = "main?faces-redirect=true";
	private static final String PAGE_LOGIN = "/login";
	private static final String PAGE_STAY_AT_THE_SAME = null;
 
	private String login;
	private String haslo;
 
	public String getLogin() {
		return login;
	}
 
	public void setLogin(String login) {
		this.login = login;
	}
 
	public String getHaslo() {
		return haslo;
	}
 
	public void setHaslo(String haslo) {
		this.haslo = haslo;
	}
 
	@Inject
	UserDAO userDAO;
 
	public String doLogin() {
		FacesContext ctx = FacesContext.getCurrentInstance();

		Uzytkownik user = userDAO.login(login, haslo);
 

		if (user == null) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Niepoprawny login lub hasło", null));
			return PAGE_STAY_AT_THE_SAME;
		}
 
		RemoteClient<Uzytkownik> client = new RemoteClient<Uzytkownik>(); 
		client.setDetails(user);
 
		String role = user.getRola().getNazwaRoli();
 
		if (role != null) {
			client.getRoles().add(role);
		}
		HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
		client.store(request);
 
 
		return PAGE_MAIN;
	}
	public String doLogout(){
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(true);
		session.invalidate();
		return PAGE_LOGIN;
	}
 
}
 