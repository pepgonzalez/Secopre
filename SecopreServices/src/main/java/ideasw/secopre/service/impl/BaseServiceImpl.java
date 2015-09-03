/**
 * 
 */
package ideasw.secopre.service.impl;

import ideasw.secopre.dao.JpaDao;
import ideasw.secopre.model.base.Persistible;
import ideasw.secopre.service.BaseService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author jorge.canoc@gmail.com
 *
 */
@Service
public class BaseServiceImpl implements BaseService {

	@Autowired
	private JpaDao dao;

	@Override
	public <E extends Persistible> Object persistAndReturnId(E entity) {
		return dao.persistAndReturnId(entity);
	}

	@Override
	public <E extends Persistible> void save(E entity) {
		dao.save(entity);
	}

	@Override
	public <E extends Persistible> void persist(E entity) {
		dao.persist(entity);
	}

	@Override
	public <E extends Persistible> E update(E entity) {
		return dao.update(entity);
	}

	@Override
	public <E extends Persistible> void remove(E entity) {
		dao.remove(entity);
	}

	@Override
	public <E extends Persistible> E findById(Class<E> entityClass, Long id) {
		return dao.findById(entityClass, id);
	}

	@Override
	public <E extends Persistible> List<E> findAll(Class<E> entityClass) {
		return dao.findAll(entityClass);
	}

	@Override
	public <E extends Persistible> E getReference(Class<E> entityClass, Long id) {
		return dao.getReference(entityClass, id);
	}

	@Override
	public <E extends Persistible> E executeNativeQuerySimpleResult(
			Class<E> entityClass, String query) {

		return dao.executeNativeQuerySimpleResult(entityClass, query);
	}

	@Override
	public <E extends Persistible> List<E> findByProperty(Class<E> entityClass,
			String propertyName, Object value) {
		return dao.findByProperty(entityClass, propertyName, value);
	}

	@Override
	public <E extends Persistible> List<E> findByProperties(
			Class<E> entityClass, Map<String, Object> params) {
		return dao.findByProperties(entityClass, params);
	}

	@Override
	public <E extends Persistible> List<E> findByPropertiesWithOrder(
			Class<E> entityClass, Map<String, Object> params,
			String... orderByAttributes) {
		return dao.findByPropertiesWithOrder(entityClass, params,
				orderByAttributes);
	}

	@Override
	public <E extends Persistible> void refresh(E entity) {
		dao.refresh(entity);
	}

	@Override
	public <E extends Persistible> List<E> executeNativeQueryMultipleResult(
			Class<E> entityClass, String query) {
		return dao.executeNativeQueryMultipleResult(entityClass, query);
	}

	@Override
	public <E extends Persistible> E executeQuerySimpleResult(
			Class<E> entityClass, String query, Map<String, Object> parameters) {
		return dao.executeQuerySimpleResult(entityClass, query, parameters);
	}

	@Override
	public <E extends Persistible> List<E> executeQueryMultipleResult(
			Class<E> entityClass, String query, Map<String, Object> parameters) {
		return dao.executeQueryMultipleResult(entityClass, query, parameters);
	}

}
