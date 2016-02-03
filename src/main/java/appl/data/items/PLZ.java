package appl.data.items;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "plz", schema = "public")
public class PLZ {
	private int plzId;
	private String postcode;
	private String place;

	@OneToMany(mappedBy = "plz")
	private Set<User> users;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PLZID")
	public int getPlzId() {
		return plzId;
	}

	@Column(name = "postcode", unique = false, nullable = false)
	public String getPostcode() {
		return postcode;
	}

	@Column(name = "place", unique = false, nullable = false)
	public String getPlace() {
		return place;
	}

	public void setPlzId(int plzId) {
		this.plzId = plzId;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public void setPlace(String place) {
		this.place = place;
	}

}
