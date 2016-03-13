package appl.data.items;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entity class representing (German) postal code and place.
 * 
 * @author Johannes
 *
 */
@Entity
@Table(name = "plz", schema = "public")
public class PLZ {
	private int plzId;
	private String postcode;
	private String place;

	@OneToMany(mappedBy = "plz")
	private Set<User> users;

	private PLZ() {

	}

	/**
	 * Constructor to initiate PLZ. The id of the entity is a generated value.
	 * 
	 * Both parameters must not be null.
	 * 
	 * @param postcode
	 *            the postal code of the place
	 * @param place
	 *            the name of the place
	 */
	public PLZ(String postcode, String place) {
		setPostcode(postcode);
		setPlace(place);
	}

	public PLZ(int plzId, String postalCode, String place2) {
		setPlzId(plzId);
		setPostcode(postcode);
		setPlace(place);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PLZID")
	public int getPlzId() {
		return plzId;
	}

	@Column(name = "postcode", unique = false, nullable = false, length = 5)
	public String getPostcode() {
		return postcode;
	}

	@Column(name = "place", unique = false, nullable = false)
	public String getPlace() {
		return place;
	}

	private void setPlzId(int plzId) {
		this.plzId = plzId;
	}

	private void setPostcode(String postcode) {
		if (postcode == null || "".equals(postcode)) {
			throw new IllegalArgumentException("Postcode must not be null or empty string.");
		}
		this.postcode = postcode;
	}

	private void setPlace(String place) {
		if (place == null || "".equals(place)) {
			throw new IllegalArgumentException("Place must not be null or empty string.");
		}
		this.place = place;
	}

}
