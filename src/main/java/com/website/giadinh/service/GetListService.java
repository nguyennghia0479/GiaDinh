package com.website.giadinh.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.website.giadinh.dao.GetListDao;
import com.website.giadinh.entity.Khoa;
import com.website.giadinh.entity.NganhHoc;

@Service
@Transactional
public class GetListService {
	@Autowired
	private GetListDao getListDao;

	public Map<String, String> getNoiSinhList() {
		Map<String, String> noiSinh = new HashMap<String, String>();
		noiSinh.put("An Giang", "An Giang");
		noiSinh.put("Kon Tum", "Kon Tum");
		noiSinh.put("Bà Rịa - Vũng Tàu", "Bà Rịa - Vũng Tàu");
		noiSinh.put("Lai Châu", "Lai Châu");
		noiSinh.put("Bắc Giang", "Bắc Giang");
		noiSinh.put("Lâm Đồng", "Lâm Đồng");
		noiSinh.put("Bắc Kạn", "Bắc Kạn");
		noiSinh.put("Lạng Sơn", "Lạng Sơn");
		noiSinh.put("Bạc Liêu", "Bạc Liêu");
		noiSinh.put("Lào Cai", "Lào Cai");
		noiSinh.put("Bắc Ninh", "Bắc Ninh");
		noiSinh.put("Long An", "Long An");
		noiSinh.put("Bến Tre", "Bến Tre");
		noiSinh.put("Nam Định", "Nam Định");
		noiSinh.put("Bình Định", "Bình Định");
		noiSinh.put("Nghệ An", "Nghệ An");
		noiSinh.put("Bình Dương", "Bình Dương");
		noiSinh.put("Ninh Bình", "Ninh Bình");
		noiSinh.put("Bình Phước", "Bình Phước");
		noiSinh.put("Ninh Thuận", "Ninh Thuận");
		noiSinh.put("Bình Thuận", "Bình Thuận");
		noiSinh.put("Phú Thọ", "Phú Thọ");
		noiSinh.put("Cà Mau", "Cà Mau");
		noiSinh.put("Phú Yên", "Phú Yên");
		noiSinh.put("Cần Thơ", "Cần Thơ");
		noiSinh.put("Quảng Bình", "Quảng Bình");
		noiSinh.put("Cao Bằng", "Cao Bằng");
		noiSinh.put("Quảng Nam", "Quảng Nam");
		noiSinh.put("Đà Nẵng", "Đà Nẵng");
		noiSinh.put("Quảng Ngãi", "Quảng Ngãi");
		noiSinh.put("Đắk Lắk", "Đắk Lắk");
		noiSinh.put("Quảng Ninh", "Quảng Ninh");
		noiSinh.put("Đắk Nông", "Đắk Nông");
		noiSinh.put("Quảng Trị", "Quảng Trị");
		noiSinh.put("Điện Biên", "Điện Biên");
		noiSinh.put("Sóc Trăng", "Sóc Trăng");
		noiSinh.put("Đồng Nai", "Đồng Nai");
		noiSinh.put("Sơn La", "Sơn La");
		noiSinh.put("Đồng Tháp", "Đồng Tháp");
		noiSinh.put("Tây Ninh", "Tây Ninh");
		noiSinh.put("Gia Lai", "Gia Lai");
		noiSinh.put("Thái Bình", "Thái Bình");
		noiSinh.put("Hà Giang", "Hà Giang");
		noiSinh.put("Thái Nguyên", "Thái Nguyên");
		noiSinh.put("Hà Nam", "Hà Nam");
		noiSinh.put("Thanh Hóa", "Thanh Hóa");
		noiSinh.put("Hà Nội", "Hà Nội");
		noiSinh.put("Thừa Thiên Huế", "Thừa Thiên Huế");
		noiSinh.put("Hà Tĩnh", "Hà Tĩnh");
		noiSinh.put("Tiền Giang", "Tiền Giang");
		noiSinh.put("Hải Dương", "Hải Dương");
		noiSinh.put("Thành phố Hồ Chí Minh", "Thành phố Hồ Chí Minh");
		noiSinh.put("Hải Phòng", "Hải Phòng");
		noiSinh.put("Trà Vinh", "Trà Vinh");
		noiSinh.put("Hậu Giang", "Hậu Giang");
		noiSinh.put("Tuyên Quang", "Tuyên Quang");
		noiSinh.put("Hòa Bình", "Hòa Bình");
		noiSinh.put("Vĩnh Long", "Vĩnh Long");
		noiSinh.put("Hưng Yên", "Hưng Yên");
		noiSinh.put("Vĩnh Phúc", "Vĩnh Phúc");
		noiSinh.put("Khánh Hòa", "Khánh Hòa");
		noiSinh.put("Yên Bái", "Yên Bái");
		noiSinh.put("Kiên Giang", "Kiên Giang");

		Map<String, String> noiSinhSort = new LinkedHashMap<String, String>();
		noiSinh.entrySet().stream().sorted(Map.Entry.comparingByKey())
				.forEachOrdered(e -> noiSinhSort.put(e.getKey(), e.getValue()));
		return noiSinhSort;
	}

	public Map<String, String> getTrinhDoList() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("Giáo sư", "Giáo sư");
		map.put("Phó giáo sư", "Phó giáo sư");
		map.put("Tiến sỹ", "Tiến sỹ");
		map.put("Thạc sỹ", "Thạc sỹ");
		map.put("Cử nhân", "Cử nhân");

		Map<String, String> sort = new LinkedHashMap<String, String>();
		map.entrySet().stream().sorted(Map.Entry.comparingByKey())
				.forEachOrdered(e -> sort.put(e.getKey(), e.getValue()));
		return sort;
	}

	public Map<String, String> getKhoaList() {
		List<Khoa> list = getListDao.getKhoaList();
		Map<String, String> map = list.stream().collect(Collectors.toMap(Khoa::getMaKhoa, Khoa::getTenKhoa));
		return map;
	}

	public Map<String, String> getNganhHocList() {
		List<NganhHoc> list = getListDao.getNganhHocList();
		Map<String, String> map = list.stream().collect(Collectors.toMap(NganhHoc::getMaNganh, NganhHoc::getTenNganh));
		return map;
	}
}