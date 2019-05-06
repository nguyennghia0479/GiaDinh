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

import com.website.giadinh.entity.LopHoc;
import com.website.giadinh.entity.NganhHoc;

@Repository("lopHocDao")
public class LopHocDaoImpl implements LopHocDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<LopHoc> findAll() {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<LopHoc> cq = cb.createQuery(LopHoc.class);
		Root<LopHoc> root = cq.from(LopHoc.class);
		cq.select(root).orderBy(cb.asc(root.get("nganhHoc")));
		List<LopHoc> list = session.createQuery(cq).getResultList();
		return list;
	}

	@Override
	public LopHoc findById(String maLop) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.get(LopHoc.class, maLop);
	}

	@Override
	public Boolean isExistKey(String maLop) {
		if (findById(maLop) == null) {
			return false;
		}
		return true;
	}

	@Override
	public Boolean isExistReference(String maLop) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(LopHoc lopHoc) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(lopHoc);
	}

	@Override
	public void update(LopHoc lopHoc) {
		Session session = this.sessionFactory.getCurrentSession();
		session.merge(lopHoc);
	}

	@Override
	public void delete(LopHoc lopHoc) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(lopHoc);
	}

	@Override
	public Long countList() {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<LopHoc> root = cq.from(LopHoc.class);
		cq.select(cb.count(root.get("maLop")));
		Long count = session.createQuery(cq).getSingleResult();
		return count;
	}

	@Override
	public Long countSearchResult(String keyword) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<LopHoc> root = cq.from(LopHoc.class);
		Join<LopHoc, NganhHoc> join = root.join("nganhHoc");
		cq.select(cb.count(root.get("maLop"))).where(cb.or(cb.like(root.get("maLop"), "%" + keyword + "%"),
				cb.like(join.get("tenNganh"), "%" + keyword + "%")));
		Long count = session.createQuery(cq).getSingleResult();
		return count;
	}

	@Override
	public List<String> searchAuto(String keyword) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<String> cq = cb.createQuery(String.class);
		Root<LopHoc> root = cq.from(LopHoc.class);
		Join<LopHoc, NganhHoc> join = root.join("nganhHoc");
		cq.select(root.get("maLop")).where(cb.like(root.get("maLop"), "%" + keyword + "%"));
		List<String> list = session.createQuery(cq).getResultList();
		if (list.isEmpty()) {
			cq.select(join.get("tenNganh")).where(cb.like(join.get("tenNganh"), "%" + keyword + "%"));
			list = session.createQuery(cq).getResultList();
		}
		return list;
	}

	@Override
	public List<LopHoc> search(String keyword) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<LopHoc> cq = cb.createQuery(LopHoc.class);
		Root<LopHoc> root = cq.from(LopHoc.class);
		Join<LopHoc, NganhHoc> join = root.join("nganhHoc");
		cq.select(root).where(cb.or(cb.like(root.get("maLop"), "%" + keyword + "%"),
				cb.like(join.get("tenNganh"), "%" + keyword + "%")));
		List<LopHoc> list = session.createQuery(cq).getResultList();
		return list;
	}
}