package com.website.giadinh.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.website.giadinh.entity.Khoa;
import com.website.giadinh.entity.LopHoc;
import com.website.giadinh.entity.NganhHoc;

@Repository
public class GetListDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<Khoa> getKhoaList() {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Khoa> cq = cb.createQuery(Khoa.class);
		Root<Khoa> root = cq.from(Khoa.class);
		cq.select(root);
		List<Khoa> list = session.createQuery(cq).getResultList();
		return list;
	}
	
	public List<NganhHoc> getNganhHocList() {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<NganhHoc> cq = cb.createQuery(NganhHoc.class);
		Root<NganhHoc> root = cq.from(NganhHoc.class);
		cq.select(root);
		List<NganhHoc> list = session.createQuery(cq).getResultList();
		return list;
	}
	
	public List<LopHoc> getLopHocList() {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<LopHoc> cq = cb.createQuery(LopHoc.class);
		Root<LopHoc> root = cq.from(LopHoc.class);
		cq.select(root);
		List<LopHoc> list = session.createQuery(cq).getResultList();
		return list;
	}
}