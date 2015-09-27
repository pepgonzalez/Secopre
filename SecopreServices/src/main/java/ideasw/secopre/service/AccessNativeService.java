package ideasw.secopre.service;

import java.util.List;

import ideasw.secopre.dto.Authorization;
import ideasw.secopre.dto.Formality;
import ideasw.secopre.dto.Inbox;
import ideasw.secopre.dto.Request;
import ideasw.secopre.model.security.User;

public interface AccessNativeService {
	
	List<Formality> getFormalityAvailableByUser(User user);
	
	Long startFormality(Request request, Long userId);
	
	List<Inbox> getInboxByUserId(Long userId);
	
	void invokeNextStage(Request requestForm,  Long userId);
	
	Request getRequestById(Long requestId);
	
	public int insertOrUpdateRequestDetail(Request request);

	Authorization getAuthorization(Request request, User user);
	
	public Long getRequestNextConsecutive();
}
