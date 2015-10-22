package ideasw.secopre.service;

import ideasw.secopre.dto.UpdateEntry;

import java.util.List;

public interface EntryConfigService {

	public boolean cloneEntries();
	
	public boolean updateEntries(List<UpdateEntry> updateList);
	
}
