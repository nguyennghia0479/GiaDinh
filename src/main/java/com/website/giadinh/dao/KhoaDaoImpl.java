package com.website.giadinh.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.website.giadinh.entity.Khoa;

@Repository("khoaDao")
public class KhoaDaoImpl implements KhoaDao {
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Khoa> findAll() {
		Session session = this.sessionFactory.getCurrentSession();
//		List<Khoa> list = session.createQuery("from Khoa").getResultList();
//		return list;

		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Khoa> cq = cb.createQuery(Khoa.class);
		Root<Khoa> root = cq.from(Khoa.class);
		cq.select(root);
		Query query = session.createQuery(cq);
		return query.getResultList();
	}

	@Override
	public Khoa findById(String maKhoa) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.get(Khoa.class, maKhoa);
	}

	@Override
	public Boolean existKey(String pKey) {
		Session session = this.sessionFactory.getCurrentSession();
		Khoa khoa = session.get(Khoa.class, pKey);
		if (khoa != null) {
			return true;
		}
		return false;
	}

	@Override
	public void add(Khoa khoa) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(khoa);
	}

	@Override
	public void update(Khoa khoa) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(khoa);
	}

	@Override
	public Boolean delete(String maKhoa) {
		Session session = this.sessionFactory.getCurrentSession();
		Khoa khoa = session.byId(Khoa.class).load(maKhoa);
		if (khoa != null) {
			session.delete(khoa);
			return true;
		}
		return false;
	}

	@Override
	public Long countList() {
		Session session = this.sessionFactory.getCurrentSession();
//		Long count = (Long) session.createQuery("select count(maKhoa) from Khoa").getSingleResult();
//		return count;

		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Khoa> root = cq.from(Khoa.class);
		cq.select(cb.count(root.get("maKhoa")));
		Long count = session.createQuery(cq).getSingleResult();
		return count;
	}

	@Override
	public Long countSearchResult(String keyword) {
		Session session = this.sessionFactory.getCurrentSession();
//		Long count = (Long) session.createQuery("select count(maKhoa) from Khoa where tenKhoa like :keyword")
//				.setParameter("keyword", "%" + keyword + "%").getSingleResult();
//		return count;
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Khoa> root = cq.from(Khoa.class);
		cq.select(cb.count(root.get("maKhoa"))).where(cb.like(root.get("tenKhoa"), "%" + keyword + "%"));
		Long count = session.createQuery(cq).getSingleResult();
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> searchAuto(String keyword) {
		Session session = this.sessionFactory.getCurrentSession();
//		List<String> list = session.createQuery("select tenKhoa from Khoa where tenKhoa like :keyword")
//				.setParameter("keyword", "%" + keyword + "%").getResultList();
//		return list;

		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<String> cq = cb.createQuery(String.class);
		Root<Khoa> root = cq.from(Khoa.class);
		cq.select(root.get("tenKhoa")).where(cb.like(root.get("tenKhoa"), "%" + keyword + "%"));
		Query query = session.createQuery(cq);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Khoa> search(String keyword) {
		Session session = this.sessionFactory.getCurrentSession();
//		List<Khoa> list = session.createQuery("from Khoa where tenKhoa like :keyword")
//				.setParameter("keyword", "%" + keyword + "%").getResultList();
//		return list;

		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Khoa> cq = cb.createQuery(Khoa.class);
		Root<Khoa> root = cq.from(Khoa.class);
		cq.select(root).where(cb.like(root.get("tenKhoa"), "%" + keyword + "%"));
		Query query = session.createQuery(cq);
		return query.getResultList();
	}
}