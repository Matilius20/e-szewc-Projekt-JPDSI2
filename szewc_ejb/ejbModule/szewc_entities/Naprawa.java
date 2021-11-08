package szewc_entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the naprawa database table.
 * 
 */
@Entity
@NamedQuery(name="Naprawa.findAll", query="SELECT n FROM Naprawa n")
public class Naprawa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_naprawa")
	private int idNaprawa;

	private float cena;

	@Temporal(TemporalType.DATE)
	private Date data;

	@Lob
	private String status;

	//bi-directional many-to-one association to RodzajNaprawy
	@ManyToOne
	@JoinColumn(name="id_rodzaj_naprawy")
	private RodzajNaprawy rodzajNaprawy;

	//bi-directional many-to-one association to RodzajPlatnosci
	@ManyToOne
	@JoinColumn(name="id_rodzaj_platnosci")
	private RodzajPlatnosci rodzajPlatnosci;

	//bi-directional many-to-one association to Uzytkownik
	@ManyToOne
	@JoinColumn(name="id_uzytkownik")
	private Uzytkownik uzytkownik;

	public Naprawa() {
	}

	public int getIdNaprawa() {
		return this.idNaprawa;
	}

	public void setIdNaprawa(int idNaprawa) {
		this.idNaprawa = idNaprawa;
	}

	public float getCena() {
		return this.cena;
	}

	public void setCena(float cena) {
		this.cena = cena;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public RodzajNaprawy getRodzajNaprawy() {
		return this.rodzajNaprawy;
	}

	public void setRodzajNaprawy(RodzajNaprawy rodzajNaprawy) {
		this.rodzajNaprawy = rodzajNaprawy;
	}

	public RodzajPlatnosci getRodzajPlatnosci() {
		return this.rodzajPlatnosci;
	}

	public void setRodzajPlatnosci(RodzajPlatnosci rodzajPlatnosci) {
		this.rodzajPlatnosci = rodzajPlatnosci;
	}

	public Uzytkownik getUzytkownik() {
		return this.uzytkownik;
	}

	public void setUzytkownik(Uzytkownik uzytkownik) {
		this.uzytkownik = uzytkownik;
	}

}