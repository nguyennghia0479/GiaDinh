package com.website.giadinh.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.website.giadinh.entity.Khoa;
import com.website.giadinh.entity.NganhHoc;

@Repository("nganhHocDao")
public class NganhHocDaoImpl implements NganhHocDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<NganhHoc> findAll() {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<NganhHoc> cq = cb.createQuery(NganhHoc.class);
		Root<NganhHoc> root = cq.from(NganhHoc.class);
		cq.select(root).orderBy(cb.asc(root.get("khoa")), cb.asc(root.get("tenNganh")));
		List<NganhHoc> list = session.createQuery(cq).getResultList();
		return list;
	}

	@Override
	public NganhHoc findById(String maNganh) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.get(NganhHoc.class, maNganh);
	}

	@Override
	public Boolean isExistKey(String maNganh) {
		if (findById(maNganh) == null) {
			return false;
		}
		return true;
	}

	@Override
	public Boolean isExistReference(String maNganh) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(NganhHoc nganhHoc) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(nganhHoc);

	}

	@Override
	public void update(NganhHoc nganhHoc) {
		Session session = this.sessionFactory.getCurrentSession();
		session.merge(nganhHoc);
	}

	@Override
	public void delete(NganhHoc nganhHoc) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(nganhHoc);
	}

	@Override
	public Long countList() {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<NganhHoc> root = cq.from(NganhHoc.class);
		cq.select(cb.count(root.get("maNganh")));
		Long count = session.createQuery(cq).getSingleResult();
		return count;
	}

	@Override
	public Long countSearchResult(String keyword) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<NganhHoc> root = cq.from(NganhHoc.class);
		Join<NganhHoc, Khoa> join = root.join("khoa");
		cq.select(cb.count(root.get("maNganh"))).where(cb.or(cb.like(root.get("maNganh"), "%" + keyword + "%"),
				cb.like(root.get("tenNganh"), "%" + keyword + "%"), cb.like(join.get("tenKhoa"), "%" + keyword + "%")));
		Long count = session.createQuery(cq).getSingleResult();
		return count;
	}

	@Override
	public List<String> searchAuto(String keyword) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<String> cq = cb.createQuery(String.class);
		Root<NganhHoc> root = cq.from(NganhHoc.class);
		Join<NganhHoc, Khoa> join = root.join("khoa");
		cq.select(root.get("tenNganh")).where(cb.like(root.get("tenNganh"), "%" + keyword + "%"));
		List<String> list = session.createQuery(cq).getResultList();
		if (list.isEmpty()) {
			cq.select(join.get("tenKhoa")).where(cb.like(join.get("tenKhoa"), "%" + keyword + "%")).distinct(true);
			list = session.createQuery(cq).getResultList();
		}
		if (list.isEmpty()) {
			cq.select(root.get("maNganh")).where(cb.like(root.get("maNganh"), "%" + keyword + "%"));
			list = session.createQuery(cq).getResultList();
		}
		return list;
	}

	@Override
	public List<NganhHoc> search(String keyword) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<NganhHoc> cq = cb.createQuery(NganhHoc.class);
		Root<NganhHoc> root = cq.from(NganhHoc.class);
		Join<NganhHoc, Khoa> join = root.join("khoa");
		cq.select(root).where(cb.or(cb.like(root.get("maNganh"), "%" + keyword + "%"),
				cb.like(root.get("tenNganh"), "%" + keyword + "%"), cb.like(join.get("tenKhoa"), "%" + keyword + "%")));
		List<NganhHoc> list = session.createQuery(cq).getResultList();
		return list;
	}
}