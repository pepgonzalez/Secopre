package ideasw.secopre.service;

import ideasw.secopre.model.base.Persistible;

import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Clase de Generica de servicios que soporta las operaciones mas comunes de JPA
 * tales como
 * <ul>
 * <li>save</li>
 * <li>persist</li>
 * <li>persistAndReturnId</li>
 * <li>update</li>
 * <li>remove</li>
 * <li>findById</li>
 * <li>findAll</li>
 * <li>getReference</li>
 * <li>executeNativeQuerySimpleResult</li>
 * <li>findByProperty</li>
 * <li>etc</li>
 * </ul>
 * 
 * 
 * @author jorge.canoc@gmail.com
 *
 */
public interface BaseService {
	/**
	 * Metodo que permite persistir una {@link Entity} que extienda de
	 * {@link Persistible} y ademas retorna el Id generado por la unidad de
	 * persistencia
	 * 
	 * @param entity
	 *            Es la entidad a persistir
	 * @return la entidad persistida
	 */
	public <E extends Persistible> Object persistAndReturnId(E entity);

	/**
	 * Metodo que sobre escribe el metodo ofrecido por JPA save, permite
	 * almacenar cualquier tipo de {@link Entity} que extienda de
	 * {@link Persistible}
	 * 
	 * @param entity
	 *            la entidad a almacenar
	 */
	public <E extends Persistible> void save(E entity);

	/**
	 * Metodo que sobre escribe el metodo ofrecido por JPA persist, permite
	 * almacenar cualquier tipo de {@link Entity} que extienda de
	 * {@link Persistible}
	 * 
	 * @param entity
	 *            la entidad a almacenar
	 */
	public <E extends Persistible> void persist(E entity);

	/**
	 * Metodo que sobre escribe el metodo ofrecido por JPA update, permite
	 * almacenar cualquier tipo de {@link Entity} que extienda de
	 * {@link Persistible}
	 * 
	 * @param entity
	 *            la entidad a almacenar
	 */
	public <E extends Persistible> E update(E entity);

	/**
	 * Metodo que sobre escribe el metodo ofrecido por JPA remove, permite
	 * almacenar cualquier tipo de {@link Entity} que extienda de
	 * {@link Persistible}
	 * 
	 * @param entity
	 *            la entidad a almacenar
	 */
	public <E extends Persistible> void remove(E entity);

	/**
	 * Metodo que permite realizar una busqueda por {@link Id} de una
	 * {@link Entity} que extienda de {@link Persistible}
	 * 
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public <E extends Persistible> E findById(Class<E> entityClass, Long id);

	/**
	 * Metodo que permite realizar una busqueda full de una {@link Entity} que
	 * extienda de {@link Persistible}
	 * 
	 * @param entityClass
	 * @return
	 */
	public <E extends Persistible> List<E> findAll(Class<E> entityClass);

	/**
	 * Metodo que permite obtener la referencia de una {@link Entity} que
	 * extienda de {@link Persistible}, esta referencia es obtenida directamente
	 * de la unidad de persistencia
	 * 
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public <E extends Persistible> E getReference(Class<E> entityClass, Long id);

	/**
	 * Metodo que permite ejecutar un Query nativo y asignar el valor a una
	 * {@link Entity} que extienda de {@link Persistible}
	 * 
	 * @param entityClass
	 * @param query
	 * @return
	 */
	public <E extends Persistible> E executeNativeQuerySimpleResult(
			Class<E> entityClass, String query);

	/**
	 * Metodo que permite buscar mediante una propiedad de una {@link Entity}
	 * 
	 * @param entityClass
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public <E extends Persistible> List<E> findByProperty(Class<E> entityClass,
			String propertyName, final Object value);

	/**
	 * Metodo que permite buscar mediante una propiedad de una {@link Entity}
	 * 
	 * @param entityClass
	 * @param params
	 * @return
	 */
	public <E extends Persistible> List<E> findByProperties(
			Class<E> entityClass, Map<String, Object> params);

	/**
	 * Metodo que permite buscar mediante una propiedad de una {@link Entity} y
	 * ordena deacuerdo a los parametros indicados
	 * 
	 * @param entityClass
	 * @param params
	 * @param orderByAttributes
	 * @return
	 */
	public <E extends Persistible> List<E> findByPropertiesWithOrder(
			Class<E> entityClass, Map<String, Object> params,
			String... orderByAttributes);

	/**
	 * Metodo que permite refrescar una {@link Entity} directamente de la
	 * entidad de persistencia
	 * 
	 * @param entity
	 */
	public <E extends Persistible> void refresh(E entity);

	/**
	 * Metodo que permite ejecutar un query nativo y retornar mutiples
	 * resultados mapeados en una lista de {@link Entity}
	 * 
	 * @param entityClass
	 * @param query
	 * @return
	 */
	public <E extends Persistible> List<E> executeNativeQueryMultipleResult(
			Class<E> entityClass, String query);

	/**
	 * Metodo que permite ejecutar un query basado en JPQL y retornar un
	 * resultado simple mapeado en {@link Entity}
	 * 
	 * @param entityClass
	 * @param query
	 * @param parameters
	 * @return
	 */
	public <E extends Persistible> E executeQuerySimpleResult(
			Class<E> entityClass, String query, Map<String, Object> parameters);

	/**
	 * Metodo que permite ejecutar un query basado en JPQL y los resultados son
	 * mapeados en una lista de {@link Entity}
	 * 
	 * @param entityClass
	 * @param query
	 * @param parameters
	 * @return
	 */
	public <E extends Persistible> List<E> executeQueryMultipleResult(
			Class<E> entityClass, String query, Map<String, Object> parameters);

}
