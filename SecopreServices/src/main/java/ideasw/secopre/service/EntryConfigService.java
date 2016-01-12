package ideasw.secopre.service;

import ideasw.secopre.dto.AnnualBudgetFile;
import ideasw.secopre.dto.EntryBalance;
import ideasw.secopre.dto.EntryFilter;
import ideasw.secopre.dto.UpdateEntry;
import ideasw.secopre.enums.StatusEntry;

import java.util.List;

public interface EntryConfigService {

	public Long importExcel(AnnualBudgetFile fileBean, String username)
			throws Exception;

	public boolean cloneEntries(String userId) throws Exception;

	public boolean updateEntries(List<UpdateEntry> updateList) throws Exception;

	public EntryBalance getEntryBalance(EntryFilter filter, StatusEntry status);

	public boolean existEntriesInConfig(boolean currentYear);

	public Integer numberEntriesNextYear();

	public boolean releaseBudget(String userId) throws Exception;

	public boolean schedulingBalance(EntryFilter filter) throws Exception;

}
