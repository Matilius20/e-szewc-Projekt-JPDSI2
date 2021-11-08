package szewc_entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the rola database table.
 * 
 */
@Entity
@NamedQuery(name="Rola.findAll", query="SELECT r FROM Rola r")
public class Rola implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_rola")
	private int idRola;

	@Column(name="nazwa_roli")
	private String nazwaRoli;

	//bi-directional many-to-one association to Uzytkownik
	@OneToMany(mappedBy="rola")
	private List<Uzytkownik> uzytkowniks;

	public Rola() {
	}

	public int getIdRola() {
		return this.idRola;
	}

	public void setIdRola(int idRola) {
		this.idRola = idRola;
	}

	public String getNazwaRoli() {
		return this.nazwaRoli;
	}

	public void setNazwaRoli(String nazwaRoli) {
		this.nazwaRoli = nazwaRoli;
	}

	public List<Uzytkownik> getUzytkowniks() {
		return this.uzytkowniks;
	}

	public void setUzytkowniks(List<Uzytkownik> uzytkowniks) {
		this.uzytkowniks = uzytkowniks;
	}

	public Uzytkownik addUzytkownik(Uzytkownik uzytkownik) {
		getUzytkowniks().add(uzytkownik);
		uzytkownik.setRola(this);

		return uzytkownik;
	}

	public Uzytkownik removeUzytkownik(Uzytkownik uzytkownik) {
		getUzytkowniks().remove(uzytkownik);
		uzytkownik.setRola(null);

		return uzytkownik;
	}

}