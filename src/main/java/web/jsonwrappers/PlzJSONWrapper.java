package web.jsonwrappers;

import appl.data.items.PLZ;

public class PlzJSONWrapper {

	private int plzId;

	private String postcode;

	private String place;

	public PlzJSONWrapper() {

	}

	public PlzJSONWrapper(PLZ plz) {
		if (plz == null) {
			throw new IllegalArgumentException("The passed PLZ is null.");
		}
		this.plzId = plz.getPlzId();
		this.postcode = plz.getPostcode();
		this.place = plz.getPlace();
	}

	public int getPlzId() {
		return plzId;
	}

	public void setPlzId(int plzId) {
		this.plzId = plzId;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}
}
