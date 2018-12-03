package ideasw.secopre.dto;

import java.util.HashMap;

import ideasw.secopre.model.security.Menu;

/**
 * Clase de estereotipo DTO que permite almacenar la informacion correspondiente
 * a un menu simple, la estructura de esta clase, es un objeto de tipo
 * {@link Menu} y una lista de childs del mismo tipo
 * 
 * @author jorge.canoc@gmail.com
 *
 */
public class RenderSingleMenu {

	private Menu parent;
	private HashMap<Long,Menu> childs;

	/**
	 * @return the parent
	 */
	public Menu getParent() {
		return parent;
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(Menu parent) {
		this.parent = parent;
	}

	/**
	 * @return the childs
	 */
	public HashMap<Long,Menu> getChilds() {
		return childs;
	}

	/**
	 * @param childs
	 *            the childs to set
	 */
	public void setChilds(HashMap<Long,Menu> childs) {
		this.childs = childs;
	}

}
