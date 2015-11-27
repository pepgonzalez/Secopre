package ideasw.secopre.service;

import ideasw.secopre.dto.EntryBalance;
import ideasw.secopre.dto.EntryFilter;
import ideasw.secopre.dto.UpdateEntry;
import ideasw.secopre.enums.StatusEntry;

import java.util.List;

public interface EntryConfigService {

	public boolean cloneEntries(String userId) throws Exception;

	public boolean updateEntries(List<UpdateEntry> updateList) throws Exception;

	public EntryBalance getEntryBalance(EntryFilter filter, StatusEntry status);

}
