package com.website.giadinh.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.website.giadinh.entity.PhanCong;

@Repository("phanCongDao")
public class PhanCongDaoImpl implements PhanCongDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<PhanCong> findAll() {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<PhanCong> cq = cb.createQuery(PhanCong.class);
		Root<PhanCong> root = cq.from(PhanCong.class);
		cq.select(root);
		List<PhanCong> list = session.createQuery(cq).getResultList();
		return list;
	}

	@Override
	public PhanCong findById(Integer maPC) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.get(PhanCong.class, maPC);
	}

	@Override
	public Boolean isExistReference(Integer maPC) {
		return true;
	}

	@Override
	public void add(PhanCong phanCong) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(phanCong);
	}

	@Override
	public void update(PhanCong phanCong) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(phanCong);
	}

	@Override
	public void delete(PhanCong phanCong) {
		Session session = this.sessionFactory.getCurrentSession();
		session.merge(phanCong);
	}

	@Override
	public Long countList() {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<PhanCong> root = cq.from(PhanCong.class);
		cq.select(cb.count(root.get("maPC")));
		Long count = session.createQuery(cq).getSingleResult();
		return count;
	}

	@Override
	public Long countSearchResult(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> searchAuto(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PhanCong> search(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}
}