package appl.data.dao;

import org.springframework.transaction.annotation.Transactional;

import appl.data.items.ArchiveBook;

@Transactional
public interface ArchiveDAO {
	
	public void update(ArchiveBook archiveItem);

}
