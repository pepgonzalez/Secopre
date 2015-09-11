package ideasw.secopre.service;

import java.util.List;

import ideasw.secopre.dto.Formality;
import ideasw.secopre.dto.Request;
import ideasw.secopre.model.security.User;

public interface AccessNativeService {
	
	List<Formality> getFormalityAvailableByUser(User user);
	
	Long startFormality(Request request);
	
}
