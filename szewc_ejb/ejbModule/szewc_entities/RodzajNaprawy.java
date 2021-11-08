package szewc_entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the rodzaj_naprawy database table.
 * 
 */
@Entity
@Table(name="rodzaj_naprawy")
@NamedQuery(name="RodzajNaprawy.findAll", query="SELECT r FROM RodzajNaprawy r")
public class RodzajNaprawy implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_naprawa")
	private int idNaprawa;

	@Column(name="rodzaj_naprawy")
	private String rodzajNaprawy;

	//bi-directional many-to-one association to Naprawa
	@OneToMany(mappedBy="rodzajNaprawy")
	private List<Naprawa> naprawas;

	public RodzajNaprawy() {
	}

	public int getIdNaprawa() {
		return this.idNaprawa;
	}

	public void setIdNaprawa(int idNaprawa) {
		this.idNaprawa = idNaprawa;
	}

	public String getRodzajNaprawy() {
		return this.rodzajNaprawy;
	}

	public void setRodzajNaprawy(String rodzajNaprawy) {
		this.rodzajNaprawy = rodzajNaprawy;
	}

	public List<Naprawa> getNaprawas() {
		return this.naprawas;
	}

	public void setNaprawas(List<Naprawa> naprawas) {
		this.naprawas = naprawas;
	}

	public Naprawa addNaprawa(Naprawa naprawa) {
		getNaprawas().add(naprawa);
		naprawa.setRodzajNaprawy(this);

		return naprawa;
	}

	public Naprawa removeNaprawa(Naprawa naprawa) {
		getNaprawas().remove(naprawa);
		naprawa.setRodzajNaprawy(null);

		return naprawa;
	}

}