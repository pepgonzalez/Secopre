package ideasw.secopre.model;

import java.io.Serializable;

/**
 * Marker Interface que permite englobar la fuuncionalidad de JPA para todas las
 * entidas que implementen esta interface
 * 
 * @author jorge.canoc@gmail.com
 *
 */
public interface Persistible extends Serializable {
	public Long getId();
}
