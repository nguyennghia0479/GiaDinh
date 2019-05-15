package com.website.giadinh.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.website.giadinh.entity.GiangVien;

@Repository("giangVienDao")
public class GiangVienDaoImpl implements GiangVienDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<GiangVien> findAll() {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<GiangVien> cq = cb.createQuery(GiangVien.class);
		Root<GiangVien> root = cq.from(GiangVien.class);
		cq.select(root);
		List<GiangVien> list = session.createQuery(cq).getResultList();
		return list;
	}

	@Override
	public GiangVien findById(String maGV) {
		Session session = this.sessionFactory.getCurrentSession();
		return session.get(GiangVien.class, maGV);
	}

	@Override
	public Boolean isExistKey(String maGV) {
		if(findById(maGV) == null) {
			return false;
		}
		return true;
	}

	@Override
	public Boolean isExistReference(String maGV) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(GiangVien giangVien) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(giangVien);
	}

	@Override
	public void update(GiangVien giangVien) {
		Session session = this.sessionFactory.getCurrentSession();
		session.merge(giangVien);
	}

	@Override
	public void delete(GiangVien giangVien) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(giangVien);
	}

	@Override
	public Long countList() {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<GiangVien> root = cq.from(GiangVien.class);
		cq.select(cb.count(root.get("maGV")));
		Long count = session.createQuery(cq).getSingleResult();
		return count;
	}

	@Override
	public Long countSearchResult(String keyword) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<GiangVien> root = cq.from(GiangVien.class);
		cq.select(cb.count(root.get("maGV"))).where(cb.like(root.get("maGV"), "%" + keyword + "%"));
		Long count = session.createQuery(cq).getSingleResult();
		return count;
	}

	@Override
	public List<String> searchAuto(String keyword) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<String> cq = cb.createQuery(String.class);
		Root<GiangVien> root = cq.from(GiangVien.class);
		cq.select(root.get("hoTen")).where(cb.like(root.get("hoTen"), "%" + keyword + "%"));
		List<String> list = session.createQuery(cq).getResultList();
		return list;
	}

	@Override
	public List<GiangVien> search(String keyword) {
		Session session = this.sessionFactory.getCurrentSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<GiangVien> cq = cb.createQuery(GiangVien.class);
		Root<GiangVien> root = cq.from(GiangVien.class);
		cq.select(root).where(cb.like(root.get("hoTen"), "%" + keyword + "%"));
		List<GiangVien> list = session.createQuery(cq).getResultList();
		return list;
	}
}