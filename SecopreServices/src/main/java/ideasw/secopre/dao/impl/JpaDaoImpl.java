/**
 * 
 */
package ideasw.secopre.dao.impl;

import ideasw.secopre.dao.JpaDao;
import ideasw.secopre.model.base.Persistible;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * @author jorge.canoc@gmail.com
 *
 */
@Repository
@Transactional
public class JpaDaoImpl implements JpaDao {

	@PersistenceContext
	protected EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ideasw.secopre.dao.JpaDAO#persistAndReturnId(ideasw.secopre.model.Persistible
	 * )
	 */
	@Override
	public <E extends Persistible> Object persistAndReturnId(E entity) {
		persist(entity);
		return entityManager.getEntityManagerFactory().getPersistenceUnitUtil()
				.getIdentifier(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ideasw.secopre.dao.JpaDAO#save(ideasw.secopre.model.Persistible)
	 */
	@Override
	public <E extends Persistible> void save(E entity) {
		if (entity.getId() != null) {
			update(entity);
		} else {
			persist(entity);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ideasw.secopre.dao.JpaDAO#persist(ideasw.secopre.model.Persistible)
	 */
	@Override
	public <E extends Persistible> void persist(E entity) {
		entityManager.persist(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ideasw.secopre.dao.JpaDAO#update(ideasw.secopre.model.Persistible)
	 */
	@Override
	public <E extends Persistible> E update(E entity) {
		return entityManager.merge(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ideasw.secopre.dao.JpaDAO#remove(ideasw.secopre.model.Persistible)
	 */
	@Override
	public <E extends Persistible> void remove(E entity) {
		entity = entityManager.merge(entity);
		entityManager.remove(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ideasw.secopre.dao.JpaDAO#findById(java.lang.Class,
	 * java.lang.Object)
	 */
	@Override
	public <E extends Persistible> E findById(Class<E> entityClass, Long id) {
		return entityManager.find(entityClass, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ideasw.secopre.dao.JpaDAO#findAll(java.lang.Class)
	 */
	@Override
	public <E extends Persistible> List<E> findAll(Class<E> entityClass) {
		CriteriaQuery<E> criteriaQuery = entityManager.getCriteriaBuilder()
				.createQuery(entityClass);
		criteriaQuery.from(entityClass);
		TypedQuery<E> query = entityManager.createQuery(criteriaQuery);
		query.setHint("org.hibernate.cacheable", "true");
		return query.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ideasw.secopre.dao.JpaDAO#getReference(java.lang.Class,
	 * java.lang.Object)
	 */
	@Override
	public <E extends Persistible> E getReference(Class<E> entityClass, Long id) {
		return entityManager.getReference(entityClass, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ideasw.secopre.dao.JpaDAO#executeNativeQuerySimpleResult(java.lang.Class,
	 * java.lang.String)
	 */
	@Override
	public <E extends Persistible> E executeNativeQuerySimpleResult(
			Class<E> entityClass, String query) {
		Query nativeQuery = entityManager.createNativeQuery(query);
		return entityClass.cast(nativeQuery.getSingleResult());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ideasw.secopre.dao.JpaDAO#findByProperty(java.lang.Class,
	 * java.lang.String, java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <E extends Persistible> List<E> findByProperty(Class<E> entityClass,
			String propertyName, Object value) {
		final String queryString = "select model from "
				+ entityClass.getSimpleName() + " model where model."
				+ propertyName + "= :propertyValue";
		Query query = entityManager.createQuery(queryString);
		query.setParameter("propertyValue", value);
		return query.getResultList();

	}

	//
	@SuppressWarnings("unchecked")
	/*
	 * (non-Javadoc)
	 * 
	 * @see ideasw.secopre.dao.JpaDAO#findByProperties(java.lang.Class,
	 * java.util.Map)
	 */
	@Override
	public <E extends Persistible> List<E> findByProperties(
			Class<E> entityClass, Map<String, Object> params) {
		final StringBuilder queryString = new StringBuilder(
				"select model from " + entityClass.getSimpleName() + " model ");
		boolean setParameters = false;
		if (params != null && !params.isEmpty()) {
			setParameters = true;
			queryString.append(" where ");
			int size = params.size();
			int index = 0;
			for (String property : params.keySet()) {
				String propertyMap = getValidProperty(property);// checa si
																// tiene "." la
																// propiedad
				queryString.append("model." + property + "=:" + propertyMap);
				if (index != (size - 1)) {
					queryString.append(" and ");
				}
				index++;
			}
		}
		Query query = entityManager.createQuery(queryString.toString());
		if (setParameters) {
			setQueryParametersByProperties(query, params);
		}
		return query.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ideasw.secopre.dao.JpaDAO#findByPropertiesWithOrder(java.lang.Class,
	 * java.util.Map, java.lang.String[])
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <E extends Persistible> List<E> findByPropertiesWithOrder(
			Class<E> entityClass, Map<String, Object> params,
			String... orderByAttributes) {
		final StringBuilder queryString = new StringBuilder(
				"select model from " + entityClass.getSimpleName() + " model ");
		boolean setParameters = false;
		int size = 0;
		int index = 0;
		if (params != null && !params.isEmpty()) {
			setParameters = true;
			queryString.append(" where ");
			size = params.size();
			for (String property : params.keySet()) {
				String propertyMap = getValidProperty(property);// checa si
																// tiene "." la
																// propiedad
				queryString.append("model." + property + "=:" + propertyMap);
				if (index != (size - 1)) {
					queryString.append(" and ");
				}
				index++;
			}
		}
		if (orderByAttributes != null && orderByAttributes.length > 0) {
			size = orderByAttributes.length;
			index = 0;
			queryString.append(" ORDER BY ");
			for (String orderAttribute : orderByAttributes) {
				queryString.append("model." + orderAttribute);
				if (index != (size - 1)) {
					queryString.append(",");
				}
				index++;
			}
		}
		Query query = entityManager.createQuery(queryString.toString());
		if (setParameters) {
			setQueryParametersByProperties(query, params);
		}
		return query.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ideasw.secopre.dao.JpaDAO#refresh(ideasw.secopre.model.Persistible)
	 */
	@Override
	public <E extends Persistible> void refresh(E entity) {
		entityManager.refresh(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ideasw.secopre.dao.JpaDAO#executeNativeQueryMultipleResult(java.lang.
	 * Class, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <E extends Persistible> List<E> executeNativeQueryMultipleResult(
			Class<E> entityClass, String query) {
		Query nativeQuery = entityManager.createNativeQuery(query);
		return nativeQuery.getResultList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ideasw.secopre.dao.JpaDAO#executeQuerySimpleResult(java.lang.Class,
	 * java.lang.String, java.util.Map)
	 */
	@Override
	public <E extends Persistible> E executeQuerySimpleResult(
			Class<E> entityClass, String query, Map<String, Object> parameters) {
		try {
			Query entityQuery = entityManager.createQuery(query);
			this.setQueryParameters(entityQuery, parameters);
			return entityClass.cast(entityQuery.getSingleResult());
		} catch (NoResultException e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ideasw.secopre.dao.JpaDAO#executeQueryMultipleResult(java.lang.Class,
	 * java.lang.String, java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <E extends Persistible> List<E> executeQueryMultipleResult(
			Class<E> entityClass, String query, Map<String, Object> parameters) {
		Query entityQuery = entityManager.createQuery(query);
		this.setQueryParameters(entityQuery, parameters);
		return entityQuery.getResultList();
	}

	private void setQueryParameters(Query entityQuery,
			Map<String, Object> parameters) {
		Iterator<Entry<String, Object>> it = parameters.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> pairs = it.next();
			entityQuery.setParameter(pairs.getKey(), pairs.getValue());
		}
	}

	private void setQueryParametersByProperties(Query entityQuery,
			Map<String, Object> parameters) {
		Iterator<Entry<String, Object>> it = parameters.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> pairs = it.next();
			String key = getValidProperty(pairs.getKey());
			entityQuery.setParameter(key, pairs.getValue());
		}
	}

	public static String getValidProperty(String property) {
		String validProperty = property;
		StringBuilder str = new StringBuilder("");
		if (property.contains(".")) {
			int i = 0;
			for (String word : property.split("\\.")) {
				if (i == 0) {
					word = word.toLowerCase();
				} else {
					word = StringUtils.capitalize(word);
				}
				str.append(word);
				i++;
			}
			validProperty = str.toString();
		}
		return validProperty;
	}

}
