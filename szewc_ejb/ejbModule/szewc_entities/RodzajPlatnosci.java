package szewc_entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the rodzaj_platnosci database table.
 * 
 */
@Entity
@Table(name="rodzaj_platnosci")
@NamedQuery(name="RodzajPlatnosci.findAll", query="SELECT r FROM RodzajPlatnosci r")
public class RodzajPlatnosci implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_rodzaj_platnosci")
	private int idRodzajPlatnosci;

	@Column(name="rodzaj_platnosci")
	private String rodzajPlatnosci;

	//bi-directional many-to-one association to Naprawa
	@OneToMany(mappedBy="rodzajPlatnosci")
	private List<Naprawa> naprawas;

	public RodzajPlatnosci() {
	}

	public int getIdRodzajPlatnosci() {
		return this.idRodzajPlatnosci;
	}

	public void setIdRodzajPlatnosci(int idRodzajPlatnosci) {
		this.idRodzajPlatnosci = idRodzajPlatnosci;
	}

	public String getRodzajPlatnosci() {
		return this.rodzajPlatnosci;
	}

	public void setRodzajPlatnosci(String rodzajPlatnosci) {
		this.rodzajPlatnosci = rodzajPlatnosci;
	}

	public List<Naprawa> getNaprawas() {
		return this.naprawas;
	}

	public void setNaprawas(List<Naprawa> naprawas) {
		this.naprawas = naprawas;
	}

	public Naprawa addNaprawa(Naprawa naprawa) {
		getNaprawas().add(naprawa);
		naprawa.setRodzajPlatnosci(this);

		return naprawa;
	}

	public Naprawa removeNaprawa(Naprawa naprawa) {
		getNaprawas().remove(naprawa);
		naprawa.setRodzajPlatnosci(null);

		return naprawa;
	}

}