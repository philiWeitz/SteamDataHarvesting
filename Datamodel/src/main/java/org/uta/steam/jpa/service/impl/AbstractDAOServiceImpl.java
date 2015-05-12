package org.uta.steam.jpa.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.uta.steam.jpa.model.AbstractEntity;

abstract class AbstractDAOServiceImpl<T extends AbstractEntity> {

	private static final String PERSISTENCE_UNIT_NAME = "steamDataHarvesting";

	private static Logger LOG = LogManager
			.getLogger(AbstractDAOServiceImpl.class);

	private EntityManagerFactory factory;

	public AbstractDAOServiceImpl() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}

	protected EntityManager getEntityManager() {
		return factory.createEntityManager();
	}

	public T saveOrUpdate(T item) {
		T result = null;

		EntityManager em = getEntityManager();

		try {
			em.getTransaction().begin();

			if (null == item.getId()) {
				// new item
				em.persist(item);
				result = item;
			} else {
				// update item
				result = em.merge(item);
			}

			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			LOG.error("Error while saving \"" + item.getClass() + "\"", e);

		} finally {
			em.close();
		}

		return result;
	}

	public T saveOrUpdateAll(List<T> items) {
		T result = null;

		EntityManager em = getEntityManager();

		try {
			em.getTransaction().begin();

			for (T item : items) {
				if (null == item.getId()) {
					// new item
					em.persist(item);
					result = item;
				} else {
					// update item
					result = em.merge(item);
				}
			}
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			LOG.error("Error while saving list of \"" + getGenericClass()
					+ "\"", e);

		} finally {
			em.close();
		}

		return result;
	}

	public T getById(Long id) {
		T result = null;

		EntityManager em = getEntityManager();

		try {
			em.getTransaction().begin();
			result = em.find(getGenericClass(), id);
			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			LOG.error("Error while getting \"" + result.getClass() + "\" (id: "
					+ id + ")", e);

		} finally {
			em.close();
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		List<T> results = Collections.EMPTY_LIST;

		EntityManager em = getEntityManager();

		try {
			em.getTransaction().begin();

			Query query = em.createQuery("SELECT e FROM "
					+ getGenericClass().getSimpleName() + " e");
			results = query.getResultList();

			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			LOG.error("Error while getting result list for \""
					+ getGenericClass() + "\"", e);

		} finally {
			em.close();
		}

		return results;
	}

	public void delete(T item) {
		delete(item.getId());
	}

	public void delete(Long id) {

		EntityManager em = getEntityManager();

		try {

			if (null != id) {
				em.getTransaction().begin();

				T item = em.find(getGenericClass(), id);
				em.remove(item);

				em.getTransaction().commit();
			}
		} catch (Exception e) {
			em.getTransaction().rollback();
			LOG.error("Error while deleting by id: \"" + id + "\"", e);

		} finally {
			em.close();
		}
	}

	@SuppressWarnings("unchecked")
	protected <A> List<A> issueQuery(String queryString) {
		List<A> results = Collections.emptyList();

		EntityManager em = getEntityManager();

		try {
			em.getTransaction().begin();

			Query query = em.createQuery(queryString);
			results = query.getResultList();

			em.getTransaction().commit();

		} catch (Exception e) {
			em.getTransaction().rollback();
			LOG.error("Error while performing database query " + queryString, e);

		} finally {
			em.close();
		}

		return results;
	}

	@SuppressWarnings("unchecked")
	protected <A> A issueQuerySingleResult(String queryString) {
		A result = null;

		EntityManager em = getEntityManager();

		try {
			em.getTransaction().begin();

			Query query = em.createQuery(queryString);
			result = (A) query.getSingleResult();

			em.getTransaction().commit();

		} catch (NoResultException e) {
			// just return null
		} catch (Exception e) {
			em.getTransaction().rollback();
			LOG.error("Error while performing single result database query "
					+ queryString, e);

		} finally {
			em.close();
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	private Class<T> getGenericClass() {
		return (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}
}
