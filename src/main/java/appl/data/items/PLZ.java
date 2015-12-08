package appl.data.items;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "PLZ", schema = "public", uniqueConstraints = { @UniqueConstraint(columnNames = "postcode") })
public class PLZ {
	private String postcode;
	private String place;

	@Id
	@GeneratedValue
	@Column(name = "POSTCODE", unique = true, nullable = false)
	public String getPostcode() {
		return postcode;
	}

	@Column(name = "PLACE", unique = true, nullable = false)
	public String getPlace() {
		return place;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public void setPlace(String place) {
		this.place = place;
	}

}
