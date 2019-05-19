package com.website.giadinh.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.website.giadinh.entity.PhongHoc;

@Repository("phongHocDao")
public class PhongHocDaoImpl implements PhongHocDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<PhongHoc> findAll() {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<PhongHoc> cq = cb.createQuery(PhongHoc.class);
		Root<PhongHoc> root = cq.from(PhongHoc.class);
		cq.select(root);
		List<PhongHoc> list = session.createQuery(cq).getResultList();
		return list;
	}

	@Override
	public PhongHoc findById(String maPH) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.get(PhongHoc.class, maPH);
	}

	@Override
	public Boolean isExistKey(String maPH) {
		if (findById(maPH) == null) {
			return false;
		}
		return true;
	}

	@Override
	public Boolean isExistReference(String maPH) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(PhongHoc phongHoc) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(phongHoc);
	}

	@Override
	public void update(PhongHoc phongHoc) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(phongHoc);
	}

	@Override
	public void delete(PhongHoc phongHoc) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(phongHoc);
	}

	@Override
	public Long countList() {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<PhongHoc> root = cq.from(PhongHoc.class);
		cq.select(cb.count(root.get("maPH")));
		Long count = session.createQuery(cq).getSingleResult();
		return count;
	}

	@Override
	public Long countSearchResult(String keyword) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<PhongHoc> root = cq.from(PhongHoc.class);
		cq.select(cb.count(root.get("maPH"))).where(cb.or(cb.like(root.get("maPH"), "%" + keyword + "%"),
				cb.like(root.get("loaiPH"), "%" + keyword + "%")));
		Long count = session.createQuery(cq).getSingleResult();
		return count;
	}

	@Override
	public List<String> searchAuto(String keyword) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<String> cq = cb.createQuery(String.class);
		Root<PhongHoc> root = cq.from(PhongHoc.class);
		cq.select(root.get("maPH")).where(cb.like(root.get("maPH"), "%" + keyword + "%"));
		List<String> list = session.createQuery(cq).getResultList();
		if (list.isEmpty()) {
			cq.select(root.get("loaiPH")).where(cb.like(root.get("loaiPH"), "%" + keyword + "%")).distinct(true);
			list = session.createQuery(cq).getResultList();
		}
		return list;
	}

	@Override
	public List<PhongHoc> search(String keyword) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<PhongHoc> cq = cb.createQuery(PhongHoc.class);
		Root<PhongHoc> root = cq.from(PhongHoc.class);
		cq.select(root).where(cb.or(cb.like(root.get("maPH"), "%" + keyword + "%"),
				cb.like(root.get("loaiPH"), "%" + keyword + "%")));
		List<PhongHoc> list = session.createQuery(cq).getResultList();
		return list;
	}
}