package com.website.giadinh.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.website.giadinh.entity.MonHoc;

@Repository("monHocDao")
public class MonHocDaoImpl implements MonHocDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<MonHoc> findAll() {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<MonHoc> cq = cb.createQuery(MonHoc.class);
		Root<MonHoc> root = cq.from(MonHoc.class);
		cq.select(root);
		List<MonHoc> list = session.createQuery(cq).getResultList();
		return list;
	}

	@Override
	public MonHoc findById(String maMH) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.get(MonHoc.class, maMH);
	}

	@Override
	public Boolean isExistKey(String maMH) {
		if (findById(maMH) == null) {
			return false;
		}
		return true;
	}

	@Override
	public Boolean isExistReference(String maMH) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(MonHoc monHoc) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(monHoc);
	}

	@Override
	public void update(MonHoc monHoc) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(monHoc);
	}

	@Override
	public void delete(MonHoc monHoc) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(monHoc);
	}

	@Override
	public Long countList() {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<MonHoc> root = cq.from(MonHoc.class);
		cq.select(cb.count(root.get("maMH")));
		Long count = session.createQuery(cq).getSingleResult();
		return count;
	}

	@Override
	public Long countSearchResult(String keyword) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<MonHoc> root = cq.from(MonHoc.class);
		cq.select(cb.count(root.get("maMH")))
				.where(cb.or(cb.like(root.get("maMH"), "%" + keyword + "%"),
						cb.like(root.get("tenMH"), "%" + keyword + "%"), cb.equal(root.get("soTC"), keyword),
						cb.equal(root.get("lyThuyet"), keyword), cb.equal(root.get("thucHanh"), keyword)));
		Long count = session.createQuery(cq).getSingleResult();
		return count;
	}

	@Override
	public List<String> searchAuto(String keyword) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<String> cq = cb.createQuery(String.class);
		Root<MonHoc> root = cq.from(MonHoc.class);
		cq.select(root.get("tenMH")).where(cb.like(root.get("tenMH"), "%" + keyword + "%"));
		List<String> list = session.createQuery(cq).getResultList();
		if (list.isEmpty()) {
			cq.select(root.get("maMH")).where(cb.like(root.get("maMH"), "%" + keyword + "%"));
			list = session.createQuery(cq).getResultList();
		}
		return list;
	}

	@Override
	public List<MonHoc> search(String keyword) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<MonHoc> cq = cb.createQuery(MonHoc.class);
		Root<MonHoc> root = cq.from(MonHoc.class);
		cq.select(root)
				.where(cb.or(cb.like(root.get("maMH"), "%" + keyword + "%"),
						cb.like(root.get("tenMH"), "%" + keyword + "%"), cb.equal(root.get("soTC"), keyword),
						cb.equal(root.get("lyThuyet"), keyword), cb.equal(root.get("thucHanh"), keyword)));
		List<MonHoc> list = session.createQuery(cq).getResultList();
		return list;
	}
}