package szewc_entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the uzytkownik database table.
 * 
 */
@Entity
@NamedQuery(name="Uzytkownik.findAll", query="SELECT u FROM Uzytkownik u")
public class Uzytkownik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_uzytkownik")
	private int idUzytkownik;

	private String haslo;

	private String imie;

	private String login;

	private String nazwisko;

	@Column(name="numer_telefonu")
	private String numerTelefonu;

	//bi-directional many-to-one association to Naprawa
	@OneToMany(mappedBy="uzytkownik")
	private List<Naprawa> naprawas;

	//bi-directional many-to-one association to Rola
	@ManyToOne
	@JoinColumn(name="id_rola")
	private Rola rola;

	public Uzytkownik() {
	}

	public int getIdUzytkownik() {
		return this.idUzytkownik;
	}

	public void setIdUzytkownik(int idUzytkownik) {
		this.idUzytkownik = idUzytkownik;
	}

	public String getHaslo() {
		return this.haslo;
	}

	public void setHaslo(String haslo) {
		this.haslo = haslo;
	}

	public String getImie() {
		return this.imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNazwisko() {
		return this.nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public String getNumerTelefonu() {
		return this.numerTelefonu;
	}

	public void setNumerTelefonu(String numerTelefonu) {
		this.numerTelefonu = numerTelefonu;
	}

	public List<Naprawa> getNaprawas() {
		return this.naprawas;
	}

	public void setNaprawas(List<Naprawa> naprawas) {
		this.naprawas = naprawas;
	}

	public Naprawa addNaprawa(Naprawa naprawa) {
		getNaprawas().add(naprawa);
		naprawa.setUzytkownik(this);

		return naprawa;
	}

	public Naprawa removeNaprawa(Naprawa naprawa) {
		getNaprawas().remove(naprawa);
		naprawa.setUzytkownik(null);

		return naprawa;
	}

	public Rola getRola() {
		return this.rola;
	}

	public void setRola(Rola rola) {
		this.rola = rola;
	}

}