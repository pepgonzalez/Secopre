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
	
	List<Inbox> getInboxByUserId(Long userId);
	
	Long startFormality(Request request, Long userId);
	
	void invokeNextStage(Request requestForm,  Long userId);
	
	Long getRequestNextConsecutive();
	
	Request getRequestById(Long requestId);
	
	Request getRequestAndDetailById(Long requestId);
	
	List<RequestHistory> getRequestHistory(Long requestId);
	
	int insertOrUpdateRequestDetail(Request request);

	int updateRequestUploadedFile(Long requestId, String uploadedFilePath);

	Authorization getAuthorization(Request request, User user);
	
	Map<Long, String> getProgramaticKeysMap();
	
	Map<Long, String> getEntriesMap(Long programaticKey);
	
	Map<Long, String> getMovementTypesMap();
	
	Map<Long, String> getMonthsMap();

	List<Entry> getEntries(Long programaticKey);
	
}
