package ideasw.secopre.service;

import java.util.List;
import java.util.Map;

import ideasw.secopre.dto.Authorization;
import ideasw.secopre.dto.Formality;
import ideasw.secopre.dto.Inbox;
import ideasw.secopre.dto.Request;
import ideasw.secopre.dto.RequestHistory;
import ideasw.secopre.model.security.User;
import ideasw.secopre.model.Entry;


public interface AccessNativeService {
	
	List<Formality> getFormalityAvailableByUser(User user);
	
	Long startFormality(Request request, Long userId);
	
	List<Inbox> getInboxByUserId(Long userId);
	
	void invokeNextStage(Request requestForm,  Long userId);
	
	Request getRequestById(Long requestId);
	
	Request getRequestAndDetailById(Long requestId);
	
	public int insertOrUpdateRequestDetail(Request request);

	Authorization getAuthorization(Request request, User user);
	
	public Long getRequestNextConsecutive();

	int updateRequestUploadedFile(Long requestId, String uploadedFilePath);
	
	Map<Long, String> getProgramaticKeysMap();
	
	Map<Long, String> getEntriesMap(Long programaticKey);

	List<Entry> getEntries(Long programaticKey);
	
	Map<Long, String> getMonthsMap();
	
	public List<RequestHistory> getRequestHistory(Long requestId);
}
