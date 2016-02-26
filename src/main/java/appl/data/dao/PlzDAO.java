package appl.data.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import appl.data.items.PLZ;

@Transactional
public interface PlzDAO {

	public Optional<PLZ> getPLZ(int plzId);

	public Optional<PLZ> getPLZ(String postalCode, String place);

	public Optional<Integer> getPLZId(String postalCode, String place);

	public List<PLZ> getPLZByPostalCode(String postalCode);

	public List<PLZ> getPLZByPlace(String place);

}
