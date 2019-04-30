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

import com.website.giadinh.entity.NganhHoc;

@Repository("nganhHocDao")
public class NganhHocDaoImpl implements NganhHocDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<NganhHoc> findAll() {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<NganhHoc> cq = cb.createQuery(NganhHoc.class);
		Root<NganhHoc> root = cq.from(NganhHoc.class);
		cq.select(root);
		Query query = session.createQuery(cq);
		return query.getResultList();
	}

}
